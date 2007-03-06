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
import javax.microedition.lcdui.*;
import ounl.otec.contextclient.state.State;
import ounl.otec.contextclient.gui.*;
import ounl.otec.contextclient.main.CampusConstants;
/**
 *
 * @author Tim
 */
public class DummySensor extends Sensor implements CommandListener
{
    private DummySensorMenu	m_sensorMenu;
    private final String        K_OBJECT_ID_VALUE = "cm-01";
    
    /**
     */
    public DummySensor(Display display) 
    {
        super("DummySensor", display);
        m_sensorMenu = new DummySensorMenu(this, display);
    }	
                
    /** Normally starts operating the sensor, however this method is not
     *  used by the user entered code sensor.
     */
    public void startSensor()
    {
    }

    /** Normally stops operating the sensor, however this method is not used
     *  by the user entered code sensor.
     */
    public void stopSensor()
    {
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
                    //get the entered code value
                    String code = K_OBJECT_ID_VALUE;
                    //add the entered code as object id to sensor data			
                    this.setValue(CampusConstants.K_OBJECT_ID_KEY, code);                                                                    
            }
            else if (c == CampusConstants.K_BACK_COMMAND)
            {
                    m_sensorMenu.commandAction(c, d);
            }
    }

    /** Returns the sensor menu used at sensor activation by the user to enter
     *  an object id code manually.
     *  @return a VisualMenu that makes it possible for the user to enter an
     *  object id via the mobile device keyboard.
     */
    public VisualMenu getSensorMenu()
    {
            return m_sensorMenu;
    }

    /** Since this sensor does not need any configuration, this method does
     *  not return any graphical menu, instead it returns nothing(null)
     *  @return null, this sensor does not need to be configured.
     */
    public StateChangeMenu getSensorConfigMenu()
    {
            return null;
    }

    /** Inner class giving the implementation for the menu used at the activation
     *  of the UserEnteredSensorMenu.
     *  @author Tim de Jong
     */
    private class DummySensorMenu extends VisualMenu
    {
            CommandListener             m_owner;

            /** Constructor
             *  @param owner, the command listener the commands of this menu should be
             *  send to. In this case the sensor.
             *  @param ownerDisplay, the display that will be used to display this menu.
             */
            public DummySensorMenu(CommandListener owner, Display ownerDisplay)
            {
                    super(ownerDisplay, "UserEnteredSensorMenu");
                    m_owner = owner;
            }

            /**
             *  @param
             */
            public void stateUpdated(State s)
            {

            }

            /** Creates the graphical user interface for this sensor and return it.
             *  @return the graphical representation for this sensor.
             */ 
            public Displayable getDisplayable()
            {
                    //create textbox for entering code information
                    Form imageForm = new Form("SemaCode Sensor");
                    imageForm.setCommandListener(m_owner);
                    //add semacode Image
                    try
                    {
                        Image semacodeImage = Image.createImage("E:\\My Documents\\PhD\\Projects\\Context Blogger\\cm01_semacode.png" );
                    
                        imageForm.append(semacodeImage);
                        //set possible commands for videoCanvas
                        imageForm.addCommand(CampusConstants.K_CAPTURE_COMMAND);
                        imageForm.addCommand(CampusConstants.K_BACK_COMMAND);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    return imageForm;
            }
    }
}
