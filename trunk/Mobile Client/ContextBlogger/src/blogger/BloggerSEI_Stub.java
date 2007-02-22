// This class was generated by 172 StubGenerator.
// Contents subject to change without notice.
// @generated

package blogger;

import javax.xml.rpc.JAXRPCException;
import javax.xml.namespace.QName;
import javax.microedition.xml.rpc.Operation;
import javax.microedition.xml.rpc.Type;
import javax.microedition.xml.rpc.ComplexType;
import javax.microedition.xml.rpc.Element;

public class BloggerSEI_Stub implements blogger.BloggerSEI, javax.xml.rpc.Stub {
	private String[] _propertyNames;
	private Object[] _propertyValues;

	public BloggerSEI_Stub() {
		_propertyNames = new String[] {ENDPOINT_ADDRESS_PROPERTY};
		_propertyValues = new Object[] {"http://192.168.0.122:8080/CM1/Blogger"};
	}

	public void _setProperty(String name, Object value) {
		int size = _propertyNames.length;
		for (int i = 0; i < size; ++i) {
			if (_propertyNames[i].equals(name)) {
				_propertyValues[i] = value;
				return;
			}
		}
		// Need to expand our array for a new property
		String[] newPropNames = new String[size + 1];
		System.arraycopy(_propertyNames, 0, newPropNames, 0, size);
		_propertyNames = newPropNames;
		Object[] newPropValues = new Object[size + 1];
		System.arraycopy(_propertyValues, 0, newPropValues, 0, size);
		_propertyValues = newPropValues;

		_propertyNames[size] = name;
		_propertyValues[size] = value;
	}

	public Object _getProperty(String name) {
		for (int i = 0; i < _propertyNames.length; ++i) {
			if (_propertyNames[i].equals(name)) {
				return _propertyValues[i];
			}
		}
		if (ENDPOINT_ADDRESS_PROPERTY.equals(name) || USERNAME_PROPERTY.equals(name) || PASSWORD_PROPERTY.equals(name)) {
			return null;
		}
		if (SESSION_MAINTAIN_PROPERTY.equals(name)) {
			return new java.lang.Boolean(false);
		}
		throw new JAXRPCException("Stub does not recognize property: "+name);
	}

	protected void _prepOperation(Operation op) {
		for (int i = 0; i < _propertyNames.length; ++i) {
			op.setProperty(_propertyNames[i], _propertyValues[i].toString());
		}
	}

	// 
	//  Begin user methods
	// 

	public boolean login(java.lang.String string_1) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[1];
		inputObject[0] = string_1;

		Operation op = Operation.newInstance(_qname_wsdl_Login, _type_Login, _type_LoginResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		boolean result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = ((java.lang.Boolean)resultObj2).booleanValue();
		return result;
	}

	public java.lang.String post(java.lang.String string_1, java.lang.String string_2, java.lang.String string_3, java.lang.String string_4, java.lang.String string_5) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[5];
		inputObject[0] = string_1;
		inputObject[1] = string_2;
		inputObject[2] = string_3;
		inputObject[3] = string_4;
		inputObject[4] = string_5;

		Operation op = Operation.newInstance(_qname_wsdl_Post, _type_Post, _type_PostResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = (java.lang.String)resultObj2;
		return result;
	}

	public java.lang.String getCategories(java.lang.String string_1) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[1];
		inputObject[0] = string_1;

		Operation op = Operation.newInstance(_qname_wsdl_getCategories, _type_getCategories, _type_getCategoriesResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = (java.lang.String)resultObj2;
		return result;
	}

	public java.lang.String getRating(java.lang.String string_1, java.lang.String string_2, java.lang.String string_3) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[3];
		inputObject[0] = string_1;
		inputObject[1] = string_2;
		inputObject[2] = string_3;

		Operation op = Operation.newInstance(_qname_wsdl_getRating, _type_getRating, _type_getRatingResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = (java.lang.String)resultObj2;
		return result;
	}

	public java.lang.String postComment(java.lang.String string_1, java.lang.String string_2, java.lang.String string_3) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[3];
		inputObject[0] = string_1;
		inputObject[1] = string_2;
		inputObject[2] = string_3;

		Operation op = Operation.newInstance(_qname_wsdl_postComment, _type_postComment, _type_postCommentResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = (java.lang.String)resultObj2;
		return result;
	}

	public java.lang.String postMedia(java.lang.String string_1) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[1];
		inputObject[0] = string_1;

		Operation op = Operation.newInstance(_qname_wsdl_postMedia, _type_postMedia, _type_postMediaResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = (java.lang.String)resultObj2;
		return result;
	}

	public java.lang.String registerUser(java.lang.String string_1, java.lang.String string_2, java.lang.String string_3) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[3];
		inputObject[0] = string_1;
		inputObject[1] = string_2;
		inputObject[2] = string_3;

		Operation op = Operation.newInstance(_qname_wsdl_registerUser, _type_registerUser, _type_registerUserResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = (java.lang.String)resultObj2;
		return result;
	}

	public java.lang.String retreive_blog(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[2];
		inputObject[0] = string_1;
		inputObject[1] = string_2;

		Operation op = Operation.newInstance(_qname_wsdl_retreive_blog, _type_retreive_blog, _type_retreive_blogResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = (java.lang.String)resultObj2;
		return result;
	}

	public java.lang.String retrieve_blogList(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[2];
		inputObject[0] = string_1;
		inputObject[1] = string_2;

		Operation op = Operation.newInstance(_qname_wsdl_retrieve_blogList, _type_retrieve_blogList, _type_retrieve_blogListResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = (java.lang.String)resultObj2;
		return result;
	}

	public java.lang.String setRating(java.lang.String string_1, java.lang.String string_2, java.lang.String string_3, int int_4) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[4];
		inputObject[0] = string_1;
		inputObject[1] = string_2;
		inputObject[2] = string_3;
		inputObject[3] = new java.lang.Integer(int_4);

		Operation op = Operation.newInstance(_qname_wsdl_setRating, _type_setRating, _type_setRatingResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object resultObj2 = ((Object[])resultObj)[0];
		result = (java.lang.String)resultObj2;
		return result;
	}
	// 
	//  End user methods
	// 

	protected static final QName _qname_String_1 = new QName("", "String_1");
	protected static final QName _qname_String_2 = new QName("", "String_2");
	protected static final QName _qname_String_3 = new QName("", "String_3");
	protected static final QName _qname_String_4 = new QName("", "String_4");
	protected static final QName _qname_String_5 = new QName("", "String_5");
	protected static final QName _qname_int_4 = new QName("", "int_4");
	protected static final QName _qname_result = new QName("", "result");
	protected static final QName _qname_Login = new QName("urn:Blogger/types", "Login");
	protected static final QName _qname_LoginResponse = new QName("urn:Blogger/types", "LoginResponse");
	protected static final QName _qname_Post = new QName("urn:Blogger/types", "Post");
	protected static final QName _qname_PostResponse = new QName("urn:Blogger/types", "PostResponse");
	protected static final QName _qname_getCategories = new QName("urn:Blogger/types", "getCategories");
	protected static final QName _qname_getCategoriesResponse = new QName("urn:Blogger/types", "getCategoriesResponse");
	protected static final QName _qname_getRating = new QName("urn:Blogger/types", "getRating");
	protected static final QName _qname_getRatingResponse = new QName("urn:Blogger/types", "getRatingResponse");
	protected static final QName _qname_postComment = new QName("urn:Blogger/types", "postComment");
	protected static final QName _qname_postCommentResponse = new QName("urn:Blogger/types", "postCommentResponse");
	protected static final QName _qname_postMedia = new QName("urn:Blogger/types", "postMedia");
	protected static final QName _qname_postMediaResponse = new QName("urn:Blogger/types", "postMediaResponse");
	protected static final QName _qname_registerUser = new QName("urn:Blogger/types", "registerUser");
	protected static final QName _qname_registerUserResponse = new QName("urn:Blogger/types", "registerUserResponse");
	protected static final QName _qname_retreive_blog = new QName("urn:Blogger/types", "retreive_blog");
	protected static final QName _qname_retreive_blogResponse = new QName("urn:Blogger/types", "retreive_blogResponse");
	protected static final QName _qname_retrieve_blogList = new QName("urn:Blogger/types", "retrieve_blogList");
	protected static final QName _qname_retrieve_blogListResponse = new QName("urn:Blogger/types", "retrieve_blogListResponse");
	protected static final QName _qname_setRating = new QName("urn:Blogger/types", "setRating");
	protected static final QName _qname_setRatingResponse = new QName("urn:Blogger/types", "setRatingResponse");
	protected static final QName _qname_wsdl_Login = new QName("urn:Blogger/wsdl", "Login");
	protected static final QName _qname_wsdl_Post = new QName("urn:Blogger/wsdl", "Post");
	protected static final QName _qname_wsdl_getCategories = new QName("urn:Blogger/wsdl", "getCategories");
	protected static final QName _qname_wsdl_getRating = new QName("urn:Blogger/wsdl", "getRating");
	protected static final QName _qname_wsdl_postComment = new QName("urn:Blogger/wsdl", "postComment");
	protected static final QName _qname_wsdl_postMedia = new QName("urn:Blogger/wsdl", "postMedia");
	protected static final QName _qname_wsdl_registerUser = new QName("urn:Blogger/wsdl", "registerUser");
	protected static final QName _qname_wsdl_retreive_blog = new QName("urn:Blogger/wsdl", "retreive_blog");
	protected static final QName _qname_wsdl_retrieve_blogList = new QName("urn:Blogger/wsdl", "retrieve_blogList");
	protected static final QName _qname_wsdl_setRating = new QName("urn:Blogger/wsdl", "setRating");
	protected static final Element _type_Login;
	protected static final Element _type_LoginResponse;
	protected static final Element _type_Post;
	protected static final Element _type_PostResponse;
	protected static final Element _type_getCategories;
	protected static final Element _type_getCategoriesResponse;
	protected static final Element _type_getRating;
	protected static final Element _type_getRatingResponse;
	protected static final Element _type_postComment;
	protected static final Element _type_postCommentResponse;
	protected static final Element _type_postMedia;
	protected static final Element _type_postMediaResponse;
	protected static final Element _type_registerUser;
	protected static final Element _type_registerUserResponse;
	protected static final Element _type_retreive_blog;
	protected static final Element _type_retreive_blogResponse;
	protected static final Element _type_retrieve_blogList;
	protected static final Element _type_retrieve_blogListResponse;
	protected static final Element _type_setRating;
	protected static final Element _type_setRatingResponse;
	static {
		// Create all of the Type's that this stub uses, once.
		Element _type_String_1;
		_type_String_1 = new Element(_qname_String_1, Type.STRING, 1, 1, true);
		ComplexType _complexType_login;
		_complexType_login = new ComplexType();
		_complexType_login.elements = new Element[1];
		_complexType_login.elements[0] = _type_String_1;
		_type_Login = new Element(_qname_Login, _complexType_login);
		Element _type_result;
		_type_result = new Element(_qname_result, Type.BOOLEAN);
		ComplexType _complexType_loginResponse;
		_complexType_loginResponse = new ComplexType();
		_complexType_loginResponse.elements = new Element[1];
		_complexType_loginResponse.elements[0] = _type_result;
		_type_LoginResponse = new Element(_qname_LoginResponse, _complexType_loginResponse);
		Element _type_String_2;
		_type_String_2 = new Element(_qname_String_2, Type.STRING, 1, 1, true);
		Element _type_String_3;
		_type_String_3 = new Element(_qname_String_3, Type.STRING, 1, 1, true);
		Element _type_String_4;
		_type_String_4 = new Element(_qname_String_4, Type.STRING, 1, 1, true);
		Element _type_String_5;
		_type_String_5 = new Element(_qname_String_5, Type.STRING, 1, 1, true);
		ComplexType _complexType_post;
		_complexType_post = new ComplexType();
		_complexType_post.elements = new Element[5];
		_complexType_post.elements[0] = _type_String_1;
		_complexType_post.elements[1] = _type_String_2;
		_complexType_post.elements[2] = _type_String_3;
		_complexType_post.elements[3] = _type_String_4;
		_complexType_post.elements[4] = _type_String_5;
		_type_Post = new Element(_qname_Post, _complexType_post);
		Element _type_result2;
		_type_result2 = new Element(_qname_result, Type.STRING, 1, 1, true);
		ComplexType _complexType_postResponse;
		_complexType_postResponse = new ComplexType();
		_complexType_postResponse.elements = new Element[1];
		_complexType_postResponse.elements[0] = _type_result2;
		_type_PostResponse = new Element(_qname_PostResponse, _complexType_postResponse);
		_type_getCategories = new Element(_qname_getCategories, _complexType_login);
		_type_getCategoriesResponse = new Element(_qname_getCategoriesResponse, _complexType_postResponse);
		ComplexType _complexType_getRating;
		_complexType_getRating = new ComplexType();
		_complexType_getRating.elements = new Element[3];
		_complexType_getRating.elements[0] = _type_String_1;
		_complexType_getRating.elements[1] = _type_String_2;
		_complexType_getRating.elements[2] = _type_String_3;
		_type_getRating = new Element(_qname_getRating, _complexType_getRating);
		_type_getRatingResponse = new Element(_qname_getRatingResponse, _complexType_postResponse);
		_type_postComment = new Element(_qname_postComment, _complexType_getRating);
		_type_postCommentResponse = new Element(_qname_postCommentResponse, _complexType_postResponse);
		_type_postMedia = new Element(_qname_postMedia, _complexType_login);
		_type_postMediaResponse = new Element(_qname_postMediaResponse, _complexType_postResponse);
		_type_registerUser = new Element(_qname_registerUser, _complexType_getRating);
		_type_registerUserResponse = new Element(_qname_registerUserResponse, _complexType_postResponse);
		ComplexType _complexType_retreive_blog;
		_complexType_retreive_blog = new ComplexType();
		_complexType_retreive_blog.elements = new Element[2];
		_complexType_retreive_blog.elements[0] = _type_String_1;
		_complexType_retreive_blog.elements[1] = _type_String_2;
		_type_retreive_blog = new Element(_qname_retreive_blog, _complexType_retreive_blog);
		_type_retreive_blogResponse = new Element(_qname_retreive_blogResponse, _complexType_postResponse);
		_type_retrieve_blogList = new Element(_qname_retrieve_blogList, _complexType_retreive_blog);
		_type_retrieve_blogListResponse = new Element(_qname_retrieve_blogListResponse, _complexType_postResponse);
		Element _type_int_4;
		_type_int_4 = new Element(_qname_int_4, Type.INT);
		ComplexType _complexType_setRating;
		_complexType_setRating = new ComplexType();
		_complexType_setRating.elements = new Element[4];
		_complexType_setRating.elements[0] = _type_String_1;
		_complexType_setRating.elements[1] = _type_String_2;
		_complexType_setRating.elements[2] = _type_String_3;
		_complexType_setRating.elements[3] = _type_int_4;
		_type_setRating = new Element(_qname_setRating, _complexType_setRating);
		_type_setRatingResponse = new Element(_qname_setRatingResponse, _complexType_postResponse);
	}

}
