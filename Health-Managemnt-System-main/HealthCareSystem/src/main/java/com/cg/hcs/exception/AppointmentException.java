package com.cg.hcs.exception;

@SuppressWarnings("serial")
public class AppointmentException extends Exception {
	public AppointmentException(){
		
	}
	public AppointmentException(String message){
		super(message);
	}

}
