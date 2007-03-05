package ounl.otec.contextclient.gui;
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
import java.util.Vector;
import ounl.otec.contextclient.state.State;
import ounl.otec.contextclient.main.CampusConstants;

/** Shows all categories available in the blog.
 *  @author Tim de Jong
 */
public class CategoryMenu extends ItemMenu 
{
    BlogEntryListMenu               m_categoryEntryMenu;
    State                           m_categoryState;
    
    /** Constructor
     *  @param ownerDisplay the display that will be showing this menu.
     *  @param menuName the name and title for this categorymenu. 
     */
    public CategoryMenu (Display ownerDisplay, String menuName) 
    {
        super(ownerDisplay, menuName);      
        
        m_categoryState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_CATEGORY_LIST_STATE);
        this.retrieveState(m_categoryState);        
    }
    
    /**
     *  TODO make more general, so only ItemMenu class is needed!
     */
    public void stateUpdated(State s)
    {
        if (s.equals(m_categoryState))
        {
            Boolean result = (Boolean)s.getValue(CampusConstants.K_RESULT_KEY);
            if (result.booleanValue())
            {
                Vector categoryNames = (Vector)s.getValue(CampusConstants.K_ITEM_NAMES_KEY);
                Vector categories = (Vector)s.getValue(CampusConstants.K_ITEM_VALUES_KEY);
                if (categoryNames.size() == categories.size())
                {
                    //clear all previous content from the menu
                    removeAll();
                    //add the categories found to the menu            
                    for (int i = 0; i < categoryNames.size(); i++)
                    {                       
                       String categoryName = (String)categoryNames.elementAt(i);
                       Category category = (Category)categories.elementAt(i);                       

                       StateChangeMenu categoryChangeMenu = new StateChangeMenu(getOwnerDisplay(),s);
                       categoryChangeMenu.addStateChange(CampusConstants.K_CATEGORY_KEY, category);
                       addMenuItem(categoryName,categoryChangeMenu);
                    }
                }
            }
            else
            {
                
            }
        }
    }   
    
}
