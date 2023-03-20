package com.notepad.app.prototype_in_singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySingleton {
    private Prototype prototype;

    @Autowired
    public MySingleton(Prototype prototype) {
        this.prototype = prototype;
    }

    public void doSomething() {
        prototype.bar();
    }

    public void doSomethingElse() {
        prototype.foo();
    }
}

abstract class Singleton {
    protected  abstract Prototype prototype();

    public void doSomething() {
        prototype().foo();
    }

    public void doSomethingElse() {
        prototype().bar();
    }
}
