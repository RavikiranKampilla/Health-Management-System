package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class Upload extends JFrame {

    private JTextField nameField;
    private JLabel imageLabel;
    private File selectedFile;

    Upload() {
        setTitle("Upload Image to Database");
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

        JButton chooseButton = new JButton("Choose Image");
        chooseButton.setBounds(150, 80, 150, 30);
        add(chooseButton);

        imageLabel = new JLabel();
        imageLabel.setBounds(150, 120, 200, 200);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(imageLabel);

        JButton uploadButton = new JButton("Upload");
        uploadButton.setBounds(50, 350, 120, 30);
        add(uploadButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(190, 350, 100, 30);
        add(clearButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(320, 350, 100, 30);
        add(backButton);

        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Image File");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                    imageLabel.setIcon(icon);
                }
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null && !nameField.getText().isEmpty()) {
                    uploadImageToDatabase(nameField.getText(), selectedFile);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a name and select an image!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                imageLabel.setIcon(null);
                selectedFile = null;
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setVisible(true);
    }

    public void uploadImageToDatabase(String name, File file) {
        try {
            conn c = new conn();  // Using your existing connection class
            PreparedStatement ps = c.connection.prepareStatement("INSERT INTO Upload (name, image) VALUES (?, ?)");

            FileInputStream fis = new FileInputStream(file);
            ps.setString(1, name);
            ps.setBinaryStream(2, fis, (int) file.length());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Image uploaded successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Upload failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Upload();
    }
}