/*
 * BlogEntryActionMenu.java
 *
 * Created on 31 januari 2007, 16:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import javax.microedition.lcdui.*;

/**
 *
 * @author Tim
 */
public class BlogEntryActionMenu extends ItemMenu
{
    private BlogEntry               m_entry;
    
    /** Creates a new instance of BlogEntryActionMenu 
     *  @param
     *  @param     
     */
    public BlogEntryActionMenu (Display ownerDisplay, BlogEntry entry) 
    {
        super(ownerDisplay, entry.getTitle());    
        m_entry = entry;
        buildEntryMenu(entry);
    }
    
    /**
     *  @param
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
    
    /**
     *  @return
     */
    public BlogEntry getEntry()
    {
        return m_entry;
    }
    
    /**
     *  @param 
     */
    public void setEntry(BlogEntry entry)
    {
        m_entry = entry;
    }
}
