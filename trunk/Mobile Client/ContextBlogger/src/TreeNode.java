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

/**	This class represents a node from a tree datastructure.
	@author Tim de Jong
 */
public class TreeNode
{
	private TreeNode				m_parent;
	private Object					m_value;
	private Vector					m_children = new Vector();

	/** Default Constructor
         *  Constructs a root node with no parent.
         *  @param value the value stored in this TreeNode.
	 */
	public TreeNode(Object value)
	{
		this(null, value);
	}

	/** Constructor
         *  Constructs a node or node hierarchy that is add below a certain
         *  parent node.
         *  @param parent the parent node for this node or node hierarchy.
         *  @param value the value stored in this TreeNode
	 */
	public TreeNode(TreeNode parent, Object value)
	{
		m_parent = parent;
		m_value = value;
	}

	/** Sets the parent for this node/node hierarchy
         *  @param parent the parent of this node
	 */
	public void setParent(TreeNode parent)
	{
		m_parent = parent;
	}

	/** Returns the parent of this node/node hierarchy.
         *  @return the parent of this node/node hierarchy.
	 */
	public TreeNode getParent()
	{
		return m_parent;
	}

	/** Sets the value stored in this node.
         *  @param value the value to be stored in this node.
	 */
	public void setValue(Object value)
	{
		m_value = value;
	}

	/** Returns the value that is stored in this node.
         *  @return the value object stored in this node.
	 */
	public Object getValue()
	{
		return m_value;
	}

	/** Adds a child node to this node. Also sets the parent of this child to this
         *  node.
         *  @param child the child node to be added.
	 */
	public void addChild(TreeNode child)
	{
                child.setParent(this);
		m_children.addElement(child);
	}

	/** Removes a child node from this node.
         *  @param child the child to be removed.
         *  @return true if removing the child was successful, false otherwise.
	 */
	public boolean removeChild(TreeNode child)
	{
		return m_children.removeElement(child);
	}

	/** Returns a vector with all children of this node.
         *  @return a vector with all children of this node.
	 */
	public Vector getChildren()
	{
		return m_children;
	}

	/** Sets the children for this node. If there was a group of children
         *  before this method was called, they are deleted from the node and
         *  replaced by the vector with children given as a parameter to this
         *  node.
         *  @param children the new collection of children to be added to the node.
	 */
	public void setChildren(Vector children)
	{
		m_children = children;
	}

	/** Returns whether this node is the root node in the hierarchy. It checks
         *  whether the parent of this node is non-existent (null). If the tree has
         *  been built correctly only the root node has no parent.
         *  @return true if the node is a root node, ie. if its parent is null,
         *  false otherwise.
	 */
	public boolean isRoot()
	{
		return (m_parent == null);
	}
}