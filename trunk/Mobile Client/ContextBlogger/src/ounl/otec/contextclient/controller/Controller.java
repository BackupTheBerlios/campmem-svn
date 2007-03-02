package ounl.otec.contextclient.controller;
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
import java.util.Stack;
import java.util.Vector;
import ounl.otec.contextclient.sensor.ISensorListener;
import ounl.otec.contextclient.state.State;
import ounl.otec.contextclient.actuator.Actuator;

/** The Controller class is responsible for all logic between sensors and actuators on the mobile client.   
 *  @author Tim de Jong
 */
public abstract class Controller implements ISensorListener, Runnable
{
	private Stack				m_stateChanges = new Stack();
	private Vector				m_controllerLogic = new Vector();
	private Vector				m_actuators = new Vector();

	/** Constructor
	 */
	public Controller()
	{
	}

	/**
	 */
	public void run()
	{
		while (true)
		{
			if (!m_stateChanges.empty())
			{
				State s = (State)m_stateChanges.pop();
				//process the state first in the controller logic
				for (int i = 0; i < m_controllerLogic.size(); i++)
				{
					IControllerLogic logic = (IControllerLogic)m_controllerLogic.elementAt(i);
					if (logic.isHandling(s))
					{
						//this controller logic handles State s
						s = logic.handle(s);
					}
				}
				//pass the processed state through to the actuators
				for (int i = 0; i < m_actuators.size(); i++)
				{
					Actuator actuator = (Actuator) m_actuators.elementAt(i);
					if (actuator.isActingFor(s))
					{
						actuator.act(s);
					}
				}
			}
		}
	}

	/**
	 */
	public void stateUpdated(State s)
	{
		m_stateChanges.push(s);
	}

	/**
	 */
	public void sensorError(Exception e)
	{
	}

	/**
	 */
	public void addControllerLogic(IControllerLogic logic)
	{
		m_controllerLogic.addElement(logic);
	}

	/**
	 */
	public boolean removeControllerLogic(IControllerLogic logic)
	{
		return m_controllerLogic.removeElement(logic);
	}

	/**
	 */
	public void addActuator(Actuator actuator)
	{
		m_actuators.addElement(actuator);
	}

	/**
	 */
	public boolean removeActuator(Actuator actuator)
	{
		return m_actuators.removeElement(actuator);
	}
}