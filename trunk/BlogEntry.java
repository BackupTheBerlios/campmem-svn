/*
 * BlogEntry.java
 *
 * Created on 31 januari 2007, 16:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Tim
 */
public class BlogEntry
{
    private String m_postID = "";
    private String m_category = "";
    private String m_link = "";
    private String m_title = "";
    private String m_objectID = "";
    private int m_rating = 0;
    /**
     */
    public BlogEntry()
    {    
    }
    
    /**
     */
    public BlogEntry(String postID, String category, String link, String title)
    {
        m_postID = postID;
        m_category = category;
        m_link = link;
        m_title = title;       
    }
    
    /**
     */
    public void setPostID(String postID)
    {
        m_postID = postID.trim();
    }
    
    /**
     */
    public String getPostID()
    {
        return m_postID;
    }
    
    /**
     */
    public void setCategory(String category)
    {
        m_category = category;
    }
    
    /**
     */
    public String getCategory()
    {
        return m_category;
    }
    
    /**
     */
    public void setLink(String link)
    {
        m_link = link.trim();
    }
    
    /**
     */
    public String getLink()
    {
        return m_link;
    }
    
    /**
     */
    public void setTitle(String title)
    {
        m_title = title.trim();
    }
    
    /**
     */
    public String getTitle()
    {
        return m_title;
    }
    
    /**
     */
    public void setRating(int rating)
    {
        m_rating = rating;
    }
    
    /**
     */
    public int getRating()
    {
        return m_rating;
    }
    
    /**
     */
    public void setObjectID(String objectID)
    {
        m_objectID = objectID;
    }
    
    /**
     */
    public String getObjectID()
    {
       return m_objectID; 
    }
    
    public String toString()
    {
        return "entry: [postID = " + m_postID + " link= " + m_link + " title= " + m_title + "]";
    
    }
}
