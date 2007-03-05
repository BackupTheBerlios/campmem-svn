package ounl.otec.contextclient.gui;
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
import ounl.otec.contextclient.gui.*;
import ounl.otec.contextclient.sensor.SemaCodeSensor;
import ounl.otec.contextclient.sensor.Sensor;
import ounl.otec.contextclient.sensor.UserEnteredCodeSensor;
import ounl.otec.contextclient.state.ObjectIdStateListener;
import ounl.otec.contextclient.state.State;
import ounl.otec.contextclient.main.CampusMIDlet;
import ounl.otec.contextclient.main.CampusConstants;

/** This class creates the entire VisualMenu hierarchy for the Context Blogger Mobile Client.
 *  By calling the buildLoginMenu function a root menu with an entire navigable menu hierarchy 
 *  beneath it will be created.
 *  @author Tim de Jong
 */
public class MenuFactory
{
	private CampusMIDlet				m_owner;
	private Display					m_ownerDisplay;
	private Hashtable				m_availableSensors;
        private ItemMenu                                m_mainMenu;
        private ObjectIdStateListener                   m_objectIdStateListener;
        
	/** Constructor
         *  @param owner the MIDLet that owns this menu factory.
         *  @param ownerDisplay the display that is used to show all VisualMenus in the hierarchy.
	 */
	public MenuFactory(CampusMIDlet owner, Display ownerDisplay)
	{
                m_owner = owner;
		m_ownerDisplay = ownerDisplay;
                m_objectIdStateListener = new ObjectIdStateListener();
                
		findSensors();
	}

	/** Sets the owner display for this menu factory.
         *  @param ownerDisplay the display that is used to show all VisualMenus in the hierarchy.
	 */
	public void setOwnerDisplay(Display ownerDisplay)
	{
		m_ownerDisplay = ownerDisplay;
	}

	/** Gets the Display that is used to display all VisualMenus in the hierarchy.
         *  @return the owner display.
	 */
	public Display getOwnerDisplay()
	{
		return m_ownerDisplay;
	}

	/** Builds the root menu, the login menu, with all its submenus by calling private
         *  function that construct the hierachy.  
         *  @return the LoginMenu which is the root menu in the hierarchy and the first
         *  menu that will be shown on screen.
	 */
	public LoginMenu buildLoginMenu()
	{
		//compose login menu
		State loginState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_LOGIN_STATE);
		LoginMenu loginMenu = new LoginMenu(m_ownerDisplay, loginState, CampusConstants.K_MOBILE_ID);

		//compose mainMenu		
		m_mainMenu = new ItemMenu(m_ownerDisplay, CampusConstants.K_MIDLET_NAME);                
                
		State state = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_CURRENT_SENSOR);
		VisualMenu sensorListMenu = buildSensorMenu(state);
                m_mainMenu.addMenuItem("Scan object", buildScanObjectMenu(sensorListMenu, state));
		m_mainMenu.addMenuItem("Select sensor", sensorListMenu);
		//add mainMenu after the login menu
		loginMenu.addMenuTransition(CampusConstants.K_SIGNED_IN, m_mainMenu);
		
                LoginFailedMenu loginFailedMenu = new LoginFailedMenu(m_ownerDisplay, "Login Failed");
                loginMenu.addMenuTransition(CampusConstants.K_LOGIN_FAILED, loginFailedMenu);

                //disable mainmenu back command and enable quitcommand
                m_mainMenu.setBackCommandAvailable(false);
                m_mainMenu.setQuitCommandAvailable(true);
                
		return loginMenu;
	}
        
        private VisualMenu buildMainMenu(VisualMenu scanObjectMenu, VisualMenu sensorMenu, VisualMenu blogFilterMenu)
        {
            //compose mainMenu            
            ItemMenu mainMenu = new ItemMenu(m_ownerDisplay, CampusConstants.K_MIDLET_NAME);  
            
            State state = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_CURRENT_SENSOR);
            mainMenu.addMenuItem("Scan New Object", scanObjectMenu);
            mainMenu.addMenuItem("Select Sensor", sensorMenu);
            mainMenu.addMenuItem("View Current Object", blogFilterMenu);
            mainMenu.addMenuItem("Create New Entry", new BlogEntryCreateMenu(m_ownerDisplay, "BlogEntryCreateMenu"));
            //disable mainmenu back command and enable quitcommand
            mainMenu.setBackCommandAvailable(false);
            mainMenu.setQuitCommandAvailable(true);
            
            return mainMenu;
        }
        
	private VisualMenu buildSensorMenu(State state)
	{
		ItemMenu sensorMenu = new ItemMenu(m_ownerDisplay,"Available Sensors");
		//sensorMenu.setSelectCommand(K_SELECT_SENSOR);

		Enumeration e = m_availableSensors.keys();
		while (e.hasMoreElements())
		{
			String sensorName = (String)e.nextElement();
			//if a sensor is chosen the current sensor state has to be updated
			StateChangeMenu stateChangeMenu = new StateChangeMenu(m_ownerDisplay, state, false);
			stateChangeMenu.addStateChange(CampusConstants.K_MENU_ID, sensorName);

			sensorMenu.addMenuItem(sensorName, stateChangeMenu);
		}

		return sensorMenu;
	}

	private void findSensors()
	{
		m_availableSensors = new Hashtable();

		SemaCodeSensor semaCodeSensor = new SemaCodeSensor(m_ownerDisplay);
		//semaCodeSensor.setSensorListener(this);
		//semaCodeSensor.addConfigData(CampusConstants.K_SENSOR_OWNER, this);

		//semaCodeSensor.addConfigData(CampusConstants.K_CANVAS_KEY, m_videoCanvas);

		m_availableSensors.put(semaCodeSensor.getName(), semaCodeSensor);

		UserEnteredCodeSensor userCodeSensor = new UserEnteredCodeSensor(m_ownerDisplay);
		//userCodeSensor.setSensorListener(this);
		//userCodeSensor.addConfigData(CampusConstants.K_SENSOR_OWNER, this);
		m_availableSensors.put(userCodeSensor.getName(), userCodeSensor);

		//m_selectedSensor = semaCodeSensor;
	}

	private VisualMenu buildScanObjectMenu(VisualMenu sensorListMenu, State s)
	{
		//make a transition menu for scanning objects with a certain user-chosen sensor
		SensorTransitionMenu scanObjectMenu = new SensorTransitionMenu(m_ownerDisplay, s, false);
				              
                //construct a state change menu that changes the category filter state to view all objects
                State s2 = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_CATEGORY_FILTER_MENU_STATE);
                StateChangeMenu allEntriesMenu = new StateChangeMenu(m_ownerDisplay,s2);
                allEntriesMenu.addStateChange(CampusConstants.K_MENU_ID, "AllEntryMenu");
                //construct a state change menu that displays a menu to view the available category filters           
                StateChangeMenu entriesByCategoryMenu = new StateChangeMenu(m_ownerDisplay,s2);//not right yet, change!
                entriesByCategoryMenu.addStateChange(CampusConstants.K_MENU_ID, "EntriesByCategory");

                ItemMenu blogFilterMenu = new ItemMenu(m_ownerDisplay, "View Blog Entries");
                
                
                blogFilterMenu.addMenuItem("All entries", allEntriesMenu);
                blogFilterMenu.addMenuItem("Entries by category", entriesByCategoryMenu);            

                //construct a blog entry list menu that shows all blog entries
                BlogEntryListMenu entryMenu = new BlogEntryListMenu(m_ownerDisplay, "Blog Entries for Object");                
                //construct a state transition menu that listens for the category filter state to change
                //depending on what filter is chosen a specific menu will be chosen
                StateTransitionMenu categoryFilterTransitionMenu = new StateTransitionMenu(m_ownerDisplay,s2, true);
                blogFilterMenu.addChild(categoryFilterTransitionMenu);
                         
                        
                CategoryMenu categoryMenu = new CategoryMenu(m_ownerDisplay,"Available Categories");
                //build category filter menu
                BlogEntryListMenu categoryEntryMenu = new BlogEntryListMenu(m_ownerDisplay, "Blog Entries for Category",true);
                State categoryFilterState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_CATEGORY_FILTER_STATE);
                categoryFilterState.addStateListener(categoryEntryMenu);
                categoryMenu.addChild(categoryEntryMenu);
                
                categoryFilterTransitionMenu.addMenuTransition("AllEntryMenu", entryMenu);
                categoryFilterTransitionMenu.addMenuTransition("EntriesByCategory", categoryMenu);
            
                //add transition menu's for each sensor, so when a certain sensor has been chosen it will also be displayed
		//when scan object has been chosen
		Enumeration e = m_availableSensors.elements();                
                int i = 0;
                
                //add two stateListeners to the ObjectIDState
                State objectIDState = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_OBJECT_ID_STATE);
                objectIDState.addStateListener(entryMenu);
                objectIDState.addStateListener(categoryEntryMenu);
                
                while (e.hasMoreElements())
		{			
                        Sensor sensor = (Sensor)e.nextElement();
                        sensor.setValue(CampusConstants.K_MENU_ID, "BlogFilterMenu");                                                              
                        sensor.addStateListener(m_objectIdStateListener);
                        
                        StateTransitionMenu stateTransitionMenu = new StateTransitionMenu(m_ownerDisplay, sensor, true);
                        stateTransitionMenu.addMenuTransition("BlogFilterMenu", blogFilterMenu);
                        
			VisualMenu sensorMenu = sensor.getSensorMenu();
                        sensorMenu.addChild(blogFilterMenu);//not sure whether this is necessary...
			scanObjectMenu.addSensorTransition(sensor);
                        
                        //set the first sensor as the default selected one
                        if (i == 0)
                        {
                            s.setValue(CampusConstants.K_MENU_ID, sensor.getName());
                        }
                        i++;
		}
                //change the parent of the blogfilter menu to the main menu, so menu traversal goes back to main menu
                blogFilterMenu.setParent(buildMainMenu(scanObjectMenu, sensorListMenu, blogFilterMenu));
                
		return scanObjectMenu;
	}
         
        
	/*private VisualMenu buildWaitingScreen()
	{
		Form frm = new Form("Waiting...");
		frm.setTicker(new Ticker("Please wait..."));
		return frm;
	}*/
}