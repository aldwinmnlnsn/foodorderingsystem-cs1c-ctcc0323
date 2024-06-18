package restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class resto {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TitleWindow titleWindow = new TitleWindow();
                titleWindow.setVisible(true);
            }
        });
    }
}


class TitleWindow extends JFrame {

    public TitleWindow() {
        setTitle("SATISFEED");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);

        // Load the main image and create a JLabel for it
        ImageIcon imageIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/TitleWindow pic.png", 540, 720); // Adjust  width to half of JFrame width
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setPreferredSize(new Dimension(540, 720)); // Set preferred size for image

        // Load the small logo image
        ImageIcon logoIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/SATISFEED LOGO.png", 300, 300); // Adjust size as needed
        JLabel logoLabel = new JLabel(logoIcon);

        // Create JPanel for greetings and buttons
        JPanel textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Insets for padding

        // Add logo above the "Hello" label with reduced space
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center align the logo
        textPanel.add(logoLabel, gbc);

        JLabel helloLabel = new JLabel("Hello,");
        helloLabel.setFont(new Font("EVOGRIA", Font.BOLD, 30));
        helloLabel.setForeground(Color.BLACK); // Set color to black
        gbc.gridy++;
        textPanel.add(helloLabel, gbc);

        JLabel welcomeLabel = new JLabel("Welcome to SATISFEED!");
        welcomeLabel.setFont(new Font("EVOGRIA", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.BLACK); // Set color to black
        gbc.gridy++;
        textPanel.add(welcomeLabel, gbc);

        JButton proceedButton = new JButton("Proceed to Order");
        proceedButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        proceedButton.setBackground(Color.ORANGE);
        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                TabbedOrderWindow orderWindow = new TabbedOrderWindow();
                orderWindow.setVisible(true);
                dispose();
            }
        });
        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 0, 0); // Increase top padding for button
        textPanel.add(proceedButton, gbc);

        // Main panel to hold image and textPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(imageLabel, BorderLayout.WEST); // Image on the left
        mainPanel.add(textPanel, BorderLayout.CENTER); // Text on the right

        add(mainPanel);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Delete log.txt
                File log = new File("log.txt");
                if (log.exists()) {
                    log.delete();
                }
            }
        });
    }
    

    private ImageIcon createImageIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path); // Load the image
        Image image = icon.getImage(); // Transform it
        Image newimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale it smoothly
        return new ImageIcon(newimg); // Transform it back to ImageIcon
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TitleWindow titleWindow = new TitleWindow();
                titleWindow.setVisible(true);
            }
        });
    }
}


class TabbedOrderWindow extends JFrame {
    // Declare Spinners for Food
    private JSpinner porkSisigSpinner, chickenBBQSpinner, porkBBQSpinner,
            porkSinigangSpinner, crispyKareKareSpinner, bangusSisigSpinner;

    // Declare Spinners for Drinks
    private JSpinner icedTeaSpinner, pineappleJuiceSpinner, mangoJuiceSpinner, waterSpinner;

    // Declare Spinners for Desserts
    private JSpinner haloHaloSpinner, lecheFlanSpinner, cheesecakeSpinner;

    
    public TabbedOrderWindow() {
        setTitle("SATISFEED - Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel foodPanel = createFoodPanel();
        JPanel drinksPanel = createDrinksPanel();
        JPanel dessertsPanel = createDessertsPanel();

        tabbedPane.addTab("Food", foodPanel);
        tabbedPane.addTab("Drinks", drinksPanel);
        tabbedPane.addTab("Desserts", dessertsPanel);
        
        

        JButton checkoutButton = new JButton("View order summary");
        checkoutButton.setBackground(Color.ORANGE);

        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve quantities from TabbedOrderWindow
                int porkSisigQuantity = (int) porkSisigSpinner.getValue();
                int chickenBBQQuantity = (int) chickenBBQSpinner.getValue();
                int porkBBQQuantity = (int) porkBBQSpinner.getValue();
                int porkSinigangQuantity = (int) porkSinigangSpinner.getValue();
                int crispyKareKareQuantity = (int) crispyKareKareSpinner.getValue();
                int bangusSisigQuantity = (int) bangusSisigSpinner.getValue();

                int icedTeaQuantity = (int) icedTeaSpinner.getValue();
                int pineappleJuiceQuantity = (int) pineappleJuiceSpinner.getValue();
                int mangoJuiceQuantity = (int) mangoJuiceSpinner.getValue();
                int waterQuantity = (int) waterSpinner.getValue();

                int haloHaloQuantity = (int) haloHaloSpinner.getValue();
                int lecheFlanQuantity = (int) lecheFlanSpinner.getValue();
                int cheesecakeQuantity = (int) cheesecakeSpinner.getValue();

                // Create order summary window with quantities
                OrderSummaryWindow orderSummaryWindow = new OrderSummaryWindow(
                        porkSisigQuantity, chickenBBQQuantity, porkBBQQuantity,
                        porkSinigangQuantity, crispyKareKareQuantity, bangusSisigQuantity,
                        icedTeaQuantity, pineappleJuiceQuantity, mangoJuiceQuantity,
                        waterQuantity, haloHaloQuantity, lecheFlanQuantity, cheesecakeQuantity);
             
                dispose();
                orderSummaryWindow.setVisible(true);
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(checkoutButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createFoodPanel() {
        JPanel foodPanel = new JPanel();
        foodPanel.setLayout(new GridLayout(2, 3, 10, 10)); // 2 rows, 3 columns with gaps
        foodPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel
        
        
        // Pork Sisig
        JLabel porkSisigLabel = new JLabel("Pork Sisig", JLabel.CENTER);
        porkSisigLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon porkSisigIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/pork sisigg.png", 250, 250);
        JLabel porkSisigImageLabel = new JLabel(porkSisigIcon, JLabel.CENTER);
        porkSisigSpinner = createSpinner();
        foodPanel.add(createItemPanelWithImage(porkSisigImageLabel, porkSisigLabel, porkSisigSpinner));

        // Chicken BBQ
        JLabel chickenBBQLabel = new JLabel("Chicken BBQ", JLabel.CENTER);
        chickenBBQLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon chickenBBQIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/chicken bbq.png", 250, 250);
        JLabel chickenBBQImageLabel = new JLabel(chickenBBQIcon, JLabel.CENTER);
        chickenBBQSpinner = createSpinner();
        foodPanel.add(createItemPanelWithImage(chickenBBQImageLabel, chickenBBQLabel, chickenBBQSpinner));

        // Pork BBQ
        JLabel porkBBQLabel = new JLabel("Pork BBQ", JLabel.CENTER);
        porkBBQLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon porkBBQIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/pork bbq.png", 250, 250);
        JLabel porkBBQImageLabel = new JLabel(porkBBQIcon, JLabel.CENTER);
        porkBBQSpinner = createSpinner();
        foodPanel.add(createItemPanelWithImage(porkBBQImageLabel, porkBBQLabel, porkBBQSpinner));

        // Pork Sinigang
        JLabel porkSinigangLabel = new JLabel("Pork Sinigang", JLabel.CENTER);
        porkSinigangLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon porkSinigangIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/pork sinigang.png", 250, 250);
        JLabel porkSinigangImageLabel = new JLabel(porkSinigangIcon, JLabel.CENTER);
        porkSinigangSpinner = createSpinner();
        foodPanel.add(createItemPanelWithImage(porkSinigangImageLabel, porkSinigangLabel, porkSinigangSpinner));

        // Crispy Kare-Kare
        JLabel crispyKareKareLabel = new JLabel("Crispy Kare-Kare", JLabel.CENTER);
        crispyKareKareLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon crispyKareKareIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/crispy kare-kare.png", 250, 250);
        JLabel crispyKareKareImageLabel = new JLabel(crispyKareKareIcon, JLabel.CENTER);
        crispyKareKareSpinner = createSpinner();
        foodPanel.add(createItemPanelWithImage(crispyKareKareImageLabel, crispyKareKareLabel, crispyKareKareSpinner));

        // Bangus Sisig
        JLabel bangusSisigLabel = new JLabel("Bangus Sisig", JLabel.CENTER);
        bangusSisigLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon bangusSisigIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/BANGUS SISIG.png", 250, 250);
        JLabel bangusSisigImageLabel = new JLabel(bangusSisigIcon, JLabel.CENTER);
        bangusSisigSpinner = createSpinner();
        foodPanel.add(createItemPanelWithImage(bangusSisigImageLabel, bangusSisigLabel, bangusSisigSpinner));

        return foodPanel;
	}

	private JPanel createDrinksPanel() {
        JPanel drinksPanel = new JPanel();
        drinksPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns with gaps
        drinksPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Iced Tea
        JLabel icedTeaLabel = new JLabel("Iced Tea", JLabel.CENTER);
        icedTeaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon icedTeaIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/iced tea.png", 250, 250);
        JLabel icedTeaImageLabel = new JLabel(icedTeaIcon, JLabel.CENTER);
        icedTeaSpinner = createSpinner();
        drinksPanel.add(createItemPanelWithImage(icedTeaImageLabel, icedTeaLabel, icedTeaSpinner));

        // Pineapple Juice
        JLabel pineappleJuiceLabel = new JLabel("Pineapple Juice", JLabel.CENTER);
        pineappleJuiceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon pineappleJuiceIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/pineapple juice.png", 250, 250);
        JLabel pineappleJuiceImageLabel = new JLabel(pineappleJuiceIcon, JLabel.CENTER);
        pineappleJuiceSpinner = createSpinner();
        drinksPanel.add(createItemPanelWithImage(pineappleJuiceImageLabel, pineappleJuiceLabel, pineappleJuiceSpinner));

        // Mango Juice
        JLabel mangoJuiceLabel = new JLabel("Mango Juice", JLabel.CENTER);
        mangoJuiceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon mangoJuiceIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/mango juice.png", 250, 250);
        JLabel mangoJuiceImageLabel = new JLabel(mangoJuiceIcon, JLabel.CENTER);
        mangoJuiceSpinner = createSpinner();
        drinksPanel.add(createItemPanelWithImage(mangoJuiceImageLabel, mangoJuiceLabel, mangoJuiceSpinner));

        // Water
        JLabel waterLabel = new JLabel("Water", JLabel.CENTER);
        waterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon waterIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/water.png", 250, 250);
        JLabel waterImageLabel = new JLabel(waterIcon, JLabel.CENTER);
        waterSpinner = createSpinner();
        drinksPanel.add(createItemPanelWithImage(waterImageLabel, waterLabel, waterSpinner));

        return drinksPanel;
    }

	private JPanel createDessertsPanel() {
	    JPanel dessertsPanel = new JPanel();
	    dessertsPanel.setLayout(new GridLayout(2, 1, 10, 10)); // 2 rows, 1 column with gaps
	    dessertsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel
	    
	    // Halo-Halo
	    JLabel haloHaloLabel = new JLabel("Halo-Halo", JLabel.CENTER);
	    haloHaloLabel.setFont(new Font("Arial", Font.BOLD, 14));
	    ImageIcon haloHaloIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/halo halo.png", 250, 250);
	    JLabel haloHaloImageLabel = new JLabel(haloHaloIcon, JLabel.CENTER);
	    haloHaloSpinner = createSpinner();
	    dessertsPanel.add(createItemPanelWithImage(haloHaloImageLabel, haloHaloLabel, haloHaloSpinner));

	    // Leche Flan
	    JLabel lecheFlanLabel = new JLabel("Leche Flan", JLabel.CENTER);
	    lecheFlanLabel.setFont(new Font("Arial", Font.BOLD, 14));
	    ImageIcon lecheFlanIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/leche flan.png", 250, 250);
	    JLabel lecheFlanImageLabel = new JLabel(lecheFlanIcon, JLabel.CENTER);
	    lecheFlanSpinner = createSpinner();
	    dessertsPanel.add(createItemPanelWithImage(lecheFlanImageLabel, lecheFlanLabel, lecheFlanSpinner));

	    // Cheesecake
	    JLabel cheesecakeLabel = new JLabel("Cheesecake", JLabel.CENTER);
	    cheesecakeLabel.setFont(new Font("Arial", Font.BOLD, 14));
	    ImageIcon cheesecakeIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/cheesecake.png", 250, 250);
	    JLabel cheesecakeImageLabel = new JLabel(cheesecakeIcon, JLabel.CENTER);
	    cheesecakeSpinner = createSpinner();

	    // Create a panel for the cheesecake item with centered alignment
	    JPanel cheesecakePanel = createItemPanelWithImage(cheesecakeImageLabel, cheesecakeLabel, cheesecakeSpinner);
	    cheesecakePanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

	    dessertsPanel.add(cheesecakePanel);

	    // New text label for the cheesecake description
	    JLabel cheesecakeDescriptionLabel = new JLabel("<html><div style='font-family: EVOGRIA; font-size: 40px; text-align: center;'>Savoring Satisfaction, One Bite at a Time</div></html>");
	    cheesecakeDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
	    dessertsPanel.add(cheesecakeDescriptionLabel);

	    return dessertsPanel;
	}

    private JSpinner createSpinner() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 10, 1); // Initial value, min value, max value, step
        return new JSpinner(spinnerModel);
    }

        private JPanel createItemPanelWithImage(JLabel imageLabel, JLabel itemLabel, JSpinner spinner) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.add(imageLabel, BorderLayout.CENTER);

            JPanel detailsPanel = new JPanel(new BorderLayout());
            detailsPanel.add(itemLabel, BorderLayout.NORTH);
            detailsPanel.add(spinner, BorderLayout.CENTER);

            itemPanel.add(detailsPanel, BorderLayout.SOUTH);
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

            return itemPanel;
    }

    private ImageIcon createImageIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path); // Load the image
        Image image = icon.getImage(); // Transform it
        Image newimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale it smoothly
        return new ImageIcon(newimg); // Transform it back to ImageIcon
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TabbedOrderWindow orderWindow = new TabbedOrderWindow();
                orderWindow.setVisible(true);
                
    
            }
        });
    }
}

class OrderSummaryWindow extends JFrame {
	private static int orderCount = 0; 
	 private int orderNumber;
    private double totalPrice;

    public OrderSummaryWindow(
            int porkSisigQuantity, int chickenBBQQuantity, int porkBBQQuantity,
            int porkSinigangQuantity, int crispyKareKareQuantity, int bangusSisigQuantity,
            int icedTeaQuantity, int pineappleJuiceQuantity, int mangoJuiceQuantity,
            int waterQuantity, int haloHaloQuantity, int lecheFlanQuantity, int cheesecakeQuantity) {
    	
    	orderCount++; // Increment order count
        orderNumber = orderCount; // Set current order number

        setTitle("SATISFEED - Order Summary");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 700);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        
        totalPrice = calculateTotalPrice(porkSisigQuantity, chickenBBQQuantity, porkBBQQuantity,
                porkSinigangQuantity, crispyKareKareQuantity, bangusSisigQuantity,
                icedTeaQuantity, pineappleJuiceQuantity, mangoJuiceQuantity,
                waterQuantity, haloHaloQuantity, lecheFlanQuantity, cheesecakeQuantity);

        // Load the logo image
        ImageIcon logoIcon = createImageIcon("C:/Users/arvin/Desktop/SATISFEED/SATISFEED LOGO.png", 200, 200); // Adjust size as needed
        JLabel logoLabel = new JLabel(logoIcon, JLabel.CENTER);

        // Create the order summary label with HTML formatting
        JLabel orderSummaryLabel = new JLabel("<html><div style='text-align: center; font-size: 18pt;'>Order Summary: <br><br>"
                + generateSummaryHTML(porkSisigQuantity, chickenBBQQuantity, porkBBQQuantity,
                porkSinigangQuantity, crispyKareKareQuantity, bangusSisigQuantity,
                icedTeaQuantity, pineappleJuiceQuantity, mangoJuiceQuantity,
                waterQuantity, haloHaloQuantity, lecheFlanQuantity, cheesecakeQuantity)
                + "<br>Total Price: " + calculateTotalPrice(porkSisigQuantity, chickenBBQQuantity, porkBBQQuantity,
                porkSinigangQuantity, crispyKareKareQuantity, bangusSisigQuantity,
                icedTeaQuantity, pineappleJuiceQuantity, mangoJuiceQuantity,
                waterQuantity, haloHaloQuantity, lecheFlanQuantity, cheesecakeQuantity) + " pesos</div></html>");

        orderSummaryLabel.setFont(new Font("EVOGRIA", Font.BOLD, 14));

        // Center align the order summary label
        orderSummaryLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create button panel with Checkout and Back buttons
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(Color.ORANGE);
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter cash amount:");
                try {
                    double cash = Double.parseDouble(input);
                    if (cash >= totalPrice) {
                        double change = cash - totalPrice;
                        JOptionPane.showMessageDialog(null, "Change: " + change + " pesos");
                        JOptionPane.showMessageDialog(null, "<html><div style='text-align: center;'>Thank you for dining in with SATISFEED! <br>Your order number is " + String.format("%02d", orderNumber) + ".</div></html>");

                        writeOrderToFile(porkSisigQuantity, chickenBBQQuantity, porkBBQQuantity,
                                porkSinigangQuantity, crispyKareKareQuantity, bangusSisigQuantity,
                                icedTeaQuantity, pineappleJuiceQuantity, mangoJuiceQuantity,
                                waterQuantity, haloHaloQuantity, lecheFlanQuantity, cheesecakeQuantity, totalPrice, cash, change);
                        
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                TitleWindow titleWindow = new TitleWindow();
                                titleWindow.setVisible(true);
                            }
                        });
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient cash amount.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.ORANGE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                TabbedOrderWindow orderWindow = new TabbedOrderWindow();
                orderWindow.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkoutButton);
        buttonPanel.add(backButton);

        // Main panel to hold everything
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add logo at the top
        panel.add(logoLabel, BorderLayout.NORTH);

        // Add centered order summary label
        panel.add(orderSummaryLabel, BorderLayout.CENTER);

        // Add button panel at the bottom
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Delete log.txt
                File log = new File("log.txt");
                if (log.exists()) {
                    log.delete();
                }
            }
        });
    }

    private String generateSummaryHTML(
            int porkSisigQuantity, int chickenBBQQuantity, int porkBBQQuantity,
            int porkSinigangQuantity, int crispyKareKareQuantity, int bangusSisigQuantity,
            int icedTeaQuantity, int pineappleJuiceQuantity, int mangoJuiceQuantity,
            int waterQuantity, int haloHaloQuantity, int lecheFlanQuantity, int cheesecakeQuantity) {

        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");

        if (porkSisigQuantity > 0) {
            sb.append("<li>Pork Sisig (" + porkSisigQuantity + ")</li>");
        }
        if (chickenBBQQuantity > 0) {
            sb.append("<li>Chicken BBQ (" + chickenBBQQuantity + ")</li>");
        }
        if (porkBBQQuantity > 0) {
            sb.append("<li>Pork BBQ (" + porkBBQQuantity + ")</li>");
        }
        if (porkSinigangQuantity > 0) {
            sb.append("<li>Pork Sinigang (" + porkSinigangQuantity + ")</li>");
        }
        if (crispyKareKareQuantity > 0) {
            sb.append("<li>Crispy Kare-Kare (" + crispyKareKareQuantity + ")</li>");
        }
        if (bangusSisigQuantity > 0) {
            sb.append("<li>Bangus Sisig (" + bangusSisigQuantity + ")</li>");
        }
        if (icedTeaQuantity > 0) {
            sb.append("<li>Iced Tea (" + icedTeaQuantity + ")</li>");
        }
        if (pineappleJuiceQuantity > 0) {
            sb.append("<li>Pineapple Juice (" + pineappleJuiceQuantity + ")</li>");
        }
        if (mangoJuiceQuantity > 0) {
            sb.append("<li>Mango Juice (" + mangoJuiceQuantity + ")</li>");
        }
        if (waterQuantity > 0) {
            sb.append("<li>Water (" + waterQuantity + ")</li>");
        }
        if (haloHaloQuantity > 0) {
            sb.append("<li>Halo-Halo (" + haloHaloQuantity + ")</li>");
        }
        if (lecheFlanQuantity > 0) {
            sb.append("<li>Leche Flan (" + lecheFlanQuantity + ")</li>");
        }
        if (cheesecakeQuantity > 0) {
            sb.append("<li>Cheesecake (" + cheesecakeQuantity + ")</li>");
        }

        sb.append("</ul>");
        return sb.toString();
    }

    private double calculateTotalPrice(
            int porkSisigQuantity, int chickenBBQQuantity, int porkBBQQuantity,
            int porkSinigangQuantity, int crispyKareKareQuantity, int bangusSisigQuantity,
            int icedTeaQuantity, int pineappleJuiceQuantity, int mangoJuiceQuantity,
            int waterQuantity, int haloHaloQuantity, int lecheFlanQuantity, int cheesecakeQuantity) {

        double totalPrice = 0;
        totalPrice += porkSisigQuantity * 130;
        totalPrice += chickenBBQQuantity * 100;
        totalPrice += porkBBQQuantity * 120;
        totalPrice += porkSinigangQuantity * 125;
        totalPrice += crispyKareKareQuantity * 110;
        totalPrice += bangusSisigQuantity * 95;
        totalPrice += icedTeaQuantity * 45;
        totalPrice += pineappleJuiceQuantity * 40;
        totalPrice += mangoJuiceQuantity * 40;
        totalPrice += waterQuantity * 20;
        totalPrice += haloHaloQuantity * 60;
        totalPrice += lecheFlanQuantity * 50;
        totalPrice += cheesecakeQuantity * 70;

        return totalPrice;
    }
    private void writeOrderToFile(
            int porkSisigQuantity, int chickenBBQQuantity, int porkBBQQuantity,
            int porkSinigangQuantity, int crispyKareKareQuantity, int bangusSisigQuantity,
            int icedTeaQuantity, int pineappleJuiceQuantity, int mangoJuiceQuantity,
            int waterQuantity, int haloHaloQuantity, int lecheFlanQuantity, int cheesecakeQuantity,
            double totalPrice, double cash, double change) {

        String filename = "log.txt";

        try (FileWriter writer = new FileWriter(filename, true)) {
            // Append order details to file
            writer.write("Order Number: " + String.format("%02d", orderNumber) + "\n");
            writer.write("Items Ordered:\n");

            if (porkSisigQuantity > 0) {
                writer.write("Pork Sisig: " + porkSisigQuantity + "\n");
            }
            if (chickenBBQQuantity > 0) {
                writer.write("Chicken BBQ: " + chickenBBQQuantity + "\n");
            }
            if (porkBBQQuantity > 0) {
                writer.write("Pork BBQ: " + porkBBQQuantity + "\n");
            }
            if (porkSinigangQuantity > 0) {
                writer.write("Pork Sinigang: " + porkSinigangQuantity + "\n");
            }
            if (crispyKareKareQuantity > 0) {
                writer.write("Crispy Kare-Kare: " + crispyKareKareQuantity + "\n");
            }
            if (bangusSisigQuantity > 0) {
                writer.write("Bangus Sisig: " + bangusSisigQuantity + "\n");
            }
            if (icedTeaQuantity > 0) {
                writer.write("Iced Tea: " + icedTeaQuantity + "\n");
            }
            if (pineappleJuiceQuantity > 0) {
                writer.write("Pineapple Juice: " + pineappleJuiceQuantity + "\n");
            }
            if (mangoJuiceQuantity > 0) {
                writer.write("Mango Juice: " + mangoJuiceQuantity + "\n");
            }
            if (waterQuantity > 0) {
                writer.write("Water: " + waterQuantity + "\n");
            }
            if (haloHaloQuantity > 0) {
                writer.write("Halo-Halo: " + haloHaloQuantity + "\n");
            }
            if (lecheFlanQuantity > 0) {
                writer.write("Leche Flan: " + lecheFlanQuantity + "\n");
            }
            if (cheesecakeQuantity > 0) {
                writer.write("Cheesecake: " + cheesecakeQuantity + "\n");
            }

            writer.write("Total Price - " + totalPrice + " pesos\n");
            writer.write("Cash Input - " + cash + " pesos\n");
            writer.write("Change - " + change + " pesos\n\n");
            writer.write("\n---------------------------------------------");
            
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
   
    
   

    private ImageIcon createImageIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path); // Load the image
        Image image = icon.getImage(); // Transform it
        Image newimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale it smoothly
        return new ImageIcon(newimg); // Transform it back to ImageIcon

    }
    
}

