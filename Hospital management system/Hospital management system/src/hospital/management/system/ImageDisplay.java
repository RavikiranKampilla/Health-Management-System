package hospital.management.system;

import javax.swing.*;
import java.awt.*;

public class ImageDisplay extends JFrame {

    ImageDisplay() {
        // Set frame properties
        setTitle("Image Display");
        setSize(800, 500); // Set window size
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Load image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/hms.jpg")); // Update the path
        Image img = imageIcon.getImage().getScaledInstance(728, 450, Image.SCALE_SMOOTH); // Scale to fit
        ImageIcon scaledIcon = new ImageIcon(img);

        // Add image to JLabel
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0, 0, 728, 411); // Position and size
        add(label);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageDisplay();
    }
}
