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
import ounl.otec.contextclient.state.IStateListener;
import ounl.otec.contextclient.state.State;
import ounl.otec.contextclient.main.CampusConstants;
import java.util.Vector;

/** The BlogEntryListMenu lists all or a filtered list of blog entries.
 *  @author Tim de Jong
 */
public class BlogEntryListMenu  extends ItemMenu                                
{          
        private String              m_objectID;
        private String              m_categoryFilter;
        private State               m_entriesState;
        
        /** Constructor for menu that shows all blog entries found.
         *  @param ownerDisplay the display that will display this menu.
         *  @param menuName the name and title for this itemmenu.
	 */
	public BlogEntryListMenu(Display ownerDisplay, String menuName)
	{
		super(ownerDisplay, menuName); 
                m_entriesState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_ENTRIES_STATE);               
	}
       
        /** Constructor
         *  @param ownerDisplay the display that will display this menu.
         *  @param menuName the name and title for this itemmenu.
         *  @param filterResults whether the results this list shows are filtered or not.
         *  When filterResults is true the results are filtered according to a category filter
         *  depening on the value K_CATEGORY_FILTER_STATE has. When false, all blog entries are shown.
	 */
	public BlogEntryListMenu(Display ownerDisplay, String menuName, boolean filterResults)
	{
		super(ownerDisplay, menuName);
                //m_filterResults = filterResults;
	}
        
        /** Implements the method from the IStateListener interface. The BlogEntryListMenu listens to changes
         *  of two states. First of all it listens to changes in the object id state; when changed the list has to be updated.
         *  Also, the menu listens to changes in the K_CATEGORY_FILTER_STATE so the filter for the blog entries is updated when
         *  it's changed.
         *  @param s the changed state this menu listens to.
         */
        public void stateUpdated(State s)
        {          
            State categoryFilterState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_CATEGORY_FILTER_STATE);
            if (s.equals(categoryFilterState))
            {
                Category selectedCategory = (Category)categoryFilterState.getValue(CampusConstants.K_CATEGORY_KEY); 
                m_categoryFilter = selectedCategory.getName();
                getOwnerDisplay().setCurrent(this.getDisplayable());                
            }
            else if (s.equals(m_entriesState))
            {                
                Boolean result = (Boolean)s.getValue(CampusConstants.K_RESULT_KEY);
                if (result.booleanValue())
                {
                    //a change has taken place in our menu content
                    Vector itemNames = (Vector)s.getValue(CampusConstants.K_ITEM_NAMES_KEY);
                    Vector itemValues = (Vector)s.getValue(CampusConstants.K_ITEM_VALUES_KEY);
                    if (itemNames.size() == itemValues.size())
                    {
                        //clear all previous content from the menu
                        removeAll();
                        //add new found content to the item menu
                        for (int i = 0; i < itemNames.size(); i++)
                        {
                            String itemName = (String)itemNames.elementAt(i);
                            BlogEntry entry = (BlogEntry)itemValues.elementAt(i);
                            
                            BlogEntryActionMenu actionMenu = new BlogEntryActionMenu (getOwnerDisplay(), entry);                                 
                            addMenuItem(entry.getTitle(), actionMenu);   
                        }
                    }
                }
                else
                {
                    
                }
            }
            else
            {
                m_objectID = (String)s.getValue(CampusConstants.K_OBJECT_ID_KEY);                           
            }  
        
        }     
        
}
