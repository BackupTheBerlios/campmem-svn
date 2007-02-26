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
import java.util.Hashtable;

/** A menu that transfers control to the sensor chosen. The sensor chosen is
 *  given by a state that can be changed in the sensor settings menu.
 *  @author Tim de Jong
 */
public class SensorTransitionMenu extends StateTransitionMenu
{
    private Hashtable m_sensors = new Hashtable();
    private Sensor m_currentSensor;
    
    /** Constructor
     *  @param ownerDisplay the display used to display the sensor menu when the
     *  sensor is active.
     *  @param transitionState the state that is listened to and that will be used
     *  to choose the sensor menu the control will be transfered to.
     *  @param active indicates whether the sensor menu is active. True, if control
     *  should automatically be transfered to the submenu, false otherwise.
     */
    public SensorTransitionMenu(Display ownerDisplay, State transitionState, boolean active) 
    {
        super(ownerDisplay, transitionState, active);
    }
    
    /** Adds a sensor as a possible transition from this menu. The sensor name will be used
     *  as the name for the transition. If the transitionState is changed so that one of the
     *  possible sensor transitions is chosen, the SensorTransitionMenu will transfer control
     *  to that sensor.
     *  @param s the sensor that will be added as a sensor transition for this menu.
     */
    public void addSensorTransition(Sensor s)
    {
        addMenuTransition(s.getName(),s.getSensorMenu());
        m_sensors.put(s.getName(), s);
    }
    
    /** Called when the one of the states this menu listens to has been changed. If the state
     *  changed is the transition state the control is transfered to the sensor represented
     *  by the state value.
     *  @param s the state that has been changed.
     */
    public void stateUpdated(State s)
    {        
        super.stateUpdated(s);
        
        String sensorType = (String)s.getValue(CampusConstants.K_MENU_ID);     
        if (sensorType != null)
        {
            m_currentSensor = (Sensor)m_sensors.get(sensorType);                    
        }       
    }
    
    /** Gets the displayable with the graphical representation of this menu. Because
     *  the StateTransitionMenu normally has no graphical representation, the chosen
     *  sensor's menu GUI is returned instead. As a side-effect this method starts the
     *  sensor that has been chosen.
     *  @return the displayable representing the chosen sensor's sensor menu.
     */
    public Displayable getDisplayable()
    {
        m_currentSensor.startSensor();
        return m_currentSensor.getSensorMenu().getDisplayable();
    }
}
