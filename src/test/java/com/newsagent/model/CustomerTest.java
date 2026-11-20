package com.newsagent.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CustomerTest {

    // ===== Black-Box Tests (TC01 - TC45) =====

    // TC01
    // Technique: BVA
    // Test Objective: Verify first name is accepted when exactly 1 character long
    // Input(s): First Name = "J"
    // Expected Output(s): First name accepted
    @Test
    void validFirstNameMinBoundary_TC01() {
        Customer c = new Customer();
        assertTrue(c.verifyFirstName("J"));
    }

    // TC02
    // Technique: EP
    // Test Objective: Verify first name is accepted when more than 1 character
    // Input(s): First Name = "John"
    // Expected Output(s): First name accepted
    @Test
    void validFirstName_TC02() {
        Customer c = new Customer();
        assertTrue(c.verifyFirstName("John"));
    }

    // TC03
    // Technique: BVA
    // Test Objective: Verify first name is rejected when blank
    // Input(s): First Name = ""
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameBlank_TC03() {
        Customer c = new Customer();
        assertFalse(c.verifyFirstName(""));
    }

    // TC04
    // Technique: EP
    // Test Objective: Verify first name is rejected when only spaces
    // Input(s): First Name = "   "
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameSpaces_TC04() {
        Customer c = new Customer();
        assertFalse(c.verifyFirstName("   "));
    }

    // TC05
    // Technique: BVA
    // Test Objective: Verify surname is accepted when exactly 1 character long
    // Input(s): Surname = "S"
    // Expected Output(s): Surname accepted
    @Test
    void validSurnameMinBoundary_TC05() {
        Customer c = new Customer();
        assertTrue(c.verifySurname("S"));
    }

    // TC06
    // Technique: EP
    // Test Objective: Verify surname is accepted when more than 1 character
    // Input(s): Surname = "Smith"
    // Expected Output(s): Surname accepted
    @Test
    void validSurname_TC06() {
        Customer c = new Customer();
        assertTrue(c.verifySurname("Smith"));
    }

    // TC07
    // Technique: BVA
    // Test Objective: Verify surname is rejected when blank
    // Input(s): Surname = ""
    // Expected Output(s): Error message: "Invalid Surname"
    @Test
    void invalidSurnameBlank_TC07() {
        Customer c = new Customer();
        assertFalse(c.verifySurname(""));
    }

    // TC08
    // Technique: BVA
    // Test Objective: Verify address is accepted when exactly 1 character long
    // Input(s): Address = "M"
    // Expected Output(s): Address accepted
    @Test
    void validAddressMinBoundary_TC08() {
        Customer c = new Customer();
        assertTrue(c.verifyAddress("M"));
    }

    // TC09
    // Technique: EP
    // Test Objective: Verify address is accepted when more than 1 character
    // Input(s): Address = "Main Street, Athlone"
    // Expected Output(s): Address accepted
    @Test
    void validAddress_TC09() {
        Customer c = new Customer();
        assertTrue(c.verifyAddress("Main Street, Athlone"));
    }

    // TC10
    // Technique: BVA
    // Test Objective: Verify address is rejected when blank
    // Input(s): Address = ""
    // Expected Output(s): Error message: "Invalid Address"
    @Test
    void invalidAddressBlank_TC10() {
        Customer c = new Customer();
        assertFalse(c.verifyAddress(""));
    }

    // TC11
    // Technique: EP
    // Test Objective: Verify phone number is accepted when exactly 10 digits
    // Input(s): Phone Number = "0871234567"
    // Expected Output(s): Phone number accepted
    @Test
    void validPhoneNumber_TC11() {
        Customer c = new Customer();
        assertTrue(c.verifyPhoneNumber("0871234567"));
    }

    // TC12
    // Technique: BVA
    // Test Objective: Verify phone number is rejected when fewer than 10 digits
    // Input(s): Phone Number = "087123456"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberTooShort_TC12() {
        Customer c = new Customer();
        assertFalse(c.verifyPhoneNumber("087123456"));
    }

    // TC13
    // Technique: BVA
    // Test Objective: Verify phone number is rejected when more than 10 digits
    // Input(s): Phone Number = "08712345678"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberTooLong_TC13() {
        Customer c = new Customer();
        assertFalse(c.verifyPhoneNumber("08712345678"));
    }

    // TC14
    // Technique: EP
    // Test Objective: Verify phone number is rejected when containing alphabetic characters
    // Input(s): Phone Number = "08712AB567"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberAlphaCharacters_TC14() {
        Customer c = new Customer();
        assertFalse(c.verifyPhoneNumber("08712AB567"));
    }

    // TC15
    // Technique: BVA
    // Test Objective: Verify delivery area is accepted at the minimum boundary
    // Input(s): Delivery Area = 1
    // Expected Output(s): Delivery area accepted
    @Test
    void validDeliveryAreaMinBoundary_TC15() {
        Customer c = new Customer();
        assertTrue(c.verifyDeliveryArea(1));
    }

    // TC16
    // Technique: BVA
    // Test Objective: Verify delivery area is accepted at the maximum boundary
    // Input(s): Delivery Area = 24
    // Expected Output(s): Delivery area accepted
    @Test
    void validDeliveryAreaMaxBoundary_TC16() {
        Customer c = new Customer();
        assertTrue(c.verifyDeliveryArea(24));
    }

    // TC17
    // Technique: BVA
    // Test Objective: Verify delivery area is rejected below the minimum
    // Input(s): Delivery Area = 0
    // Expected Output(s): Error message: "Invalid Delivery Area"
    @Test
    void invalidDeliveryAreaTooLow_TC17() {
        Customer c = new Customer();
        assertFalse(c.verifyDeliveryArea(0));
    }

    // TC18
    // Technique: BVA
    // Test Objective: Verify delivery area is rejected above the maximum
    // Input(s): Delivery Area = 25
    // Expected Output(s): Error message: "Invalid Delivery Area"
    @Test
    void invalidDeliveryAreaTooHigh_TC18() {
        Customer c = new Customer();
        assertFalse(c.verifyDeliveryArea(25));
    }

    // TC19
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify customer account is created when all details are valid
    // Input(s): John, Smith, Main Street, Athlone, 0871234567, 5
    // Expected Output(s): Customer account created successfully
    @Test
    void createValidCustomerAccount_TC19() {
        Customer c = new Customer();
        assertTrue(c.createCustomerAccount("John", "Smith", "Main Street, Athlone", "0871234567", 5));
    }

    // TC20
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify customer account creation fails with invalid first name
    // Input(s): "", Smith, Main Street, Athlone, 0871234567, 5
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidFirstName_TC20() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("", "Smith", "Main Street, Athlone", "0871234567", 5));
    }

    // TC21
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify customer account creation fails with invalid surname
    // Input(s): John, "", Main Street, Athlone, 0871234567, 5
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidSurname_TC21() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("John", "", "Main Street, Athlone", "0871234567", 5));
    }

    // TC22
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify customer account creation fails with invalid address
    // Input(s): John, Smith, "", 0871234567, 5
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidAddress_TC22() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("John", "Smith", "", "0871234567", 5));
    }

    // TC23
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify customer account creation fails with invalid phone number
    // Input(s): John, Smith, Main Street, Athlone, 087123456, 5
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidPhoneNumber_TC23() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("John", "Smith", "Main Street, Athlone", "087123456", 5));
    }

    // TC24
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify customer account creation fails with invalid delivery area
    // Input(s): John, Smith, Main Street, Athlone, 0871234567, 25
    // Expected Output(s): Customer account not created
    @Test
    void createAccountInvalidDeliveryArea_TC24() {
        Customer c = new Customer();
        assertFalse(c.createCustomerAccount("John", "Smith", "Main Street, Athlone", "0871234567", 25));
    }


    // TC25
    // Technique: BVA
    // Test Objective: Verify first name is accepted at min+1 (2 characters)
    // Input(s): First Name = "Jo"
    // Expected Output(s): First name accepted
    @Test
    void validFirstNameMinPlusOne_TC25() {
        Customer c = new Customer();
        assertTrue(c.verifyFirstName("Jo"));
    }

    // TC26
    // Technique: BVA
    // Test Objective: Verify first name is accepted at max-1 (49 characters)
    // Input(s): First Name = 49 x "a"
    // Expected Output(s): First name accepted
    @Test
    void validFirstNameMaxMinusOne_TC26() {
        Customer c = new Customer();
        assertTrue(c.verifyFirstName("a".repeat(49)));
    }

    // TC27
    // Technique: BVA
    // Test Objective: Verify first name is accepted at the maximum (50 characters)
    // Input(s): First Name = 50 x "a"
    // Expected Output(s): First name accepted
    @Test
    void validFirstNameMaxBoundary_TC27() {
        Customer c = new Customer();
        assertTrue(c.verifyFirstName("a".repeat(50)));
    }

    // TC28
    // Technique: BVA
    // Test Objective: Verify first name is rejected above the maximum (51 characters)
    // Input(s): First Name = 51 x "a"
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameTooLong_TC28() {
        Customer c = new Customer();
        assertFalse(c.verifyFirstName("a".repeat(51)));
    }

    // TC29
    // Technique: BVA
    // Test Objective: Verify surname is accepted at min+1 (2 characters)
    // Input(s): Surname = "Ng"
    // Expected Output(s): Surname accepted
    @Test
    void validSurnameMinPlusOne_TC29() {
        Customer c = new Customer();
        assertTrue(c.verifySurname("Ng"));
    }

    // TC30
    // Technique: BVA
    // Test Objective: Verify surname is accepted at max-1 (49 characters)
    // Input(s): Surname = 49 x "a"
    // Expected Output(s): Surname accepted
    @Test
    void validSurnameMaxMinusOne_TC30() {
        Customer c = new Customer();
        assertTrue(c.verifySurname("a".repeat(49)));
    }

    // TC31
    // Technique: BVA
    // Test Objective: Verify surname is accepted at the maximum (50 characters)
    // Input(s): Surname = 50 x "a"
    // Expected Output(s): Surname accepted
    @Test
    void validSurnameMaxBoundary_TC31() {
        Customer c = new Customer();
        assertTrue(c.verifySurname("a".repeat(50)));
    }

    // TC32
    // Technique: BVA
    // Test Objective: Verify surname is rejected above the maximum (51 characters)
    // Input(s): Surname = 51 x "a"
    // Expected Output(s): Error message: "Invalid Surname"
    @Test
    void invalidSurnameTooLong_TC32() {
        Customer c = new Customer();
        assertFalse(c.verifySurname("a".repeat(51)));
    }

    // TC33
    // Technique: EP
    // Test Objective: Verify surname made only of spaces is rejected (invalid partition: whitespace)
    // Input(s): Surname = "   "
    // Expected Output(s): Error message: "Invalid Surname"
    @Test
    void invalidSurnameSpaces_TC33() {
        Customer c = new Customer();
        assertFalse(c.verifySurname("   "));
    }

    // TC34
    // Technique: BVA
    // Test Objective: Verify address is accepted at min+1 (2 characters)
    // Input(s): Address = "A1"
    // Expected Output(s): Address accepted
    @Test
    void validAddressMinPlusOne_TC34() {
        Customer c = new Customer();
        assertTrue(c.verifyAddress("A1"));
    }

    // TC35
    // Technique: BVA
    // Test Objective: Verify address is accepted at max-1 (149 characters)
    // Input(s): Address = 149 x "a"
    // Expected Output(s): Address accepted
    @Test
    void validAddressMaxMinusOne_TC35() {
        Customer c = new Customer();
        assertTrue(c.verifyAddress("a".repeat(149)));
    }

    // TC36
    // Technique: BVA
    // Test Objective: Verify address is accepted at the maximum (150 characters)
    // Input(s): Address = 150 x "a"
    // Expected Output(s): Address accepted
    @Test
    void validAddressMaxBoundary_TC36() {
        Customer c = new Customer();
        assertTrue(c.verifyAddress("a".repeat(150)));
    }

    // TC37
    // Technique: BVA
    // Test Objective: Verify address is rejected above the maximum (151 characters)
    // Input(s): Address = 151 x "a"
    // Expected Output(s): Error message: "Invalid Address"
    @Test
    void invalidAddressTooLong_TC37() {
        Customer c = new Customer();
        assertFalse(c.verifyAddress("a".repeat(151)));
    }

    // TC38
    // Technique: EP
    // Test Objective: Verify address made only of spaces is rejected (invalid partition: whitespace)
    // Input(s): Address = "   "
    // Expected Output(s): Error message: "Invalid Address"
    @Test
    void invalidAddressSpaces_TC38() {
        Customer c = new Customer();
        assertFalse(c.verifyAddress("   "));
    }

    // TC39
    // Technique: BVA
    // Test Objective: Verify phone number is rejected when empty (0 digits)
    // Input(s): Phone Number = ""
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberEmpty_TC39() {
        Customer c = new Customer();
        assertFalse(c.verifyPhoneNumber(""));
    }

    // TC40
    // Technique: EP
    // Test Objective: Verify 10-character phone number containing a space is rejected (invalid partition: non-digit)
    // Input(s): Phone Number = "087 123456"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberSpace_TC40() {
        Customer c = new Customer();
        assertFalse(c.verifyPhoneNumber("087 123456"));
    }

    // TC41
    // Technique: EP
    // Test Objective: Verify 10-character phone number containing a symbol is rejected (invalid partition: non-digit)
    // Input(s): Phone Number = "+353871234"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberSymbol_TC41() {
        Customer c = new Customer();
        assertFalse(c.verifyPhoneNumber("+353871234"));
    }

    // TC42
    // Technique: BVA
    // Test Objective: Verify delivery area is accepted at min+1
    // Input(s): Delivery Area = 2
    // Expected Output(s): Delivery area accepted
    @Test
    void validDeliveryAreaMinPlusOne_TC42() {
        Customer c = new Customer();
        assertTrue(c.verifyDeliveryArea(2));
    }

    // TC43
    // Technique: BVA
    // Test Objective: Verify delivery area is accepted at max-1
    // Input(s): Delivery Area = 23
    // Expected Output(s): Delivery area accepted
    @Test
    void validDeliveryAreaMaxMinusOne_TC43() {
        Customer c = new Customer();
        assertTrue(c.verifyDeliveryArea(23));
    }

    // TC44
    // Technique: EP
    // Test Objective: Verify a delivery area in the middle of the valid range is accepted (valid partition)
    // Input(s): Delivery Area = 12
    // Expected Output(s): Delivery area accepted
    @Test
    void validDeliveryAreaNominal_TC44() {
        Customer c = new Customer();
        assertTrue(c.verifyDeliveryArea(12));
    }

    // TC45
    // Technique: EP
    // Test Objective: Verify a negative delivery area is rejected (invalid partition: below range)
    // Input(s): Delivery Area = -1
    // Expected Output(s): Error message: "Invalid Delivery Area"
    @Test
    void invalidDeliveryAreaNegative_TC45() {
        Customer c = new Customer();
        assertFalse(c.verifyDeliveryArea(-1));
    }

    // ===== Name Character Tests (TC46 - TC49) =====

    // TC46
    // Technique: Equivalence Partitioning (invalid partition: digits)
    // Test Objective: Verify a first name made of digits is rejected
    // Input(s): First Name = "23232"
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameDigits_TC46() {
        Customer c = new Customer();
        assertFalse(c.verifyFirstName("23232"));
    }

    // TC47
    // Technique: Equivalence Partitioning (invalid partition: letters mixed with digits)
    // Test Objective: Verify a surname containing a digit is rejected
    // Input(s): Surname = "Sm1th"
    // Expected Output(s): Error message: "Invalid Surname"
    @Test
    void invalidSurnameDigits_TC47() {
        Customer c = new Customer();
        assertFalse(c.verifySurname("Sm1th"));
    }

    // TC48
    // Technique: Equivalence Partitioning (valid partition: apostrophe, hyphen, space)
    // Test Objective: Verify real names with an apostrophe, hyphen or space are accepted
    // Input(s): First Name = "Mary-Anne", Surname = "O'Brien", Surname = "De Burca"
    // Expected Output(s): All accepted
    @Test
    void validNamesPunctuation_TC48() {
        Customer c = new Customer();
        assertTrue(c.verifyFirstName("Mary-Anne"));
        assertTrue(c.verifySurname("O'Brien"));
        assertTrue(c.verifySurname("De Burca"));
    }

    // TC49
    // Technique: Equivalence Partitioning (invalid partition: symbols)
    // Test Objective: Verify a first name with a symbol such as @ is rejected
    // Input(s): First Name = "J@hn"
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameSymbol_TC49() {
        Customer c = new Customer();
        assertFalse(c.verifyFirstName("J@hn"));
    }

    // ===== White-Box Tests (WB01 - WB02) =====

    // WB01
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify first name, surname, address and phone number are rejected when null (covers the null check branches)
    // Input(s): First Name = null, Surname = null, Address = null, Phone Number = null
    // Expected Output(s): All four rejected
    @Test
    void invalidValuesNull_WB01() {
        Customer c = new Customer();
        assertFalse(c.verifyFirstName(null));
        assertFalse(c.verifySurname(null));
        assertFalse(c.verifyAddress(null));
        assertFalse(c.verifyPhoneNumber(null));
    }

    // WB02
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify a created customer account stores its details (covers the getter methods)
    // Input(s): John, Smith, Main Street, Athlone, 0871234567, 5
    // Expected Output(s): getFirstName() = "John", getSurname() = "Smith", getAddress() = "Main Street, Athlone", getPhoneNumber() = "0871234567", getDeliveryArea() = 5
    @Test
    void createdCustomerStoresDetails_WB02() {
        Customer c = new Customer();
        c.createCustomerAccount("John", "Smith", "Main Street, Athlone", "0871234567", 5);
        assertEquals("John", c.getFirstName());
        assertEquals("Smith", c.getSurname());
        assertEquals("Main Street, Athlone", c.getAddress());
        assertEquals("0871234567", c.getPhoneNumber());
        assertEquals(5, c.getDeliveryArea());
    }
}
