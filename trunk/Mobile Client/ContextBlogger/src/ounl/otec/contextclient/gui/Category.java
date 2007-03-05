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

/** Object representation of the blog categories.
 *  @author Tim
 */
public class Category
{
    private String              m_categoryId;
    private String              m_categoryName;
   
    /** Default Constructor
     */
    public Category()
    {      
        m_categoryId = "";
        m_categoryName = "";
    }
    
    /** Constructor
     *  @param categoryId, the id identifying the category.
     *  @param categoryName, the name of the category used in the blog.
     */
    public Category(String categoryId, String categoryName)
    {
        m_categoryId = categoryId;
        m_categoryName = categoryName;
    }
    
    /** Sets the category id for this category.
     *  @param categoryId, the categoryId acquired from the server.
     */
    public void setId(String categoryId)
    {
        m_categoryId = categoryId;
    }
    
    /** Gets the categoryId for this category
     *  @return the categoryId for this category used on the server
     */
    public String getId()
    {
        return m_categoryId;
    }
    
    /** Sets the name for the category.
     *  @param categoryName, the name of the category used in the blog.
     */
    public void setName(String categoryName)
    {
        m_categoryName = categoryName;
    }
    
    /** Gets the category name.
     *  @return the name of the category used in the blog.
     */
    public String getName()
    {
        return m_categoryName;
    }
}
