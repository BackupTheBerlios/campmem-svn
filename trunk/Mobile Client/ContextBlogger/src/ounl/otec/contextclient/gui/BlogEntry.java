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

/** Stores all data for a blog entry. The blog entry information gotten from the ContextBlogger service is stored in
 *  this class so it can be used to retrieve a specific blog entry information at a later time. For every blog entry that
 *  has multiple categories associated with it, multiple blog entry objects should be created.
 *  @author Tim de Jong
 */
public class BlogEntry
{
    private String m_postID = "";
    private String m_category = "";
    private String m_link = "";
    private String m_title = "";
    private String m_objectID = "";
    private int m_rating = 0;
    
    /** Default Constructor. 
     *  Initialises all fields to their default values.
     */
    public BlogEntry()
    {    
    }
    
    /** Constructor
     *  Initialises all fields to the values given as parameters to it.
     *  @param postID the internal blog entry id this blog entry has. Used to retrieve information about this blog entry
     *  from the database.
     *  @param category the category for this blog entry.
     *  @param link a web-url to the blog entry
     *  @param title the title of the blog entry
     */
    public BlogEntry(String postID, String category, String link, String title)
    {
        m_postID = postID;
        m_category = category;
        m_link = link;
        m_title = title;       
    }
    
    /** Sets the postID for this blog entry.
     *  @param postID a unique postID that is used to identify the blog entry in the database.
     */
    public void setPostID(String postID)
    {
        m_postID = postID.trim();
    }
    
    /** Gets the postID of this blog entry.
     *  @return a unique postID that is used to identify the blog entry in the database.
     */
    public String getPostID()
    {
        return m_postID;
    }
    
    /** Sets the category for this blog entry. 
     *  @param category the blog entry category.
     */
    public void setCategory(String category)
    {
        m_category = category;
    }
    
    /** Returns the category of this blog entry.
     *  @return the category of this blog entry.
     */
    public String getCategory()
    {
        return m_category;
    }
    
    /** Sets the web link to this blog entry.
     *  @param link the weburl to the blog entry.
     */
    public void setLink(String link)
    {
        m_link = link.trim();
    }
    
    /** Gets the web link to this blog entry.
     *  @return the weburl to the blog entry.
     */
    public String getLink()
    {
        return m_link;
    }
    
    /** Sets the title for this blog entry.
     *  @param title the blog entry title.
     */
    public void setTitle(String title)
    {
        m_title = title.trim();
    }
    
    /** Gets the title of the blog entry.
     *  @return the title of the blog entry.
     */
    public String getTitle()
    {
        return m_title;
    }
    
    /** Sets the rating the user gives for this blog entry.
     *  @param rating a rating on an integer rating scale.
     */
    public void setRating(int rating)
    {
        m_rating = rating;
    }
    
    /** Gets the rating the user gave to this blog entry
     *  @return a rating on an integer rating scale.
     */
    public int getRating()
    {
        return m_rating;
    }
    
    /** Sets the identification string of the object this blog entry is coupled to.
     *  @param objectID a unique identifier identifying an object this blog entry is coupled to.
     *  The objectID will be acquired by using the sensors.
     */
    public void setObjectID(String objectID)
    {
        m_objectID = objectID;
    }
    
    /** Gets the ID of the object this blog entry is coupled to
     *  @return a unique identifier identifying an object this blog entry is coupled to.
     */
    public String getObjectID()
    {
       return m_objectID; 
    }
    
    /** Writes a string representation of this blog entry object.
     *  @return a string representation of this blog entry object.
     */
    public String toString()
    {
        return "entry: [postID = " + m_postID + " link= " + m_link + " title= " + m_title + "]";    
    }
}
