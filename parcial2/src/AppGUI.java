import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AppGUI extends JFrame {
    private ArrayList<Product> products;

    private JTextArea displayArea;

    public AppGUI() {
        new ArrayList<>();
        products = new ArrayList<>();

        // Load existing data
        loadUsersFromFile();
        loadProductsFromFile();

        // Initialize GUI components
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        JButton registerUserButton = new JButton("Registrar Usuario");
        JButton viewProductsButton = new JButton("Ver Productos");
        JButton registerProductButton = new JButton("Registrar Producto");

        // Layout setup
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerUserButton);
        buttonPanel.add(viewProductsButton);
        buttonPanel.add(registerProductButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event handling
        registerUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });

        registerProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerProduct();
            }
        });

        // Set up JFrame
        setTitle("Registro de Usuarios y Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void registerUser() {
        // Similar implementation as before...
    }

    private void viewProducts() {
        displayArea.setText("Productos disponibles:\n");
        for (Product product : products) {
            displayArea.append(product.toString() + "\n");
        }
    }

    private void registerProduct() {
        JFrame registerProductFrame = new JFrame("Registrar Producto");
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField priceField = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(nameField);
        panel.add(new JLabel("Descripción:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Tipo:"));
        panel.add(typeField);
        panel.add(new JLabel("Precio:"));
        panel.add(priceField);

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                String type = typeField.getText();
                double price = Double.parseDouble(priceField.getText());

                Product newProduct = new Product(name, description, type, price);
                products.add(newProduct);

                // Save products to file
                saveProductsToFile();

                // Update the display
                displayArea.setText("Productos registrados:\n");
                for (Product product : products) {
                    displayArea.append(product.toString() + "\n");
                }

                // Close the registration window
                registerProductFrame.dispose();
            }
        });

        panel.add(registerButton);

        registerProductFrame.add(panel);
        registerProductFrame.setSize(300, 200);
        registerProductFrame.setLocationRelativeTo(null);
        registerProductFrame.setVisible(true);
    }

    private void loadUsersFromFile() {
        // Similar implementation as before...
    }

    private void saveProductsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/products.dat"))) {
            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProductsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/products.dat"))) {
            ArrayList<Product> object = (ArrayList<Product>) ois.readObject();
            products = object;
        } catch (IOException | ClassNotFoundException e) {
            // Handle file not found or other errors
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppGUI();
            }
        });
    }
}
