package ounl.otec.contextclient.connection;
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

import java.util.Vector;
import blogger.BloggerSEI_Stub;
import ounl.otec.contextclient.state.*;
import ounl.otec.contextclient.connection.statehandler.StateHandlerFactory;

/** Manages all connections to the ContextBlogger Service. And schedules and handles
 *  state retrievals from that service.  
 *  @author Tim de Jong
 */
public class ConnectionManager 
{
    private Thread              m_stateRetrievalThread;
    private boolean             m_isRunning = false;
    private BloggerSEI_Stub     m_bloggerStub = new BloggerSEI_Stub();
    private Vector              m_stateHandlers = new Vector();
    private Vector              m_stateRetrievalQueue = new Vector();
    private Vector              m_stateChangeQueue = new Vector();
    
    /** Constructor
     */
    public ConnectionManager() 
    {        
        addStateHandlers();                
    } 
    
    /** Closes the connection manager, stops the state retrieval thread and closes
     *  all open connections.
     */
    public void close()
    {
        m_stateRetrievalThread.interrupt();
    }
    
    /** Adds a state handler to the list of handlers in the connection manager. 
     *  Each statehandler can be used to retrieve at least the values for one
     *  state from the ContextBlogger Service.
     *  @param stateHandler a stateHandler that will handle the retrieval of one 
     *  or more states.
     */
    public void addStateHandler(IStateHandler stateHandler)
    {
        m_stateHandlers.addElement(stateHandler);
    }
    
    /** Removes a state handler from the list of handlers in the connection manager.
     *  After the state handler has been removed the states handled by this state handler
     *  cannot be longer retrieved from the ContextBlogger Service.
     *  @param stateHandler the stateHandler to remove.
     */
    public boolean removeStateHandler(IStateHandler stateHandler)
    {
        return m_stateHandlers.removeElement(stateHandler);
    }
    
    /** Request a retrieval of the value of a state from the ContextBlogger Service.
     *  The state is added to the stateQueue and scheduled for retrieval via a certain
     *  connection. The state will be handled by its appropriate StateHandler. The 
     *  StateHandler may use some values in the state for this retrieval. When the
     *  state values have been retrieved and are set, state listeners listening to
     *  changes in the state fields can act upon this value change.
     *  @param s the state for which the value retrieval is requested. This 
     *  state may contain a couple of fields that can be used in the retrieval. 
     */
    public void requestStateValueRetrieval(State s)
    {
        m_stateRetrievalQueue.addElement(s);
        startThread();
    }
    
    /** Request a change of a state value on the ContextBlogger Service.
     *  @param s
     */
    public void requestStateValueChange(State s)
    {
        m_stateChangeQueue.addElement(s);
        startThread();
    }
    
    /**
     */
    private void addStateHandlers()
    {
        //add all StateHandlers to this Connection Manager
        StateHandlerFactory factory = new StateHandlerFactory();
        
        Vector handlers = factory.createHandlers(m_bloggerStub);
        for (int i = 0; i < handlers.size(); i++)
        {
            IStateHandler stateHandler = (IStateHandler)handlers.elementAt(i);
            addStateHandler(stateHandler);
        }
    }    
    
    private void startThread()
    {
        if (!m_isRunning)
        {
            //start the thread that will handle all state retrievals
            m_stateRetrievalThread = new Thread(new StateRetrievalRunner());
            m_stateRetrievalThread.run();
        }
    }
    
    /** Private Class that handles all state retrievals in a separate thread so
     *  the main program is not blocked by them.
     *  @author Tim de Jong
     */
    private class StateRetrievalRunner implements Runnable
    {
        /** Constructor
         */
        public StateRetrievalRunner()
        {            
        }
        
        public void run()
        {            
            m_isRunning = true;
            //handle state retrievals
            while (!m_stateRetrievalQueue.isEmpty())
            {
                //pop the first element of the queue to handle the state that
                //has been waiting the longest for retrieval
                State retrieveState = (State)m_stateRetrievalQueue.firstElement();                
                //loop through the state handlers to see if one of them handles the state
                for (int i = 0; i < m_stateHandlers.size(); i++)
                {                        
                    IStateHandler stateHandler = (IStateHandler)m_stateHandlers.elementAt(i);
                    if (stateHandler.isHandling(retrieveState, true))
                    {
                        //if the appropriate state handler has been found, retrieve the state
                        stateHandler.handleState(retrieveState, true);
                        //each state can only be handled by one state handler, therefore if
                        //the state has been retrieved the next state should be handled
                        break;
                    }
                }
                m_stateRetrievalQueue.removeElement(retrieveState);
            }
            
            //handles state changes
            while (!m_stateChangeQueue.isEmpty())
            {
                State changeState = (State)m_stateChangeQueue.firstElement();                
                for (int i = 0; i < m_stateHandlers.size(); i++)
                {
                    IStateHandler stateHandler = (IStateHandler)m_stateHandlers.elementAt(i);
                    if (stateHandler.isHandling(changeState, false))
                    {
                        stateHandler.handleState(changeState, false);
                        break;
                    }                        
                }
                m_stateChangeQueue.removeElement(changeState);
            }                
System.out.println("retrieval queue " + m_stateRetrievalQueue.size());                
System.out.println("change queue " + m_stateChangeQueue.size());               
           m_isRunning = false; 
        }
        
    }
}
