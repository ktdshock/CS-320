// Kenneth Dandrow

package Tests;

import static org.junit.jupiter.api.Assertions.*;
import Contact.Contact;
import org.junit.jupiter.api.Test;

public class ContactTest {

    // Test for successful contact creation
    @Test
    public void testContactCreationSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    // Test for invalid contact ID (too long)
    @Test
    public void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
        });
    }

    // Test for invalid first name (too long)
    @Test
    public void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "JohnathanDoe", "Doe", "1234567890", "123 Main St");
        });
    }

    // Test for invalid last name (too long)
    @Test
    public void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "DoeSmithson", "1234567890", "123 Main St");
        });
    }

    // Test for invalid phone number (too short)
    @Test
    public void testPhoneNotTenDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "Doe", "12345", "123 Main St");
        });
    }

    // Test for invalid phone number (non-numeric characters)
    @Test
    public void testPhoneNonNumeric() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "Doe", "12345abcdz", "123 Main St");
        });
    }

    // Test for invalid address (too long)
    @Test
    public void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "Doe", "1234567890", "This address is definitely more than thirty characters");
        });
    }

    // Test for null contact ID
    @Test
    public void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main St");
        });
    }

    // Test for null first name
    @Test
    public void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", null, "Doe", "1234567890", "123 Main St");
        });
    }

    // Test for null last name
    @Test
    public void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", null, "1234567890", "123 Main St");
        });
    }

    // Test for null phone
    @Test
    public void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "Doe", null, "123 Main St");
        });
    }

    // Test for null address
    @Test
    public void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("1", "John", "Doe", "1234567890", null);
        });
    }

    // Test for setter: valid first name update
    @Test
    public void testSetFirstNameSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    // Test for setter: invalid first name update (too long)
    @Test
    public void testSetFirstNameTooLong() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("JohnathanDoe"));
    }

    // Test for setter: valid last name update
    @Test
    public void testSetLastNameSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());
    }

    // Test for setter: invalid last name update (too long)
    @Test
    public void testSetLastNameTooLong() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("DoeSmithson"));
    }

    // Test for setter: valid phone update
    @Test
    public void testSetPhoneSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contact.setPhone("0987654321");
        assertEquals("0987654321", contact.getPhone());
    }

    // Test for setter: invalid phone update (non-numeric)
    @Test
    public void testSetPhoneNonNumeric() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("abcdefghij"));
    }

    // Test for setter: valid address update
    @Test
    public void testSetAddressSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contact.setAddress("456 Elm St");
        assertEquals("456 Elm St", contact.getAddress());
    }

    // Test for setter: invalid address update (too long)
    @Test
    public void testSetAddressTooLong() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("This address is definitely more than thirty characters"));
    }
}
