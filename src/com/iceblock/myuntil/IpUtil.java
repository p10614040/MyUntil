package com.iceblock.myuntil;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IP 工具类
 *
 * @author YanLiang
 * @date 2015-12-2.
 */
public class IpUtil {

    private IpUtil() {
    }

    /**
     * 判断IP 地址格式是否合法
     *
     * @param IP IP 地址
     * @return true:合法
     */
    public static boolean isIp(String IP) {
        boolean b = false;
        IP = StringUtils.trimToEmpty(IP);
        if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            String s[] = IP.split("\\.");
            if (Integer.parseInt(s[0]) < 255)
                if (Integer.parseInt(s[1]) < 255)
                    if (Integer.parseInt(s[2]) < 255)
                        if (Integer.parseInt(s[3]) < 255)
                            b = true;
        }
        return b;
    }

    /**
     * 获取 IP 地址段内的所有 IP，起始IP和结束IP必须
     * 在一个网段。
     *
     * @param startIp 起始IP
     * @param endIp   结束IP
     * @return ip 地址列表
     */
    public static List<String> getIpAddressInIpField(String startIp, String endIp) {
        List<String> ipAddress = new ArrayList<String>();
        if (StringUtils.isNotBlank(startIp) && isIp(startIp) && StringUtils.isNotBlank(endIp) && isIp(endIp)) {
            Long startIpValue = getIpFromString(startIp);
            Long endIpValue = getIpFromString(endIp);
            Long ipFieldValues = endIpValue - startIpValue;
            for (int i = 0; i <= ipFieldValues; i++) {
                ipAddress.add(getIpFromLong(startIpValue + i));
            }
        }
        return ipAddress;
    }

    /**
     * 把long类型的Ip转为一般Ip类型：xx.xx.xx.xx
     *
     * @param ip ip long 值
     * @return IP 地址
     */
    public static String getIpFromLong(Long ip) {
        String s1 = String.valueOf((ip & 4278190080L) / 16777216L);
        String s2 = String.valueOf((ip & 16711680L) / 65536L);
        String s3 = String.valueOf((ip & 65280L) / 256L);
        String s4 = String.valueOf(ip & 255L);
        return s1 + "." + s2 + "." + s3 + "." + s4;
    }

    /**
     * 把xx.xx.xx.xx类型的转为long类型的
     *
     * @param ip IP 地址
     * @return ip long 值
     */
    public static Long getIpFromString(String ip) {
        Long ipLong = 0L;
        String ipTemp = ip;
        ipLong = ipLong * 256
                + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf(".")));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256
                + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf(".")));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256
                + Long.parseLong(ipTemp.substring(0, ipTemp.indexOf(".")));
        ipTemp = ipTemp.substring(ipTemp.indexOf(".") + 1, ipTemp.length());
        ipLong = ipLong * 256 + Long.parseLong(ipTemp);
        return ipLong;
    }

    public static void main(String[] args) {
        String startIp = "10.90.7.250";
        String endIp = "10.90.8.10";
        List<String> ipAddress = IpUtil.getIpAddressInIpField(startIp, endIp);
        System.out.println(Collections.singletonList(ipAddress));
    }
}
