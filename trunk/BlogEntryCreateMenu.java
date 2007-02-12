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
    public Displayable m_displayable;
    
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
        m_displayable = new Form("Create a new Blog Entry");
        m_displayable.setCommandListener(this);
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
