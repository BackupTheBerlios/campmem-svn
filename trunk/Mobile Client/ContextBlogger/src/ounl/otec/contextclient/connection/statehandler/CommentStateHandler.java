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
import ounl.otec.contextclient.state.*;
import ounl.otec.contextclient.main.CampusConstants;
import ounl.otec.contextclient.gui.BlogEntry;

/**
 *
 * @author Tim
 */
public class CommentStateHandler extends BloggerStateHandler
{
    private State               m_commentState;
    
    /** Constructor
     *  @param
     */
    public CommentStateHandler(BloggerSEI_Stub bloggerStub) 
    {
        super(bloggerStub);
        m_commentState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_COMMENT_STATE);
    }
    
    public boolean isHandling(State s, boolean retrieve)
    {
        if (!retrieve && s.equals(m_commentState))
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
        if(s.equals(m_commentState))
        {
            setOperationResult(s, false);
            if (!retrieve)
            {
               //post the comment
               try
               {
                    BlogEntry entry = (BlogEntry)s.getValue(CampusConstants.K_BLOG_ENTRY_KEY);
                    String commentText = (String)s.getValue(CampusConstants.K_COMMENT_KEY);
                    String test = getBloggerStub().postComment(CampusConstants.K_MOBILE_ID, entry.getPostID(), commentText);
                    setOperationResult(s, true, true);
               }
               catch (java.rmi.RemoteException e)
               {
                   e.printStackTrace();
                   setOperationResult(s, false);
               }
           }
        }
    }
}
