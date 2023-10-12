package org.example;

import org.example.handlers.PetHandler;
import org.example.models.Pet;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PetClinicClient extends JFrame {
    private final int WIGHT = 600;
    private final int HEIGHT = 500;

    private final JButton getPetsButton = new JButton("get pets");
    private final JButton addPetButton = new JButton("add pet");

    private AddPetWindow addPetWindow;

    JPanel buttons = new JPanel();
    private final JPanel main = new JPanel();
    private JTable table = new JTable();

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ClientConfig.class);

    PetHandler petHandler = context.getBean("PetHandler", PetHandler.class);

    public PetClinicClient() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIGHT,HEIGHT);
        setTitle("Pet Clinic");
        setVisible(true);

        buttons.add(addPetButton);
        buttons.add(getPetsButton);

        main.setBorder(new EmptyBorder(10,10,10,10));
        main.add(table,BorderLayout.NORTH);

        add(buttons,BorderLayout.SOUTH);
        add(main);

        addPetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPetWindow = new AddPetWindow(petHandler);
                addPetWindow.setVisible(true);
            }
        });

        getPetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setVisible(false);
                Object[] columnNames = { "id", "name", "birthday"};
                List<Pet> pets = petHandler.getAllPet();
                Object[][] result = new String[pets.size()][];
                for (int i = 0; i < pets.size(); i++) {
                    result[i] = pets.get(i).getAll();
                }
                table = new JTable(result,columnNames);
                table.setSelectionForeground(Color.yellow);
                table.setSelectionBackground(Color.blue);
                main.add(table);
            }
        });
    }


    public static void main(String[] args) {
        new PetClinicClient();
    }
}