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

/** Interface for logic object used by a controller. The ControllerLogic interface provides a blue print for classes that
 *  can handle certain states and perform some logic on these states to change their values or make decisions based on 
 *  these states.
 *  @author Tim de Jong
 */
public interface IControllerLogic
{
	/** Returns whether the specific state s is handled by controller logic
         *  @param s the state that has to be handled.
         *  @return true if the state s is handled by the subclass of this controller logic interface.
         *  false, if the state s is not handled.
	 */
	public boolean isHandling(State s);

	/** Performs the logic on or depending on the state s.
         *  @param s the state that has to be handled by this interface's subclass.
         *  @return a state representing the results of the logic carried out. This can be a changed state s
         *  or a new state with values depending on the logic carried out on s.
	 */
	public State handle(State s);
}