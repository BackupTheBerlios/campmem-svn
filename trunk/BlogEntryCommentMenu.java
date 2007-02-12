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
/*
 * BlogEntryCommentMenu.java
 *
 * Created on 2 februari 2007, 11:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;

/**
 *
 * @author Tim
 */
public class BlogEntryCommentMenu extends VisualMenu
{
    private TextBox m_commentTextBox;
    private BlogEntry m_entry;
    
    
    
    /** Creates a new instance of BlogEntryCommentMenu */
    public BlogEntryCommentMenu(Display ownerDisplay, BlogEntry entry) 
    {
        super(ownerDisplay);
        m_entry = entry;
        buildCommentMenu(entry);
    }
    
    private void buildCommentMenu(BlogEntry entry)
    {
        m_commentTextBox = new TextBox("Comment: " + entry.getTitle(),"",255, TextField.ANY);
        m_commentTextBox.setCommandListener(this);
        m_commentTextBox.addCommand(CampusConstants.K_BACK_COMMAND);
        m_commentTextBox.addCommand(CampusConstants.K_SUBMIT_COMMAND);        
    }
    
    public void	commandAction(Command c, Displayable d)
    {
        if (c == CampusConstants.K_SUBMIT_COMMAND)
        {
           //get entered comment on blog entry
           String commentText = m_commentTextBox.getString();
           //check if the comment is not empty, we won't post any empty comments
           if (!commentText.equals(""))
           {
               //post the comment
               try
               {
                    String test = CampusConstants.K_BLOGGER_STUB.postComment(CampusConstants.K_MOBILE_ID, m_entry.getPostID(), commentText);                    
                    //if the comment has been posted return to the parent menu
                    VisualMenu parentMenu = (VisualMenu) getParent();
                    getOwnerDisplay().setCurrent(parentMenu.getDisplayable());
               }
               catch (java.rmi.RemoteException e)
               {
                   e.printStackTrace();
               }
           }
        }
        else
        {
            super.commandAction(c,d);
        }
    }
    
    public Displayable getDisplayable()
    {
        //clear textbox before showing it
        m_commentTextBox.setString("");
        return m_commentTextBox;
    }
}
