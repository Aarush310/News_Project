package com.newsagent.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BillTest {

    // ===== Black-Box Tests (TC01 - TC22) =====

    // TC01
    // Test Objective: Verify month is accepted at the minimum
    // Input(s): Month = 1
    // Expected Output(s): Month accepted
    @Test
    void validMonthMinimum_TC01() {
        Bill b = new Bill();
        assertTrue(b.verifyMonth(1));
    }

    // TC02
    // Test Objective: Verify month is accepted at the maximum
    // Input(s): Month = 12
    // Expected Output(s): Month accepted
    @Test
    void validMonthMaximum_TC02() {
        Bill b = new Bill();
        assertTrue(b.verifyMonth(12));
    }

    // TC03
    // Test Objective: Verify month is rejected below the minimum
    // Input(s): Month = 0
    // Expected Output(s): Error message: "Invalid Month"
    @Test
    void invalidMonthZero_TC03() {
        Bill b = new Bill();
        assertFalse(b.verifyMonth(0));
    }

    // TC04
    // Test Objective: Verify month is rejected above the maximum
    // Input(s): Month = 13
    // Expected Output(s): Error message: "Invalid Month"
    @Test
    void invalidMonthThirteen_TC04() {
        Bill b = new Bill();
        assertFalse(b.verifyMonth(13));
    }

    // TC05
    // Test Objective: Verify year is accepted at the minimum
    // Input(s): Year = 2000
    // Expected Output(s): Year accepted
    @Test
    void validYearMinimum_TC05() {
        Bill b = new Bill();
        assertTrue(b.verifyYear(2000));
    }

    // TC06
    // Test Objective: Verify year is rejected below the minimum
    // Input(s): Year = 1999
    // Expected Output(s): Error message: "Invalid Year"
    @Test
    void invalidYearBelow_TC06() {
        Bill b = new Bill();
        assertFalse(b.verifyYear(1999));
    }

    // TC07
    // Test Objective: Verify year is accepted at the maximum
    // Input(s): Year = 2100
    // Expected Output(s): Year accepted
    @Test
    void validYearMaximum_TC07() {
        Bill b = new Bill();
        assertTrue(b.verifyYear(2100));
    }

    // TC08
    // Test Objective: Verify year is rejected above the maximum
    // Input(s): Year = 2101
    // Expected Output(s): Error message: "Invalid Year"
    @Test
    void invalidYearAbove_TC08() {
        Bill b = new Bill();
        assertFalse(b.verifyYear(2101));
    }

    // TC09
    // Test Objective: Verify a Mon-Sat daily paper is counted for every Mon-Sat in the month
    // Input(s): Days = MON-SAT, frequency = daily, November 2026
    // Expected Output(s): 25 deliveries
    @Test
    void dailyMonToSatNovember_TC09() {
        Bill b = new Bill();
        assertEquals(25, b.countDeliveries("MON,TUE,WED,THU,FRI,SAT", "daily", 2026, 11));
    }

    // TC10
    // Test Objective: Verify a Sunday paper is counted once per Sunday
    // Input(s): Days = SUN, frequency = weekly, November 2026
    // Expected Output(s): 5 deliveries
    @Test
    void weeklySundayNovember_TC10() {
        Bill b = new Bill();
        assertEquals(5, b.countDeliveries("SUN", "weekly", 2026, 11));
    }

    // TC11
    // Test Objective: Verify a weekly paper is counted once per chosen day
    // Input(s): Days = WED, frequency = weekly, November 2026
    // Expected Output(s): 4 deliveries
    @Test
    void weeklyWednesdayNovember_TC11() {
        Bill b = new Bill();
        assertEquals(4, b.countDeliveries("WED", "weekly", 2026, 11));
    }

    // TC12
    // Test Objective: Verify a monthly magazine is counted only once in the month
    // Input(s): Days = SAT, frequency = monthly, November 2026
    // Expected Output(s): 1 delivery
    @Test
    void monthlyOncePerMonth_TC12() {
        Bill b = new Bill();
        assertEquals(1, b.countDeliveries("SAT", "monthly", 2026, 11));
    }

    // TC13
    // Test Objective: Verify a 7-day order covers every day of a leap-year February
    // Input(s): Days = MON-SUN, frequency = daily, February 2028
    // Expected Output(s): 29 deliveries
    @Test
    void dailyLeapYearFebruary_TC13() {
        Bill b = new Bill();
        assertEquals(29, b.countDeliveries("MON,TUE,WED,THU,FRI,SAT,SUN", "daily", 2028, 2));
    }

    // TC14
    // Test Objective: Verify a line total is deliveries times price
    // Input(s): 26 deliveries, price 3.00
    // Expected Output(s): 78.00
    @Test
    void lineTotalSimple_TC14() {
        Bill b = new Bill();
        assertEquals(78.00, b.lineTotal(26, 3.00), 0.001);
    }

    // TC15
    // Test Objective: Verify a line total is rounded to the cent
    // Input(s): 3 deliveries, price 3.33
    // Expected Output(s): 9.99
    @Test
    void lineTotalRounded_TC15() {
        Bill b = new Bill();
        assertEquals(9.99, b.lineTotal(3, 3.33), 0.001);
    }

    // TC16
    // Test Objective: Verify a line with no deliveries costs nothing
    // Input(s): 0 deliveries, price 3.00
    // Expected Output(s): 0.00
    @Test
    void lineTotalNoDeliveries_TC16() {
        Bill b = new Bill();
        assertEquals(0.00, b.lineTotal(0, 3.00), 0.001);
    }

    // TC17
    // Test Objective: Verify the bill total adds up every line (Mary Kelly, November 2026)
    // Input(s): 25 x 3.00, 5 x 3.80, 4 x 2.50
    // Expected Output(s): Total 104.00
    @Test
    void billTotalAllLines_TC17() {
        Bill b = new Bill();
        b.addLine(25, 3.00);
        b.addLine(5, 3.80);
        b.addLine(4, 2.50);
        assertEquals(104.00, b.getTotal(), 0.001);
    }

    // TC18
    // Test Objective: Verify a one-digit number is read as a customer ID
    // Input(s): Input = "1"
    // Expected Output(s): Customer ID (true)
    @Test
    void oneDigitIsId_TC18() {
        Bill b = new Bill();
        assertTrue(b.isCustomerId("1"));
    }

    // TC19
    // Test Objective: Verify a number with spaces around it is read as a customer ID
    // Input(s): Input = " 12 "
    // Expected Output(s): Customer ID (true)
    @Test
    void numberWithSpacesIsId_TC19() {
        Bill b = new Bill();
        assertTrue(b.isCustomerId(" 12 "));
    }

    // TC20
    // Test Objective: Verify a surname is read as a name, not an ID
    // Input(s): Input = "Kelly"
    // Expected Output(s): Name (false)
    @Test
    void surnameIsName_TC20() {
        Bill b = new Bill();
        assertFalse(b.isCustomerId("Kelly"));
    }

    // TC21
    // Test Objective: Verify letters mixed with digits are read as a name, not an ID
    // Input(s): Input = "12a"
    // Expected Output(s): Name (false)
    @Test
    void mixedIsName_TC21() {
        Bill b = new Bill();
        assertFalse(b.isCustomerId("12a"));
    }

    // TC22
    // Test Objective: Verify blank input is not a customer ID
    // Input(s): Input = ""
    // Expected Output(s): Not an ID (false)
    @Test
    void blankIsNotId_TC22() {
        Bill b = new Bill();
        assertFalse(b.isCustomerId(""));
    }

    // ===== White-Box Tests (WB01 - WB03) =====

    // WB01
    // Test Objective: Verify a new bill starts at zero (covers the constructor and getTotal)
    // Input(s): New Bill, no lines
    // Expected Output(s): Total 0.00
    @Test
    void newBillIsZero_WB01() {
        Bill b = new Bill();
        assertEquals(0.00, b.getTotal(), 0.001);
    }

    // WB02
    // Test Objective: Verify a December bill stops at the 31st (covers the last day of the year in the loop)
    // Input(s): Days = MON-FRI, frequency = daily, December 2026
    // Expected Output(s): 23 deliveries
    @Test
    void decemberLoopEndsAtYearEnd_WB02() {
        Bill b = new Bill();
        assertEquals(23, b.countDeliveries("MON,TUE,WED,THU,FRI", "daily", 2026, 12));
    }

    // WB03
    // Test Objective: Verify null input is not a customer ID (covers the null check branch)
    // Input(s): Input = null
    // Expected Output(s): Not an ID (false)
    @Test
    void nullIsNotId_WB03() {
        Bill b = new Bill();
        assertFalse(b.isCustomerId(null));
    }
}
