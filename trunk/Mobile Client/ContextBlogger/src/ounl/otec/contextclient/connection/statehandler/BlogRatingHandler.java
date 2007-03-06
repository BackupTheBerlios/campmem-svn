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
import ounl.otec.contextclient.gui.BlogEntry;

/**
 *
 * @author Tim
 */
public class BlogRatingHandler extends BloggerStateHandler
{
    private State               m_ratingState;
    
    /** Constructor 
     */
    public BlogRatingHandler(BloggerSEI_Stub bloggerStub) 
    {
        super(bloggerStub);
        m_ratingState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_RATING_STATE);        
    }
    
    public boolean isHandling(State s, boolean retrieve)
    {
        return s.equals(m_ratingState);
    }
    
    public void handleState(State s, boolean retrieve)
    {
        if (s.equals(m_ratingState))
        {
            setOperationResult(s, false);
            String returnString = "";
            try
            {
                if (retrieve)
                {
                   //get rating for blog entry
                   String objectID = (String)s.getValue(CampusConstants.K_OBJECT_ID_KEY);
                   BlogEntry entry = (BlogEntry)s.getValue(CampusConstants.K_BLOG_ENTRY_KEY);
                           
                   returnString = getBloggerStub().getRating(CampusConstants.K_MOBILE_ID, objectID, entry.getPostID());                   
                   int rating = Integer.parseInt(returnString);  
                   s.setValue(CampusConstants.K_RATE_KEY, new Integer(rating), false);
                }
                else
                {
                    Integer rating = (Integer)s.getValue(CampusConstants.K_RATE_KEY);
                    BlogEntry entry = (BlogEntry)s.getValue(CampusConstants.K_BLOG_ENTRY_KEY);
                    
                    returnString = getBloggerStub().setRating(CampusConstants.K_MOBILE_ID, entry.getObjectID(), entry.getPostID(), rating.intValue());
                }
                
                //find out if the operation was successful.
                if (!returnString.equals("false") && !returnString.equals("-1"))
                {
                    setOperationResult(s, true, true);
                }
                else
                {
                    setOperationResult(s, false);
                }
            }
            catch (java.rmi.RemoteException e)
            {
               e.printStackTrace();
               setOperationResult(s, false);
            }
        }
    }
}
