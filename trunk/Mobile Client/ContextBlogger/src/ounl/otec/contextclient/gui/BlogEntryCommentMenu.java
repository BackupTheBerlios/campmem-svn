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
import ounl.otec.contextclient.main.CampusConstants;
import ounl.otec.contextclient.state.*;

/** A BlogEntryCommentMenu is a menu that is used to create and post user comments or annotations to blog entries.
 *  @author Tim de Jong
 */
public class BlogEntryCommentMenu extends VisualMenu
{
    private TextBox m_commentTextBox;
    private BlogEntry m_entry;  
        
    /** Constructor
     *  @param ownerDisplay the display that will be used to display this menu.
     *  @param entry the blog entry this menu creates and posts the comments for.
     */
    public BlogEntryCommentMenu(Display ownerDisplay, BlogEntry entry) 
    {
        super(ownerDisplay, entry.getTitle());
        m_entry = entry;
        buildCommentMenu(entry);
    }
    
    /** Builds the comment menu graphical representation.
     *  @param entry the blog entry this menu will provide comments for. 
     */
    private void buildCommentMenu(BlogEntry entry)
    {
        m_commentTextBox = new TextBox("Comment: " + entry.getTitle(),"",255, TextField.ANY);
        m_commentTextBox.setCommandListener(this);
        m_commentTextBox.addCommand(CampusConstants.K_BACK_COMMAND);
        m_commentTextBox.addCommand(CampusConstants.K_SUBMIT_COMMAND);        
    }
    
    /** Overrides the method from the VisualMenu class to handle menu specific commands
     *  @param c the command that is carried out on the menu
     *  @param d the displayable that caused the command to be carried out.
     */
    public void	commandAction(Command c, Displayable d)
    {
        if (c == CampusConstants.K_SUBMIT_COMMAND)
        {
           //get entered comment on blog entry
           String commentText = m_commentTextBox.getString();
           //check if the comment is not empty, we won't post any empty comments
           if (!commentText.equals(""))
           {
               State commentState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_COMMENT_STATE);
               commentState.setValue(CampusConstants.K_BLOG_ENTRY_KEY, m_entry);
               commentState.setValue(CampusConstants.K_COMMENT_KEY, commentText);
               changeState(commentState); 
               this.setWaiting(true);
           }
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
        State commentState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_COMMENT_STATE);        
        if (s.equals(commentState))
        {
            Boolean result = (Boolean)s.getValue(CampusConstants.K_RESULT_KEY);
            if (result.booleanValue())
            {
                //operation has succeeded, don't display the waiting screen anymore
                this.setWaiting(false);
                //if the comment has been posted return to the parent menu
                VisualMenu parentMenu = (VisualMenu) getParent();
                getOwnerDisplay().setCurrent(parentMenu.getDisplayable());
                //commentState.clearAllData();                
            }
            else
            {
                //operation did not succeed yet!
                this.setWaiting(false);
                //show error message
            }
        }
    }
    
    /** Returns the displayable for this menu. The displayable is a graphic representation of this menu. Also this method has
     *  a side effect, it clears the textbox of this comment menu before the displayable is returned.
     *  @return the displayable that is a graphic representation of the menu.
     */ 
    public Displayable getDisplayable()
    {
        //clear textbox before showing it
        m_commentTextBox.setString("");
        return m_commentTextBox;
    }
}
