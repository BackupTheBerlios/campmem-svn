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
import java.util.Hashtable;

/**
 */
public class StateTransitionMenu	extends VisualMenu
					implements IStateListener
{
	private State				m_transitionState;
	private Displayable			m_menuDisplayable = null;
	private Hashtable			m_menuTable = new Hashtable();
	private boolean				m_active = false;

	/**
	 */
	public StateTransitionMenu(Display ownerDisplay, State transitionState, boolean active)
	{
		super(ownerDisplay);
		m_transitionState = transitionState;
		m_transitionState.addStateListener(this);
		m_active = active;
	}

        /**
         */
	public void addMenuTransition(String menuIdentifier, VisualMenu menu)
	{
		m_menuTable.put(menuIdentifier, menu);
		//also add the menu transition to the menu hierarchy
		addChild(menu);
	}

        /**
         */
	public void stateUpdated(State s)
	{
		if (m_transitionState.equals(s))
		{
			String menuID = (String)s.getValue(CampusConstants.K_MENU_ID);
			//find the newly chosen menu to transfer to when this menu is chosen
			VisualMenu menu = (VisualMenu)m_menuTable.get(menuID);
			//set the current displayable to that one of the current menu chosen
			if (menu != null)
			{
				m_menuDisplayable = menu.getDisplayable();
				//if this is an active menu, the menu will be set to current the moment the corresponding state changes
				//a passive menu will behave opposite
				if (m_active)
				{
					getOwnerDisplay().setCurrent(m_menuDisplayable);
				}
			}
		}
	}

        /**
         */
	public Displayable getDisplayable()
	{
		return m_menuDisplayable;
	}
}