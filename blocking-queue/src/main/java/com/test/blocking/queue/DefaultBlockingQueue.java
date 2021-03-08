package com.test.blocking.queue;

import java.util.LinkedList;
import java.util.Queue;

public class DefaultBlockingQueue<T> implements BlockingQueue<T>{

    public final static int DEFAULT_TOTAL_CAPACITY = 20;
    public final int totalCapacity;
    protected Queue<T> queue = new LinkedList<T>();

    public DefaultBlockingQueue() {
        this(DEFAULT_TOTAL_CAPACITY);
    }

    public DefaultBlockingQueue(int capacity) {
        this.totalCapacity = capacity;
    }

    @Override
    public void add(T elem) {
        synchronized (this) {
            try {
                while (queue.size() == totalCapacity) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        //no-op
                    }
                }
                queue.add(elem);
            } finally{
                notifyAll();
            }
        }
    }


    @Override
    public T remove() {
        synchronized (this) {
            try {
                while (this.queue.size() == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return queue.remove();

            } finally {
                notifyAll();
            }
        }
    }

    @Override
    public int size() {
        synchronized (this) {
            return this.queue.size();
        }
    }
}
