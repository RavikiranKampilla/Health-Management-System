package hospital.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HospitalManagementSystem extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public HospitalManagementSystem() {
        setTitle("Hospital Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create side navigation panel
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(6, 1, 5, 5));
        sidePanel.setBackground(Color.LIGHT_GRAY);

        JButton dashboardButton = new JButton("Dashboard");
        JButton patientsButton = new JButton("Patients");
        JButton appointmentsButton = new JButton("Appointments");
        JButton recordsButton = new JButton("Records");
        JButton settingsButton = new JButton("Settings");

        sidePanel.add(dashboardButton);
        sidePanel.add(patientsButton);
        sidePanel.add(appointmentsButton);
        sidePanel.add(recordsButton);
        sidePanel.add(settingsButton);

        // Main content area with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        JPanel dashboardPanel = createDashboardPanel();
        JPanel patientsPanel = createPatientsPanel();
        JPanel appointmentsPanel = createAppointmentsPanel();
        JPanel recordsPanel = createRecordsPanel();
        JPanel settingsPanel = createSettingsPanel();

        mainPanel.add(dashboardPanel, "Dashboard");
        mainPanel.add(patientsPanel, "Patients");
        mainPanel.add(appointmentsPanel, "Appointments");
        mainPanel.add(recordsPanel, "Records");
        mainPanel.add(settingsPanel, "Settings");

        // Add event listeners to buttons
        dashboardButton.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
        patientsButton.addActionListener(e -> cardLayout.show(mainPanel, "Patients"));
        appointmentsButton.addActionListener(e -> cardLayout.show(mainPanel, "Appointments"));
        recordsButton.addActionListener(e -> cardLayout.show(mainPanel, "Records"));
        settingsButton.addActionListener(e -> cardLayout.show(mainPanel, "Settings"));

        // Layout setup
        setLayout(new BorderLayout());
        add(sidePanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel totalPatients = new JLabel("Total Patients: 1,234");
        JLabel appointmentsToday = new JLabel("Appointments Today: 48");
        JLabel activeRecords = new JLabel("Active Records: 892");
        JLabel patientSatisfaction = new JLabel("Patient Satisfaction: 95%");

        panel.add(totalPatients);
        panel.add(appointmentsToday);
        panel.add(activeRecords);
        panel.add(patientSatisfaction);

        return panel;
    }

    private JPanel createPatientsPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Patients Page"));
        return panel;
    }

    private JPanel createAppointmentsPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Appointments Page"));
        return panel;
    }

    private JPanel createRecordsPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Records Page"));
        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Settings Page"));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalManagementSystem().setVisible(true));
    }
}
