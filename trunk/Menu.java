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
/**
 */
public class Menu
{
	private Menu					m_parent;
	private Vector					m_children = new Vector();

	/**
	 */
	public Menu()
	{
		this(null);
	}

	/**	Constructor

		@param
		@param
	 */
	public Menu(Menu parent)
	{
		m_parent = parent;
	}

	/**
		@param
	 */
	public void setParent(Menu parent)
	{
		m_parent = parent;
	}

	/**
		@return
	 */
	public Menu getParent()
	{
		return m_parent;
	}

	/**
		@param
	 */
	public void addChild(Menu child)
	{
		m_children.addElement(child);
		child.setParent(this);
	}

	/**
		@param
		@return
	 */
	public boolean removeChild(Menu child)
	{
		return m_children.removeElement(child);
	}

	/**
		@return
	 */
	public Vector getChildren()
	{
		return m_children;
	}

	/**
		@param
	 */
	public void setChildren(Vector children)
	{
		m_children = children;
	}

	/**
		@return
	 */
	public boolean isRoot()
	{
		return (m_parent == null);
	}
}