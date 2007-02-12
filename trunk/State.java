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
import java.util.Hashtable;
import java.util.Vector;

/** Represents a complex state variable.
 */
public class State
{
	private String				m_stateName;
	private Hashtable			m_values = new Hashtable();
	private Vector				m_listeners = new Vector();

	/**
	 */
	public State(String stateName)
	{
		m_stateName = stateName;
	}

	/**
	 */
	public String getStateName()
	{
		return m_stateName;
	}

	/**
	 */
	public void setValue(String valueKey, Object value)
	{
		m_values.put(valueKey, value);
		notifyUpdate();
	}

	/**
	 */
	public void removeValue(String valueKey)
	{
		m_values.remove(valueKey);
		notifyUpdate();
	}

	/**
	 */
	public Object getValue(String valueKey)
	{
		return m_values.get(valueKey);
	}

	/**
	 */
	public void clearAllData()
	{
		m_values.clear();
	}

	/**
	 */
	public void addStateListener(IStateListener listener)
	{
		m_listeners.addElement(listener);
	}

	/**
	 */
	public boolean removeStateListener(IStateListener listener)
	{
		return m_listeners.removeElement(listener);
	}

	/**
	 */
	protected void notifyUpdate()
	{
		for (int i = 0; i < m_listeners.size(); i++)
		{
			IStateListener listener = (IStateListener) m_listeners.elementAt(i);
			listener.stateUpdated(this);
		}
	}
}