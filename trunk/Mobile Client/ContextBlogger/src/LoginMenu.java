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
import javax.microedition.lcdui.game.*;

/** The LoginMenu class provides a first StateTransitionMenu which depending on the login process to the
 *  ContextBlogger service carries out a couple of actions.
 *  @author Tim de Jong
 */
public class LoginMenu extends StateTransitionMenu
{
	private LoginCanvas			m_loginCanvas = new LoginCanvas();
	private Displayable			m_loginDisplayable;
        private String                          m_mobileID;
        private State                           m_loginState;   
	
        /** Constructor
         *  @param ownerDisplay, the display that will display this menu.
         *  @param loginState, the state that represents the login progress.
         *  @param mobileID, the mobile client ID, in this case the IMEI unique device identifier.
	 */
	public LoginMenu(Display ownerDisplay, State loginState, String mobileID)
	{
		super(ownerDisplay, loginState, true);
                m_mobileID = mobileID;
                m_loginState = loginState;
		m_loginDisplayable = new LoginCanvas();                
	}

	/** Method that starts the login process.
	 */
	public void startLogin()
	{
		State loginState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_LOGIN_STATE);
		//change the loginState according to the phase of the login process
                //try to login to the server
                try
                {
                    if (CampusConstants.K_BLOGGER_STUB.login(m_mobileID))
                    {                    
                     m_loginCanvas.setLoginStatus(CampusConstants.K_SIGNED_IN);
                     m_loginState.setValue(CampusConstants.K_MENU_ID, CampusConstants.K_SIGNED_IN);
System.out.println("signed in");                     
                    }
                    else
                    {
                     m_loginCanvas.setLoginStatus(CampusConstants.K_LOGIN_FAILED);
                     m_loginState.setValue(CampusConstants.K_MENU_ID, CampusConstants.K_LOGIN_FAILED);
System.out.println("login failed");
                    }
                }
                catch(java.rmi.RemoteException e)
                {
                     m_loginCanvas.setLoginStatus(CampusConstants.K_LOGIN_FAILED);
                     m_loginState.setValue(CampusConstants.K_MENU_ID, CampusConstants.K_LOGIN_FAILED);
System.out.println("login failed " + e.toString());                     
                }                
	}

	/** Gets the displayable that is a graphical representation for this LoginMenu.
         *  @return the displayable with the graphical representation of the view.
	 */
	public Displayable getDisplayable()
	{		
                return m_loginDisplayable;
	}

        /** Inner class that will draw the login screen.
         *  @author Tim de Jong
         */    
	private class LoginCanvas extends GameCanvas
	{		
		//private variables
		private String			m_loginStatus = CampusConstants.K_INIT_LOGIN;

		/** Constructor
		 */
		public LoginCanvas()
		{
                    super(true);
                    setFullScreenMode(true);
		}

		/** Sets the login status to the value represented by the method parameter.
                 *  @param loginStatus, the current login status
		 */
		public void setLoginStatus(String loginStatus)
		{
			m_loginStatus = loginStatus;
		}

		/** Gets the login status set for this LoginCanvas.
                 *  @return the login status
		 */
		public String getLoginStatus()
		{
                    return m_loginStatus;
		}

		/**
		 */
		public void start()
		{
		}

		/** Draws the LoginCanvas, depending on the current login status.
                 *  @param g, the graphics object used for all drawing primitives.
		 */
		public void paint(Graphics g)
		{
                     g.setColor(0,0,255);                     
                     g.fillRect(0,0,getWidth(), getHeight());
                     g.drawString("Logged in", 30, 30, Graphics.TOP|Graphics.LEFT);
			/*if(m_loginStatus.equals(CampusConstants.K_INIT_LOGIN))
			{
                        }
                        else if (m_loginStatus.equals(CampusConstants.K_LOGGING_IN))
                        {
                        }
                        else if (m_loginStatus.equals(CampusConstants.K_SIGNED_IN))
                        {
                            g.setColor(0,0,255);
                            g.fillRect(0,0,getWidth(), getHeight());
                            g.drawString("Logged in", 30, 30, Graphics.TOP|Graphics.LEFT);
                        }
                        else 	
                        {
                            g.fillRect(0,0,g.getClipWidth(), g.getClipHeight());
                            g.drawString("Logging in...", 30, 30, Graphics.TOP|Graphics.LEFT);
			}
			*/
		}
	}
}