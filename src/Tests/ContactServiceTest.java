// Kenneth Dandrow

package Tests;

import static org.junit.jupiter.api.Assertions.*;
import Contact.Contact;
import Contact.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    // Test for adding a valid contact
    @Test
    public void testAddContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertEquals("John", service.getContact("1").getFirstName());
    }

    // Test for adding a contact with a duplicate ID
    @Test
    public void testAddDuplicateContact() {
        Contact contact1 = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("1", "Jane", "Doe", "0987654321", "456 Elm St");
        service.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(contact2); // Attempt to add duplicate contact
        });
    }

    // Test for deleting a valid contact
    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.deleteContact("1");
        assertNull(service.getContact("1"));
    }

    // Test for deleting a contact with a non-existent ID
    @Test
    public void testDeleteNonExistentContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("999")); // Deleting non-existent contact
    }

    // Test for updating a valid contact
    @Test
    public void testUpdateContact() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.updateContact("1", "Jane", null, null, null);
        assertEquals("Jane", service.getContact("1").getFirstName());
    }

    // Test for updating a non-existent contact
    @Test
    public void testUpdateNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("99", "Jane", null, null, null); // Updating non-existent contact
        });
    }

    // Test for updating contact with all fields
    @Test
    public void testUpdateContactAllFields() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.updateContact("1", "Jane", "Smith", "0987654321", "456 Elm St");
        Contact updatedContact = service.getContact("1");
        assertEquals("Jane", updatedContact.getFirstName());
        assertEquals("Smith", updatedContact.getLastName());
        assertEquals("0987654321", updatedContact.getPhone());
        assertEquals("456 Elm St", updatedContact.getAddress());
    }

    // Test for updating with empty fields (no change expected)
    @Test
    public void testUpdateWithEmptyFields() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.updateContact("1", "", "", "", ""); // Attempt to update with empty fields
        Contact unchangedContact = service.getContact("1");
        assertEquals("John", unchangedContact.getFirstName()); // No change expected
        assertEquals("Doe", unchangedContact.getLastName());
        assertEquals("1234567890", unchangedContact.getPhone());
        assertEquals("123 Main St", unchangedContact.getAddress());
    }
}
