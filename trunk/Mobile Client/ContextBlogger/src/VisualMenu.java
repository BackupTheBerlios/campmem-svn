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

/** Abstract superclass for a Menu hierarchy that will be represented in the GUI.
 *  @author Tim de Jong
 */
public abstract class VisualMenu    extends Menu
                                    implements CommandListener
{
	private Display				m_ownerDisplay;
        private boolean                         m_backCommand;
        private boolean                         m_quitCommand;
        
	/** Constructor
         *  @param ownerDisplay the display that will be used to show this visual menu.
	 */
	public VisualMenu(Display ownerDisplay)
	{
		this(ownerDisplay, true, false);
	}

	/** Constructor
         *  @param ownerDisplay the display that will be used to show this visual menu.
         *  @param backCommand if true a back command will be added to the menu. 
         *  @param quitCommand if true a quit command will be added to the menu.
	 */
	public VisualMenu(Display ownerDisplay, boolean backCommand, boolean quitCommand)
	{
		m_ownerDisplay = ownerDisplay;
		//add back command to displayable
                m_backCommand = backCommand;
                m_quitCommand = quitCommand;
	}

	/** Returns the display used to show the visual menu.
         *  @return the display used to show the visual menu.
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

       /** Implements the method from the CommandListener interface to handle
        *  the back and exit commands in a unified way for all subclasses. 
        *  Subclasses can override this method to handle more commands, but for
        *  the back and exit commands they should call this method to handle these
        *  commands.
        *  @param c the command that is carried out on the menu
        *  @param d the displayable that caused the command to be carried out.
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
        
        /** Sets whether the back command should be available.
         *  @param available true if a back command should be added to the menu,
         *  false otherwise.
         */
        public void setBackCommandAvailable(boolean available)
        {
            m_backCommand = available;
        }
        
        /** Returns a boolean value that indicates whether this menu has a back 
         *  command or not.
         *  @return true if the menu has a back command, false otherwise.
         */
        public boolean getBackCommandAvailable()
        {
            return m_backCommand;
        }
        
        /** Sets whether the quit command should be available.
         *  @param available true if a quit command should be added to the menu,
         *  false otherwise.
         */
        public void setQuitCommandAvailable(boolean available)
        {            
            m_quitCommand = available;
        }
        
        /** Returns a boolean value that indicates whether this menu has a quit 
         *  command or not.
         *  @return true if the menu has a quit command, false otherwise.
         */
        public boolean getQuitCommandAvailable()
        {
            return m_quitCommand;
        }
        
	/** Subclasses should override this method to return the displayable giving 
         *  a graphical representation for this menu.
         *  @return the displayable representing this menu.
         */ 
	public abstract Displayable getDisplayable();
}