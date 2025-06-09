import util.ObjectPlus;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class Admin_OrderUI {
    private JButton button1;
    private JButton showOrdersButton;
    private JPanel panel1;
    private JTable table1;
    private JFrame frame;

    private Class<?> categoryClass;

    public Admin_OrderUI(Class<?> categoryClass) {
        this.categoryClass = categoryClass;

        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        // Dodana kolumna "Zdjęcie"
        String[] columnNames = {"Zdjęcie", "Name", "Price", "Stock", "Quantity"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Tylko "Quantity" edytowalne
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return ImageIcon.class; // Zdjęcie
                if (columnIndex == 2) return Double.class;
                if (columnIndex == 3) return Integer.class;
                if (columnIndex == 4) return Integer.class;
                return String.class;
            }
        };

        table1 = new JTable(tableModel);
        table1.setRowHeight(60); // Większa wysokość na miniaturę
        JScrollPane tableScroll = new JScrollPane(table1);
        panel1.add(tableScroll, BorderLayout.CENTER);

        // Szerokość kolumny na zdjęcie
        table1.getColumnModel().getColumn(0).setPreferredWidth(60);

        button1 = new JButton("Order");
        panel1.add(button1, BorderLayout.SOUTH);

        showOrdersButton = new JButton("Show All Orders");
        panel1.add(showOrdersButton, BorderLayout.NORTH);

        frame = new JFrame("Admin Order Management");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        fillProductTableByCategory();

        button1.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            int rowCount = model.getRowCount();
            HashMap<Product, Integer> orderMap = new HashMap<>();
            StringBuilder summary = new StringBuilder("Ordered:\n");
            double totalCost = 0;

            for (int i = 0; i < rowCount; i++) {
                int quantity = 0;
                Object quantityObj = model.getValueAt(i, 4);
                if (quantityObj instanceof Integer) {
                    quantity = (Integer) quantityObj;
                } else if (quantityObj instanceof String && !((String) quantityObj).isEmpty()) {
                    try {
                        quantity = Integer.parseInt((String) quantityObj);
                    } catch (NumberFormatException ignored) {}
                }
                if (quantity > 0) {
                    String productName = (String) model.getValueAt(i, 1);
                    double price = (double) model.getValueAt(i, 2);
                    for (Object obj : ObjectPlus.getExtentFromClass(categoryClass)) {
                        Product product = (Product) obj;
                        if (product.getName().equals(productName)) {
                            orderMap.put(product, quantity);
                            summary.append("- ").append(productName)
                                    .append(" x ").append(quantity)
                                    .append(" = ").append(String.format("%.2f", price * quantity)).append(" zł\n");
                            totalCost += price * quantity;
                            break;
                        }
                    }
                }
            }

            if (orderMap.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No products has been selected");
                return;
            }

            OrderToShop order = new OrderToShop(orderMap, (Admin) Main.person);
            try {
                ObjectPlus.saveExtent();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error while saving extent: " + ex.getMessage());
                ex.printStackTrace();
            }
            summary.append("\nTotal cost: ").append(String.format("%.2f", totalCost)).append(" zł");
            JOptionPane.showMessageDialog(frame, summary.toString());
            fillProductTableByCategory();
        });

        showOrdersButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("OrderToShop Browser");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new OrderToShopBrowser().getContentPane());
                frame.setSize(800, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        });
    }

    public void fillProductTableByCategory() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        if (categoryClass != null) {
            for (Object obj : ObjectPlus.getExtentFromClass(categoryClass)) {
                Product product = (Product) obj;
                ImageIcon icon = null;
                if (product.getIMG_PATH() != null && !product.getIMG_PATH().isEmpty()) {
                    icon = new ImageIcon(product.getIMG_PATH());
                    // Skalowanie do miniatury np. 50x50 px
                    Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(img);
                }
                Object[] row = {
                        icon,
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        0
                };
                model.addRow(row);
            }
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
