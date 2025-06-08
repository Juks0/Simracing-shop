import javax.swing.*;

public class CategoriesUI {
    private JPanel panel1;
    private JList<String> list1;
    private JFrame frame;

    // Dodaj pole do przechowywania wybranej klasy
    private Class<?> selectedCategoryClass;

    public CategoriesUI() {
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fillListWithSubclassNames();

        frame = new JFrame("Admin UI");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 400);

        // Listener na wybór kategorii
        list1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String className = list1.getSelectedValue();
                // Zamień nazwę na klasę
                switch (className) {
                    case "Base":
                        selectedCategoryClass = Base.class;
                        break;
                    case "Wheel":
                        selectedCategoryClass = Wheel.class;
                        break;
                    case "Pedals":
                        selectedCategoryClass = Pedals.class;
                        break;
                    default:
                        selectedCategoryClass = null;
                }
                if (selectedCategoryClass != null) {
                    // Otwórz nowe okno Admin_OrderUI z wybraną klasą
                    Admin_OrderUI adminOrderUI = new Admin_OrderUI(selectedCategoryClass);
                    adminOrderUI.setVisible(true);
                    frame.dispose(); // zamknij obecne okno
                }
            }
        });
    }

    private void fillListWithSubclassNames() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Class<?>[] subclasses = {Base.class, Wheel.class, Pedals.class};
        for (Class<?> subclass : subclasses) {
            listModel.addElement(subclass.getSimpleName());
        }
        list1.setModel(listModel);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
