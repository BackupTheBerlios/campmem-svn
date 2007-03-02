package ounl.otec.contextclient.actuator;
import ounl.otec.contextclient.state.IStateListener;
import ounl.otec.contextclient.state.State;
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
/** This class is an abstract superclass for all actuators available in the mobile device. The class provides functionality
 *  common to all actuators. All actuators are statelisteners, they act for a certain state. If the state changes a certain
 *  action can be triggered.
 *  @author Tim de Jong
 */
public abstract class Actuator implements IStateListener
{
        private State m_actingState;
        
	/** Constructor
         *  @param actingState the state this actuator is acting of for.
	 */
	public Actuator(State actingState)
	{
            m_actingState = actingState;
	}

	/** Returns whether this actuator is acting for a certain state.
         *  @return true, is the s equals the state this actuator is acting for,
         *  false otherwise.
	 */
	public boolean isActingFor(State s)
        {
            return m_actingState.equals(s);
        }
        

	/** Will be implemented by the subclasses to act in a certain way when a state change occurs.
         *  @param s acts on a state s. It uses the data stored in s to act in a certain way, using the
         *  actuator.
	 */
	public abstract void act(State s);

	/** Should be implemented to listen to state changes in the acting state. Depending on some internal
         *  logic a decision can me made to act upon the state change or not.
         *  @param s a state that has been change. Doesn't have to be the acting state.
	 */
	public abstract void stateUpdated(State s);
        
        /** Gets the acting state the actuator acts upon.
         *  @return the acting state.
         */ 
        public State getActingState()
        {
            return m_actingState;
        }
        
        /** Sets the acting state for this actuator
         *  @param s the state the actuator should act upon.
         */
        public void setActingState(State s)
        {
            m_actingState = s;
        }
}