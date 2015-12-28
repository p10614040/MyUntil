/**
 * 
 * @Title HttpRequestUtil.java
 * @Package com.iceblock.myuntil
 * @Description request工具类
 * @author yan.liang
 * @date 2015年8月13日 上午10:17:10
 * @copyright USTCINFO
 */
package com.iceblock.myuntil;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * @author YanLiang
 * 
 */
public class HttpRequestUtil {
	
	/**
	 * 静态方法，防止实例化
	 */
	private HttpRequestUtil(){
	}

	/**
	 * @author yan.liang
	 * @date 2015年8月13日 上午9:40:59
	 * @Description 获取客户端真实IP
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * @author yan.liang
	 * @date 2015年8月13日 上午10:01:09
	 * @Description 获取访问地址的IP开头字符串
	 */
	public static String getRequestUrlIpFirst(HttpServletRequest request){
		String ipFirst = "10";
		String requestUrl = request.getRequestURL().toString();//获取当前请求的URL
		String requestIp = StringUtils.split(StringUtils.split(requestUrl, "//")[1], ":")[0];
		String requestIpFirst = StringUtils.substring(requestIp, 0, StringUtils.indexOf(requestIp, "."));
		if(StringUtils.isNotBlank(requestIpFirst)){
			ipFirst = requestIpFirst;
		}
		return ipFirst;
	}
}
