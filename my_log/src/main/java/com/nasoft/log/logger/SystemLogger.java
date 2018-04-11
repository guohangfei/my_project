package com.nasoft.log.logger;

import java.util.Date;

public interface SystemLogger {
	void log(String userId, String ipaddress, String type, String logClass, String logMethod, String logParameters, String status, String message, Date date);
}
