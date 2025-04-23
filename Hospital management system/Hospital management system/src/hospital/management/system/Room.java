package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Room extends JFrame {

    JTable table;
    Room() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null); // Fixed: Allows absolute positioning

        // Load and scale image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/roomm.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(image);

        // Add image to JLabel
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(600, 200, 200, 200);

        panel.add(label); // Add label to panel
        add(panel); // Add panel to JFrame

        table = new JTable();
        table.setBounds(10,40,500,400);
        table.setBackground(new Color(90,156,163));
        panel.add(table);

        try {
            conn c = new conn();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Room NO");
        label1.setBounds(12, 15, 80, 15);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);



        setSize(900, 600);
        setLayout(null);
        setLocation(300, 230);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Room();
    }
}
