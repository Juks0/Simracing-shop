import util.ObjectPlus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class OrderToShopBrowser {
    private JPanel contentPane;
    private JList<OrderToShop> orderList;
    private JTable productTable;
    private DefaultListModel<OrderToShop> orderListModel;
    private DefaultTableModel productTableModel;

    public OrderToShopBrowser() {
        contentPane = new JPanel(new BorderLayout());

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.setCellRenderer(new OrderToShopCellRenderer());
        JScrollPane orderScroll = new JScrollPane(orderList);
        orderScroll.setPreferredSize(new Dimension(300, 400));
        contentPane.add(orderScroll, BorderLayout.WEST);

        String[] columnNames = {"Serial", "Name", "Price", "Quantity", "Total"};
        productTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        productTable = new JTable(productTableModel);
        JScrollPane productScroll = new JScrollPane(productTable);
        contentPane.add(productScroll, BorderLayout.CENTER);

        orderList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showProductsForSelectedOrder();
            }
        });

        fillOrderList();
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    private void fillOrderList() {
        orderListModel.clear();
        for (OrderToShop order : ObjectPlus.getExtentFromClass(OrderToShop.class)) {
            orderListModel.addElement(order);
        }
        if (!orderListModel.isEmpty()) {
            orderList.setSelectedIndex(0);
        }
    }

    private void showProductsForSelectedOrder() {
        productTableModel.setRowCount(0);
        OrderToShop selectedOrder = orderList.getSelectedValue();
        if (selectedOrder == null) return;

        HashMap<Product, Integer> orderMap = selectedOrder.getOrders();
        for (Map.Entry<Product, Integer> entry : orderMap.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            double price = p.getPrice();
            double total = price * qty;
            productTableModel.addRow(new Object[]{
                    p.getSerial(),
                    p.getName(),
                    String.format("%.2f", price),
                    qty,
                    String.format("%.2f", total)
            });
        }
    }

    private static class OrderToShopCellRenderer extends DefaultListCellRenderer {
        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof OrderToShop) {
                OrderToShop order = (OrderToShop) value;
                StringBuilder sb = new StringBuilder();
                sb.append("Order date: ").append(sdf.format(order.getDate()))
                        .append(" | Products: ").append(order.getOrders().size());
                setText(sb.toString());
            }
            return c;
        }
    }

//    public static void main(String[] args) {
//        try {
//            ObjectPlus.loadExtent();
//        } catch (Exception e) {
//            System.out.println("Nie udało się załadować ekstensji: " + e.getMessage());
//        }
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("OrderToShop Browser");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setContentPane(new OrderToShopBrowser().getContentPane());
//            frame.setSize(800, 400);
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }
}
