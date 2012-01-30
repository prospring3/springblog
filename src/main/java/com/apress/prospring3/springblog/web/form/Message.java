/**
 * Created on Dec 15, 2011
 */
package com.apress.prospring3.springblog.web.form;

/**
 * @author Clarence
 *
 */
public class Message {

	private String type;
	
	private String message;
	
	public Message() {
	}
	
	public Message(String type, String message) {
		this.type = type;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
}
