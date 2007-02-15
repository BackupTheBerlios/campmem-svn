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
/*
 * SensorTransitionMenu.java
 *
 * Created on 19 januari 2007, 11:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import javax.microedition.lcdui.*;
import java.util.Hashtable;

/**
 *
 * @author Tim
 */
public class SensorTransitionMenu extends StateTransitionMenu
{
    private Hashtable m_sensors = new Hashtable();
    private Sensor m_currentSensor;
    
    /** Creates a new instance of SensorTransitionMenu */
    public SensorTransitionMenu(Display ownerDisplay, State transitionState, boolean active) 
    {
        super(ownerDisplay, transitionState, active);
    }
    
    /**
     */
    public void addSensorTransition(Sensor s)
    {
        addMenuTransition(s.getName(),s.getSensorMenu());
        m_sensors.put(s.getName(), s);
    }
    
    /**
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
    
    public Displayable getDisplayable()
    {
        m_currentSensor.startSensor();
        return m_currentSensor.getSensorMenu().getDisplayable();
    }
}
