package org.messages.dto;

import java.util.List;

public class Host {

    private String hostName;
    private List<Message> messages;

    public Host(String hostName, List<Message> messages) {
        this.hostName = hostName;
        this.messages = messages;
    }

    public String getHostName() {
        return hostName;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
