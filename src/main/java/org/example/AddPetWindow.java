package org.example;

import org.example.handlers.PetHandler;
import org.example.models.Pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class AddPetWindow extends JFrame {
    private final int WIGHT = 400;
    private final int HEIGHT = 200;

    private final JButton addButton = new JButton("save pet");
    private final JTextArea name = new JTextArea("name");
    private final JTextArea birthday = new JTextArea("birthday");
    private final JPanel inputPanel = new JPanel();

    public AddPetWindow(PetHandler petHandler) throws HeadlessException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        setSize(WIGHT,HEIGHT);
        setTitle("new pet");
        inputPanel.add(name);
        inputPanel.add(birthday);
        inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.X_AXIS));
        add(inputPanel);
        add(addButton,BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pet pet = new Pet(0,name.getText(), Timestamp.valueOf(birthday.getText()));
                petHandler.savePet(pet);
            }
        });
    }
}
