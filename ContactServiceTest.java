package test;

import edu.snhu.Contact;
import edu.snhu.ContactService;
import org.junit.jupiter.api.*;

public class ContactServiceTest {
    ContactService contactService = ContactService.getInstance();
    Contact michael = new Contact("Michael", "Scott", "1234567891", "123 Main St", "101");
    Contact dwight = new Contact("Dwight", "Schrute", "4567891232", "1 Beets Dr", "102");
    Contact jim = new Contact("Jim", "Halpert","1234455888", "22 Quarry Ln", "101");
    @BeforeEach
    void deleteAll() {
        contactService.deleteAllContacts();
    }

    @Test
    void testAddingContacts() {
        // Add first contact
        Assertions.assertTrue(contactService.addContact(michael));
        // Check if michael is in contacts
        Assertions.assertTrue(contactService.getContacts().contains(michael));

        // Add second contact
        Assertions.assertTrue(contactService.addContact(dwight));
        // Check if dwight is in contacts
        Assertions.assertTrue(contactService.getContacts().contains(dwight));

    }

    @Test
    void testAddDuplicateContacts() {
        // Add michael then try to add jim who has same contactId as michael
        contactService.addContact(michael);
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.addContact(jim));

        // Add dwight then try to add dwight again
        contactService.addContact(dwight);
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.addContact(dwight));
    }

    @Test
    void testUpdateContacts() {
        contactService.addContact(michael);
        // Update single field
        Assertions.assertTrue(contactService.updateContact(
                "101", "Michael", "Scarn", "1234567890", "123 Main St"));

        // Update multiple fields
        Assertions.assertTrue(contactService.updateContact(
                "101", "Date", "Mike", "9876543210", "321 Main St"));

        // Update with replace method
        Assertions.assertTrue(contactService.replaceContact("101", michael));

        // Attempt to update contactId or update with non-existing contactId
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.updateContact(
                "100", "Date", "Mike", "9876543210", "321 Main St"));

        // Attempt to update to an invalid value
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.updateContact(
                "101", "Date", "Mike", "9", "321 Main St"));

        // Attempt to update to null
        Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.updateContact(
                "101", null, null, null, null));
    }

    @Test
    void testDeletingContacts() {
        contactService.addContact(michael);
        contactService.addContact(dwight);

        // Delete existing contacts
        Assertions.assertTrue(contactService.deleteContact(michael.getContactId()));
        Assertions.assertTrue(contactService.deleteContact(dwight.getContactId()));

        // Attempt to delete contact that does not exist
        Assertions.assertFalse(contactService.deleteContact("104"));
        Assertions.assertFalse(contactService.deleteContact(""));
    }

}
