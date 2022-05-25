package com.qiao.album;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootTest
public class MTest {
    @Test
    public void test1() throws UnknownHostException {
        System.out.println(Inet4Address.getLocalHost().getHostAddress());
    }
}
