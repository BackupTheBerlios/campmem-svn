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
 * @author Tim
 */
public class BlogEntryCreateHandler extends BloggerStateHandler
{
    private State               m_entryCreateState;
    /** 
     */
    public BlogEntryCreateHandler(BloggerSEI_Stub bloggerStub) 
    {
        super(bloggerStub);
        m_entryCreateState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_ENTRY_CREATE_STATE);
    }
    
    public boolean isHandling(State s, boolean retrieve)
    {
        if (!retrieve && s.equals(m_entryCreateState))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void handleState(State s, boolean retrieve)
    {
        if (!retrieve && s.equals(m_entryCreateState))
        {
            //post the blog entry to the blog
            try
            {
               String objectID = (String)s.getValue(CampusConstants.K_OBJECT_ID_KEY);
               String categories = (String)s.getValue(CampusConstants.K_CATEGORIES_KEY);
               String title = (String)s.getValue(CampusConstants.K_TITLE_KEY);
               String body = (String)s.getValue(CampusConstants.K_BODY_KEY);
               this.getBloggerStub().post(CampusConstants.K_MOBILE_ID, objectID, categories, title, body);
               //this class adds another blog entry to the blog, therefore the list of available blog entries has to be updated.
               //by setting the objectIDState to its current value, actually nothing changes, however setting the objectID value
               //sparks a new stateChange, every listener will therefore informed that the object has changed and that the list
               //of available blog entries has to be updated. This is a somewhat dirty hack that will disappear as soon as 
               //the caching functionality is available.
               State objectIDState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_OBJECT_ID_STATE);
               objectIDState.setValue(CampusConstants.K_OBJECT_ID_KEY, objectID);
               
               this.setOperationResult(s, true);
            }
            catch (java.rmi.RemoteException e)
            {
               e.printStackTrace();
               this.setOperationResult(s, false);
            }
        }
    }
}
