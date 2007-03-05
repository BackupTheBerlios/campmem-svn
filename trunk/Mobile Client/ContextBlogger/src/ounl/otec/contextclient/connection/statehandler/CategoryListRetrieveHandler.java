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
import ounl.otec.contextclient.gui.Category;
import ounl.otec.contextclient.main.CampusConstants;
import javax.xml.parsers.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import java.util.Vector;
import java.io.ByteArrayInputStream;

/**
 *
 * @author Tim
 */
public class CategoryListRetrieveHandler extends BloggerStateHandler
{
    private State               m_categoryState;
    
    /** Constructor 
     */
    public CategoryListRetrieveHandler(BloggerSEI_Stub bloggerStub) 
    {
        super(bloggerStub);
        m_categoryState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_CATEGORY_LIST_STATE);
    }
    
    public boolean isHandling(State s, boolean retrieve)
    {
        return (retrieve && s.equals(m_categoryState));        
    }
    
    public void handleState(State s, boolean retrieve)
    {
        if (retrieve && s.equals(m_categoryState))
        {
            createCategoryList(s);
        }
    }
        
    public void createCategoryList(State s)
    {
        try
        {
            //get the blog entries from the server
            String categoryXML = getBloggerStub().getCategories(CampusConstants.K_MOBILE_ID);
            //if no security error occured, get the blog entries
            if (!categoryXML.equals("-1"))
            {
                //convert the string into an inputstream
                ByteArrayInputStream str = new ByteArrayInputStream(categoryXML.getBytes());
                //get a parser
                SAXParserFactory parserFactory = SAXParserFactory.newInstance();
                try
                {
                    SAXParser saxParser = parserFactory.newSAXParser();
                    //parse xml and convert them to blog entry objects
                    Vector categories = new Vector();
                    saxParser.parse(str, new CategoryHandler(categories));

                    State categoryFilterState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_CATEGORY_FILTER_STATE);
                    
                    Vector categoryNames = new Vector();                                                         
                    for (int i = 0; i < categories.size(); i++)
                    {
                       Category category = (Category)categories.elementAt(i);                       
                       categoryNames.addElement(category.getName());                       
                    } 
                    //add the categories found and their names to the state
                    s.setValue(CampusConstants.K_ITEM_NAMES_KEY, categoryNames);
                    s.setValue(CampusConstants.K_ITEM_VALUES_KEY, categories);
                    
                    setOperationResult(s, true);
                }
                catch (Exception e)
                {
                    setOperationResult(s, false);
                }
            }
            else
            {
                setOperationResult(s, false);
            }
        }
        catch (java.rmi.RemoteException e)
        {
            setOperationResult(s, false);
        }		
    }
    
    
    private class CategoryHandler extends DefaultHandler
    {
        private final int       K_NO_TAG_TYPE               = -1;
        private final int       K_CATEGORY_LIST_TYPE        = 0;
        private final int       K_CATEGORY_TAG_TYPE         = 1;
        private final int       K_CATEGORY_ID_TAG_TYPE      = 2;
        private final int       K_CATEGORY_NAME_TAG_TYPE    = 3;
        
        private int             m_currentTagType = K_NO_TAG_TYPE;       
        private Vector          m_categories;
        private Category        m_currentCategory;

        private String          m_categoryID = "";
        private String          m_categoryName = "";
        
        public CategoryHandler(Vector categories)
        {                
            m_categories = categories;
        }
 
/*<?xml version='1.0'?>    
            <categoryList>
                <category>
                    <categoryId>...</categoryId>
                    <categoryName>...</categoryName>
                </category>
            </categoryList>*/
        public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes attributes)
        {                              
            if (qName.equals("category"))
            {
                m_currentCategory = new Category();
                m_currentTagType = K_CATEGORY_TAG_TYPE;
            }

            else if (qName.equals("categoryId"))
            {                   
                m_currentTagType = K_CATEGORY_ID_TAG_TYPE;
            }
            else if (qName.equals("categoryName"))
            {
                m_currentTagType = K_CATEGORY_NAME_TAG_TYPE;
            }           
        }

        public void characters(char[] ch, int start, int length)
        {
            for (int i = start; i < start + length; i++)
            {
                if (ch[i] != ' ')
                {
                    switch (m_currentTagType)
                    {                  
                        case K_CATEGORY_ID_TAG_TYPE:
                            m_categoryID = m_categoryID + ch[i];
                        break;
                        case K_CATEGORY_NAME_TAG_TYPE:
                            m_categoryName = m_categoryName + ch[i];
                        break;
                    }
                }
            }
        }

        public void endElement(java.lang.String uri, java.lang.String localName, java.lang.String qName)
        {
            if (qName.equals("category"))
            {
               m_categories.addElement(m_currentCategory);               
               m_currentCategory = null;
            } 
            else if (qName.equals("categoryId"))
            {                    
                m_currentCategory.setId(m_categoryID);
                m_categoryID = "";                    
            }
            else if (qName.equals("categoryName"))
            {
                m_currentCategory.setName(m_categoryName);
                m_categoryName = "";
            }           
        }

        public int getNumberBlogEntries()
        {
            return m_categories.size();
        }
    }
}
