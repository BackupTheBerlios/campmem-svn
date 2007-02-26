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

/** The BlogEntryActionMenu is an ItemMenu that displays all possible actions that can be
 *  carried out on a blog entry. 
 *  @author Tim de Jong
 */
public class BlogEntryActionMenu extends ItemMenu
{
    private BlogEntry               m_entry;
    
    /** Creates a new instance of BlogEntryActionMenu 
     *  @param ownerDisplay the display object that will be used to display this menu.
     *  @param entry the blog entry the actions should or could be carried out upon.    
     */
    public BlogEntryActionMenu (Display ownerDisplay, BlogEntry entry) 
    {
        super(ownerDisplay, entry.getTitle());    
        m_entry = entry;
        buildEntryMenu(entry);
    }
    
    /** Builds the blog entry menu, by creating an item menu with all possible actions.
     *  @param entry, the blog entry this menu is created for.
     */
    private void buildEntryMenu(BlogEntry entry)
    {
        //create menu item for viewing blog entries
        State s = CampusConstants.K_STATE_FACTORY.getState(CampusConstants.K_ENTRIES_STATE);
        
        StateChangeMenu entryChangeMenu = new StateChangeMenu(getOwnerDisplay(),s);        
        entryChangeMenu.addStateChange(CampusConstants.K_BLOG_ENTRY_KEY, entry);       
        addMenuItem("View entry",entryChangeMenu);
        
        //create a menu for rating blog entries
        BlogEntryRateMenu rateMenu = new BlogEntryRateMenu(getOwnerDisplay(), entry);
        this.addMenuItem("Rate entry", rateMenu);
        
        //creat a menu for adding comments to blog entries
        BlogEntryCommentMenu commentMenu = new BlogEntryCommentMenu(getOwnerDisplay(), entry);
        this.addMenuItem("Comment", commentMenu);
    }
    
    /** Gets the entry this menu can carried out actions upon.     
     *  @return the entry this menu acts on.
     */
    public BlogEntry getEntry()
    {
        return m_entry;
    }
    
    /** Sets the blog entry that can be acted upon by this menu.
     *  @param entry the blog entry the actions in this menu are meant for.
     */
    public void setEntry(BlogEntry entry)
    {
        m_entry = entry;
    }
}
