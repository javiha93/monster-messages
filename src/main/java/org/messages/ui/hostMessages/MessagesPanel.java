package org.messages.ui.hostMessages;

import org.messages.dto.Host;
import org.messages.dto.Message;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MessagesPanel extends JPanel {

    public MessagesPanel(Host host) {
        setPreferredSize(new Dimension(200, getHeight()));
        List<Message> messages = host.getMessages();
        for (Message message : messages) {
            JButton button = new JButton(message.getMessageName());
            button.addActionListener(e -> updateMessageInfo(host.getHostName() + "/" + message.getMessageUri()));
            button.setPreferredSize(new Dimension( 200, 50));
            add(button);
        }
    }

    public MessagesPanel() {

    }

    public void updateMessageInfo(String uri) {
        ((HostMessagesPanel) getParent().getParent()).updateMessageInfoPanel(uri);
    }
}
