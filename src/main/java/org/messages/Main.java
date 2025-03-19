package org.messages;

import mdlaf.MaterialLookAndFeel;
import org.messages.ui.Frame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new MaterialLookAndFeel());
        Frame frame = new Frame("", args);
    }
}