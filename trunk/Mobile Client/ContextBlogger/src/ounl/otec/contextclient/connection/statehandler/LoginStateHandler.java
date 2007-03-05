package ounl.otec.contextclient.connection.statehandler;
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
import blogger.BloggerSEI_Stub;
import ounl.otec.contextclient.connection.*;
import ounl.otec.contextclient.state.State;
import ounl.otec.contextclient.main.CampusConstants;

/**
 *
 *  @author Tim
 */
public class LoginStateHandler extends BloggerStateHandler
{
    private State                   m_loginState;
    /** Constructor
     */
    public LoginStateHandler(BloggerSEI_Stub bloggerStub) 
    {
        super(bloggerStub);
        m_loginState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_LOGIN_STATE);
    }
    
    public boolean isHandling(State s, boolean retrieve)
    {   
        boolean test = (retrieve && s.equals(m_loginState));
System.out.println(s.getStateName() + " " + m_loginState.getStateName());
        System.out.println("test" + test + " " + retrieve + " " + s.equals(m_loginState));        
        return test;
    }
    
    public void handleState(State s, boolean retrieve)
    {
System.out.println("login: handleState 0");        
        if (retrieve && s.equals(m_loginState))
        {
System.out.println("login: handleState 1");           
            //try to login to the server
            try
            {
                if (getBloggerStub().login(CampusConstants.K_MOBILE_ID))
                {                    
                    s.setValue(CampusConstants.K_MENU_ID, CampusConstants.K_SIGNED_IN);    
                }
                else
                {            
                    s.setValue(CampusConstants.K_MENU_ID, CampusConstants.K_LOGIN_FAILED);    
                }
                setOperationResult(s, true);
            }
            catch(java.rmi.RemoteException e)
            {             
                s.setValue(CampusConstants.K_MENU_ID, CampusConstants.K_LOGIN_FAILED);    
                setOperationResult(s, false);
            }
        }
    }
    
}
