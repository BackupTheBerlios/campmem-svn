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
import ounl.otec.contextclient.state.State;
import ounl.otec.contextclient.main.CampusConstants;

/** The BlogEntryCreateMenu is used to create new blog entries in the blog. 
 *  @author Tim de Jong
 */
public class BlogEntryCreateMenu extends VisualMenu
{
    private Displayable             m_displayable;
    private TextField               m_titleField;
    private TextField               m_bodyField;
    private State                   m_entryCreateState;
    /** Constructor
     *  @param ownerDisplay the display object that will be used to display the menu.
     *  @param menuName the name and title for this menu.
     */
    public BlogEntryCreateMenu(Display ownerDisplay, String menuName) 
    {
        super (ownerDisplay, menuName);
        m_entryCreateState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_ENTRY_CREATE_STATE);
        createMenu();
    }
    
    /** Creates the menu that is used to create new blog entries.
     */
    private void createMenu()
    {
        Form menuForm = new Form("Create a new Blog Entry");
        m_displayable = menuForm;
        m_displayable.setCommandListener(this);
        //create textField that will contain the blog's title
        m_titleField = new TextField("Title", "", 25, TextField.ANY);
        menuForm.append(m_titleField);
        //create textField that will contain the blog's body
        m_bodyField = new TextField("Body","",500, TextField.ANY);
        m_bodyField.setLayout(Item.LAYOUT_VEXPAND);
        menuForm.append(m_bodyField);
        //add commands
        m_displayable.addCommand(CampusConstants.K_BACK_COMMAND);
        m_displayable.addCommand(CampusConstants.K_SUBMIT_COMMAND);
    }
    
    /** Overrides the method from the VisualMenu class to handle menu specific commands
     *  @param c the command that is carried out on the menu
     *  @param d the displayable that caused the command to be carried out.
     */
    public void	commandAction(Command c, Displayable d)
    {
        if (c == CampusConstants.K_SUBMIT_COMMAND)
        {
           //get the entered blog parameters
           m_entryCreateState.setValue(CampusConstants.K_TITLE_KEY, m_titleField.getString());
           m_entryCreateState.setValue(CampusConstants.K_BODY_KEY, m_bodyField.getString());
           //maybe it's a good idea to make one state for the object id, this state can be updated 
           //and queried whenever the object id is needed.           
           State objectIDState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_OBJECT_ID_STATE);
           String objectID = (String)objectIDState.getValue(CampusConstants.K_OBJECT_ID_KEY);
           m_entryCreateState.setValue(CampusConstants.K_OBJECT_ID_KEY, objectID);
           
           String categories = "";                      
           m_entryCreateState.setValue(CampusConstants.K_CATEGORIES_KEY, categories);
           //request a state change to add a blog entry to the blog
           changeState(m_entryCreateState);
           this.setWaiting(true);
        }
        else
        {
            super.commandAction(c,d);
        }
    }
    /**
     *  @param
     */
    public void stateUpdated(State s)
    {
        if (s.equals(m_entryCreateState))
        {
            Boolean result = (Boolean)m_entryCreateState.getValue(CampusConstants.K_RESULT_KEY);
            if (result.booleanValue())
            {
                this.setWaiting(false);
                //if the post has been done, return to the parent menu! 
                //TODO add an operation for this in the visualmenu class!
                VisualMenu parentMenu = (VisualMenu)this.getParent();
                this.getOwnerDisplay().setCurrent(parentMenu.getDisplayable());
            }
            else
            {
                
            }
        }
    }
    
    /** Returns the displayable that provides the graphical representation of this menu
     *  @return the displayable with the graphical representation of this menu.
     */
    public Displayable getDisplayable()
    {
        return m_displayable;
    }
}
