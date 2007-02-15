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

/**
 */
public abstract class VisualMenu 	extends Menu
									implements CommandListener
{
	private Display				m_ownerDisplay;
        private boolean                         m_backCommand;
        private boolean                         m_quitCommand;
        
	/**
	 */
	public VisualMenu(Display ownerDisplay)
	{
		this(ownerDisplay, true, false);
	}

	/**
	 */
	public VisualMenu(Display ownerDisplay, boolean backCommand, boolean quitCommand)
	{
		m_ownerDisplay = ownerDisplay;
		//add back command to displayable
                m_backCommand = backCommand;
                m_quitCommand = quitCommand;
	}

	/**
	 */
	protected Display getOwnerDisplay()
	{
		return m_ownerDisplay;
	}

	/**
	 */
	public void addCommands(Displayable displayable)
	{
		if (!isRoot() && this.m_backCommand)
		{
			displayable.addCommand(CampusConstants.K_BACK_COMMAND);
		}
		if (m_quitCommand)
		{
			displayable.addCommand(CampusConstants.K_EXIT_COMMAND);
		}
	}

	/**
	 */
	public void commandAction(Command c, Displayable d)
	{
		if (c == CampusConstants.K_BACK_COMMAND)
		{
			//find out if we can go back from this menu
			if (!isRoot())
			{
				//the menu is a submenu of another menu, find the previous menu
				VisualMenu parentMenu = (VisualMenu)getParent();

				/*
					if the menu type is a transitional menu, the menu itself has to be skipped when travelling back into
					the hierarchy, therefore this commandAction method has to be overridden to change the default behaviour.
					dirty hack, change this later!!!
	 			*/
	 			if (
						(parentMenu instanceof StateTransitionMenu) &&
						(!parentMenu.isRoot())
					)
				{
					VisualMenu grandParentMenu = (VisualMenu)parentMenu.getParent();
					parentMenu = grandParentMenu;
				}

				//go back to parent menu
				m_ownerDisplay.setCurrent(parentMenu.getDisplayable());
			}
		}
		else if (c == CampusConstants.K_EXIT_COMMAND)
		{
                    State quitState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_QUIT_STATE);
                    quitState.setValue(CampusConstants.K_QUIT_KEY, new Boolean(true));
		}
	}
        
        /**
         */
        public void setBackCommandAvailable(boolean available)
        {
            m_backCommand = available;
        }
        
        /**
         */
        public boolean getBackCommandAvailable()
        {
            return m_backCommand;
        }
        
        /**
         */
        public void setQuitCommandAvailable(boolean available)
        {            
            m_quitCommand = available;
        }
        
        /**
         */
        public boolean getQuitCommandAvailable()
        {
            return m_quitCommand;
        }
        
	/**
	 */
	public abstract Displayable getDisplayable();
}