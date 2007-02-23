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

/** A StateChangeMenu changes the property values of a State according to some
 *  StateChanges that have been added to it. The StateChanges take place at the 
 *  moment the menu is chosen. A StateChangeMenu doesn't have a graphical 
 *  representation.
 *  @author Tim de Jong
 */
public class StateChangeMenu extends VisualMenu
{
	private State				m_menuState;
	private Hashtable			m_stateChanges = new Hashtable();

	/** Constructor.
         *  @param ownerDisplay, the display that is used to display this menu.
         *  Note: this is only needed for the VisualMenu this menu inherits from,
         *  the menu itself does not have a graphical representation and therefore
         *  does not need a display.
         *  @param state, the state that should be changed when this menu has been
         *  chosen.   
	 */
	public StateChangeMenu(Display ownerDisplay, State state)
	{
		this(ownerDisplay, state, true);
	}

	/** Constructor.
         *  @param ownerDisplay, the display that is used to display this menu.
         *  Note: this is only needed for the VisualMenu this menu inherits from,
         *  the menu itself does not have a graphical representation and therefore
         *  does not need a display.
         *  @param state, the state that should be changed when this menu has been
         *  chosen.
         *  @param backCommand, indicates whether a backcommand should be added to the menu.
         *  If true a backcommand is added to the menu, if false no command will be added.   
	 */
	public StateChangeMenu(Display ownerDisplay, State state, boolean backcommand)
	{
		super(ownerDisplay, backcommand, false);
		m_menuState = state;
	}

	/** Adds a state change to this menu. A state change can change one 
         *  property of a state. The property to change is given by the valueField
         *  parameter of this method. The new value this property should be given
         *  is given by newValue.
         *  @param valueField, the name of the state property to change.
         *  @param newValue, the new value for the property.
	 */
	public void addStateChange(String valueField, Object newValue)
	{
		m_stateChanges.put(valueField, newValue);
	}

	/** Removes a state change that should change the property indicated by
         *  the valueField parameter.
         *  @param valueField, the name of the property that was effected by the
         *  state change and for which this state change should be removed.
         *  @return the new value that was attached to this state change.
	 */
	public Object removeStateChange(String valueField)
	{
		return m_stateChanges.remove(valueField);
	}

	/** Changes the State by applying all state changes that have been added
         *  to this menu.
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

	/** Because the StateChangeMenu has no graphical representation this method
         *  doesn't return a displayable, but null. However, when this method is called,
         *  ie. when the menu has been selected, the state changes are carried out.
         *  @return null, a StateChangeMenu has no visual representation.
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