package org.messages.ui;

import org.homs.improterm.telnet.TermTelnetClient;
import org.homs.improterm.ui.console.ConsoleComponent;
import org.messages.ui.hostMessages.HostMessagesPanel;

import javax.swing.*;

public class MessagesTerminal extends JSplitPane {

    private ConsoleComponent consoleComponent = new ConsoleComponent(new TermTelnetClient());
    HostMessagesPanel hostMessagesPanel = new HostMessagesPanel(consoleComponent);
    SentMessagesPanel sentMessagesPanel = new SentMessagesPanel(consoleComponent.getTextPane());
    public MessagesTerminal() {
        setLeftComponent(hostMessagesPanel);
        setRightComponent(sentMessagesPanel);
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(0.2);
    }


}
