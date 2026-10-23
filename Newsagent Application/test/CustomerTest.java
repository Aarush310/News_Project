import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CustomerTest {

    // ===== Black-Box Tests (TC01 - TC24) =====

    // TC01
    // Test Objective: Verify first name is accepted when exactly 1 character long
    // Input(s): First Name = "J"
    // Expected Output(s): First name accepted
    @Test
    void validFirstNameMinBoundary_TC01() {
        Customer c = new Customer();
        assertTrue(c.verifyFirstName("J"));
    }

    // TC02
    // Test Objective: Verify first name is accepted when more than 1 character
    // Input(s): First Name = "John"
    // Expected Output(s): First name accepted
    @Test
    void validFirstName_TC02() {
        Customer c = new Customer();
        assertTrue(c.verifyFirstName("John"));
    }

    // TC03
    // Test Objective: Verify first name is rejected when blank
    // Input(s): First Name = ""
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameBlank_TC03() {
        Customer c = new Customer();
        assertFalse(c.verifyFirstName(""));
    }

    // TC04
    // Test Objective: Verify first name is rejected when only spaces
    // Input(s): First Name = "   "
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameSpaces_TC04() {
        Customer c = new Customer();
        assertFalse(c.verifyFirstName("   "));
    }

    // TC05
    // Test Objective: Verify surname is accepted when exactly 1 character long
    // Input(s): Surname = "S"
    // Expected Output(s): Surname accepted
    @Test
    void validSurnameMinBoundary_TC05() {
        Customer c = new Customer();
        assertTrue(c.verifySurname("S"));
    }

    // TC06
    // Test Objective: Verify surname is accepted when more than 1 character
    // Input(s): Surname = "Smith"
    // Expected Output(s): Surname accepted
    @Test
    void validSurname_TC06() {
        Customer c = new Customer();
        assertTrue(c.verifySurname("Smith"));
    }

    // TC07
    // Test Objective: Verify surname is rejected when blank
    // Input(s): Surname = ""
    // Expected Output(s): Error message: "Invalid Surname"
    @Test
    void invalidSurnameBlank_TC07() {
        Customer c = new Customer();
        assertFalse(c.verifySurname(""));
    }

    // TC08
    // Test Objective: Verify address is accepted when exactly 1 character long
    // Input(s): Address = "M"
    // Expected Output(s): Address accepted
    @Test
    void validAddressMinBoundary_TC08() {
        Customer c = new Customer();
        assertTrue(c.verifyAddress("M"));
    }

    // TC09
    // Test Objective: Verify address is accepted when more than 1 character
    // Input(s): Address = "Main Street, Athlone"
    // Expected Output(s): Address accepted
    @Test
    void validAddress_TC09() {
        Customer c = new Customer();
        assertTrue(c.verifyAddress("Main Street, Athlone"));
    }

    // TC10
    // Test Objective: Verify address is rejected when blank
    // Input(s): Address = ""
    // Expected Output(s): Error message: "Invalid Address"
    @Test
    void invalidAddressBlank_TC10() {
        Customer c = new Customer();
        assertFalse(c.verifyAddress(""));
    }

    // TC11
    // Test Objective: Verify phone number is accepted when exactly 10 digits
    // Input(s): Phone Number = "0871234567"
    // Expected Output(s): Phone number accepted
    @Test
    void validPhoneNumber_TC11() {
        Customer c = new Customer();
        assertTrue(c.verifyPhoneNumber("0871234567"));
    }

    // TC12
    // Test Objective: Verify phone number is rejected when fewer than 10 digits
    // Input(s): Phone Number = "087123456"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberTooShort_TC12() {
        Customer c = new Customer();
        assertFalse(c.verifyPhoneNumber("087123456"));
    }

    // TC13
    // Test Objective: Verify phone number is rejected when more than 10 digits
    // Input(s): Phone Number = "08712345678"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberTooLong_TC13() {
        Customer c = new Customer();
        assertFalse(c.verifyPhoneNumber("08712345678"));
    }

    // TC14
    // Test Objective: Verify phone number is rejected when containing alphabetic characters
    // Input(s): Phone Number = "08712AB567"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberAlphaCharacters_TC14() {
        Customer c = new Customer();
        assertFalse(c.verifyPhoneNumber("08712AB567"));
    }

    // TC15
    // Test Objective: Verify delivery area is accepted at the minimum boundary
    // Input(s): Delivery Area = 1
    // Expected Output(s): Delivery area accepted
    @Test
    void validDeliveryAreaMinBoundary_TC15() {
        Customer c = new Customer();
        assertTrue(c.verifyDeliveryArea(1));
    }

    // TC16
    // Test Objective: Verify delivery area is accepted at the maximum boundary
    // Input(s): Delivery Area = 24
    // Expected Output(s): Delivery area accepted
    @Test
    void validDeliveryAreaMaxBoundary_TC16() {
        Customer c = new Customer();
        assertTrue(c.verifyDeliveryArea(24));
    }

    // TC17
    // Test Objective: Verify delivery area is rejected below the minimum
    // Input(s): Delivery Area = 0
    // Expected Output(s): Error message: "Invalid Delivery Area"
    @Test
    void invalidDeliveryAreaTooLow_TC17() {
        Customer c = new Customer();
        assertFalse(c.verifyDeliveryArea(0));
    }

    // TC18
    // Test Objective: Verify delivery area is rejected above the maximum
    // Input(s): Delivery Area = 25
    // Expected Output(s): Error message: "Invalid Delivery Area"
    @Test
    void invalidDeliveryAreaTooHigh_TC18() {
        Customer c = new Customer();
        assertFalse(c.verifyDeliveryArea(25));
    }

    // TC19
    // Test Objective: Verify customer account is created when all details are valid
    // Input(s): John, Smith, Main Street, Athlone, 0871234567, 5
    // Expected Output(s): Customer account created successfully
    @Test
    void createValidCustomerAccount_TC19() {
        Customer c = new Customer();
        assertTrue(c.createCustomerAccount("John", "Smith", "Main Street, Athlone", "0871234567", 5));
    }

    // TC20
    // Test Objective: Verify customer account creation fails with invalid first name
    // Input(s): "", Smith, Main Street, Athlone, 0871234567, 5
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidFirstName_TC20() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("", "Smith", "Main Street, Athlone", "0871234567", 5));
    }

    // TC21
    // Test Objective: Verify customer account creation fails with invalid surname
    // Input(s): John, "", Main Street, Athlone, 0871234567, 5
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidSurname_TC21() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("John", "", "Main Street, Athlone", "0871234567", 5));
    }

    // TC22
    // Test Objective: Verify customer account creation fails with invalid address
    // Input(s): John, Smith, "", 0871234567, 5
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidAddress_TC22() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("John", "Smith", "", "0871234567", 5));
    }

    // TC23
    // Test Objective: Verify customer account creation fails with invalid phone number
    // Input(s): John, Smith, Main Street, Athlone, 087123456, 5
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidPhoneNumber_TC23() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("John", "Smith", "Main Street, Athlone", "087123456", 5));
    }

    // TC24
    // Test Objective: Verify customer account creation fails with invalid delivery area
    // Input(s): John, Smith, Main Street, Athlone, 0871234567, 25
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidDeliveryArea_TC24() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("John", "Smith", "Main Street, Athlone", "0871234567", 25));
    }

}
