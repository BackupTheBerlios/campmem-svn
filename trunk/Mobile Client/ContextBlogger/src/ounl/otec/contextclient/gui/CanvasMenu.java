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
import javax.microedition.lcdui.game.*;
import ounl.otec.contextclient.state.State;

/** Simple Menu that has a canvas as its displayable.
 *  @author Tim de Jong
 */
public class CanvasMenu extends VisualMenu
{
	private Canvas m_canvas;
       	
        /** Default Constructor, creates a default menu canvas for this CanvasMenu.
         *  @param ownerDisplay the display that is used to show this menu.
         *  @param menuName the user readable name of this menu.
         */
        public CanvasMenu(Display ownerDisplay, String menuName)
        {
            super(ownerDisplay, menuName);
            m_canvas = new MenuCanvas();
        }
        
        /** Constructor, takes canvas that should be showed in this CanvasMenu as a
         *  parameter.
         *  @param ownerDisplay the display that is used to show this menu.
         *  @param menuName the user readable name of this menu.
         *  @param canvas the canvas that has to be displayed.
	 */
	public CanvasMenu(Display ownerDisplay, String menuName, Canvas canvas)
	{
            super(ownerDisplay, menuName);
            m_canvas = canvas;
	}

	/** Returns the displayable that gives a graphic representation for this menu, 
         *  in this case the canvas.
         *  @return the canvas that is a graphical representation for this menu.
	 */
	public Displayable getDisplayable()
	{
            return m_canvas;
	}
        
        /** Sets the canvas to be displayed by this menu.
         *  @param canvas the canvas to be displayed by this menu.
         */
	public void setCanvas(Canvas canvas)
        {
            m_canvas = canvas;
        }
        
        /** Gets the canvas that is displayed by this menu
         *  @return the displayed canvas.
	 */
	public Canvas getCanvas()
	{
            return m_canvas;
	}

        /**
         *  @param
         */
        public void stateUpdated(State s)
        {
            
        }
        
	/** Private class implementing a GameCanvas to be used by the
         *  default constructor of the CanvasMenu class.
	 */
	private class MenuCanvas extends GameCanvas
	{
            /** Constructor
             */
            public MenuCanvas()
            {
                super(false);
                setFullScreenMode(true);
            }
           
            public void start()
            {
            }

            /** Called when the MenuCanvas has to be drawn or redrawn.
             *  @param g, the graphics object reponsible for all drawing operations.
             */
            public void paint(Graphics g)
            {
            }
	}
}