package com.patterns2.observer.homework;

import java.util.*;


public class TasksQueue implements Observable {

    private final String name;
    private final Student student;
    private final Deque<Task> tasks;
    private final List<Observer> observers;

    public TasksQueue(String name, Student student) {
        this.name = name;
        this.student = student;
        tasks = new ArrayDeque<>();
        observers = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.offer(task);
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

    public Student getStudent() {
        return student;
    }

    public Deque<Task> getTasks() {
        return tasks;
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
