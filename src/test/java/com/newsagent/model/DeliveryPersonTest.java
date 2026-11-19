package com.newsagent.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DeliveryPersonTest {

    // ===== Black-Box Tests (TC01 - TC35) =====

    // TC01
    // Technique: BVA
    // Test Objective: Verify first name is rejected below the minimum (blank)
    // Input(s): First Name = ""
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameBlank_TC01() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyFirstName(""));
    }

    // TC02
    // Technique: BVA
    // Test Objective: Verify first name is accepted at the minimum (1 character)
    // Input(s): First Name = "A"
    // Expected Output(s): First Name accepted
    @Test
    void validFirstNameMin_TC02() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyFirstName("A"));
    }

    // TC03
    // Technique: BVA
    // Test Objective: Verify first name is accepted at min+1 (2 characters)
    // Input(s): First Name = "Ab"
    // Expected Output(s): First Name accepted
    @Test
    void validFirstNameMinPlusOne_TC03() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyFirstName("Ab"));
    }

    // TC04
    // Technique: EP
    // Test Objective: Verify a typical first name is accepted (valid partition)
    // Input(s): First Name = "Sean"
    // Expected Output(s): First Name accepted
    @Test
    void validFirstName_TC04() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyFirstName("Sean"));
    }

    // TC05
    // Technique: BVA
    // Test Objective: Verify first name is accepted at max-1 (49 characters)
    // Input(s): First Name = 49 x "a"
    // Expected Output(s): First Name accepted
    @Test
    void validFirstNameMaxMinusOne_TC05() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyFirstName("a".repeat(49)));
    }

    // TC06
    // Technique: BVA
    // Test Objective: Verify first name is accepted at the maximum (50 characters)
    // Input(s): First Name = 50 x "a"
    // Expected Output(s): First Name accepted
    @Test
    void validFirstNameMax_TC06() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyFirstName("a".repeat(50)));
    }

    // TC07
    // Technique: BVA
    // Test Objective: Verify first name is rejected above the maximum (51 characters)
    // Input(s): First Name = 51 x "a"
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameTooLong_TC07() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyFirstName("a".repeat(51)));
    }

    // TC08
    // Technique: EP
    // Test Objective: Verify first name made only of spaces is rejected (invalid partition: whitespace)
    // Input(s): First Name = "   "
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void invalidFirstNameSpaces_TC08() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyFirstName("   "));
    }

    // TC09
    // Technique: BVA
    // Test Objective: Verify surname is rejected below the minimum (blank)
    // Input(s): Surname = ""
    // Expected Output(s): Error message: "Invalid Surname"
    @Test
    void invalidSurnameBlank_TC09() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifySurname(""));
    }

    // TC10
    // Technique: BVA
    // Test Objective: Verify surname is accepted at the minimum (1 character)
    // Input(s): Surname = "A"
    // Expected Output(s): Surname accepted
    @Test
    void validSurnameMin_TC10() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifySurname("A"));
    }

    // TC11
    // Technique: BVA
    // Test Objective: Verify surname is accepted at min+1 (2 characters)
    // Input(s): Surname = "Ab"
    // Expected Output(s): Surname accepted
    @Test
    void validSurnameMinPlusOne_TC11() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifySurname("Ab"));
    }

    // TC12
    // Technique: EP
    // Test Objective: Verify a typical surname is accepted (valid partition)
    // Input(s): Surname = "Walsh"
    // Expected Output(s): Surname accepted
    @Test
    void validSurname_TC12() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifySurname("Walsh"));
    }

    // TC13
    // Technique: BVA
    // Test Objective: Verify surname is accepted at max-1 (49 characters)
    // Input(s): Surname = 49 x "a"
    // Expected Output(s): Surname accepted
    @Test
    void validSurnameMaxMinusOne_TC13() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifySurname("a".repeat(49)));
    }

    // TC14
    // Technique: BVA
    // Test Objective: Verify surname is accepted at the maximum (50 characters)
    // Input(s): Surname = 50 x "a"
    // Expected Output(s): Surname accepted
    @Test
    void validSurnameMax_TC14() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifySurname("a".repeat(50)));
    }

    // TC15
    // Technique: BVA
    // Test Objective: Verify surname is rejected above the maximum (51 characters)
    // Input(s): Surname = 51 x "a"
    // Expected Output(s): Error message: "Invalid Surname"
    @Test
    void invalidSurnameTooLong_TC15() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifySurname("a".repeat(51)));
    }

    // TC16
    // Technique: EP
    // Test Objective: Verify surname made only of spaces is rejected (invalid partition: whitespace)
    // Input(s): Surname = "   "
    // Expected Output(s): Error message: "Invalid Surname"
    @Test
    void invalidSurnameSpaces_TC16() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifySurname("   "));
    }

    // TC17
    // Technique: EP
    // Test Objective: Verify a 10-digit phone number is accepted (valid partition)
    // Input(s): Phone Number = "0861234567"
    // Expected Output(s): Phone Number accepted
    @Test
    void validPhoneNumber_TC17() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyPhoneNumber("0861234567"));
    }

    // TC18
    // Technique: BVA
    // Test Objective: Verify phone number is rejected when empty (0 digits)
    // Input(s): Phone Number = ""
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberEmpty_TC18() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyPhoneNumber(""));
    }

    // TC19
    // Technique: BVA
    // Test Objective: Verify phone number is rejected with 9 digits (one below)
    // Input(s): Phone Number = "086123456"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberTooShort_TC19() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyPhoneNumber("086123456"));
    }

    // TC20
    // Technique: BVA
    // Test Objective: Verify phone number is rejected with 11 digits (one above)
    // Input(s): Phone Number = "08612345678"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberTooLong_TC20() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyPhoneNumber("08612345678"));
    }

    // TC21
    // Technique: EP
    // Test Objective: Verify phone number with letters is rejected (invalid partition: non-digit)
    // Input(s): Phone Number = "08612AB567"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberLetters_TC21() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyPhoneNumber("08612AB567"));
    }

    // TC22
    // Technique: EP
    // Test Objective: Verify phone number with a space is rejected (invalid partition: non-digit)
    // Input(s): Phone Number = "086 123456"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberSpace_TC22() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyPhoneNumber("086 123456"));
    }

    // TC23
    // Technique: BVA
    // Test Objective: Verify delivery area is rejected below the minimum
    // Input(s): Delivery Area = 0
    // Expected Output(s): Error message: "Invalid Delivery Area"
    @Test
    void invalidDeliveryAreaTooLow_TC23() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyDeliveryArea(0));
    }

    // TC24
    // Technique: BVA
    // Test Objective: Verify delivery area is accepted at the minimum
    // Input(s): Delivery Area = 1
    // Expected Output(s): Delivery Area accepted
    @Test
    void validDeliveryAreaMin_TC24() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyDeliveryArea(1));
    }

    // TC25
    // Technique: BVA
    // Test Objective: Verify delivery area is accepted at min+1
    // Input(s): Delivery Area = 2
    // Expected Output(s): Delivery Area accepted
    @Test
    void validDeliveryAreaMinPlusOne_TC25() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyDeliveryArea(2));
    }

    // TC26
    // Technique: EP
    // Test Objective: Verify delivery area is accepted in the middle of the range (valid partition)
    // Input(s): Delivery Area = 12
    // Expected Output(s): Delivery Area accepted
    @Test
    void validDeliveryAreaNominal_TC26() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyDeliveryArea(12));
    }

    // TC27
    // Technique: BVA
    // Test Objective: Verify delivery area is accepted at max-1
    // Input(s): Delivery Area = 23
    // Expected Output(s): Delivery Area accepted
    @Test
    void validDeliveryAreaMaxMinusOne_TC27() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyDeliveryArea(23));
    }

    // TC28
    // Technique: BVA
    // Test Objective: Verify delivery area is accepted at the maximum
    // Input(s): Delivery Area = 24
    // Expected Output(s): Delivery Area accepted
    @Test
    void validDeliveryAreaMax_TC28() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.verifyDeliveryArea(24));
    }

    // TC29
    // Technique: BVA
    // Test Objective: Verify delivery area is rejected above the maximum
    // Input(s): Delivery Area = 25
    // Expected Output(s): Error message: "Invalid Delivery Area"
    @Test
    void invalidDeliveryAreaTooHigh_TC29() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyDeliveryArea(25));
    }

    // TC30
    // Technique: EP
    // Test Objective: Verify delivery area is rejected when negative (invalid partition)
    // Input(s): Delivery Area = -1
    // Expected Output(s): Error message: "Invalid Delivery Area"
    @Test
    void invalidDeliveryAreaNegative_TC30() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyDeliveryArea(-1));
    }

    // TC31
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a valid delivery person is created
    // Input(s): "Sean", "Walsh", "0861234567", 3
    // Expected Output(s): Delivery Person created
    @Test
    void createValidDeliveryPerson_TC31() {
        DeliveryPerson p = new DeliveryPerson();
        assertTrue(p.createDeliveryPerson("Sean", "Walsh", "0861234567", 3));
    }

    // TC32
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a delivery person is not created with an invalid first name
    // Input(s): "", "Walsh", "0861234567", 3
    // Expected Output(s): Error message: "Invalid First Name"
    @Test
    void createDeliveryPersonInvalidFirstName_TC32() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.createDeliveryPerson("", "Walsh", "0861234567", 3));
    }

    // TC33
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a delivery person is not created with an invalid surname
    // Input(s): "Sean", "", "0861234567", 3
    // Expected Output(s): Error message: "Invalid Surname"
    @Test
    void createDeliveryPersonInvalidSurname_TC33() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.createDeliveryPerson("Sean", "", "0861234567", 3));
    }

    // TC34
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a delivery person is not created with an invalid phone number
    // Input(s): "Sean", "Walsh", "", 3
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void createDeliveryPersonInvalidPhoneNumber_TC34() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.createDeliveryPerson("Sean", "Walsh", "", 3));
    }

    // TC35
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a delivery person is not created with an invalid delivery area
    // Input(s): "Sean", "Walsh", "0861234567", 25
    // Expected Output(s): Error message: "Invalid Delivery Area"
    @Test
    void createDeliveryPersonInvalidDeliveryArea_TC35() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.createDeliveryPerson("Sean", "Walsh", "0861234567", 25));
    }

    // ===== White-Box Tests (WB01 - WB02) =====

    // WB01
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify firstName, surname, phoneNumber are rejected when null (covers the null check branches)
    // Input(s): firstName = null, surname = null, phoneNumber = null
    // Expected Output(s): All rejected
    @Test
    void invalidValuesNull_WB01() {
        DeliveryPerson p = new DeliveryPerson();
        assertFalse(p.verifyFirstName(null));
        assertFalse(p.verifySurname(null));
        assertFalse(p.verifyPhoneNumber(null));
    }

    // WB02
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify a created delivery person stores its details (covers the getter methods)
    // Input(s): "Sean", "Walsh", "0861234567", 3
    // Expected Output(s): Getters return the stored values
    @Test
    void createdDeliveryPersonStoresDetails_WB02() {
        DeliveryPerson p = new DeliveryPerson();
        p.createDeliveryPerson("Sean", "Walsh", "0861234567", 3);
        assertEquals("Sean", p.getFirstName());
        assertEquals("Walsh", p.getSurname());
        assertEquals("0861234567", p.getPhoneNumber());
        assertEquals(3, p.getDeliveryArea());
    }
}
