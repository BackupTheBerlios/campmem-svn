package ounl.otec.contextclient.state;
import ounl.otec.contextclient.state.IStateListener;

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
import ounl.otec.contextclient.main.CampusConstants;
/** A StateListener that should be attached to States that change the current object id value.
 *  This StateListener is attached to sensors that find the current object id. When the state
 *  representing the sensor value has changed, this StateListener makes sure the value of the
 *  K_OBJECT_ID_STATE is synchronised, so it always represents the object id of the object that
 *  was scanned the last. 
 *  @author Tim
 */
public class ObjectIdStateListener implements IStateListener
{
    private State m_objectIDState;
    
    /** Constructor
     *  Gets the K_OBJECT_ID state that has to be updated by each change event this StateListener 
     *  listens to. 
     */
    public ObjectIdStateListener() 
    {
        m_objectIDState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_OBJECT_ID_STATE);
    }
    
    /** Called whenever a State this StateListener is attached to has been changed.
     *  @param s the State which value has changed.
     */
    public void stateUpdated(State s)
    {
        //update the objectID state to the current
        Object newObjectID = s.getValue(CampusConstants.K_OBJECT_ID_KEY);       
        m_objectIDState.setValue(CampusConstants.K_OBJECT_ID_KEY, newObjectID);
    }
}
