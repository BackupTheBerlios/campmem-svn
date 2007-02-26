//package ounl.otec.CampusMemories.Mobile;
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

/** Provides an interface for class listening to sensor state updates. Also these classes should handle the errors
 *  that can be caused by the sensors.
 *  @author Tim de Jong
 */
public interface ISensorListener extends IStateListener
{
	/** Called when the state representing the sensor values has been changed.
         *  Inherited from IStateListener.
         *  @param s the sensor state that has been changed.  
	 */
	public void stateUpdated(State s);

	/** Called when an exception occurred during the sensor operation. The subclass should
         *  handle the sensorException or throw it through to a class that can handle it
         *  @param e the exception that has been throwed by the sensor.
	 */
	public void sensorError(Exception e);
}
