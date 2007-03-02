package ounl.otec.contextclient.main;
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

/** Class representing a menu hierarchy that consists of one root menu and several children menus; each of these children
 *  menus can have a menu hierarchy of its own attached to it. The Menu class offers some standard hierarchy related methods
 *  to its subclasses.
 *  @author Tim de Jong
 */
public class Menu
{
	private Menu                m_parent;
	private Vector              m_children = new Vector();
        private String              m_menuName;

	/** Default Constructor
         *  The Default Constructor creates a Menu object with no parent. Therefore it constructs a menu at the highest 
         *  level, ie. one with a root node
         *  @param name a user friendly name of the menu
	 */
	public Menu(String name)
	{
		this(name, null);
	}

	/** Constructor
         *  Creates a menu hierarchy that has a specific parent menu. This constructor is meant to be used to
         *  construct a menu hierarchy at a lower level.
         *  @param name a user friendly name of the menu
         *  @param parent a parent menu node for this part of the menu hierarchy.		
	 */
	public Menu(String name, Menu parent)
	{
		m_parent = parent;
                m_menuName = name;
	}

	/** Sets the parent menu node for this menu.
         *  @param parent the parent menu node for this menu.
	 */
	public void setParent(Menu parent)
	{
		m_parent = parent;
	}

	/** Gets the parent menu node for this menu.
         *  @return the parent menu node for this menu.
	 */
	public Menu getParent()
	{
		return m_parent;
	}

	/** Adds a child menu to this menu's collection of children.
         *  @param child a child menu to be added.
	 */
	public void addChild(Menu child)
	{
		m_children.addElement(child);
		child.setParent(this);
	}

	/** Removes a child from this menu's child collection and with it
         *  a menu hierarchy below that child node 
         *  @param child the child to be deleted.
         *  @return true if the removal was successfull, false otherwise.
	 */
	public boolean removeChild(Menu child)
	{
		return m_children.removeElement(child);
	}

	/** Gets the entire collection of children of this menu.
         *  @return the collection of children directly connected to this menu node.
	 */
	public Vector getChildren()
	{
		return m_children;
	}

	/** Sets an entire collection of children for this menu.
         *  @param children a new collection of children for this menu.
         *  Note: if there was an old collection of children before calling this
         *  operation it will be deleted!
	 */
	public void setChildren(Vector children)
	{
		m_children = children;
	}
        
        /** Sets the user friendly menu name for this menu node.
         *  @param name a menu name that can be used to identify the menu to the user with.
         */
        public void setName(String name)
        {
            m_menuName = name;
        }
        
        /** Returns the user friendly menu name for this menu node.
         *  @return a menu name that can be used to identify the menu to the user with.
         */
        public String getName()
        {
            return m_menuName;
        }
        
	/** Returns whether this Menu is the root node. The root node is found
         *  by checking whether its parent node is non-existent (equal to null).
         *  In a menu hierarchy built correctly only the root node should have no
         *  parent.
         *  @return true, if this menu is the root node, false otherwise.
	 */
	public boolean isRoot()
	{
		return (m_parent == null);
	}
}