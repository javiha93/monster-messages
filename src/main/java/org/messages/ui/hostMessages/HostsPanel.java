package org.messages.ui.hostMessages;

import org.homs.commons.resources.ImprovedColor;
import org.messages.HostManager;
import org.messages.dto.Host;
import org.messages.dto.Message;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class HostsPanel extends JPanel {
    List<Host> hosts = HostManager.getHosts();

    public HostsPanel() {
        setPreferredSize(new Dimension(130, getHeight()));
        for (Host host : hosts) {
            JButton button = new JButton(host.getHostName());
            button.addActionListener(e -> updateMessages(host));
            button.setPreferredSize(new Dimension( 130, 50));
            add(button);
        }
    }

    public void updateMessages(Host host) {
        ((HostMessagesPanel) getParent().getParent()).updateMessagesPanel(host);
    }
}
