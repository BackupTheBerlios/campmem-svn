import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
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
/**
 */
public class CanvasMenu extends VisualMenu
{
	private Canvas m_canvas;
       	
        /**
         */
        public CanvasMenu(Display ownerDisplay)
        {
            super(ownerDisplay);
            m_canvas = new MenuCanvas();
        }
        
        /**
	 */
	public CanvasMenu(Display ownerDisplay, Canvas canvas)
	{
            super(ownerDisplay);
            m_canvas = canvas;
	}

	/**
	 */
	public Displayable getDisplayable()
	{
            return m_canvas;
	}

	public void setCanvas(Canvas canvas)
        {
            m_canvas = canvas;
        }
        
        /**
	 */
	public Canvas getCanvas()
	{
            return m_canvas;
	}

	/**
	 */
	private class MenuCanvas extends GameCanvas
	{
            /**
             */
            public MenuCanvas()
            {
                super(false);
                setFullScreenMode(true);
            }

            /**
             */
            public void start()
            {
            }

            /**
             */
            public void paint(Graphics g)
            {
            }
	}
}