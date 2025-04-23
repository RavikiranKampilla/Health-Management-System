package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class DisplayImage extends JFrame {
    private JTextField nameField;
    private JLabel imageLabel;

    DisplayImage() {
        setTitle("Display Image from Database");
        setUndecorated(true);
        setSize(500, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel nameLabel = new JLabel("Enter Name:");
        nameLabel.setBounds(50, 30, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 30, 250, 30);
        add(nameField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(50, 80, 120, 30);
        add(searchButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(190, 80, 100, 30);
        add(clearButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(320, 80, 100, 30);
        add(backButton);

        imageLabel = new JLabel();
        imageLabel.setBounds(150, 130, 200, 200);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(imageLabel);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().isEmpty()) {
                    displayImageFromDatabase(nameField.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                imageLabel.setIcon(null);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the current window
            }
        });

        setVisible(true);
    }

    public void displayImageFromDatabase(String name) {
        try {
            conn c = new conn(); // Using your existing connection class
            PreparedStatement ps = c.connection.prepareStatement("SELECT image FROM Upload WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                byte[] imageData = rs.getBytes("image");
                ImageIcon icon = new ImageIcon(new ImageIcon(imageData).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                imageLabel.setIcon(icon);
            } else {
                JOptionPane.showMessageDialog(null, "No image found for this name!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving image!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new DisplayImage();
    }
}
