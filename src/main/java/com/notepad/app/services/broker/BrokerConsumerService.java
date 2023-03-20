package com.notepad.app.services.broker;

public interface BrokerConsumerService<T> {
    void consume(T arg);
}
