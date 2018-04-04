package com.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Description: ip工具类
 * Created on: 2017-07-10 11:34
 *
 * @author: zuorenzhi
 * Copyright (c) 2017年 国美融通科技
 */

public class IpUtils {

    /**
     * from chen web
     * 获取客户端IP地址
     * 如果有多级反向代理,X-Forwarded-For的值并不止一个，而是一串Ｉｐ值,取第一个ip
     */
    public static String getRemoteIpAddr(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static void main(String[] args) throws SocketException {
//        getLocalIpAddress();
        System.out.println(IpUtils.getRealIp());
    }


    //获取本机内网ip
    public static void getLocalIpAddress() {
        try {
            System.out.println(InetAddress.getLocalHost().getHostName());
            System.out.println(InetAddress.getLocalHost().getHostAddress());
//            System.out.println(InetAddress.getLocalHost().getAddress());
//            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRealIp() throws SocketException {
        String localIp = null;// 本地IP，如果没有配置外网IP则返回它
        String netIp = null;// 外网IP

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean isFind = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !isFind) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                // 外网IP
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    netIp = ip.getHostAddress();
                    isFind = true;
                    break;
                    // 内网IP
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    localIp = ip.getHostAddress();
                }
            }
        }
        if (netIp != null && !"".equals(netIp)) {
            return netIp;
        } else {
            return localIp;
        }
    }
}
