package org.messages.ui.hostMessages;

import org.homs.improterm.Main;
import org.homs.improterm.command.CommandsConfig;
import org.homs.improterm.ui.editor.HomsTextEditor;
import org.homs.improterm.ui.editor.HomsTextEditorBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Consumer;

public class MessageInfoPanel extends JPanel {

    HomsTextEditor editor;

    public MessageInfoPanel(CommandsConfig commandsConfig, Consumer<String> onSendCommand) {
        setLayout(new BorderLayout());
        String fileContent = "";
        editor = new HomsTextEditorBuilder().build(fileContent, commandsConfig, onSendCommand);

        editor.getTextPane().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {
                    onSendCommand.accept(editor.getSelectedText());
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        editor.setPreferredSize(new Dimension(getWidth(), getHeight()));
        editor.setRowHeaderView(null);
        editor.setBorder(new EmptyBorder(0, 20, 0, 0));
        add(editor, BorderLayout.CENTER);
    }

    public void updateMessageInfo(String uri) {
        editor.updateText(getMessageContent("messages/" + uri));
    }

    private String getMessageContent(String uri) {
        String fileContent = null;
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(uri)) {
            if (inputStream != null) {
                fileContent = readStreamAsString(inputStream);
                System.out.println("File Content:\n" + fileContent);
            } else {
                System.out.println("File not found: " + uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private static String readStreamAsString(InputStream inputStream) throws IOException {
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
