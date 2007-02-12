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
import java.util.Enumeration;
import javax.microedition.lcdui.*;

/**
 */
public class StateChangeMenu extends VisualMenu
{
	private State				m_menuState;
	private Hashtable			m_stateChanges = new Hashtable();

	/**
	 */
	public StateChangeMenu(Display ownerDisplay, State state)
	{
		this(ownerDisplay, state, true);
	}

	/**
	 */
	public StateChangeMenu(Display ownerDisplay, State state, boolean backcommand)
	{
		super(ownerDisplay, backcommand, false);
		m_menuState = state;
	}

	/**
	 */
	public void addStateChange(String valueField, Object newValue)
	{
		m_stateChanges.put(valueField, newValue);
	}

	/**
	 */
	public Object removeStateChange(String valueField)
	{
		return m_stateChanges.remove(valueField);
	}

	/**
	 */
	public void changeState()
	{
		Enumeration keys = m_stateChanges.keys();
		while (keys.hasMoreElements())
		{
			//get all valueFields that chould change
			String valueFieldKey = (String)keys.nextElement();
			//get the new values for the valuefields
			Object newValue = m_stateChanges.get(valueFieldKey);
			//update all valueFields corresponding to this state change
			m_menuState.setValue(valueFieldKey, newValue);
		}
	}

	/**
	 */
	public Displayable getDisplayable()
	{
		//the displayable is gotten from the menu at the moment the menu is chosen
		//when a state change menu is chosen, it chould change its state accordingly
		changeState();
		//a state change menu in its minimal form is not visible
		return null;
	}
}