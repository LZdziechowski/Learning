package com.patterns2.observer.forum;

public class ForumUser implements Observer {

    private final String username;
    private int updateCount;

    public ForumUser(String userName) {
        this.username = userName;
    }

    @Override
    public void update(ForumTopic forumTopic) {
        System.out.println(username + ": New messages in topic " + forumTopic.getName() + "\n" +
                " (total: " + forumTopic.getMessages().size() + " messages");
    updateCount++;
    }

    public String getUsername() {
        return username;
    }

    public int getUpdateCount() {
        return updateCount;
    }
}
