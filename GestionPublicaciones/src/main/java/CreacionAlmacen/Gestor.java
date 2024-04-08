package CreacionAlmacen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Gestor extends JDialog {
    private Gestor gestor;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton addContactButton;
    private JButton removeContactButton;
    private JTable contactTable;
    private DefaultTableModel contactTableModel;

    private java.util.List<Contact> contacts;

    public Gestor() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }
    public Gestor(JFrame parent) {
            super(parent, "Contact Manager", true);
            setLayout(new BorderLayout());

            gestor = new Gestor();
            addContactButton = new JButton("Add Contact");
            removeContactButton = new JButton("Remove Contact");

            nameField = new JTextField(20);
            emailField = new JTextField(20);
            phoneField = new JTextField(20);

            JPanel contactButtonPanel = new JPanel(new FlowLayout());

            contactButtonPanel.add(addContactButton);
            contactButtonPanel.add(removeContactButton);

            add(contactButtonPanel, BorderLayout.NORTH);

            JPanel info = new JPanel(new FlowLayout());
            info.add(new JTextField("Name:"));
            info.add(new JTextField("Email:"));
            info.add(new JTextField("Phone:"));
            add(info, BorderLayout.CENTER);

            contactTableModel = new DefaultTableModel();

            addContactButton.addActionListener(e -> {
                Contact contact = new Contact(nameField.getText(), emailField.getText(), phoneField.getText());
                gestor.addContact(contact);
                contactTableModel.addRow(new Object[]{contact.getName(), contact.getEmail(), contact.getNumber()});
            });

            removeContactButton.addActionListener(e -> {
                int selectedRow = contactTable.getSelectedRow();
                if (selectedRow != -1) {
                    gestor.removeContact(gestor.getContacts().get(selectedRow));
                    contactTableModel.removeRow(selectedRow);
                }
            });

        contactTableModel = new DefaultTableModel(new Object[]{"Name", "Email", "Phone"}, 0);

        contactTable = new JTable(contactTableModel);

        add(new JScrollPane(contactTable), BorderLayout.CENTER);

        addContactButton.addActionListener(e -> {
            Contact contact = new Contact(nameField.getText(), emailField.getText(), phoneField.getText());
            gestor.addContact(contact);
            contactTableModel.addRow(new Object[]{contact.getName(), contact.getEmail(), contact.getNumber()});
        });
            setSize(400, 300);
        }
    }




