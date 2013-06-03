package com.openshift.test.socket;

import javax.inject.Inject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SocketClient {

    private long lastCall;
    private String lastmessage;
    private long lastPing;

    @Inject
    private MessageGateway gateway;

    public void sendMessage() {
        long time = System.currentTimeMillis();
        log.info("sending message");

        lastCall = time;
        lastmessage = gateway.sendMessage(time + "");
        lastPing = System.currentTimeMillis() - time;

        log.info("received message {}, after {} milliseconds", lastmessage, lastPing);
    }
}
