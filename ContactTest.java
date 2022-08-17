package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.snhu.Contact;

public class ContactTest {

    @Test
    void testStandardContact() {
        Contact contact = new Contact(
                "Michael", "Scott", "7023352211", "1705 Sutton Place", "123");

        assertEquals("Michael", contact.getFirstName());
        assertEquals("Scott", contact.getLastName());
        assertEquals("7023352211", contact.getPhone());
        assertEquals("1705 Sutton Place", contact.getAddress());
        assertEquals("123", contact.getContactId());
    }

    @Test
    void testFieldTooLong() {
        // Test firstName too long
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                "MichaelMichaelMichael", "Scott", "1234567890", "123 Main St", "123"));

        // Test lastName too long
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                "Michael", "ScottScottScott", "1234567890", "123 Main St", "123"));

        // Test address too long
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                "Michael", "Scott", "1234567890", "123 Main St 123 Main St 123 Main St", "123"));

        // Test contactId too long
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                "Michael", "Scott", "1234567890", "123 Main St", "12345678911"));
    }

    @Test
    void testPhoneNumberLength() {
        // Phone number too long
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                "Michael", "Scott", "123456789", "123 Main St", "123"));

        // Phone number too short
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                "Michael", "Scott", "1234567", "123 Main St", "123"));
    }

    @Test
    void testFieldIsNull() {
        // Test firstName not null
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                null, "Scott", "1234567890", "123 Main St", "123"));

        // Test lastName not null
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                "Michael", null, "1234567890", "123 Main St", "123"));

        // Test address not null
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                "Michael", "Scott", "1234567890", null, "123"));

        // Test contactId not null
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Contact(
                "Michael", "Scott", "1234567890", "123 Main St", null));
    }
}
