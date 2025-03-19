package org.messages.dto;

public class Message {

    private String messageName;
    private String messageUri;

    public Message(String messageName, String messageUri) {
        this.messageName = messageName;
        this.messageUri = messageUri;
    }

    public String getMessageName() {
        return messageName;
    }

    public String getMessageUri() {
        return messageUri;
    }
}
