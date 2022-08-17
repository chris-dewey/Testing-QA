package edu.snhu;

import java.util.ArrayList;
import java.util.Objects;


/** Singleton ContactService Class  **/
public class ContactService {
    // Holds single instance
    private static ContactService singleton_instance = null;
    // ArrayList to hold all contacts
    private static final ArrayList<Contact> contacts = new ArrayList<>();

    /**
     * Constructor
     * @return Singleton ContactService Instance
     */
    public static ContactService getInstance() {
        if (singleton_instance == null) {
            singleton_instance = new ContactService();
        }
        return singleton_instance;
    }

    /**
     * Add a contact to contact list.
     * @param contact: Contact object with uniqueId
     * @return true if successful addition.
     * @throws IllegalArgumentException if contactId is not unique.
     */
    public boolean addContact(Contact contact) {
        for (Contact existingContact: contacts) {
            if (existingContact.getContactId().equals(contact.getContactId())) {
                throw new IllegalArgumentException("Contact already in contacts");
            }
        }
        contacts.add(contact);
        return true;
    }

    /**
     * Delete a contact from contact list.
     * @param contactId: String identifying contact in contact list
     * @return true if successful, false if no contact to delete
     */
    public boolean deleteContact(String contactId) {
        return contacts.removeIf(contact -> Objects.equals(contact.getContactId(), contactId));
    }

    /**
     * Replace existing contact in contact list.
     * @param contactId: String identifying contact to update in contact list
     * @param contact: Replacement Contact object.
     * @return true if successful
     * @throws IllegalArgumentException if contactId not found in contact list.
     */
    public boolean replaceContact(String contactId, Contact contact) {
        if (deleteContact(contactId)) {
            return addContact(contact);
        }
        throw new IllegalArgumentException("No matching contactId");
    }

    /**
     * Update existing contact in contact list
     * @param contactId: String identifying contact to update in contact list.
     * @param firstName: replacement first name: String max 10 characters
     * @param lastName: replacement last name: String max 10 characters
     * @param number: replacement phone number: String must be 10 characters
     * @param address: replacement address: String max 30 characters
     * @return true if update successful
     * @throws IllegalArgumentException if contactId not found in contact list.
     */
    public boolean updateContact(String contactId, String firstName, String lastName, String number, String address) {
        // Check if any values are invalid - throws exception if an invalid parameter is entered
        new Contact(firstName, lastName, number, address, contactId);
        // Search contact list for match. If found replace existing attributes with provided parameters.
        for (Contact contact : contacts) {
            if (contact.getContactId().equals(contactId)) {
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setPhone(number);
                contact.setAddress(address);
                return true;
            }
        }
        throw new IllegalArgumentException("No matching contactId");
    }

    /**
     * Get all contacts in list.
     * @return ArrayList of contacts.
     */
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    /**
     * Retrieve a contact from contact list.
     * @param contactId: String identifying contact
     * @return Contact object with matching contactId
     */
    public Contact getContact(String contactId) {
        for (Contact contact: contacts) {
            if (contact.getContactId().equals(contactId)) {
                return contact;
            }
        }
        return null;
    }

    // FIXME: Remove after testing
    public void deleteAllContacts() {
        contacts.clear();
    }
}
