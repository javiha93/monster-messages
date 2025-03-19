package org.messages.ui.hostMessages;

import org.homs.improterm.command.CommandsExecutor;
import org.homs.improterm.telnet.TermTelnetClient;
import org.homs.improterm.ui.console.ConsoleComponent;
import org.homs.improterm.ui.editor.MartinJTextPane;
import org.messages.dto.Host;
import org.messages.dto.Message;
import org.messages.ui.MessagesTerminal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HostMessagesPanel extends JPanel {

    HostsPanel hostsPanel = new HostsPanel();
    MessagesPanel messagesPanel = new MessagesPanel();
    private ConsoleComponent consoleComponent;
    private CommandsExecutor controller;
    MessageInfoPanel messageInfoPanel;
    JPanel selectPanel = new JPanel();
    public HostMessagesPanel(ConsoleComponent consoleComponent) {
        this.consoleComponent = consoleComponent;
        this.controller = new CommandsExecutor(consoleComponent::sendCommand);
        this.messageInfoPanel = new MessageInfoPanel(controller.getCommandsConfig(), controller::executeScript);
        setLayout(new BorderLayout());

        selectPanel.setLayout(new BorderLayout());
        selectPanel.add(hostsPanel, BorderLayout.WEST);
        selectPanel.add(messagesPanel, BorderLayout.EAST);

        add(selectPanel, BorderLayout.WEST);
        add(messageInfoPanel, BorderLayout.CENTER);
        add(consoleComponent.getTextPane(), BorderLayout.EAST);

        configure();
    }

    public void updateMessagesPanel(Host host) {
        selectPanel.remove(messagesPanel);
        messagesPanel = new MessagesPanel(host);
        selectPanel.add(messagesPanel, BorderLayout.EAST);
        ((MessagesTerminal) getParent()).setDividerLocation(0.8);

        revalidate();
        repaint();
    }

    public void updateMessageInfoPanel(String uri) {
        messageInfoPanel.updateMessageInfo(uri);

        revalidate();
        repaint();
    }

    public void configure(){
        consoleComponent.setTelnetUrl("127.0.0.1:23");
        consoleComponent.setTelnetUser("_system");
        consoleComponent.setTelnetPass("CONNECT");
        consoleComponent.connect();
    }
}
