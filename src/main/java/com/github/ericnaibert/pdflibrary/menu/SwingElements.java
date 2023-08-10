package com.github.ericnaibert.pdflibrary.menu;

import com.github.ericnaibert.pdflibrary.storage.PathStorage;

import javax.swing.*;
import java.awt.*;

public class SwingElements {

    public static void showTextFieldPopUp() {

        JFrame frame = new JFrame();

        FlowLayout flowLayout = new FlowLayout();

        JPanel panel = new JPanel();
        panel.setLayout(flowLayout);

        JLabel label = new JLabel();
        label.setText("Enter the Name of Your Library");
        label.setFont(new Font("Arial", Font.BOLD, 17));
        panel.add(label);

        JTextField textField = new JTextField();
        textField.setRequestFocusEnabled(true);
        textField.setFocusable(true);
        textField.setPreferredSize(new Dimension(300, 30));
        textField.addActionListener(e -> {
            PathStorage.storePathToFile(textField.getText());
            frame.setVisible(false);
        });
        panel.add(textField);

        frame.add(panel);
        frame.setSize(320, 100);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

}
