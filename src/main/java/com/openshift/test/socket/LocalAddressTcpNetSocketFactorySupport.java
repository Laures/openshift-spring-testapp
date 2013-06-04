package com.openshift.test.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;

import lombok.RequiredArgsConstructor;

import org.springframework.integration.ip.tcp.connection.support.TcpSocketFactorySupport;

@RequiredArgsConstructor
public class LocalAddressTcpNetSocketFactorySupport implements TcpSocketFactorySupport {

    private final InetAddress localAddress;

    @Override
    public ServerSocketFactory getServerSocketFactory() {
        throw new RuntimeException("unsupported");
    }

    @Override
    public SocketFactory getSocketFactory() {
        return new LocalAddressSocketFactory(SocketFactory.getDefault());
    }

    @RequiredArgsConstructor
    public class LocalAddressSocketFactory extends SocketFactory {

        private final SocketFactory factory;

        @Override
        public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
            return factory.createSocket(host, port, localAddress, 0);
        }

        @Override
        public Socket createSocket(InetAddress host, int port) throws IOException {
            return factory.createSocket(host, port, localAddress, 0);
        }

        @Override
        public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
            return factory.createSocket(host, port, localHost, localPort);
        }

        @Override
        public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
            return factory.createSocket(address, port, localAddress, localPort);
        }

    }

}
