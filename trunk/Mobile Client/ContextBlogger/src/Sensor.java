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
import java.util.*;
import javax.microedition.lcdui.*;

/** Abstract class implementing some basic functionality and a basic interface for
 *  all mobile device sensors.
 *  @author Tim de Jong
 */
public abstract class Sensor extends State
{	
	private Display				m_display;
	private String				m_sensorName;
	protected Hashtable                     m_configData = new Hashtable();

	/** Constructor
         *  @param sensorName the name of the sensor.
         *  @param display the display that will be used to show a graphical menu during
         *  sensor operation.
	 */
	public Sensor(String sensorName, Display display)
	{
		super(sensorName);
		m_sensorName = sensorName;
		m_display = display;
	}

	/** Adds a ISensorListener to the list of state listeners of this Sensor.
         *  The sensor listener that is added to the sensor is informed about all
         *  sensor changes and all sensor errors.
         *  @param listener the sensor listener to be attached to this sensor.
	 */
	public void addSensorListener(ISensorListener listener)
	{		
                addStateListener(listener);
	}

	/** Notifies the sensor listeners that this sensor's values have been updated.
	 */
	public void notifySensorUpdate()
	{
		notifyUpdate();
	}

	/** Notifies all sensor listeners that an error occured during sensor execution.
         *  @param e the exception that occured during sensor execution.
	 */
	public void notifySensorError(Exception e)
	{
		Vector listeners = this.getStateListeners();
                //loop through the list of state listener
                for (int i = 0; i < listeners.size(); i++)
                {
                    //find out which of them are sensor listeners
                    Object listener = listeners.elementAt(i);
                    if (listener instanceof ISensorListener)
                    {
                        //send the error message to all sensor listeners in the list of state listeners.
                        ISensorListener sensorListener = (ISensorListener)listener;
                        sensorListener.sensorError(e);
                    }
                }
	}

	/**
	 */
	public void addConfigData(String configKey, Object configData)
	{
		m_configData.put(configKey, configData);
	}

	/** Returns the human readable name of this sensor used in the graphical
         *  user interface.
         *  @return the name of this sensor.
	 */
	public String getName()
	{
		return m_sensorName;
	}

	/** Returns the display that this sensor uses to display any graphical
         *  user interface during sensor execution. This method will be mostly 
         *  used by the subclasses to get the display object stored in this class.
         *  @return the display used to show the menu.
	 */
	public Display getDisplay()
	{
		return m_display;
	}

        /** Will be implemented in the subclasses to start sensor operation.
         */
	public abstract void startSensor();
	
        /** Will be implemented in the subclassses to stop sensor operation.
         */
        public abstract void stopSensor();
        
        /** Will be implemented in the subclasses to provide a graphical representation
         *  during sensor operation.
         *  @return a VisualMenu providing a GUI during sensor operation.
         */
	public abstract VisualMenu getSensorMenu();
        
        /** Will be implemented in the subclasses for sensors that need extra configuration
         *  before they can start scanning. This method will return a GUI for configuring
         *  the sensor.
         *  @return a config menu for the sensor.
         */
	public abstract StateChangeMenu getSensorConfigMenu();
}
