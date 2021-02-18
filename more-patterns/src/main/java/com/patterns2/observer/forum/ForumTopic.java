package com.patterns2.observer.forum;

import java.util.ArrayList;
import java.util.List;

public class ForumTopic implements  Observable {

    private final List<String> messages;
    private final String name;
    private final List<Observer> observers;

    public ForumTopic(String name) {
        this.messages = new ArrayList<>();
        this.name = name;
        this.observers = new ArrayList<>();
    }

    public void addPost(String post) {
        messages.add(post);
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public String getName() {
        return name;
    }

    public List<String> getMessages() {
        return messages;
    }
}
