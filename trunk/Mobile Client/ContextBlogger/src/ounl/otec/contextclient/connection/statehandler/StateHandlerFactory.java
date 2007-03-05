package ounl.otec.contextclient.connection.statehandler;
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
import blogger.BloggerSEI_Stub;
import java.util.Vector;
/**
 *
 * @author Tim
 */
public class StateHandlerFactory 
{
    
    /**
     */
    public StateHandlerFactory() 
    {
    }
    
    /**
     */
    public Vector createHandlers(BloggerSEI_Stub bloggerStub)
    {
        Vector handlers = new Vector();
        //add all handlers needed to the vector
        handlers.addElement(new BlogEntryCreateHandler(bloggerStub));
        handlers.addElement(new BlogListRetrieveHandler(bloggerStub));
        handlers.addElement(new BlogRatingHandler(bloggerStub));
        handlers.addElement(new CategoryListRetrieveHandler(bloggerStub));
        handlers.addElement(new CommentStateHandler(bloggerStub));
        handlers.addElement(new LoginStateHandler(bloggerStub));
        
        return handlers;
    }
    
}
