package com.test.blocking.queue;

public interface BlockingQueue<T> {

    void add (T elem);
    T remove ();
    int size();

}

