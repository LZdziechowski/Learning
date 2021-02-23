package com.patterns2.observer.homework;

public class Mentor implements Observer {

    private final String name;
    private int updateCount;

    public Mentor(String name) {
        this.name = name;
    }

    @Override
    public void update(TasksQueue tasksQueue) {
        int size = tasksQueue.getTasks().size();
        System.out.println("Hi " + name + ". There is new task to check. \n" +
                "Student: " + tasksQueue.getStudent().getName() + "\n" +
                "Task: " + tasksQueue.getTasks().getLast().getName());
        updateCount++;
    }

    public int getUpdateCount() {
        return updateCount;
    }
}
