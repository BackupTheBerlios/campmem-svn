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
import javax.microedition.lcdui.*;
/**
 * @author Tim
 *
 */
public class UserEnteredCodeSensor
					extends Sensor
					implements CommandListener
{
	private TextBox			m_codeBox;
	private UserEnteredSensorMenu	m_sensorMenu;

	public UserEnteredCodeSensor(Display display)
	{
		super("User Entered Code", display);
		m_sensorMenu = new UserEnteredSensorMenu(this, display);
	}

	/* (non-Javadoc)
	 * @see ounl.otec.CampusMemories.Mobile.Sensor#startSensor()
	 */
	public void startSensor()
	{
	}

	/* (non-Javadoc)
	 * @see ounl.otec.CampusMemories.Mobile.Sensor#stopSensor()
	 */
	public void stopSensor()
	{
	}

	/**
	 */
	public void commandAction(Command c, Displayable s)
	{
		//if code entered, send update information to sensorlisteners
		if (c == CampusConstants.K_CAPTURE_COMMAND)
		{
			//get the entered code value
			String code = m_codeBox.getString();
			//add the entered code as object id to sensor data
			this.setValue(CampusConstants.K_OBJECT_ID_KEY, code);
			//notify the sensor listeners of the update
			//notifySensorUpdate();                                                
		}
		else if (c == CampusConstants.K_BACK_COMMAND)
		{
			m_sensorMenu.commandAction(c, s);
		}
	}

	/**
	 */
	public VisualMenu getSensorMenu()
	{
		return m_sensorMenu;
	}

	/**
	 */
	public StateChangeMenu getSensorConfigMenu()
	{
		return null;
	}

	/**
	 */
	class UserEnteredSensorMenu extends VisualMenu
	{
		CommandListener             m_owner;

		public UserEnteredSensorMenu(CommandListener owner, Display ownerDisplay)
		{
			super(ownerDisplay);
			m_owner = owner;
		}

		public Displayable getDisplayable()
		{
			//create textbox for entering code information
			m_codeBox = new TextBox("Enter Object ID", "",10,TextField.ANY);
			m_codeBox.setCommandListener(m_owner);
			//set possible commands for videoCanvas
			m_codeBox.addCommand(CampusConstants.K_CAPTURE_COMMAND);
			m_codeBox.addCommand(CampusConstants.K_BACK_COMMAND);

			return m_codeBox;
		}
	}
}
