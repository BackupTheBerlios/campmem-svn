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
 * BlogEntryRateMenu.java
 *
 * Created on 2 februari 2007, 14:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import javax.microedition.lcdui.*;

/**
 *
 * @author Tim
 */
public class BlogEntryRateMenu extends VisualMenu
{
    private BlogEntry           m_entry;
    private Form                m_rateForm;
    private Gauge               m_rateSlider;
    
    private final Command       K_RATE_COMMAND = new Command("Rate", Command.SCREEN, 2);
    
    /** Creates a new instance of BlogEntryRateMenu */
    public BlogEntryRateMenu(Display ownerDisplay, BlogEntry entry) 
    {
        super(ownerDisplay);
        m_entry = entry;
        buildRateForm(m_entry);
    }
    
    private void buildRateForm(BlogEntry entry)
    {
        m_rateForm = new Form("Rate: " + entry.getTitle());
        m_rateForm.setCommandListener(this);
        //add commands to rate form
        m_rateForm.addCommand(CampusConstants.K_BACK_COMMAND);
        m_rateForm.addCommand(K_RATE_COMMAND);
        
        m_rateSlider = new Gauge("Rating", true, 5, 0);        
        m_rateForm.append(m_rateSlider);
        m_rateSlider.setValue(entry.getRating());
    }
    
     public void commandAction(Command c, Displayable d)
     {
        if (c == K_RATE_COMMAND)
        {
           int rating = m_rateSlider.getValue();
           try
           {
                String returnString = CampusConstants.K_BLOGGER_STUB.setRating(CampusConstants.K_MOBILE_ID, m_entry.getObjectID(), m_entry.getPostID(), rating);
                if(returnString.equals("true"))
                {
                    //rating set properly, go back to the parent menu
                    VisualMenu parentMenu = (VisualMenu) this.getParent();
                    this.getOwnerDisplay().setCurrent(parentMenu.getDisplayable());
                }
                else
                {
                    //rating no set properly, reset rate slider
                    //display error message?
                    m_rateSlider.setValue(0);
                }
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
     
    public Displayable getDisplayable()
    {
        return m_rateForm;
    }
}