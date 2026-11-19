package com.newsagent.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NewsagentTest {

    // ===== Black-Box Tests (TC01 - TC26) =====

    // TC01
    // Technique: BVA
    // Test Objective: Verify shop name is rejected below the minimum (blank)
    // Input(s): Shop Name = ""
    // Expected Output(s): Error message: "Invalid Shop Name"
    @Test
    void invalidShopNameBlank_TC01() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyShopName(""));
    }

    // TC02
    // Technique: BVA
    // Test Objective: Verify shop name is accepted at the minimum (1 character)
    // Input(s): Shop Name = "A"
    // Expected Output(s): Shop Name accepted
    @Test
    void validShopNameMin_TC02() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyShopName("A"));
    }

    // TC03
    // Technique: BVA
    // Test Objective: Verify shop name is accepted at min+1 (2 characters)
    // Input(s): Shop Name = "Ab"
    // Expected Output(s): Shop Name accepted
    @Test
    void validShopNameMinPlusOne_TC03() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyShopName("Ab"));
    }

    // TC04
    // Technique: EP
    // Test Objective: Verify a typical shop name is accepted (valid partition)
    // Input(s): Shop Name = "Athlone News"
    // Expected Output(s): Shop Name accepted
    @Test
    void validShopName_TC04() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyShopName("Athlone News"));
    }

    // TC05
    // Technique: BVA
    // Test Objective: Verify shop name is accepted at max-1 (49 characters)
    // Input(s): Shop Name = 49 x "a"
    // Expected Output(s): Shop Name accepted
    @Test
    void validShopNameMaxMinusOne_TC05() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyShopName("a".repeat(49)));
    }

    // TC06
    // Technique: BVA
    // Test Objective: Verify shop name is accepted at the maximum (50 characters)
    // Input(s): Shop Name = 50 x "a"
    // Expected Output(s): Shop Name accepted
    @Test
    void validShopNameMax_TC06() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyShopName("a".repeat(50)));
    }

    // TC07
    // Technique: BVA
    // Test Objective: Verify shop name is rejected above the maximum (51 characters)
    // Input(s): Shop Name = 51 x "a"
    // Expected Output(s): Error message: "Invalid Shop Name"
    @Test
    void invalidShopNameTooLong_TC07() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyShopName("a".repeat(51)));
    }

    // TC08
    // Technique: EP
    // Test Objective: Verify shop name made only of spaces is rejected (invalid partition: whitespace)
    // Input(s): Shop Name = "   "
    // Expected Output(s): Error message: "Invalid Shop Name"
    @Test
    void invalidShopNameSpaces_TC08() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyShopName("   "));
    }

    // TC09
    // Technique: BVA
    // Test Objective: Verify address is rejected below the minimum (blank)
    // Input(s): Address = ""
    // Expected Output(s): Error message: "Invalid Address"
    @Test
    void invalidAddressBlank_TC09() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyAddress(""));
    }

    // TC10
    // Technique: BVA
    // Test Objective: Verify address is accepted at the minimum (1 character)
    // Input(s): Address = "A"
    // Expected Output(s): Address accepted
    @Test
    void validAddressMin_TC10() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyAddress("A"));
    }

    // TC11
    // Technique: BVA
    // Test Objective: Verify address is accepted at min+1 (2 characters)
    // Input(s): Address = "Ab"
    // Expected Output(s): Address accepted
    @Test
    void validAddressMinPlusOne_TC11() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyAddress("Ab"));
    }

    // TC12
    // Technique: EP
    // Test Objective: Verify a typical address is accepted (valid partition)
    // Input(s): Address = "12 Main Street, Athlone"
    // Expected Output(s): Address accepted
    @Test
    void validAddress_TC12() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyAddress("12 Main Street, Athlone"));
    }

    // TC13
    // Technique: BVA
    // Test Objective: Verify address is accepted at max-1 (149 characters)
    // Input(s): Address = 149 x "a"
    // Expected Output(s): Address accepted
    @Test
    void validAddressMaxMinusOne_TC13() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyAddress("a".repeat(149)));
    }

    // TC14
    // Technique: BVA
    // Test Objective: Verify address is accepted at the maximum (150 characters)
    // Input(s): Address = 150 x "a"
    // Expected Output(s): Address accepted
    @Test
    void validAddressMax_TC14() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyAddress("a".repeat(150)));
    }

    // TC15
    // Technique: BVA
    // Test Objective: Verify address is rejected above the maximum (151 characters)
    // Input(s): Address = 151 x "a"
    // Expected Output(s): Error message: "Invalid Address"
    @Test
    void invalidAddressTooLong_TC15() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyAddress("a".repeat(151)));
    }

    // TC16
    // Technique: EP
    // Test Objective: Verify address made only of spaces is rejected (invalid partition: whitespace)
    // Input(s): Address = "   "
    // Expected Output(s): Error message: "Invalid Address"
    @Test
    void invalidAddressSpaces_TC16() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyAddress("   "));
    }

    // TC17
    // Technique: EP
    // Test Objective: Verify a 10-digit phone number is accepted (valid partition)
    // Input(s): Phone Number = "0861234567"
    // Expected Output(s): Phone Number accepted
    @Test
    void validPhoneNumber_TC17() {
        Newsagent n = new Newsagent();
        assertTrue(n.verifyPhoneNumber("0861234567"));
    }

    // TC18
    // Technique: BVA
    // Test Objective: Verify phone number is rejected when empty (0 digits)
    // Input(s): Phone Number = ""
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberEmpty_TC18() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyPhoneNumber(""));
    }

    // TC19
    // Technique: BVA
    // Test Objective: Verify phone number is rejected with 9 digits (one below)
    // Input(s): Phone Number = "086123456"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberTooShort_TC19() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyPhoneNumber("086123456"));
    }

    // TC20
    // Technique: BVA
    // Test Objective: Verify phone number is rejected with 11 digits (one above)
    // Input(s): Phone Number = "08612345678"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberTooLong_TC20() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyPhoneNumber("08612345678"));
    }

    // TC21
    // Technique: EP
    // Test Objective: Verify phone number with letters is rejected (invalid partition: non-digit)
    // Input(s): Phone Number = "08612AB567"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberLetters_TC21() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyPhoneNumber("08612AB567"));
    }

    // TC22
    // Technique: EP
    // Test Objective: Verify phone number with a space is rejected (invalid partition: non-digit)
    // Input(s): Phone Number = "086 123456"
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void invalidPhoneNumberSpace_TC22() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyPhoneNumber("086 123456"));
    }

    // TC23
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a valid newsagent is created
    // Input(s): "Athlone News", "12 Main Street, Athlone", "0906471234"
    // Expected Output(s): Newsagent created
    @Test
    void createValidNewsagent_TC23() {
        Newsagent n = new Newsagent();
        assertTrue(n.createNewsagent("Athlone News", "12 Main Street, Athlone", "0906471234"));
    }

    // TC24
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a newsagent is not created with an invalid shop name
    // Input(s): "", "12 Main Street, Athlone", "0906471234"
    // Expected Output(s): Error message: "Invalid Shop Name"
    @Test
    void createNewsagentInvalidShopName_TC24() {
        Newsagent n = new Newsagent();
        assertFalse(n.createNewsagent("", "12 Main Street, Athlone", "0906471234"));
    }

    // TC25
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a newsagent is not created with an invalid address
    // Input(s): "Athlone News", "", "0906471234"
    // Expected Output(s): Error message: "Invalid Address"
    @Test
    void createNewsagentInvalidAddress_TC25() {
        Newsagent n = new Newsagent();
        assertFalse(n.createNewsagent("Athlone News", "", "0906471234"));
    }

    // TC26
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a newsagent is not created with an invalid phone number
    // Input(s): "Athlone News", "12 Main Street, Athlone", ""
    // Expected Output(s): Error message: "Invalid Phone Number"
    @Test
    void createNewsagentInvalidPhoneNumber_TC26() {
        Newsagent n = new Newsagent();
        assertFalse(n.createNewsagent("Athlone News", "12 Main Street, Athlone", ""));
    }

    // ===== White-Box Tests (WB01 - WB02) =====

    // WB01
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify shopName, address, phoneNumber are rejected when null (covers the null check branches)
    // Input(s): shopName = null, address = null, phoneNumber = null
    // Expected Output(s): All rejected
    @Test
    void invalidValuesNull_WB01() {
        Newsagent n = new Newsagent();
        assertFalse(n.verifyShopName(null));
        assertFalse(n.verifyAddress(null));
        assertFalse(n.verifyPhoneNumber(null));
    }

    // WB02
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify a created newsagent stores its details (covers the getter methods)
    // Input(s): "Athlone News", "12 Main Street, Athlone", "0906471234"
    // Expected Output(s): Getters return the stored values
    @Test
    void createdNewsagentStoresDetails_WB02() {
        Newsagent n = new Newsagent();
        n.createNewsagent("Athlone News", "12 Main Street, Athlone", "0906471234");
        assertEquals("Athlone News", n.getShopName());
        assertEquals("12 Main Street, Athlone", n.getAddress());
        assertEquals("0906471234", n.getPhoneNumber());
    }
}
