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
import java.util.Hashtable;
import java.util.Enumeration;
import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;

/**
 */
public class CampusMIDlet
				extends MIDlet
				implements CommandListener, ISensorListener
{
		//constants
		private final Command       K_SELECT_SENSOR = new Command("Select Sensor", Command.ITEM, 1);
               // private final String        K_MOBILE_ID_KEY = "123";		
                
                //private variables
		private Display             m_display;
		private Sensor              m_selectedSensor;
		private MenuFactory         m_menuFactory;
                private State               m_blogEntryState;
                private State               m_quitState;
                                
		/**
		 */
		public CampusMIDlet()
		{
                        //get mobile identification number for user identification
                        CampusConstants.K_MOBILE_ID = this.getMobileID();
			m_display = Display.getDisplay(this);
			m_menuFactory = new MenuFactory(this, m_display);
		}

		/**
		 */
		public void startApp()
		{            
                    try
                    {
                        String serverURL = getServerURL();                        
                        System.out.println(serverURL);
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                    
                    LoginMenu loginMenu = m_menuFactory.buildLoginMenu();
                    m_display.setCurrent(loginMenu.getDisplayable());
                    loginMenu.startLogin();
                    //create a state for a blog entry selection change
                    m_blogEntryState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_ENTRIES_STATE);
                    m_blogEntryState.addStateListener(this);
                    //create a state that indicates the application should quit
                    m_quitState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_QUIT_STATE);
                    m_quitState.addStateListener(this);
		}

		/**
		 */
		public void pauseApp()
		{
		}

		/**
		 */
		public void destroyApp(boolean unconditional)
		{
		}

		/**
		 */
		public void quit()
		{
			destroyApp(false);
			notifyDestroyed();
		}

		/**
		 */
		public void commandAction(Command c, Displayable s)
		{
			if (c == CampusConstants.K_CAPTURE_COMMAND)
			{
				((SemaCodeSensor)m_selectedSensor).decode();
				//m_display.setCurrent(menuFactory.waitingScreen());

				//after capturing the sensor can be stopped
				m_selectedSensor.stopSensor();
			}
		}

		/**
		 */
		public void stateUpdated(State s)
		{
			//if the semacode image is processed to an object id, process it here
			if ((s instanceof SemaCodeSensor) || (s instanceof UserEnteredCodeSensor))
			{
                            //display found id
                            String url = (String)s.getValue(CampusConstants.K_OBJECT_ID_KEY);

                            Form semaCodeForm = new Form("Semacode Result");
			    semaCodeForm.addCommand(CampusConstants.K_BACK_COMMAND);
			    semaCodeForm.setCommandListener(this);
			    semaCodeForm.append("The URL is:\n");
			    semaCodeForm.append(url + "\n");
			    m_display.setCurrent(semaCodeForm);
			}
                        else if (s.equals(this.m_blogEntryState))
                        {                            
                            //get the blog entry the user selected from the blog entry menu
                            BlogEntry entry = (BlogEntry)m_blogEntryState.getValue(CampusConstants.K_BLOG_ENTRY_KEY);
                            //if selected launch the blog entry link in the platform's browser
                            try
                            {
                                this.platformRequest(entry.getLink());
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                        else if (s.equals(this.m_quitState))
                        {
                           if (((Boolean)m_quitState.getValue(CampusConstants.K_QUIT_KEY)).equals(Boolean.TRUE))
                           {
                                quit();
                           }
                        }
		}

		/**
		 */
		public void sensorError(Exception e)
		{
			TextBox errorBox = new TextBox("An error occurred", e.getMessage(),200, TextField.ANY);
			m_display.setCurrent(errorBox);
		}
                
                /** This method will try to identify the mobile device and hence the user using it by finding the IMEI 
                 *  International Mobile Equipment Identity code of the mobile device, using System property values 
                 *  commonly used by manufacturers. If the IMEI number cannot be found the user is given a standard id 
                 *  which corresponds to the standard user.
                 *  @return if successful a unique ID identifying a mobile device and user, otherwise a standard id 
                 *  corresponding to a general or standard user.
                 */
                private String getMobileID()
                {
                    //TODO make this the Mobile Phone Identification Number
                    //TODO use IMEI International Mobile Equipment Identity or IMSI International Mobile Subscriber Identity
                    String imeiIdentification = "";
                    
                    //try to find the IMEI code of this mobile device
                    for (int i = 0; i < CampusConstants.K_IMEI_MANUFACTURER.length; i++)
                    {                    
                        imeiIdentification = System.getProperty(CampusConstants.K_IMEI_MANUFACTURER[i]);
                        //if some IMEI number is found, we stop searching
                        if (imeiIdentification != null && !imeiIdentification.equals(""))
                        {
                            break;
                        }                      
                    }
                    
                    //if not found use standard identification number 
                    if (imeiIdentification == null || imeiIdentification.equals(""))
                    {
                        imeiIdentification = CampusConstants.K_MOBILE_DEFAULT_ID;
                    }

                    return imeiIdentification;
                }
                
                /** Gets the address from the Context Blogger Service Server, from our berlios server.
                 *  The Context Blogger Service Server can migrate over time, while the berlios server
                 *  will most probably not. Therefore, we decided to query the berlios server for the 
                 *  address of the Context Blogger Service Server and connect to that address afterwards.
                 */
                private String getServerURL() throws IOException
                {                    
                    String serverURL  = "";
                     
                    HttpConnection c = null;
                    InputStream is = null;
                    OutputStream os = null;
                    int rc;

                    try 
                    {
                        c = (HttpConnection)Connector.open(CampusConstants.K_BERLIOS_URL);

                        // Set the request method and headers
                        c.setRequestMethod(HttpConnection.POST);
                        c.setRequestProperty("If-Modified-Since",
                            "29 Oct 1999 19:43:31 GMT");
                        c.setRequestProperty("User-Agent",
                            "Profile/MIDP-2.0 Configuration/CLDC-1.0");
                        c.setRequestProperty("Content-Language", "en-US");

                        // Getting the output stream may flush the headers
                        os = c.openOutputStream();
                        os.write("LIST games\n".getBytes());
                        os.flush();           // Optional, getResponseCode will flush

                        // Getting the response code will open the connection,
                        // send the request, and read the HTTP response headers.
                        // The headers are stored until requested.
                        rc = c.getResponseCode();
                        if (rc != HttpConnection.HTTP_OK) 
                        {
                            throw new IOException("HTTP response code: " + rc);
                        }

                        is = c.openInputStream();

                        // Get the ContentType
                        String type = c.getType();
                        //processType(type);

                        // Get the length and process the data
                        int len = (int)c.getLength();
                        if (len > 0) 
                        {
                             int actual = 0;
                             int bytesread = 0 ;
                             byte[] data = new byte[len];
                             while ((bytesread != len) && (actual != -1)) 
                             {
                                actual = is.read(data, bytesread, len - bytesread);
                                
                                bytesread += actual;
                             }
                             
                             for (int i = 0; i < data.length;i++)
                             {
                                serverURL += (char)data[i];
                             }                            
                        } 
                        else 
                        {
                            int ch;
                            while ((ch = is.read()) != -1) 
                            {
                                //process((byte)ch);
                                
                            }
                        }
                    } 
                    catch (ClassCastException e) 
                    {
                        throw new IllegalArgumentException("Not an HTTP URL");
                    } 
                    finally 
                    {
                        if (is != null)
                            is.close();
                        if (os != null)
                            os.close();
                        if (c != null)
                            c.close();
                    }
                    
                    return serverURL;
                }
}

