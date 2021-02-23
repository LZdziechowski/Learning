package com.patterns2.observer.homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TasksQueueTestSuite {

    @Test
    public void testUpdate() {
        //Given
        Mentor mentor1 = new Mentor("mentor1");
        Mentor mentor2 = new Mentor("mentor2");
        Student student1 = new Student("student1");
        Student student2 = new Student("student2");
        TasksQueue tasksQueueStudent1 = new TasksQueue("student1Tasks", student1);
        TasksQueue tasksQueueStudent2 = new TasksQueue("student2Tasks", student2);
        tasksQueueStudent1.registerObserver(mentor1);
        tasksQueueStudent2.registerObserver(mentor2);
        //When
        tasksQueueStudent1.addTask(new Task("task1", "solution1"));
        tasksQueueStudent1.addTask(new Task("task2", "solution2"));
        tasksQueueStudent2.addTask(new Task("task1", "solution1"));
        tasksQueueStudent2.addTask(new Task("task2", "solution2"));
        tasksQueueStudent2.addTask(new Task("task3", "solution3"));
        //Then
        assertEquals(2, mentor1.getUpdateCount());
        assertEquals(3, mentor2.getUpdateCount());
    }

}