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

/** Provides the user interface with a standard Warning Screen that should be used
 *  when an exception or error occurs. The Menu can handle both critical as 
 *  non-critical errors.
 *  @author Tim
 */
public class WarningMenu extends VisualMenu                            
{
    private String              m_warningMessage;
    private Displayable         m_errorDisplayable;
    
    private Command             K_NEXT_COMMAND = new Command("Next", Command.SCREEN, 2);;
    
    /** Constructor
     *  @param ownerDisplay the display that will show this menu.
     *  @param warningMessage the warning or error message that will be displayed 
     *  by this warning menu.
     *  @param critical indicates whether the warning or error is critical or not.
     */
    public WarningMenu(Display ownerDisplay, String warningMessage, boolean critical) 
    {
        super(ownerDisplay, "Warning");
        m_warningMessage = warningMessage;
        createWarningMenu(critical);
    }
    
    /** Creates the warning menu, so every warning or error is handled in a standardised
     *  way and in a standardised look and feel.
     *  @param critical, indicates whether the warning or error is critical or not.
     */
    private void createWarningMenu(boolean critical)
    {
        Form warningForm = new Form("Warning!");
        m_errorDisplayable = warningForm;
        //display the error message on screen
        Ticker errorMessageTicker = new Ticker(m_warningMessage);
        warningForm.setTicker(errorMessageTicker);
        //the kind of error determines the kind of commands possible
        //if the error is critical the program has to exit, hence an exit command is added to the menu
        //if the error is not critical, execution can continue
        if (critical)
        {
            //add exit command to the menu
            warningForm.addCommand(CampusConstants.K_EXIT_COMMAND);
        }
        else
        {
            //add next command if the error is not critical
            warningForm.addCommand(K_NEXT_COMMAND);
        }
    }
    
    /** Implements the CommandListener interface method to handle sensor menu specific commands
     *  @param c the command that is carried out on the sensor menu
     *  @param d the displayable in the sensor menu that caused the command to be carried out.
     */
    public void commandAction(Command c, Displayable d)
    {
        if (c == K_NEXT_COMMAND)
        {
            
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
        return m_errorDisplayable;
    }
}
