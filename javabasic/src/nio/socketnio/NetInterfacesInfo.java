package nio.socketnio;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Enumeration;
import java.util.Optional;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package nio.socketnio
 * @Description:
 * @Date: 2021/11/3 13:54
 */
public class NetInterfacesInfo {

    public static void main(String[] args) throws SocketException, UnsupportedEncodingException, UnknownHostException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println("网络设备名称 " + networkInterface.getName());
            System.out.println("网络设备显示名称 " + networkInterface.getDisplayName());
            System.out.println("网络接口索引 " + networkInterface.getIndex());
            System.out.println("网卡是否已经开启并运行 " + networkInterface.isUp());
            System.out.println("isLook 是否为回调接口 " + networkInterface.isLoopback());
            System.out.println("最大传输单元 " + networkInterface.getMTU());

            byte[] hardwareAddress = networkInterface.getHardwareAddress();
            Optional.ofNullable(hardwareAddress).ifPresent(System.out::println);
            // 获取 ip
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                System.out.println("获取此IP地址的完全限定域名 " + inetAddress.getCanonicalHostName());
                System.out.println("获取此IP 地址字符串 " + inetAddress.getHostAddress());
                System.out.println("返回IP 地址字符串 " + inetAddress.getHostAddress());
                System.out.println("返回此InetAddress对象的原始 IP 地址 ");
                byte[] address = inetAddress.getAddress();
                for (int i = 0; i < address.length; i++) {
                    System.out.print(address[i] + " ");
                }
                System.out.println();
            }
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("localHost.getAddress() 地址为: " );
            for (int i = 0; i < localHost.getAddress().length; i++) {
                System.out.print(" " + localHost.getAddress()[i] + " ");
            }
            System.out.println();
            System.out.println("InetAddress.getLoopbackAddress() 地址为 ==");
            InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
            byte[] loopbackIpAddress = loopbackAddress.getAddress();
            for (int i = 0; i < loopbackIpAddress.length; i++) {
                System.out.println();
                System.out.print(" " + loopbackIpAddress[i] + " ");
            }
            System.out.println();

        }
    }
}
