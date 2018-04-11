package com.nasoft.log.bean;

public class SystemLogInfo {
	private static ThreadLocal<SystemLogInfo> logInfo = new ThreadLocal<SystemLogInfo>();
	
	public static SystemLogInfo getSystemLogInfo(){
		return logInfo.get();
	}
	
	public static void setSystemLogInfo(SystemLogInfo sli){
		logInfo.set(sli);
	}
	
	private String userId;
	private String ipaddress;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
}
