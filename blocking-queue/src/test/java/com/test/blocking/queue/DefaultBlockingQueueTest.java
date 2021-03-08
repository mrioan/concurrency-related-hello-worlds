package com.test.blocking.queue;

import org.junit.Assert;
import org.junit.Test;

public class DefaultBlockingQueueTest {

    @Test
    public void test() throws Exception {
        BlockingQueue<Integer> blockingQueue = new DefaultBlockingQueue<>();
        Thread thread1 = null;
        Thread thread2 = null;
        int producerQuantity = 119;
        int consumerQuantity = 100;
        try {
            thread1 = new Thread(new Producer(blockingQueue, producerQuantity));
            thread2 = new Thread(new Consumer(blockingQueue, consumerQuantity));
            thread1.start();
            thread2.start();
        } catch (Exception e) {
            org.junit.Assert.fail("There was an Exception");
        }
        thread1.join(5000);
        thread2.join(5000);
        System.out.printf("Quantity of items in the queue: %s.%n", blockingQueue.size());
        Assert.assertEquals( producerQuantity - consumerQuantity, blockingQueue.size());
    }

    @Test
    public void testCannotFinish() throws Exception {
        BlockingQueue<Integer> blockingQueue = new DefaultBlockingQueue<>();
        Thread thread1 = null;
        Thread thread2 = null;
        int producerQuantity = 121;
        int consumerQuantity = 100;
        try {
            thread1 = new Thread(new Producer(blockingQueue, producerQuantity));
            thread2 = new Thread(new Consumer(blockingQueue, consumerQuantity));
            thread1.start();
            thread2.start();
        } catch (Exception e) {
            org.junit.Assert.fail("There was an Exception");
        }
        thread2.join(5000);
        Thread.sleep(500);
        System.out.printf("Quantity of items in the queue: %s.%n", blockingQueue.size());
        Assert.assertEquals(20, blockingQueue.size());
        Assert.assertTrue(thread1.isAlive());
    }

}