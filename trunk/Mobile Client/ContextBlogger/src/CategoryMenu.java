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
import javax.xml.parsers.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import java.util.Vector;
import java.io.ByteArrayInputStream;

/** Shows all categories available in the blog.
 *  @author Tim de Jong
 */
public class CategoryMenu extends ItemMenu 
{
    BlogEntryListMenu m_categoryEntryMenu;
    
    /** Constructor
     *  @param ownerDisplay the display that will be showing this menu.
     *  @param menuTitle the title for this categorymenu. 
     */
    public CategoryMenu (Display ownerDisplay, String menuTitle) 
    {
        super(ownerDisplay, menuTitle);      
        
        Thread t = new Thread(new CategoryRetrieve(this));
        t.run(); 
    }
    
    /** Retrieves all categories from the server.
     */
    private class CategoryRetrieve implements Runnable
    {        
        private ItemMenu            m_owner;

        public CategoryRetrieve(ItemMenu owner)
        {
            m_owner = owner;            
        }

        public void run()
        {
            createCategoryList();
        }

        public void createCategoryList()
        {
            try
            {
                //get the blog entries from the server
                String categoryXML = CampusConstants.K_BLOGGER_STUB.getCategories(CampusConstants.K_MOBILE_ID);
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
                            
                        State s = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_CATEGORY_FILTER_STATE);
                        //add the categories found to the menu
                        for (int i = 0; i < categories.size(); i++)
                        {
                           Category category = (Category)categories.elementAt(i);                       

                           StateChangeMenu categoryChangeMenu = new StateChangeMenu(m_owner.getOwnerDisplay(),s);
                           categoryChangeMenu.addStateChange(CampusConstants.K_CATEGORY_KEY, category);
                           m_owner.addMenuItem(category.getName(),categoryChangeMenu);
                        } 
                    }
                    catch (Exception e)
                    {                        
                    }
                }
            }
            catch (java.rmi.RemoteException e)
            {
            }		
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
