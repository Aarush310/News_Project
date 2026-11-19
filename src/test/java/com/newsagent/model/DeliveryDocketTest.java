package com.newsagent.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class DeliveryDocketTest {

    // ===== Black-Box Tests (TC01 - TC21) =====

    // TC01
    // Technique: BVA
    // Test Objective: Verify area number is accepted at the minimum boundary
    // Input(s): Area Number = 1
    // Expected Output(s): Area accepted
    @Test
    void validAreaMinBoundary_TC01() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.verifyAreaNumber(1));
    }

    // TC02
    // Technique: BVA
    // Test Objective: Verify area number is accepted at the maximum boundary
    // Input(s): Area Number = 24
    // Expected Output(s): Area accepted
    @Test
    void validAreaMaxBoundary_TC02() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.verifyAreaNumber(24));
    }

    // TC03
    // Technique: BVA
    // Test Objective: Verify area number is rejected below the minimum
    // Input(s): Area Number = 0
    // Expected Output(s): Error message: "Invalid Area Number"
    @Test
    void invalidAreaZero_TC03() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.verifyAreaNumber(0));
    }

    // TC04
    // Technique: BVA
    // Test Objective: Verify area number is rejected above the maximum
    // Input(s): Area Number = 25
    // Expected Output(s): Error message: "Invalid Area Number"
    @Test
    void invalidAreaTooHigh_TC04() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.verifyAreaNumber(25));
    }

    // TC05
    // Technique: EP
    // Test Objective: Verify a docket date is accepted
    // Input(s): Date = 2026-11-13
    // Expected Output(s): Date accepted
    @Test
    void validDocketDate_TC05() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.verifyDocketDate(LocalDate.of(2026, 11, 13)));
    }

    // TC06
    // Technique: EP
    // Test Objective: Verify a missing docket date is rejected
    // Input(s): Date = null
    // Expected Output(s): Error message: "Invalid Date"
    @Test
    void invalidDocketDateNull_TC06() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.verifyDocketDate(null));
    }

    // TC07
    // Technique: EP
    // Test Objective: Verify status PENDING is accepted
    // Input(s): Status = "PENDING"
    // Expected Output(s): Status accepted
    @Test
    void validStatusPending_TC07() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.verifyStatus("PENDING"));
    }

    // TC08
    // Technique: EP
    // Test Objective: Verify status DELIVERED is accepted
    // Input(s): Status = "DELIVERED"
    // Expected Output(s): Status accepted
    @Test
    void validStatusDelivered_TC08() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.verifyStatus("DELIVERED"));
    }

    // TC09
    // Technique: EP
    // Test Objective: Verify any other status is rejected
    // Input(s): Status = "SENT"
    // Expected Output(s): Error message: "Invalid Status"
    @Test
    void invalidStatusOther_TC09() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.verifyStatus("SENT"));
    }

    // TC10
    // Technique: EP
    // Test Objective: Verify a PENDING docket can be deleted
    // Input(s): Status = "PENDING"
    // Expected Output(s): Delete allowed
    @Test
    void canDeletePending_TC10() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.canDelete("PENDING"));
    }

    // TC11
    // Technique: EP
    // Test Objective: Verify a DELIVERED docket cannot be deleted
    // Input(s): Status = "DELIVERED"
    // Expected Output(s): Error message: "Docket already delivered"
    @Test
    void cannotDeleteDelivered_TC11() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.canDelete("DELIVERED"));
    }

    // TC12
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a docket is created when all details are valid
    // Input(s): Area = 3, Date = 2026-11-13
    // Expected Output(s): Docket created with status PENDING
    @Test
    void createValidDocket_TC12() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.createDeliveryDocket(3, LocalDate.of(2026, 11, 13)));
        assertEquals("PENDING", d.getStatus());
    }

    // TC13
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify docket creation fails with an invalid area
    // Input(s): Area = 0, Date = 2026-11-13
    // Expected Output(s): Docket not created
    @Test
    void createDocketInvalidArea_TC13() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.createDeliveryDocket(0, LocalDate.of(2026, 11, 13)));
    }

    // TC14
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify docket creation fails with no date
    // Input(s): Area = 3, Date = null
    // Expected Output(s): Docket not created
    @Test
    void createDocketInvalidDate_TC14() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.createDeliveryDocket(3, null));
    }

    // TC15
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify a docket is updated with a valid date and status
    // Input(s): Date = 2026-11-14, Status = "DELIVERED"
    // Expected Output(s): Docket updated
    @Test
    void updateValidDocket_TC15() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.updateDeliveryDocket(LocalDate.of(2026, 11, 14), "DELIVERED"));
    }

    // TC16
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify docket update fails with an invalid status
    // Input(s): Date = 2026-11-14, Status = "SENT"
    // Expected Output(s): Docket not updated
    @Test
    void updateDocketInvalidStatus_TC16() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.updateDeliveryDocket(LocalDate.of(2026, 11, 14), "SENT"));
    }


    // TC17
    // Technique: BVA
    // Test Objective: Verify docket area number is accepted at min+1
    // Input(s): Area Number = 2
    // Expected Output(s): Area number accepted
    @Test
    void validAreaMinPlusOne_TC17() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.verifyAreaNumber(2));
    }

    // TC18
    // Technique: BVA
    // Test Objective: Verify docket area number is accepted at max-1
    // Input(s): Area Number = 23
    // Expected Output(s): Area number accepted
    @Test
    void validAreaMaxMinusOne_TC18() {
        DeliveryDocket d = new DeliveryDocket();
        assertTrue(d.verifyAreaNumber(23));
    }

    // TC19
    // Technique: EP
    // Test Objective: Verify a negative docket area number is rejected (invalid partition: below range)
    // Input(s): Area Number = -1
    // Expected Output(s): Error message: "Invalid Area Number"
    @Test
    void invalidAreaNegative_TC19() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.verifyAreaNumber(-1));
    }

    // TC20
    // Technique: EP
    // Test Objective: Verify a lowercase status is rejected (invalid partition: wrong case)
    // Input(s): Status = "pending"
    // Expected Output(s): Error message: "Invalid Status"
    @Test
    void invalidStatusLowercase_TC20() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.verifyStatus("pending"));
    }

    // TC21
    // Technique: EP
    // Test Objective: Verify a blank status is rejected (invalid partition: empty)
    // Input(s): Status = ""
    // Expected Output(s): Error message: "Invalid Status"
    @Test
    void invalidStatusBlank_TC21() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.verifyStatus(""));
    }

    // ===== White-Box Tests (WB01 - WB03) =====

    // WB01
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify a null status is rejected (covers the null branch in verifyStatus and canDelete)
    // Input(s): Status = null
    // Expected Output(s): Status rejected, delete not allowed
    @Test
    void invalidStatusNull_WB01() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.verifyStatus(null));
        assertFalse(d.canDelete(null));
    }

    // WB02
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify docket update fails with no date (covers the date branch in updateDeliveryDocket)
    // Input(s): Date = null, Status = "DELIVERED"
    // Expected Output(s): Docket not updated
    @Test
    void updateDocketNullDate_WB02() {
        DeliveryDocket d = new DeliveryDocket();
        assertFalse(d.updateDeliveryDocket(null, "DELIVERED"));
    }

    // WB03
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify a created docket stores its details (covers the getters and setters)
    // Input(s): Area = 7, Date = 2026-11-13, ID = 5
    // Expected Output(s): getDocketId() = 5, getAreaNumber() = 7, getDocketDate() = 2026-11-13, getStatus() = "PENDING"
    @Test
    void createdDocketStoresDetails_WB03() {
        DeliveryDocket d = new DeliveryDocket();
        d.createDeliveryDocket(7, LocalDate.of(2026, 11, 13));
        d.setDocketId(5);
        assertEquals(5, d.getDocketId());
        assertEquals(7, d.getAreaNumber());
        assertEquals(LocalDate.of(2026, 11, 13), d.getDocketDate());
        assertEquals("PENDING", d.getStatus());
    }
}
