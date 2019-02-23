package com.app;

public class ResponseBean<T> {

	private String status;
	
	@Override
	public String toString() {
		return "ResponseBean [status=" + status + ", data=" + data.toString() + "]";
	}
	private T data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
