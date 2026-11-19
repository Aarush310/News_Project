
package com.newsagent.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryAreaTest {

    // ===== Black-Box Tests (TC01 - TC23) =====

    // TC01
    // Technique: BVA
    // Test Objective: Verify area number is accepted at the minimum
    // Input(s): Area Number = 1
    // Expected Output(s): Area number accepted
    @Test
    void TC01() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaNumber(1));
    }

    // TC02
    // Technique: BVA
    // Test Objective: Verify area number is accepted at the maximum
    // Input(s): Area Number = 24
    // Expected Output(s): Area number accepted
    @Test
    void TC02() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaNumber(24));
    }

    // TC03
    // Technique: BVA
    // Test Objective: Verify area number is rejected below the minimum
    // Input(s): Area Number = 0
    // Expected Output(s): Error message: "Invalid Area Number"
    @Test
    void TC03() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaNumber(0));
    }

    // TC04
    // Technique: BVA
    // Test Objective: Verify area number is rejected above the maximum
    // Input(s): Area Number = 25
    // Expected Output(s): Error message: "Invalid Area Number"
    @Test
    void TC04() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaNumber(25));
    }

    // TC05
    // Technique: BVA
    // Test Objective: Verify area name is accepted at the minimum (1 character)
    // Input(s): Area Name = "C"
    // Expected Output(s): Area name accepted
    @Test
    void TC05() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaName("C"));
    }

    // TC06
    // Technique: EP
    // Test Objective: Verify a typical area name is accepted (valid partition)
    // Input(s): Area Name = "Church Street"
    // Expected Output(s): Area name accepted
    @Test
    void TC06() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaName("Church Street"));
    }

    // TC07
    // Technique: BVA
    // Test Objective: Verify area name is rejected below the minimum (blank)
    // Input(s): Area Name = ""
    // Expected Output(s): Error message: "Invalid Area Name"
    @Test
    void TC07() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaName(""));
    }

    // TC08
    // Technique: BVA
    // Test Objective: Verify delivery person ID is accepted at the minimum
    // Input(s): Delivery Person ID = 1
    // Expected Output(s): Delivery person ID accepted
    @Test
    void TC08() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyDeliveryPersonId(1));
    }

    // TC09
    // Technique: BVA
    // Test Objective: Verify delivery person ID is rejected below the minimum
    // Input(s): Delivery Person ID = 0
    // Expected Output(s): Error message: "Invalid Delivery Person ID"
    @Test
    void TC09() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyDeliveryPersonId(0));
    }

    // TC10
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a delivery area is created with all valid details
    // Input(s): 5, "Church Street", 1
    // Expected Output(s): Delivery area created
    @Test
    void TC10() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.createDeliveryArea(
            5, "Church Street", 1));
    }

    // TC11
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a delivery area is not created with an invalid area number
    // Input(s): 25, "Church Street", 1
    // Expected Output(s): Error message: "Invalid Area Number"
    @Test
    void TC11() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.createDeliveryArea(
            25, "Church Street", 1));
    }

    // TC12
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a delivery area is not created with a blank area name
    // Input(s): 5, "", 1
    // Expected Output(s): Error message: "Invalid Area Name"
    @Test
    void TC12() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.createDeliveryArea(
            5, "", 1));
    }

    // TC13
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a delivery area is not created with an invalid delivery person ID
    // Input(s): 5, "Church Street", 0
    // Expected Output(s): Error message: "Invalid Delivery Person ID"
    @Test
    void TC13() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.createDeliveryArea(
            5, "Church Street", 0));
    }

    // TC14
    // Technique: BVA
    // Test Objective: Verify area number is accepted at min+1
    // Input(s): Area Number = 2
    // Expected Output(s): Area number accepted
    @Test
    void validAreaNumberMinPlusOne_TC14() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaNumber(2));
    }

    // TC15
    // Technique: BVA
    // Test Objective: Verify area number is accepted at max-1
    // Input(s): Area Number = 23
    // Expected Output(s): Area number accepted
    @Test
    void validAreaNumberMaxMinusOne_TC15() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaNumber(23));
    }

    // TC16
    // Technique: EP
    // Test Objective: Verify a negative area number is rejected (invalid partition: below range)
    // Input(s): Area Number = -1
    // Expected Output(s): Error message: "Invalid Area Number"
    @Test
    void invalidAreaNumberNegative_TC16() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaNumber(-1));
    }

    // TC17
    // Technique: BVA
    // Test Objective: Verify area name is accepted at min+1 (2 characters)
    // Input(s): Area Name = "Ab"
    // Expected Output(s): Area name accepted
    @Test
    void validAreaNameMinPlusOne_TC17() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaName("Ab"));
    }

    // TC18
    // Technique: BVA
    // Test Objective: Verify area name is accepted at max-1 (49 characters)
    // Input(s): Area Name = 49 x "a"
    // Expected Output(s): Area name accepted
    @Test
    void validAreaNameMaxMinusOne_TC18() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaName("a".repeat(49)));
    }

    // TC19
    // Technique: BVA
    // Test Objective: Verify area name is accepted at the maximum (50 characters)
    // Input(s): Area Name = 50 x "a"
    // Expected Output(s): Area name accepted
    @Test
    void validAreaNameMaxBoundary_TC19() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaName("a".repeat(50)));
    }

    // TC20
    // Technique: BVA
    // Test Objective: Verify area name is rejected above the maximum (51 characters)
    // Input(s): Area Name = 51 x "a"
    // Expected Output(s): Error message: "Invalid Area Name"
    @Test
    void invalidAreaNameTooLong_TC20() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaName("a".repeat(51)));
    }

    // TC21
    // Technique: EP
    // Test Objective: Verify an area name made only of spaces is rejected (invalid partition: whitespace)
    // Input(s): Area Name = "   "
    // Expected Output(s): Error message: "Invalid Area Name"
    @Test
    void invalidAreaNameSpaces_TC21() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaName("   "));
    }

    // TC22
    // Technique: BVA
    // Test Objective: Verify delivery person ID is accepted at min+1
    // Input(s): Delivery Person ID = 2
    // Expected Output(s): Delivery person ID accepted
    @Test
    void validDeliveryPersonIdMinPlusOne_TC22() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyDeliveryPersonId(2));
    }

    // TC23
    // Technique: EP
    // Test Objective: Verify a negative delivery person ID is rejected (invalid partition: below range)
    // Input(s): Delivery Person ID = -1
    // Expected Output(s): Error message: "Invalid Delivery Person ID"
    @Test
    void invalidDeliveryPersonIdNegative_TC23() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyDeliveryPersonId(-1));
    }

    // ===== White-Box Tests (WB01 - WB02) =====

    // WB01
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify area name is rejected when null (covers the null check branch)
    // Input(s): Area Name = null
    // Expected Output(s): Error message: "Invalid Area Name"
    @Test
    void WB01() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaName(null));
    }

    // WB02
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify a created delivery area stores its details (covers the getter methods)
    // Input(s): 5, "Church Street", 1
    // Expected Output(s): getAreaNumber() = 5, getAreaName() = "Church Street", getDeliveryPersonId() = 1
    @Test
    void WB02() {
        DeliveryArea area = new DeliveryArea(
            5, "Church Street", 1);

        assertEquals(5, area.getAreaNumber());
        assertEquals("Church Street", area.getAreaName());
        assertEquals(1, area.getDeliveryPersonId());
    }
}
