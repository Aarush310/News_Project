import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class OrderTest {

    // ===== Black-Box Tests (TC01 - TC22) =====

    // TC01
    // Test Objective: Verify customer ID is accepted at the minimum boundary
    // Input(s): Customer ID = 1
    // Expected Output(s): Customer ID accepted
    @Test
    void validCustomerIdMinBoundary_TC01() {
        Order o = new Order();
        assertTrue(o.verifyCustomerId(1));
    }

    // TC02
    // Test Objective: Verify customer ID is rejected below the minimum
    // Input(s): Customer ID = 0
    // Expected Output(s): Error message: "Invalid Customer ID"
    @Test
    void invalidCustomerIdZero_TC02() {
        Order o = new Order();
        assertFalse(o.verifyCustomerId(0));
    }

    // TC03
    // Test Objective: Verify publication ID is accepted at the minimum boundary
    // Input(s): Publication ID = 1
    // Expected Output(s): Publication ID accepted
    @Test
    void validPublicationIdMinBoundary_TC03() {
        Order o = new Order();
        assertTrue(o.verifyPublicationId(1));
    }

    // TC04
    // Test Objective: Verify publication ID is rejected below the minimum
    // Input(s): Publication ID = 0
    // Expected Output(s): Error message: "Invalid Publication ID"
    @Test
    void invalidPublicationIdZero_TC04() {
        Order o = new Order();
        assertFalse(o.verifyPublicationId(0));
    }

    // TC05
    // Test Objective: Verify delivery days are accepted at the minimum (1 day)
    // Input(s): Delivery Days = "MON"
    // Expected Output(s): Delivery days accepted
    @Test
    void validDeliveryDaysMinimum_TC05() {
        Order o = new Order();
        assertTrue(o.verifyDeliveryDays("MON"));
    }

    // TC06
    // Test Objective: Verify delivery days are accepted at the maximum (7 days)
    // Input(s): Delivery Days = "MON,TUE,WED,THU,FRI,SAT,SUN"
    // Expected Output(s): Delivery days accepted
    @Test
    void validDeliveryDaysMaximum_TC06() {
        Order o = new Order();
        assertTrue(o.verifyDeliveryDays("MON,TUE,WED,THU,FRI,SAT,SUN"));
    }

    // TC07
    // Test Objective: Verify delivery days are rejected when blank
    // Input(s): Delivery Days = ""
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysBlank_TC07() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays(""));
    }

    // TC08
    // Test Objective: Verify delivery days are rejected when a day is not valid
    // Input(s): Delivery Days = "MON,XYZ"
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysUnknownDay_TC08() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays("MON,XYZ"));
    }

    // TC09
    // Test Objective: Verify delivery days are rejected when a day is repeated
    // Input(s): Delivery Days = "MON,MON"
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysRepeated_TC09() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays("MON,MON"));
    }

    // TC10
    // Test Objective: Verify a publication not yet on the order is accepted
    // Input(s): Publication ID = 2; publications already on order = [1]
    // Expected Output(s): Publication accepted
    @Test
    void validNewPublication_TC10() {
        Order o = new Order();
        assertTrue(o.verifyNewPublication(2, new int[] {1}));
    }

    // TC11
    // Test Objective: Verify a publication already on the order is rejected
    // Input(s): Publication ID = 1; publications already on order = [1]
    // Expected Output(s): Error message: "Publication already on this order"
    @Test
    void invalidPublicationAlreadyOnOrder_TC11() {
        Order o = new Order();
        assertFalse(o.verifyNewPublication(1, new int[] {1}));
    }

    // TC12
    // Test Objective: Verify order is created when all details are valid
    // Input(s): 1, 2, MON,TUE,WED,THU,FRI, already on order = [1]
    // Expected Output(s): Order created successfully
    @Test
    void createValidOrder_TC12() {
        Order o = new Order();
        assertTrue(o.createOrder(1, 2, "MON,TUE,WED,THU,FRI", new int[] {1}));
    }

    // TC13
    // Test Objective: Verify order creation fails with invalid customer ID
    // Input(s): 0, 2, MON,TUE,WED,THU,FRI, already on order = [1]
    // Expected Output(s): Order not created
    @Test
    void createOrderInvalidCustomerId_TC13() {
        Order o = new Order();
        assertFalse(o.createOrder(0, 2, "MON,TUE,WED,THU,FRI", new int[] {1}));
    }

    // TC14
    // Test Objective: Verify order creation fails with invalid publication ID
    // Input(s): 1, 0, MON,TUE,WED,THU,FRI, already on order = [1]
    // Expected Output(s): Order not created
    @Test
    void createOrderInvalidPublicationId_TC14() {
        Order o = new Order();
        assertFalse(o.createOrder(1, 0, "MON,TUE,WED,THU,FRI", new int[] {1}));
    }

    // TC15
    // Test Objective: Verify order creation fails with invalid delivery days
    // Input(s): 1, 2, MON,XYZ, already on order = [1]
    // Expected Output(s): Order not created
    @Test
    void createOrderInvalidDeliveryDays_TC15() {
        Order o = new Order();
        assertFalse(o.createOrder(1, 2, "MON,XYZ", new int[] {1}));
    }

    // TC16
    // Test Objective: Verify order creation fails when the publication is already on the order
    // Input(s): 1, 1, MON,TUE,WED,THU,FRI, already on order = [1]
    // Expected Output(s): Order not created
    @Test
    void createOrderPublicationAlreadyOnOrder_TC16() {
        Order o = new Order();
        assertFalse(o.createOrder(1, 1, "MON,TUE,WED,THU,FRI", new int[] {1}));
    }

    // TC17
    // Test Objective: Verify a weekday-only daily paper is due on a Friday
    // Input(s): Days = MON-FRI, frequency = daily, date = Fri 16/10/2026
    // Expected Output(s): Due (true)
    @Test
    void dailyDueOnFriday_TC17() {
        Order o = new Order();
        assertTrue(o.isDueOn("MON,TUE,WED,THU,FRI", "daily", LocalDate.of(2026, 10, 16)));
    }

    // TC18
    // Test Objective: Verify a weekday-only daily paper is not due on a Saturday
    // Input(s): Days = MON-FRI, frequency = daily, date = Sat 17/10/2026
    // Expected Output(s): Not due (false)
    @Test
    void dailyNotDueOnSaturday_TC18() {
        Order o = new Order();
        assertFalse(o.isDueOn("MON,TUE,WED,THU,FRI", "daily", LocalDate.of(2026, 10, 17)));
    }

    // TC19
    // Test Objective: Verify a weekly magazine is due on its chosen day
    // Input(s): Days = SAT, frequency = weekly, date = Sat 10/10/2026
    // Expected Output(s): Due (true)
    @Test
    void weeklyDueOnChosenDay_TC19() {
        Order o = new Order();
        assertTrue(o.isDueOn("SAT", "weekly", LocalDate.of(2026, 10, 10)));
    }

    // TC20
    // Test Objective: Verify a weekly magazine is not due on another day
    // Input(s): Days = SAT, frequency = weekly, date = Mon 05/10/2026
    // Expected Output(s): Not due (false)
    @Test
    void weeklyNotDueOnOtherDay_TC20() {
        Order o = new Order();
        assertFalse(o.isDueOn("SAT", "weekly", LocalDate.of(2026, 10, 5)));
    }

    // TC21
    // Test Objective: Verify a monthly magazine is due on the first chosen day of the month
    // Input(s): Days = SAT, frequency = monthly, date = Sat 03/10/2026
    // Expected Output(s): Due (true)
    @Test
    void monthlyDueOnFirstChosenDay_TC21() {
        Order o = new Order();
        assertTrue(o.isDueOn("SAT", "monthly", LocalDate.of(2026, 10, 3)));
    }

    // TC22
    // Test Objective: Verify a monthly magazine is not due on a later chosen day in the month
    // Input(s): Days = SAT, frequency = monthly, date = Sat 10/10/2026
    // Expected Output(s): Not due (false)
    @Test
    void monthlyNotDueOnLaterChosenDay_TC22() {
        Order o = new Order();
        assertFalse(o.isDueOn("SAT", "monthly", LocalDate.of(2026, 10, 10)));
    }

    // ===== White-Box Tests (WB01 - WB02) =====

    // WB01
    // Test Objective: Verify delivery days are rejected when null (covers the null check branch)
    // Input(s): Delivery Days = null
    // Expected Output(s): Error message: "Invalid Delivery Days"
    @Test
    void invalidDeliveryDaysNull_WB01() {
        Order o = new Order();
        assertFalse(o.verifyDeliveryDays(null));
    }

    // WB02
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
}
