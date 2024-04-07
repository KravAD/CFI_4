package CreacionAlmacen;
import java.util.ArrayList;
import java.util.List;
public class Gestor {
    private List<Contact> contacts;

    public Gestor() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
