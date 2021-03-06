//package com.firnice.test.zk;
//
//import java.io.IOException;
//import java.util.concurrent.CountDownLatch;
//
//import org.apache.zookeeper.KeeperException;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//import org.apache.zookeeper.Watcher.Event.KeeperState;
//import org.apache.zookeeper.ZooKeeper;
//import org.apache.zookeeper.data.Stat;
//
///**
// * @author: liyl
// * @date: 2018/1/25 上午11:15
// * @since 1.0.0
// */
//public class ZooKeeperConnection {
//
//    private ZooKeeper zoo;
//    final CountDownLatch connectedSignal = new CountDownLatch(1);
//
//    // Method to connect zookeeper ensemble.
//    public ZooKeeper connect(String host) throws IOException,InterruptedException {
//
//        zoo = new ZooKeeper(host,5000,new Watcher() {
//
//            @Override
//            public void process(WatchedEvent we) {
//
//                if (we.getState() == KeeperState.SyncConnected) {
//                    connectedSignal.countDown();
//                }
//            }
//        });
//
//        connectedSignal.await();
//        return zoo;
//    }
//
//    // Method to disconnect from zookeeper server
//    public void close() throws InterruptedException {
//        zoo.close();
//    }
//
//    public static void main(String[] args) {
//        ZooKeeperConnection zooKeeperConnection = new ZooKeeperConnection();
//        try {
//            ZooKeeper connect = zooKeeperConnection.connect("localhost:2181");
//            try {
//                Stat node1 = connect.exists("node1", false);
//            } catch (KeeperException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
