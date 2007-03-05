package ounl.otec.contextclient.sensor;
//package ounl.otec.CampusMemories.Mobile;
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
import java.util.Hashtable;
import javax.microedition.lcdui.*;
import org.semacode.contactless.visual.VisualTagConnection;
import ounl.otec.contextclient.gui.*;
import ounl.otec.contextclient.main.CampusConstants;

/** Class that implements the functionality of a sensor for semacodes.
 *  The sensor uses a snapshop capture from a videostream, at a moment indicated
 *  by the user, as an image that will be decoded by the semacode software. Hence, this sensor
 *  makes it possible to capture and decode semacode labels to be used in the mobile client object
 *  identification
 *  @author Tim de Jong
 */
public class SemaCodeSensor extends Sensor
                            implements CommandListener
{
	private CanvasMenu			m_canvasMenu;
	private VideoControl                    m_videoControl;
	private Player				m_player;
	private Thread				m_decodeThread;

	/** Constructor
         *  @param display the display that will be used to display the sensor menu when the
         *  sensor is active.
	 */
	public SemaCodeSensor(Display display)
	{
		super("SemaCodeSensor", display);

		m_canvasMenu = new CanvasMenu(display, "SemaCodeSensorCanvas");
                 //set possible commands for videoCanvas
	    	m_canvasMenu.getDisplayable().addCommand(CampusConstants.K_CAPTURE_COMMAND);
	    	m_canvasMenu.getDisplayable().addCommand(CampusConstants.K_BACK_COMMAND);
                m_canvasMenu.getDisplayable().setCommandListener(this);               
		       
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
			//add the entered code as object id to sensor data
                        decode();    
			//notify the sensor listeners of the update
			//notifySensorUpdate();                        
		}
		else if (c == CampusConstants.K_BACK_COMMAND)
		{
			m_canvasMenu.commandAction(c, d);
		}
	}
	
        /** Starts the sensor and the capturing of the video.
	 */
	public void startSensor()
	{
            try
	    {               
                Canvas videoCanvas = m_canvasMenu.getCanvas();
	    	videoCanvas.setFullScreenMode(true);
                this.getDisplay().setCurrent(videoCanvas);
                
                
                m_player = Manager.createPlayer("capture://video");                              	    	 	
               
                m_player.realize();
                m_player.prefetch();     
	    	m_videoControl = (VideoControl)m_player.getControl("VideoControl");	    	
                  
                m_videoControl.initDisplayMode(VideoControl.USE_DIRECT_VIDEO, videoCanvas);
                                
	    	try
	    	{
	    		m_videoControl.setDisplayFullScreen(false);
                        m_videoControl.setVisible(true);
                        m_player.start();                         
	    	}
	    	catch(MediaException me2)
	    	{	    		
	    		me2.printStackTrace();
	    	}  	                              
		
	    }
	    catch(Exception e)
	    {	    	
	    	e.printStackTrace();
	    }
	}

	/** Stops the sensor, the videocapturing and cleans up.
	 */
	public void stopSensor()
	{
		if( m_videoControl == null )
		{
			//new RuntimeException("attempt to stop viewfinder when it's not running!")
		}

		try
                {
                  m_videoControl.setVisible(false);          
                 
                  //m_decodeThread.interrupt();
                  //return the player into the realised state
                  //m_player.deallocate();
                  //m_player.stop();
                  m_player.close();
                  m_player = null;
                  m_videoControl = null;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
	}

	/** Spawns a new thread that captures a semacode image and decodes it.
	 */
	public void decode()
	{         
            //new thread to decode the semacode
            //notify the listener when sensor information has been update
            m_decodeThread= new Thread(new SemaDecoder());
            m_decodeThread.start();
	}

	/** Private class that decodes the semacode image in a different thread.
         *  @author Tim de Jong
	 */
	private class SemaDecoder implements Runnable
	{              

                /** Constructor
                 */ 
                public SemaDecoder()                
                {                        
                }

                public void run()
                {
                    Image semaCodeImage = null;
                    try
                    {                       
                        byte[] raw = m_videoControl.getSnapshot(null);
                        semaCodeImage = Image.createImage(raw, 0, raw.length);
                        stopSensor();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        //notifySensorError(e);
                    }

                        String semaCode = "";
                        try
                        {
                                //convert the captured semacode into an id
                                VisualTagConnection vtc = new VisualTagConnection();
                                semaCode = vtc.readVisualTag(semaCodeImage, "Data Matrix");

                                if (semaCode == null)
                                {
                                        semaCode = "-1";
                                }
                                //store the semacode object id in the sensor
                                setValue(CampusConstants.K_OBJECT_ID_KEY, semaCode);                                
                                //notify the listener of a sensor update
                                notifySensorUpdate();                                
                        }
                        catch (Exception e)
                        {
                               // notifySensorError(e);
                                e.printStackTrace();
                        }
                }
	}

	/** Gets the menu that displays the graphical user interface when the sensor
         *  is active.
         *  @return the VisualMenu during sensor operation.
	 */
	public VisualMenu getSensorMenu()
	{
		return m_canvasMenu;
	}

	/** Gets the menu that is used to configure the sensor. As this sensor does not
         *  need any configuration, this method returns null.
         *  @return null, this sensor does not have a config menu.
	 */
	public StateChangeMenu getSensorConfigMenu()
	{
		return null;
	}
}
