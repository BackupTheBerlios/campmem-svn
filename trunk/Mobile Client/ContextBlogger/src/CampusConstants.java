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
import blogger.*;

/**
 *
 * @author Tim de Jong
 *
 */
public class CampusConstants
{
	public static String        K_MOBILE_ID                                 = "123";
    
        public static final String  K_OBJECT_ID_KEY				= "OBJECT_ID";
	public static final String  K_SENSOR_OWNER				= "SENSOR_OWNER";
	public static final String  K_CANVAS_KEY				= "CANVAS_KEY";
        public static final String  K_BLOG_ENTRY_KEY                            = "BLOG_ENTRY_KEY";
	public static final String  K_MENU_ID					= "MENU_ID";
        public static final String  K_QUIT_KEY                                  = "QUIT_KEY";
        public static final String  K_CATEGORY_KEY                              = "CATEGORY_KEY";
                
	public static final String  K_CURRENT_SENSOR				= "CURRENT_SENSOR";
	public static final String  K_LOGIN_STATE				= "LOGIN_STATE";
        public static final String  K_OBJECT_ID_STATE                           = "OBJECT_ID_STATE";
        public static final String  K_ENTRIES_STATE                             = "ENTRIES_STATE";
        public static final String  K_CATEGORY_FILTER_STATE                     = "CATEGORY_FILTER_STATE";
        public static final String  K_CATEGORY_FILTER_MENU_STATE                = "CATEGORY_FILTER_MENU_STATE";
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
	public static final StateFactory    K_STATE_FACTORY                     = new StateFactory();
        public static final BloggerSEI_Stub K_BLOGGER_STUB                      = new BloggerSEI_Stub();
}
