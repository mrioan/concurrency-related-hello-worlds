package com.test.blocking.queue;

import java.util.Random;

public class Producer implements Runnable {

    protected final BlockingQueue<Integer> blockingQueue;
    protected final int quantity;

    public Producer(BlockingQueue<Integer> blockingQueue, int quantity) {
        this.blockingQueue = blockingQueue;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Integer integer = new Random().nextInt();
            System.out.printf("Added %s%n", integer);
            this.blockingQueue.add(integer);
        }
    }
}
