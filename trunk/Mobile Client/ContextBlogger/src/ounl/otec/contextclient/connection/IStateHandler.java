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

import ounl.otec.contextclient.state.*;

/** Specifies the interface for all StateHandler classes. StateHandler classes are
 *  used in the ConnectionManager to implement State specific retrievals from the
 *  ContextBlogger Service. If the StateHandler recognises it's the suitable handler
 *  for a certain state, the state can be retrieved by remotely calling ContextBlogger
 *  Server methods specific for that state.
 *  @author Tim de Jong
 */
public interface IStateHandler 
{   
    /** Sniffer method that should be implemented by the subclasses to return 
     *  whether the StateHandler handles the state given by parameter s. If the
     *  StateHandler understands how to handle the state this method should return
     *  true. If not, it should return false.
     *  @param s the state that has to be handled. The handler should check whether
     *  it handles the State identified by s.
     *  @param retrieve a boolean indicating if the state indicated by s should be
     *  retrieved or changed. If the retrieve parameters is true, it indicates that
     *  s should be retrieved. If it is false, s is considered as a change state.
     *  StateListener implementing this interface may use the boolean parameter 
     *  also to decide whether they handle state s or not.
     *  @return true if the StateHandler handles s, false otherwise.
     */
    public boolean isHandling(State s, boolean retrieve);
    
    /** Should be implemented to perform the actual state retrieval and might 
     *  implemented to perform some logic on the values in the fields s already has.
     *  These values might then be used in the retrieval.
     *  @param s the state which value has to be retrieved. The values in s might be
     *  used in the retrieval. After this method exits, the state s should have been changed
     *  by calling its setValue method; doing this will also inform the state listeners listening
     *  to that state that it has been changed.
     *  @param retrieve a boolean indicating if the state indicated by s should be
     *  retrieved or changed. If the retrieve parameters is true, it indicates that
     *  s should be retrieved. If it is false, s is considered as a change state.       
     */
    public void handleState(State s, boolean retrieve);
}
