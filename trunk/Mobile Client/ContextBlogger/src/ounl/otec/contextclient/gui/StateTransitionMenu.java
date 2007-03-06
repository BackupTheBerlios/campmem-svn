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
import javax.microedition.lcdui.*;
import java.util.Hashtable;
import ounl.otec.contextclient.state.IStateListener;
import ounl.otec.contextclient.state.State;
import ounl.otec.contextclient.main.CampusConstants;

/** A StateTransitionMenu transfers command to one of its submenus depending on
 *  the K_MENU_ID property value of the state it's a StateListener for.
 *  @author Tim de Jong
 */
public class StateTransitionMenu	extends VisualMenu					
{
	private State				m_transitionState;
	private Displayable			m_menuDisplayable = null;
	private Hashtable			m_menuTable = new Hashtable();
	private boolean				m_active = false;

	/** Constructor
         *  @param ownerDisplay the display that will display this menu.
         *  @param transitionState the state that will be listened to for changes.
         *  Depending on the changes of the state the controls are transfered to
         *  different child menus.
         *  @param active indicates whether the menu is active or not. When true,
         *  the menu is active and transfers control automatically to a child menu
         *  when the transitionState is changed. If false the control is not automatically
         *  transfered.
	 */
	public StateTransitionMenu(Display ownerDisplay, State transitionState, boolean active)
	{
		super(ownerDisplay, transitionState.getStateName());
		m_transitionState = transitionState;
		m_transitionState.addStateListener(this);
		m_active = active;
	}

        /** Adds a possible transition to a child menu to the StateTransitionMenu.
         *  The menuIdentifier the child menu is added under will be used to
         *  check against the K_MENU_ID property of the transition state; if the
         *  K_MENU_ID property is equal to one of the menuIdentifier, control is 
         *  transfered to the menu under that menuIdentifier.
         *  @param menuIdentifier a unique identifier for the child menu to be
         *  added. The menuIdentifier will be used to find out what menu to 
         *  transfer control to.
         *  @param menu the VisualMenu to be added as a possible transition of 
         *  this transition menu.
         */
	public void addMenuTransition(String menuIdentifier, VisualMenu menu)
	{
		m_menuTable.put(menuIdentifier, menu);
		//also add the menu transition to the menu hierarchy
		addChild(menu);
	}

        /** Listens to changes in the stateTransition State this menu listens to.
         *  When the State changes menu is transfered to the menu that has a 
         *  menuIdentifier equal to the value of the K_MENU_ID property of the
         *  stateTransition State.
         *  @param s the state that has been changed.
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

        /** Returns the displayable of the menu the control has been transfered to.
         *  @return the displayable giving the graphical representation of the menu
         *  control has been transfered to.
         */
	public Displayable getDisplayable()
	{
		return m_menuDisplayable;
	}
}