package com.cg.hcs.exception;

import java.time.LocalDateTime;

public class ErrorInfo {
	private LocalDateTime time;
	private String message;
	private String url;
	public ErrorInfo() {
		super();
	}
	public ErrorInfo(LocalDateTime time, String message, String url) {
		super();
		this.time = time;
		this.message = message;
		this.url = url;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ErrorInfo [time=" + time + ", message=" + message + ", url=" + url + "]";
	}
	

}
