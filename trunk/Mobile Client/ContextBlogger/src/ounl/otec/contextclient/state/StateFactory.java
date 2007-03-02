package ounl.otec.contextclient.state;
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
import java.util.Hashtable;

/** Class responsible for creating all states and adds functionality to query for
 *  certain states.
 *  @author Tim de Jong
 */
public class StateFactory
{
	private Hashtable				m_states = new Hashtable();

	/** Constructor.
	 */
	public StateFactory()
	{
	}

	/** Creates a new state, with name given by stateName.
         *  @param stateName, the name of the new state to be created.
         *  @return a new state with a name given by stateName.
	 */
	private State createState(String stateName)
	{
		State newState = new State(stateName);
		m_states.put(stateName, newState);

		return newState;
	}

	/** Creates a new state, with name given by stateName and attaches a 
         *  certain statelistener to this state.
         *  @param stateName the name of the new state to be created.
         *  @param listener an object implementing the IStateListener interface
         *  that will be attached to the new State object and will be informed of
         *  all changes that appear in the property of the states.
         *  @return a new state with a name given by stateName.
	 */
	public State createState(String stateName, IStateListener listener)
	{
		State s = createState(stateName);
		s.addStateListener(listener);

		return s;
	}

	/** Checks if a certain state "stateName" exists, if so it returns that state,
         *  if not it returns a new state "stateName"
         *  @param stateName the name of the new state of the state to be found
         *  among the already create states.
         *  @return a new State of an already existing State with name stateName.
	 */
	public State getState(String stateName)
	{
            State s = null;
            if (m_states.containsKey(stateName))
            {
                s = (State)m_states.get(stateName);
            }
            else
            {
                s = createState(stateName);
            }
            return s;
	}
}