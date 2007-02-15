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
 * BlogEntryCreateMenu.java
 *
 * Created on 6 februari 2007, 16:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;

/**
 *
 * @author Tim
 */
public class BlogEntryCreateMenu extends VisualMenu
{
    private Displayable             m_displayable;
    private TextField               m_titleField;
    private TextField               m_bodyField;
    
    /** 
     */
    public BlogEntryCreateMenu(Display ownerDisplay) 
    {
        super (ownerDisplay);
        createMenu();
    }
    
    /**
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
    
    /**
     */
    public void	commandAction(Command c, Displayable d)
    {
        if (c == CampusConstants.K_SUBMIT_COMMAND)
        {
           //get the entered blog parameters
           String title = m_titleField.getString();
           String body = m_bodyField.getString();
           //maybe it's a good idea to make one state for the object id, this state can be updated 
           //and queried whenever the object id is needed.           
           String objectID = ""; 
           String categories = "";           
           //post the blog entry to the blog
           try
           {
               CampusConstants.K_BLOGGER_STUB.post(CampusConstants.K_MOBILE_ID, objectID, categories, title, body);
           }
           catch (java.rmi.RemoteException e)
           {
               e.printStackTrace();
           }
        }
        else
        {
            super.commandAction(c,d);
        }
    }
    
    /**
     */
    public Displayable getDisplayable()
    {
        return m_displayable;
    }
}
