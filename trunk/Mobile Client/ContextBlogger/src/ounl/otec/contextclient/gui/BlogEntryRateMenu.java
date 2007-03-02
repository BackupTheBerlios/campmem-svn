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
import ounl.otec.contextclient.state.State;

/** Provides a GUI interface for rating and viewing the rating of blog entry. Rating is possible
 *  on a scale from 0 to 5.
 *  @author Tim
 */
public class BlogEntryRateMenu extends VisualMenu
{
    private BlogEntry           m_entry;
    private Form                m_rateForm;
    private Gauge               m_rateSlider;
    
    private final Command       K_RATE_COMMAND = new Command("Rate", Command.SCREEN, 2);
    
    /** Constructor
     *  @param ownerDisplay the display that will be displaying the menu.
     *  @param entry the blogentry the rating will be given for.
     */
    public BlogEntryRateMenu(Display ownerDisplay, BlogEntry entry) 
    {
        super(ownerDisplay, "Rate: " + entry.getTitle());
        m_entry = entry;
        buildRateForm(m_entry);
    }
    
    /** Builds the graphical menu depending on the blog entry to be rated.
     *  @param entry the blog entry to be rated.
     */
    private void buildRateForm(BlogEntry entry)
    {
        m_rateForm = new Form(this.getName());
        m_rateForm.setCommandListener(this);
        //add commands to rate form
        m_rateForm.addCommand(CampusConstants.K_BACK_COMMAND);
        m_rateForm.addCommand(K_RATE_COMMAND);
        
        m_rateSlider = new Gauge("Rating", true, 5, 0);        
        m_rateForm.append(m_rateSlider);
        m_rateSlider.setValue(entry.getRating());
    }
    
    /** Overrides the method from the VisualMenu class to handle menu specific commands
     *  @param c the command that is carried out on the menu
     *  @param d the displayable that caused the command to be carried out.
     */
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
    
    /**
     *  @param
     */
    public void stateUpdated(State s)
    {

    }
    
    /** Gets the displayable giving a graphical representation for this menu
     *  @return the displayable representing this menu.
     */ 
    public Displayable getDisplayable()
    {
        return m_rateForm;
    }
}
