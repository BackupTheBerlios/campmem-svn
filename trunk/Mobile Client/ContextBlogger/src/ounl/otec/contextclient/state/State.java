package ounl.otec.contextclient.state;
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

/** Represents a complex state variable, that can contain a multiple values and 
 *  to which StateListeners can be added. These StateListeners are informed every
 *  time the State values change.
 *  @author Tim de Jong
 */
public class State
{
	private String				m_stateName;
	private Hashtable			m_values = new Hashtable();
	private Vector				m_listeners = new Vector();

	/** Constructor.
         *  @param stateName a unique name describing the state.
	 */
	public State(String stateName)
	{
		m_stateName = stateName;
	}

	/** Returns the name of this state.
         *  @return a unique name specific to this state.
	 */
	public String getStateName()
	{
		return m_stateName;
	}

	/** Sets a key-value pair describing a property of this state.
         *  @param valueKey the key describing the property, ie. the name of 
         *  the property to be set.
         *  @param value the value of the property to be set.
	 */
	public void setValue(String valueKey, Object value)
	{
		m_values.put(valueKey, value);
		notifyUpdate();
	}

	/** Removes a key-value pair describing a property of this state.
         *  @param valueKey the key describing the property to be deleted.
	 */
	public void removeValue(String valueKey)
	{
		m_values.remove(valueKey);
		notifyUpdate();
	}

	/** Gets a value of a certain property indicated by valueKey.
         *  @param valueKey the name of the property to get the value from.
         *  @return the value of the property with the name given by valueKey.
	 */
	public Object getValue(String valueKey)
	{
		return m_values.get(valueKey);
	}

	/** Clears all data in this state, ie. deletes all key-value property
         *  pairs withing this state.
	 */
	public void clearAllData()
	{
		m_values.clear();
                notifyUpdate();
	}

	/** Adds a StateListener to this State that will be informed about each
         *  update that is made to this state.
         *  @param listener an object implementing the IStateListener that will
         *  be added to this state to be informed about all updates.
	 */
	public void addStateListener(IStateListener listener)
	{
		m_listeners.addElement(listener);
	}

	/** Removes a StateListener from this State. The StateListener will not
         *  be informed about any State updates afterwards.
         *  @param listener the listener object to be deleted.
	 */
	public boolean removeStateListener(IStateListener listener)
	{
		return m_listeners.removeElement(listener);
	}
        
        /** Gets the list of all StateListeners that will be informed about State
         *  changes.
         *  @return a vector with all StateListeners.
         */
        protected Vector getStateListeners()
        {
            return m_listeners;
        }
        
	/** This method is called by the State class or its subclasses to notify
         *  all listeners about a change in State properties.
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