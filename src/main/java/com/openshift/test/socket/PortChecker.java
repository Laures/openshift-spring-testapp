package com.openshift.test.socket;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ServerSocketFactory;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.common.collect.Lists;

@Slf4j
public class PortChecker {

    @Value("${socket.server.host}")
    private String internalIp;

    private static final Map<String, Integer> ports = new HashMap<>();

    @Getter
    private Map<String, Object> results = new HashMap<>();

    static {
        ports.put("smtp-1", 25);
        ports.put("smtp-2", 465);
        ports.put("smtp-3", 587);
        ports.put("imap-1", 143);
        ports.put("imap-2", 220);
        ports.put("imap-3", 993);
        ports.put("pop-1", 109);
        ports.put("pop-2", 110);
        ports.put("pop-3", 995);
        ports.put("dns", 53);
        ports.put("kerberos-1", 88);
        ports.put("kerberos-2", 750);
        ports.put("kerberos-3", 4444);
        ports.put("ftp-1", 21);
        ports.put("ftp-2", 990);
        ports.put("mongo", 27017);
        ports.put("postgres", 5432);
        ports.put("mssql-1", 1433);
        ports.put("mssql-2", 1434);
        ports.put("oracle-1", 1521);
        ports.put("oracle-2", 2483);
        ports.put("oracle-3", 2484);
        ports.put("http-1", 80);
        ports.put("http-2", 8008);
        ports.put("http-3", 8009);
        ports.put("http-4", 8443);
        ports.put("cache-1", 8080);
        ports.put("cache-2", 8118);
        ports.put("cache-3", 8123);
        ports.put("cache-4", 10001);
        ports.put("cache-5", 10002);
        ports.put("cache-6", 10003);
        ports.put("cache-7", 10004);
        ports.put("cache-8", 10005);
        ports.put("cache-9", 10006);
        ports.put("cache-10", 10007);
        ports.put("cache-11", 10009);
        ports.put("cache-12", 10008);
        ports.put("cache-13", 10010);
        ports.put("memcache", 11211);
        ports.put("jacorb-1", 3528);
        ports.put("jacorb-2", 3529);
        ports.put("amqp-1", 5671);
        ports.put("amqp-2", 5672);
        ports.put("PulseAudio", 4713);
        ports.put("flash-1", 843);
        ports.put("flash-2", 1935);
        ports.put("munin", 4949);
        ports.put("virt-migration", 49152);
        ports.put("OCSP", 9080);

        List<Integer> other = Lists.newArrayList(3128, 5445, 5455, 8255, 8389, 9352, 9353, 9374, 9472, 9923, 9926, 9949, 9950, 9996, 9999, 10715, 10716, 10717,
                14170, 14171, 14248,
                60417);
        for (int i = 1; i < other.size(); i++) {
            ports.put("other-" + i, other.get(i));
        }
    }

    @Scheduled(fixedDelay = 20000, initialDelay = 10000)
    public void checkPorts() {
        log.info("starting port check");

        Map<String, Object> curResult = new HashMap<>();
        ServerSocket socket;
        for (Entry<String, Integer> port : ports.entrySet()) {
            try {
                InetAddress address = InetAddress.getByName(internalIp);
                socket = ServerSocketFactory.getDefault().createServerSocket(port.getValue(), -1, address);
                curResult.put(port.getKey() + " port: " + port.getValue(), "success");
                socket.close();
            } catch (Exception e) {
                curResult.put(port.getKey() + " port: " + port.getValue(), e);
            }

        }

        results = curResult;
        log.info("finished port check");
    }

}
