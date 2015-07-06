package com.tyyd.ywpt.tools.encrypt;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Type StackTraceUtil
 * @Desc 
 * @author hongxia.huhx
 * @date 2012-03-03
 * @Version V1.0
 */
public class StackTraceUtil {
	/**
	 * 取出exception中的信息
	 * 
	 * @param exception
	 * @return
	 */
	public static String getStackTrace(Throwable exception) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			return sw.toString();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
}