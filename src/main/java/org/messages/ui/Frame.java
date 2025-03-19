package org.messages.ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

        private final MessagesTerminal messagesTerminal = new MessagesTerminal();

        public Frame(String title, String[] args) {

            setTitle(title);

            setLayout(new BorderLayout());

            add(messagesTerminal);

            final Rectangle frameBounds;
            {
                GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                frameBounds = env.getMaximumWindowBounds();
                setSize(new Dimension(frameBounds.width, frameBounds.height));
            }
            setVisible();
            //improvedTerminal.configure(args);
        }

        public void setVisible() {
            setVisible(true);
            //SwingUtilities.invokeLater(() -> messagesTerminal.setDividerLocation(0.8));
        }
}
