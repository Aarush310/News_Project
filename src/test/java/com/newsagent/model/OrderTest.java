package com.newsagent.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class OrderTest {

    // ===== Black-Box Tests (TC01 - TC43) =====

    // TC01
    // Technique: BVA
    // Test Objective: Verify customer ID is accepted at the minimum boundary
    // Input(s): Customer ID = 1
    // Expected Output(s): Customer ID accepted
    @Test
    void validCustomerIdMinBoundary_TC01() {
        Order o = new Order();
        assertTrue(o.verifyCustomerId(1));
    }

    // TC02
    // Technique: BVA
    // Test Objective: Verify customer ID is rejected below the minimum
    // Input(s): Customer ID = 0
    // Expected Output(s): Error message: "Invalid Customer ID"
    @Test
    void invalidCustomerIdZero_TC02() {
        Order o = new Order();
        assertFalse(o.verifyCustomerId(0));
    }

    // TC03
    // Technique: BVA
    // Test Objective: Verify publication ID is accepted at the minimum boundary
    // Input(s): Publication ID = 1
    // Expected Output(s): Publication ID accepted
    @Test
    void validPublicationIdMinBoundary_TC03() {
        Order o = new Order();
        assertTrue(o.verifyPublicationId(1));
    }

    // TC04
    // Technique: BVA
    // Test Objective: Verify publication ID is rejected below the minimum
    // Input(s): Publication ID = 0
    // Expected Output(s): Error message: "Invalid Publication ID"
    @Test
    void invalidPublicationIdZero_TC04() {
        Order o = new Order();
        assertFalse(o.verifyPublicationId(0));
    }

    // TC05
    // Technique: BVA
    // Test Objective: Verify delivery days are accepted at the minimum (1 day)
    // Input(s): Delivery Days = "MON"
    // Expected Output(s): Delivery days accepted
    @Test
    void validDeliveryDaysMinimum_TC05() {
        Order o = new Order();
        assertTrue(o.verifyDeliveryDays("MON"));
    }

    // TC06
    // Technique: BVA
    // Test Objective: Verify delivery days are accepted at the maximum (7 days)
    // Input(s): Delivery Days = "MON,TUE,WED,THU,FRI,SAT,SUN"
    // Expected Output(s): Delivery days accepted
    @Test
    void validDeliveryDaysMaximum_TC06() {
        Order o = new Order();
        assertTrue(o.verifyDeliveryDays("MON,TUE,WED,THU,FRI,SAT,SUN"));
    }

    // TC07
    // Technique: BVA
    // Test Objective: Verify delivery days are rejected when blank
    // Input(s): Delivery Days = ""
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysBlank_TC07() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays(""));
    }

    // TC08
    // Technique: EP
    // Test Objective: Verify delivery days are rejected when a day is not valid
    // Input(s): Delivery Days = "MON,XYZ"
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysUnknownDay_TC08() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays("MON,XYZ"));
    }

    // TC09
    // Technique: EP
    // Test Objective: Verify delivery days are rejected when a day is repeated
    // Input(s): Delivery Days = "MON,MON"
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysRepeated_TC09() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays("MON,MON"));
    }

    // TC10
    // Technique: EP
    // Test Objective: Verify a publication not yet on the order is accepted
    // Input(s): Publication ID = 2; publications already on order = [1]
    // Expected Output(s): Publication accepted
    @Test
    void validNewPublication_TC10() {
        Order o = new Order();
        assertTrue(o.verifyNewPublication(2, new int[] {1}));
    }

    // TC11
    // Technique: EP
    // Test Objective: Verify a publication already on the order is rejected
    // Input(s): Publication ID = 1; publications already on order = [1]
    // Expected Output(s): Error message: "Publication already on this order"
    @Test
    void invalidPublicationAlreadyOnOrder_TC11() {
        Order o = new Order();
        assertFalse(o.verifyNewPublication(1, new int[] {1}));
    }

    // TC12
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify order is created when all details are valid
    // Input(s): 1, 2, MON,TUE,WED,THU,FRI, already on order = [1]
    // Expected Output(s): Order created successfully
    @Test
    void createValidOrder_TC12() {
        Order o = new Order();
        assertTrue(o.createOrder(1, 2, "MON,TUE,WED,THU,FRI", new int[] {1}));
    }

    // TC13
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify order creation fails with invalid customer ID
    // Input(s): 0, 2, MON,TUE,WED,THU,FRI, already on order = [1]
    // Expected Output(s): Order not created
    @Test
    void createOrderInvalidCustomerId_TC13() {
        Order o = new Order();
        assertFalse(o.createOrder(0, 2, "MON,TUE,WED,THU,FRI", new int[] {1}));
    }

    // TC14
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify order creation fails with invalid publication ID
    // Input(s): 1, 0, MON,TUE,WED,THU,FRI, already on order = [1]
    // Expected Output(s): Order not created
    @Test
    void createOrderInvalidPublicationId_TC14() {
        Order o = new Order();
        assertFalse(o.createOrder(1, 0, "MON,TUE,WED,THU,FRI", new int[] {1}));
    }

    // TC15
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify order creation fails with invalid delivery days
    // Input(s): 1, 2, MON,XYZ, already on order = [1]
    // Expected Output(s): Order not created
    @Test
    void createOrderInvalidDeliveryDays_TC15() {
        Order o = new Order();
        assertFalse(o.createOrder(1, 2, "MON,XYZ", new int[] {1}));
    }

    // TC16
    // Technique: EP (combined: one invalid field at a time)
    // Test Objective: Verify order creation fails when the publication is already on the order
    // Input(s): 1, 1, MON,TUE,WED,THU,FRI, already on order = [1]
    // Expected Output(s): Order not created
    @Test
    void createOrderPublicationAlreadyOnOrder_TC16() {
        Order o = new Order();
        assertFalse(o.createOrder(1, 1, "MON,TUE,WED,THU,FRI", new int[] {1}));
    }

    // TC17
    // Technique: EP
    // Test Objective: Verify a weekday-only daily paper is due on a Friday
    // Input(s): Days = MON-FRI, frequency = daily, date = Fri 16/10/2026
    // Expected Output(s): Due (true)
    @Test
    void dailyDueOnFriday_TC17() {
        Order o = new Order();
        assertTrue(o.isDueOn("MON,TUE,WED,THU,FRI", "daily", LocalDate.of(2026, 10, 16)));
    }

    // TC18
    // Technique: EP
    // Test Objective: Verify a weekday-only daily paper is not due on a Saturday
    // Input(s): Days = MON-FRI, frequency = daily, date = Sat 17/10/2026
    // Expected Output(s): Not due (false)
    @Test
    void dailyNotDueOnSaturday_TC18() {
        Order o = new Order();
        assertFalse(o.isDueOn("MON,TUE,WED,THU,FRI", "daily", LocalDate.of(2026, 10, 17)));
    }

    // TC19
    // Technique: EP
    // Test Objective: Verify a weekly magazine is due on its chosen day
    // Input(s): Days = SAT, frequency = weekly, date = Sat 10/10/2026
    // Expected Output(s): Due (true)
    @Test
    void weeklyDueOnChosenDay_TC19() {
        Order o = new Order();
        assertTrue(o.isDueOn("SAT", "weekly", LocalDate.of(2026, 10, 10)));
    }

    // TC20
    // Technique: EP
    // Test Objective: Verify a weekly magazine is not due on another day
    // Input(s): Days = SAT, frequency = weekly, date = Mon 05/10/2026
    // Expected Output(s): Not due (false)
    @Test
    void weeklyNotDueOnOtherDay_TC20() {
        Order o = new Order();
        assertFalse(o.isDueOn("SAT", "weekly", LocalDate.of(2026, 10, 5)));
    }

    // TC21
    // Technique: EP
    // Test Objective: Verify a monthly magazine is due on the first chosen day of the month
    // Input(s): Days = SAT, frequency = monthly, date = Sat 03/10/2026
    // Expected Output(s): Due (true)
    @Test
    void monthlyDueOnFirstChosenDay_TC21() {
        Order o = new Order();
        assertTrue(o.isDueOn("SAT", "monthly", LocalDate.of(2026, 10, 3)));
    }

    // TC22
    // Technique: EP
    // Test Objective: Verify a monthly magazine is not due on a later chosen day in the month
    // Input(s): Days = SAT, frequency = monthly, date = Sat 10/10/2026
    // Expected Output(s): Not due (false)
    @Test
    void monthlyNotDueOnLaterChosenDay_TC22() {
        Order o = new Order();
        assertFalse(o.isDueOn("SAT", "monthly", LocalDate.of(2026, 10, 10)));
    }

    // TC23
    // Technique: EP
    // Test Objective: Verify delivery days are rejected when they end with a comma
    // Input(s): Delivery Days = "MON,"
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysTrailingComma_TC23() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays("MON,"));
    }

    // TC24
    // Technique: EP
    // Test Objective: Verify a publication is accepted when the customer has no publications on the order yet
    // Input(s): Publication ID = 1; publications already on order = [] (empty)
    // Expected Output(s): Publication accepted
    @Test
    void validNewPublicationFirstOnOrder_TC24() {
        Order o = new Order();
        assertTrue(o.verifyNewPublication(1, new int[] {}));
    }

    // TC25
    // Technique: EP
    // Test Objective: Verify a monthly magazine with two chosen days is due only on the first chosen day of the month
    // Input(s): Days = MON,SAT, frequency = monthly, dates = Sat 03/10/2026 and Mon 05/10/2026
    // Expected Output(s): Sat 03/10/2026 due (true); Mon 05/10/2026 not due (false)
    @Test
    void monthlyTwoChosenDaysDueOnlyOnFirst_TC25() {
        Order o = new Order();
        assertTrue(o.isDueOn("MON,SAT", "monthly", LocalDate.of(2026, 10, 3)));
        assertFalse(o.isDueOn("MON,SAT", "monthly", LocalDate.of(2026, 10, 5)));
    }

    // TC26
    // Technique: BVA
    // Test Objective: Verify a customer name is accepted at the minimum length (1 character)
    // Input(s): Customer Name = "M"
    // Expected Output(s): Name accepted
    @Test
    void validCustomerNameMinimum_TC26() {
        Order o = new Order();
        assertTrue(o.verifyCustomerName("M"));
    }

    // TC27
    // Technique: BVA
    // Test Objective: Verify a customer name is accepted at the maximum length (50 characters)
    // Input(s): Customer Name = 50 x "a"
    // Expected Output(s): Name accepted
    @Test
    void validCustomerNameMaximum_TC27() {
        Order o = new Order();
        assertTrue(o.verifyCustomerName("a".repeat(50)));
    }

    // TC28
    // Technique: BVA
    // Test Objective: Verify a customer name is rejected above the maximum length (51 characters)
    // Input(s): Customer Name = 51 x "a"
    // Expected Output(s): Error message: "Invalid Customer Name"
    @Test
    void invalidCustomerNameTooLong_TC28() {
        Order o = new Order();
        assertFalse(o.verifyCustomerName("a".repeat(51)));
    }

    // TC29
    // Technique: EP
    // Test Objective: Verify a customer name is rejected when it is only spaces
    // Input(s): Customer Name = "   "
    // Expected Output(s): Error message: "Invalid Customer Name"
    @Test
    void invalidCustomerNameBlank_TC29() {
        Order o = new Order();
        assertFalse(o.verifyCustomerName("   "));
    }


    // TC30
    // Technique: BVA
    // Test Objective: Verify customer ID is accepted at min+1
    // Input(s): Customer ID = 2
    // Expected Output(s): Customer ID accepted
    @Test
    void validCustomerIdMinPlusOne_TC30() {
        Order o = new Order();
        assertTrue(o.verifyCustomerId(2));
    }

    // TC31
    // Technique: EP
    // Test Objective: Verify a negative customer ID is rejected (invalid partition: below range)
    // Input(s): Customer ID = -1
    // Expected Output(s): Error message: "Invalid Customer ID"
    @Test
    void invalidCustomerIdNegative_TC31() {
        Order o = new Order();
        assertFalse(o.verifyCustomerId(-1));
    }

    // TC32
    // Technique: EP
    // Test Objective: Verify a large customer ID is accepted (valid partition: no upper limit)
    // Input(s): Customer ID = 100000
    // Expected Output(s): Customer ID accepted
    @Test
    void validCustomerIdLarge_TC32() {
        Order o = new Order();
        assertTrue(o.verifyCustomerId(100000));
    }

    // TC33
    // Technique: BVA
    // Test Objective: Verify publication ID is accepted at min+1
    // Input(s): Publication ID = 2
    // Expected Output(s): Publication ID accepted
    @Test
    void validPublicationIdMinPlusOne_TC33() {
        Order o = new Order();
        assertTrue(o.verifyPublicationId(2));
    }

    // TC34
    // Technique: EP
    // Test Objective: Verify a negative publication ID is rejected (invalid partition: below range)
    // Input(s): Publication ID = -1
    // Expected Output(s): Error message: "Invalid Publication ID"
    @Test
    void invalidPublicationIdNegative_TC34() {
        Order o = new Order();
        assertFalse(o.verifyPublicationId(-1));
    }

    // TC35
    // Technique: BVA
    // Test Objective: Verify delivery days are accepted at min+1 (2 days)
    // Input(s): Delivery Days = "MON,TUE"
    // Expected Output(s): Delivery days accepted
    @Test
    void validDeliveryDaysMinPlusOne_TC35() {
        Order o = new Order();
        assertTrue(o.verifyDeliveryDays("MON,TUE"));
    }

    // TC36
    // Technique: BVA
    // Test Objective: Verify delivery days are accepted at max-1 (6 days)
    // Input(s): Delivery Days = "MON,TUE,WED,THU,FRI,SAT"
    // Expected Output(s): Delivery days accepted
    @Test
    void validDeliveryDaysMaxMinusOne_TC36() {
        Order o = new Order();
        assertTrue(o.verifyDeliveryDays("MON,TUE,WED,THU,FRI,SAT"));
    }

    // TC37
    // Technique: BVA
    // Test Objective: Verify delivery days are rejected above the maximum (8 days)
    // Input(s): Delivery Days = "MON,TUE,WED,THU,FRI,SAT,SUN,MON"
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysTooMany_TC37() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays("MON,TUE,WED,THU,FRI,SAT,SUN,MON"));
    }

    // TC38
    // Technique: EP
    // Test Objective: Verify lowercase day names are rejected (invalid partition: wrong case)
    // Input(s): Delivery Days = "mon,tue"
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysLowercase_TC38() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays("mon,tue"));
    }

    // TC39
    // Technique: EP
    // Test Objective: Verify days separated by comma and space are rejected (invalid partition: wrong format)
    // Input(s): Delivery Days = "MON, TUE"
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysSpaces_TC39() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays("MON, TUE"));
    }

    // TC40
    // Technique: BVA
    // Test Objective: Verify customer name search is accepted at min+1 (2 characters)
    // Input(s): Customer Name = "Al"
    // Expected Output(s): Customer name accepted
    @Test
    void validCustomerNameMinPlusOne_TC40() {
        Order o = new Order();
        assertTrue(o.verifyCustomerName("Al"));
    }

    // TC41
    // Technique: BVA
    // Test Objective: Verify customer name search is accepted at max-1 (49 characters)
    // Input(s): Customer Name = 49 x "a"
    // Expected Output(s): Customer name accepted
    @Test
    void validCustomerNameMaxMinusOne_TC41() {
        Order o = new Order();
        assertTrue(o.verifyCustomerName("a".repeat(49)));
    }

    // TC42
    // Technique: BVA
    // Test Objective: Verify customer name search is rejected below the minimum (0 characters)
    // Input(s): Customer Name = ""
    // Expected Output(s): Error message: "Invalid Customer Name"
    @Test
    void invalidCustomerNameEmpty_TC42() {
        Order o = new Order();
        assertFalse(o.verifyCustomerName(""));
    }

    // TC43
    // Technique: EP
    // Test Objective: Verify a full name with surrounding spaces is accepted (valid partition: trimmed)
    // Input(s): Customer Name = "  Aarush Prasad  "
    // Expected Output(s): Customer name accepted
    @Test
    void validCustomerNameTrimmed_TC43() {
        Order o = new Order();
        assertTrue(o.verifyCustomerName("  Aarush Prasad  "));
    }

    // ===== White-Box Tests (WB01 - WB03) =====

    // WB01
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify delivery days are rejected when null (covers the null check branch)
    // Input(s): Delivery Days = null
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysNull_WB01() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays(null));
    }

    // WB02
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify a created order stores its details (covers the getter methods)
    // Input(s): 1, 2, MON,TUE,WED,THU,FRI, already on order = [1]
    // Expected Output(s): getCustomerId() = 1, getPublicationId() = 2, getDeliveryDays() = "MON,TUE,WED,THU,FRI"
    @Test
    void createdOrderStoresDetails_WB02() {
        Order o = new Order();
        o.createOrder(1, 2, "MON,TUE,WED,THU,FRI", new int[] {1});
        assertEquals(1, o.getCustomerId());
        assertEquals(2, o.getPublicationId());
        assertEquals("MON,TUE,WED,THU,FRI", o.getDeliveryDays());
    }

    // WB03
    // Technique: White-box (branch / statement coverage)
    // Test Objective: Verify a customer name is rejected when null (covers the null check branch)
    // Input(s): Customer Name = null
    // Expected Output(s): Error message: "Invalid Customer Name"
    @Test
    void invalidCustomerNameNull_WB03() {
        Order o = new Order();
        assertFalse(o.verifyCustomerName(null));
    }
}
