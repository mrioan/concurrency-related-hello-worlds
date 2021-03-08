package com.test.blocking.queue;

public class Consumer implements Runnable {

    protected final BlockingQueue<Integer> blockingQueue;
    protected final int quantity;

    public Consumer(BlockingQueue<Integer> blockingQueue, int quantity) {
        this.blockingQueue = blockingQueue;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.quantity; i++) {
            System.out.printf("Removed %s%n", this.blockingQueue.remove());
        }
    }
}
