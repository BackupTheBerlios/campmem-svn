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
import java.util.Vector;
import javax.microedition.lcdui.*;
import ounl.otec.contextclient.main.*;
import ounl.otec.contextclient.state.*;

/** This class implements a VisualMenu that can display an item list.
 *  @author Tim de Jong
 */
public class ItemMenu extends VisualMenu
{
	private List				m_menuItemList;
        private State                           m_itemState;

        /** Constructor
         *  @param ownerDisplay the display showing this menu
         *  @param menuName the name and title of this item menu. This title will be displayed 
         *  on top of the menu.
         */
        public ItemMenu(Display ownerDisplay, String menuName)
        {
            this(ownerDisplay, menuName, List.IMPLICIT);
            //retrieves the initial values for this item menu
            retrieveState(getMenuState());
        }
	
        /** Constructor
         *  @param ownerDisplay the display showing this menu
         *  @param menuName the name and title of this item menu. This title will be displayed 
         *  on top of the menu.
         *  @param listType the type of list as defined in the javax.microedition.lcdui.List class.
         */
	public ItemMenu(Display ownerDisplay, String menuName, int listType)
	{
		super(ownerDisplay, menuName);
		m_menuItemList = new List(getName(), listType);
		m_menuItemList.setCommandListener(this);
	}
	
        /** Adds a submenu as a menuitem of this item menu. Each VisualMenu added to the item
         *  menu will show up as a choice in the item menu and will be added in the menu's hierarchy
         *  making navigation of the itemmenu to submenus possible.
         *  @param itemName the title the menuitem should have in the graphical menu.
         *  @param subMenu the VisualMenu that should be added below the itemmenu as a navigation option.
         */
        public void addMenuItem(String itemName, VisualMenu subMenu)
        {
            //add to visual list
            m_menuItemList.append(itemName, null);
            
            //add to data store
            addChild(subMenu);
        }
        
	/** Gets the menuitem located at index among the children 
         *  @param index the index among the children of this menu.
         *  @return the VisualMenu that was added as a child at the index location.
	 */
	public VisualMenu getMenuItem(int index)
	{
		Vector children = getChildren();
		return (VisualMenu) children.elementAt(index);
	}

	/** Removes the child menuitem located at the index position.
         *  @param index the location of the menuitem to be removed.
	 */
	public void removeMenuItem(int index)
	{
		VisualMenu removeMenu = getMenuItem(index);
		//remove from data store
		removeChild(removeMenu);
	}
        
        /** Removes all menuitems or children from this ItemMenu.  
         */
        public void removeAll()
        {
            Vector children = getChildren();
            int numChildren = children.size();
            for (int i = 0; i < numChildren;i++)
            {
                Menu menu = (Menu)children.firstElement();
                this.removeChild(menu);
            }
            m_menuItemList.deleteAll();
        }
        
        /** Returns the number of children or menuitems of this ItemMenu
         *  @return the number of menuitems that have been added to this menu.
         */
        public int getNumberOfItems()
        {
            return m_menuItemList.size();
        }     
                
	/** Overrides the method from the VisualMenu class to handle menu specific commands
         *  @param c the command that is carried out on the menu
         *  @param d the displayable that caused the command to be carried out.
         */
	public void commandAction(Command c, Displayable d)
	{
		//find out what command has spawned the action
		if (c == List.SELECT_COMMAND)
		{			
                        //find out what item in the list was selected
			int selectedIndex = m_menuItemList.getSelectedIndex();
			//get the menu belonging to the selected item
			VisualMenu childMenu = getMenuItem(selectedIndex);
			//this list has spawned a selection command, find out what item was selected
			if (childMenu != null)
			{
				getOwnerDisplay().setCurrent(childMenu.getDisplayable());
			}
		}
		else
		{
			super.commandAction(c,d);
		}
	}

        /**
         *  @param
         */
        public void stateUpdated(State s)
        {
            /*if (s.equals(getMenuState()))
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
                            Object itemValue = itemValues.elementAt(i);
                            //addMenuItem(itemName, VisualMenu subMenu)
                        }
                    }
                }
                else
                {
                    
                }
            }*/
        }
        
	/** Returns the displayable with the graphical representation of this menu
         *  As a byproduct adds the Back command and/or Exit Command to the displayable.
         *  @return the displayable representing this menu.
	 */
	public Displayable getDisplayable()
	{
		addCommands(m_menuItemList);
		return m_menuItemList;
	}
}