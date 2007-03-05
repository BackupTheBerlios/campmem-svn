package ounl.otec.contextclient.main;
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
import ounl.otec.contextclient.state.StateFactory;
import ounl.otec.contextclient.connection.ConnectionManager;

/** Constant class of the ContextBlogger Mobile Client, stores all constants in one central location.
 *  @author Tim de Jong
 */
public class CampusConstants
{	
        //some standard constants for the Mobile Client
        public static final String  K_MIDLET_NAME                               = "ContextBlogger";
        public static String        K_MOBILE_ID;
        public static String        K_MOBILE_DEFAULT_ID                         = "123";
        //url values to two services running on our berlios server
        public static String        K_BERLIOS_SERVICE_URL                       = "http://campmem.berlios.de/ServiceURL.php";
        public static String        K_BERLIOS_UPDATES_URL                       = "";
        public static String        K_BERLIOS_VERSION_URL                       = K_BERLIOS_UPDATES_URL + "?version=true";
        //these are some manufacturer specific property identifiers found in forums, use to find the IMEI code, identifying
        //the mobile device, and with that the user
        public static final String[]      
                                    K_IMEI_MANUFACTURER                         =   { 
                                                                                        "com.nokia.mid.imei",       //for nokia phones
                                                                                        "com.nokia.IMEI",           //different option for nokia phones
                                                                                        "com.sonyericsson.IMEI",    //for sony ericsson
                                                                                        "com.siemens.IMEI",         //for siemens phones
                                                                                        "device.imei",              //for motorola phones
                                                                                        "phone.imei",               //not sure if this works, but we can try
                                                                                        "IMEI"                      //also found in forums
                                                                                    };
        
        public static final String  K_OBJECT_ID_KEY				= "OBJECT_ID";
	public static final String  K_SENSOR_OWNER				= "SENSOR_OWNER";
        public static final String  K_SENSOR_DATA_KEY                           = "SENSOR_DATA_KEY";
	public static final String  K_CANVAS_KEY				= "CANVAS_KEY";
        public static final String  K_BLOG_ENTRY_KEY                            = "BLOG_ENTRY_KEY";
	public static final String  K_MENU_ID					= "MENU_ID";
        public static final String  K_QUIT_KEY                                  = "QUIT_KEY";
        public static final String  K_CATEGORY_KEY                              = "CATEGORY_KEY";
        public static final String  K_CATEGORIES_KEY                            = "CATEGORIES_KEY";       
        public static final String  K_COMMENT_KEY                               = "COMMENT_KEY";
        public static final String  K_RESULT_KEY                                = "RESULT_KEY";
        public static final String  K_TITLE_KEY                                 = "TITLE_KEY";
        public static final String  K_BODY_KEY                                  = "BODY_KEY";
        public static final String  K_RATE_KEY                                  = "RATE_KEY";
        public static final String  K_ITEM_NAMES_KEY                            = "ITEM_NAMES_KEY";
        public static final String  K_ITEM_VALUES_KEY                           = "ITEM_VALUES_KEY";
        
	public static final String  K_CURRENT_SENSOR				= "CURRENT_SENSOR";
	public static final String  K_LOGIN_STATE				= "LOGIN_STATE";
        public static final String  K_OBJECT_ID_STATE                           = "OBJECT_ID_STATE";
        public static final String  K_ENTRIES_STATE                             = "ENTRIES_STATE";
        public static final String  K_CATEGORY_LIST_STATE                       = "CATEGORY_LIST_STATE";
        public static final String  K_CATEGORY_FILTER_STATE                     = "CATEGORY_FILTER_STATE";
        public static final String  K_CATEGORY_FILTER_MENU_STATE                = "CATEGORY_FILTER_MENU_STATE";
        public static final String  K_COMMENT_STATE                             = "COMMENT_STATE";
        public static final String  K_ENTRY_CREATE_STATE                        = "ENTRY_CREATE_STATE";
        public static final String  K_RATING_STATE                              = "RATING_STATE";
        public static final String  K_QUIT_STATE                                = "QUIT_STATE";        
        //login constants
	public static final String  K_INIT_LOGIN                                = "INIT_LOGIN";
	public static final String  K_LOGGING_IN                                = "LOGGING_IN";
	public static final String  K_SIGNED_IN                                 = "SIGNED_IN";
	public static final String  K_LOGIN_FAILED                              = "LOGIN_FAILED";
	//standard command codes
	public static final Command K_CAPTURE_COMMAND                           = new Command("Capture", Command.ITEM, 1);
	public static final Command K_BACK_COMMAND				= new Command("Back", Command.BACK, 1);
        public static final Command K_SUBMIT_COMMAND                            = new Command("Submit", Command.SCREEN, 2);
	public static final Command K_EXIT_COMMAND				= new Command("Exit", Command.SCREEN, 2);
        //global variables
	public static final StateFactory        K_STATE_FACTORY                 = new StateFactory();        
        public static final ConnectionManager   K_CONNECTION_MANAGER            = new ConnectionManager();
}
