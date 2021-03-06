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
import java.io.ByteArrayInputStream;
import javax.xml.parsers.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import java.util.Vector;
import blogger.BloggerSEI_Stub;
import ounl.otec.contextclient.connection.*;
import ounl.otec.contextclient.state.State;
import ounl.otec.contextclient.gui.BlogEntry;
import ounl.otec.contextclient.main.CampusConstants;

/**
 *
 * @author Tim
 */
public class BlogListRetrieveHandler extends BloggerStateHandler
{
    private State               m_blogEntryState;
    private boolean             m_filterResults = false;        
    private String              m_categoryFilter = "";
    
    /**
     */
    public BlogListRetrieveHandler(BloggerSEI_Stub bloggerStub) 
    {
        super(bloggerStub);
        m_blogEntryState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_ENTRY_LIST_STATE);
    }
    
    
    public boolean isHandling(State s, boolean retrieve)
    {
        return retrieve && s.equals(m_blogEntryState);            
    }
    
    public void handleState(State s, boolean retrieve)
    {
        if (retrieve && s.equals(m_blogEntryState))
        {
            createBlogList(s);
        }
    }
    

    private void createBlogList(State s)
    {
        String objectID = (String)s.getValue(CampusConstants.K_OBJECT_ID_KEY);
        setOperationResult(s, false);
 System.out.println("createBlogList objectID" + objectID);       
        try
        {
            //get the blog entries from the server
            String blogListXML = getBloggerStub().retrieve_blogList(CampusConstants.K_MOBILE_ID, objectID);             
//System.out.println(blogListXML);                    
            //if no security error occured, get the blog entries
            if (!blogListXML.equals("-1"))
            {
                //convert the string into an inputstream
                ByteArrayInputStream str = new ByteArrayInputStream(blogListXML.getBytes());
                //get a parser
                SAXParserFactory parserFactory = SAXParserFactory.newInstance();
                try
                {
                    SAXParser saxParser = parserFactory.newSAXParser();
                    //parse xml and convert them to blog entry objects
                    Vector entries = new Vector();
                    saxParser.parse(str,new BlogEntryHandler(entries));
                   //add the blog entries found to the menu
System.out.println("entries size" + entries.size());                     
                    Vector blogEntryNames = new Vector();
                    for (int i = 0; i < entries.size();i++)
                    {
                       BlogEntry entry = (BlogEntry)entries.elementAt(i);                       
                       entry.setObjectID(objectID);
                       
                       blogEntryNames.addElement(entry.getTitle());
                    }  
                    //add the categories found and their names to the state
                    s.setValue(CampusConstants.K_ITEM_NAMES_KEY, blogEntryNames, false);
                    s.setValue(CampusConstants.K_ITEM_VALUES_KEY, entries, false);
                    
                    setOperationResult(s, true, true);
                }
                catch (Exception e)
                {    
                    setOperationResult(s, false);
                }
            }
        }
        catch (java.rmi.RemoteException e)
        {
            setOperationResult(s, false);
        }		
    }
   
        
    private class BlogEntryHandler extends DefaultHandler
    {
        private final int       K_NO_TAG_TYPE           = -1;
        private final int       K_ENTRY_TAG_TYPE        = 0;
        private final int       K_POSTID_TAG_TYPE       = 1;
        private final int       K_CATEGORY_TAG_TYPE     = 2;
        private final int       K_LINK_TAG_TYPE         = 3;
        private final int       K_TITLE_TAG_TYPE        = 4;

        private int             m_currentTagType = K_NO_TAG_TYPE;           
        private Vector          m_blogEntries;
        private BlogEntry       m_currentEntry;

        private String          m_postID = "";
        private String          m_category = "";
        private String          m_link = "";
        private String          m_title = "";

        public BlogEntryHandler(Vector blogEntries)
        {                
            m_blogEntries = blogEntries;
        }
 /*               <blog>
 *              <entry>
 *                  <postid>... </postid>
 *                  <categories>...</categories>
 *                  <link>...</link>
 *                  <title>...</title>
 *              </entry>
 *          </blog>
  */

        public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes attributes)
        {                              
            if (qName.equals("entry"))
            {
                m_currentEntry = new BlogEntry();
                m_currentTagType = K_ENTRY_TAG_TYPE;
            }

            else if (qName.equals("postid"))
            {                   
                m_currentTagType = K_POSTID_TAG_TYPE;
            }
            else if (qName.equals("categories"))
            {
                m_currentTagType = K_CATEGORY_TAG_TYPE;
            }
            else if (qName.equals("link"))
            {                   
                m_currentTagType = K_LINK_TAG_TYPE;
            }
            else if (qName.equals("title"))
            {                   
                m_currentTagType = K_TITLE_TAG_TYPE;
            }
        }

        public void characters(char[] ch, int start, int length)
        {
            for (int i = start; i < start+length; i++)
            {
                if (ch[i] != ' ')
                {
                    switch (m_currentTagType)
                    {
                        case K_POSTID_TAG_TYPE:                            
                            m_postID = m_postID + ch[i];
                        break;
                        case K_CATEGORY_TAG_TYPE:
                            m_category = m_category + ch[i];
                        break;
                        case K_LINK_TAG_TYPE:
                            m_link = m_link + ch[i];
                        break;
                        case K_TITLE_TAG_TYPE:
                            m_title = m_title + ch[i];
                        break;
                    }
                }
            }
        }

        public void endElement(java.lang.String uri, java.lang.String localName, java.lang.String qName)
        {
            if (qName.equals("entry"))
            {                  
                if (m_filterResults)
                {
// System.out.println("getCategory() : " + m_currentEntry.getCategory() + " m_categoryFilter: " + m_categoryFilter);
                   if (m_currentEntry.getCategory().equals(m_categoryFilter))
                   {
                        m_blogEntries.addElement(m_currentEntry);
//System.out.println("here");                            
                   }
                }
                else
                {
                    m_blogEntries.addElement(m_currentEntry);
                }
                m_currentEntry = null;
            } 
            else if (qName.equals("postid"))
            {                    
                m_currentEntry.setPostID(m_postID);
                m_postID = "";                    
            }
            else if (qName.equals("categories"))
            {
                m_category.trim();
                m_currentEntry.setCategory(m_category);                   
                m_category = "";
            }
            else if (qName.equals("link"))
            {
                m_currentEntry.setLink(m_link);
                m_link = "";
            }
            else if (qName.equals("title"))
            {
                m_currentEntry.setTitle(m_title);
                m_title = "";                    
            } 
        }

        public int getNumberBlogEntries()
        {
            return m_blogEntries.size();
        }
    }
}
