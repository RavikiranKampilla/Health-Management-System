package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2;
    JLabel backgroundLabel;

    Login() {
        setTitle("Hospital Management System - Login");
        setSize(750, 450); // Adjusted size for full image display
        setLocation(400, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load and set the background image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/hms.jpg"));
        Image img = imageIcon.getImage().getScaledInstance(750, 450, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);

        backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, 750, 450);
        add(backgroundLabel);

        // Username Label
        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(250, 150, 100, 30);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        namelabel.setForeground(Color.WHITE); // Ensure visibility on the image
        backgroundLabel.add(namelabel);

        // Password Label
        JLabel password = new JLabel("Password");
        password.setBounds(250, 200, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 16));
        password.setForeground(Color.WHITE);
        backgroundLabel.add(password);

        // Username TextField
        textField = new JTextField();
        textField.setBounds(360, 150, 150, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBackground(Color.WHITE);
        backgroundLabel.add(textField);

        // Password Field
        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(360, 200, 150, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setBackground(Color.WHITE);
        backgroundLabel.add(jPasswordField);

        // Login Button
        b1 = new JButton("Login");
        b1.setBounds(300, 260, 120, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        backgroundLabel.add(b1);

        // Cancel Button
        b2 = new JButton("Cancel");
        b2.setBounds(440, 260, 120, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        backgroundLabel.add(b2);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn c = new conn(); // Ensure this class exists in your project
                String user = textField.getText();
                String Pass = new String(jPasswordField.getPassword()); // Fixed warning

                String q = "SELECT * FROM login WHERE ID = '" + user + "' AND PW = '" + Pass + "'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()) {
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
