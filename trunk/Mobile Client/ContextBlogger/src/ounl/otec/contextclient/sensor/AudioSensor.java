package ounl.otec.contextclient.sensor;
/*
 *  Copyright (c) <2007> <Open University of the Netherlands, Tim de Jong, Bashar Al Takrouri, Marcus Specht>
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 *  documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 *  and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 *  of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 *  TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
 *  THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 *  CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 *  IN THE SOFTWARE.
 */
import javax.microedition.media.*;
import javax.microedition.media.control.*;
import javax.microedition.lcdui.*;
import ounl.otec.contextclient.gui.*;
import ounl.otec.contextclient.main.CampusConstants;
import ounl.otec.contextclient.state.State;

/**
 *
 * @author Tim
 */
public class AudioSensor    extends Sensor 
                            implements CommandListener
{
    private Player              m_player;
    private RecordControl       m_recordControl;
    //private boolean             m_recordByteStream;
    private String              m_audioFilePath = "file: ///c:/audio.amr";
    private VisualMenu          m_audioMenu;
    
    /**
     *  @param 
     */
    public AudioSensor(Display display) 
    {
        super("AudioSensor", display);          
        m_audioMenu = new AudioMenu(display);
    }
    
    /** Playback:
     *  Player player = Manager.createPlayer("file: ///c:/other/audio.amr");
        ByteArrayInputStream bis = new ByteArrayInputStream(outputStream.toByteArray() );
        Player player = Manager.createPlayer(bis, "audio/amr");
        player.realize();
        player.start();
     */
    public void startSensor()
    {
        try
        {
            m_player = Manager.createPlayer("capture://audio");
            m_player.realize();

            m_recordControl = (RecordControl)m_player.getControl("RecordControl");
            m_recordControl.setRecordLocation(m_audioFilePath);
            //or:
            //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //rc.setRecordStream(outputStream);
            m_recordControl.startRecord();
            m_player.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }        
    }
	
    public void stopSensor()
    {        
        m_player.close();
        m_recordControl = null;
        m_player = null;
    }        
    
    /** Implements the CommandListener interface method to handle sensor menu specific commands
     *  @param c the command that is carried out on the sensor menu
     *  @param d the displayable in the sensor menu that caused the command to be carried out.
     */
    public void commandAction(Command c, Displayable d)
    {
        //if code entered, send update information to sensorlisteners
        if (c == CampusConstants.K_CAPTURE_COMMAND)
        {			
            try
            {
                m_recordControl.commit();
                setValue(CampusConstants.K_SENSOR_DATA_KEY, m_audioFilePath);
            }
            catch (java.io.IOException e)
            {
                e.printStackTrace();
            }
            stopSensor();
        }
        else if (c == CampusConstants.K_BACK_COMMAND)
        {
            m_audioMenu.commandAction(c, d);
        }
    }
        
    public VisualMenu getSensorMenu()
    {
        return m_audioMenu;
    }
      
    public StateChangeMenu getSensorConfigMenu()
    {
        return null;
    }
    
    private class AudioMenu extends VisualMenu
    {
        private Form m_audioForm;
        
        public AudioMenu(Display ownerDisplay)
        {
            super(ownerDisplay, "AudioMenu");
            m_audioForm = new Form("AudioSensor");
            m_audioForm.addCommand(CampusConstants.K_CAPTURE_COMMAND);
            m_audioForm.addCommand(CampusConstants.K_BACK_COMMAND);
            m_audioForm.setCommandListener(this);
        }
        
        public void stateUpdated(State s)
        {            
        }
       
        public Displayable getDisplayable()
        {
            return m_audioForm;
        }
    }
}
