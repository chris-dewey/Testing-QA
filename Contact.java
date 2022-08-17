package edu.snhu;


public class Contact {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    final private String contactId;


    /**
     * Contact constructor.
     * @param firstName: String, non-null, max 10 characters
     * @param lastName: String, non-null, max 10 characters
     * @param phone: String, non-null, must be exactly 10 characters
     * @param address: String, non-null, max 30 characters
     * @param contactId: String, non-null, must be unique, max 10 characters
     */
    public Contact (String firstName, String lastName, String phone, String address, String contactId) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Invalid contact id");
        }
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name");
        }
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name");
        }
        if (phone == null || phone.length() != 10) {
            throw new IllegalArgumentException("Invalid phone");
        }
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address");
        }

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;

    }

    /**     Getters & Setters    **/
    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
