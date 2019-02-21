package com.app;

public class AppBean {

	
	private String natId;
	private String mobileNo;
	
	@Override
	public String toString() {
		return "AppBean [natId=" + natId + ", mobileNo=" + mobileNo + "]";
	}
	
	public String getNatId() {
		return natId;
	}
	public void setNatId(String natId) {
		this.natId = natId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
}
