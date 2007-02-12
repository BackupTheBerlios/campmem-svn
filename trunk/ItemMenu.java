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

/**
 */
public class ItemMenu extends VisualMenu
{
	private List				m_menuItemList;

        /**
         */
        public ItemMenu(Display ownerDisplay, String menuTitle)
        {
            this(ownerDisplay, menuTitle, List.IMPLICIT);
        }
	
        /**
	 */
	public ItemMenu(Display ownerDisplay, String menuTitle, int listType)
	{
		super(ownerDisplay);
		m_menuItemList = new List(menuTitle, listType);
		m_menuItemList.setCommandListener(this);
	}
	
        /**
         */
        public void addMenuItem(String itemName, VisualMenu subMenu)
        {
            //add to visual list
            m_menuItemList.append(itemName, null);
            
            //add to data store
            addChild(subMenu);
        }
        
	/**
	 */
	public VisualMenu getMenuItem(int index)
	{
		Vector children = getChildren();
		return (VisualMenu) children.elementAt(index);
	}

	/**
	 */
	public void removeMenuItem(int index)
	{
		VisualMenu removeMenu = getMenuItem(index);
		//remove from data store
		removeChild(removeMenu);
	}

        /**
         */
        public int getNumberOfItems()
        {
            return m_menuItemList.size();
        }
        
        /**
         */
        public void removeAll()
        {
            Vector children = getChildren();
            for (int i = 0; i < children.size();i++)
            {
                Menu menu = (Menu)children.firstElement();
                this.removeChild(menu);
            }
            m_menuItemList.deleteAll();
        }
        
	/**
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
	 */
	public Displayable getDisplayable()
	{
		addCommands(m_menuItemList);
		return m_menuItemList;
	}
}