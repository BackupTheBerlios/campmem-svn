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

/**
 */
public class LoginMenu extends StateTransitionMenu
{
	private LoginCanvas			m_loginCanvas = new LoginCanvas();
	private Displayable			m_loginDisplayable;
        private String                          m_mobileID;
        private State                           m_loginState;   
	
        /**
	 */
	public LoginMenu(Display ownerDisplay, State loginState, String mobileID)
	{
		super(ownerDisplay, loginState, true);
                m_mobileID = mobileID;
                m_loginState = loginState;
		m_loginDisplayable = new LoginCanvas();                
	}

	/**
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

	/**
	 */
	public Displayable getDisplayable()
	{		
                return m_loginDisplayable;
	}

	private class LoginCanvas extends GameCanvas
	{		
		//private variables
		private String			m_loginStatus = CampusConstants.K_INIT_LOGIN;

		/**
		 */
		public LoginCanvas()
		{
                    super(true);
                    setFullScreenMode(true);
		}

		/**
		 */
		public void setLoginStatus(String loginStatus)
		{
			m_loginStatus = loginStatus;
		}

		/**
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

		/**
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