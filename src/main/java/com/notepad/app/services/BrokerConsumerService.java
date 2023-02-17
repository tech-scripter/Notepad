package com.notepad.app.services;

public interface BrokerConsumerService<T> {

    void consume(T arg);
}
