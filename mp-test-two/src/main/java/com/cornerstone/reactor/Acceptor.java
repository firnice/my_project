package com.cornerstone.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @author: liyl
 * @date: 2018/2/11 下午3:24
 * @since 1.0.0
 */
public class Acceptor implements Runnable {

    private Reactor reactor;
    public Acceptor(Reactor reactor){
        this.reactor=reactor;
    }
    @Override
    public void run() {
        try {
            SocketChannel socketChannel=reactor.serverSocketChannel.accept();
            if(socketChannel!=null)//调用Handler来处理channel
                new SocketReadHandler(reactor.selector, socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
