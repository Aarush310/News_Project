
package com.newsagent.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryAreaTest {

    @Test
    void TC01() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaNumber(1));
    }

    @Test
    void TC02() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaNumber(24));
    }

    @Test
    void TC03() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaNumber(0));
    }

    @Test
    void TC04() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaNumber(25));
    }

    @Test
    void TC05() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaName("C"));
    }

    @Test
    void TC06() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyAreaName("Church Street"));
    }

    @Test
    void TC07() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaName(""));
    }

    @Test
    void TC08() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.verifyDeliveryPersonId(1));
    }

    @Test
    void TC09() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyDeliveryPersonId(0));
    }

    @Test
    void TC10() {
        DeliveryArea area = new DeliveryArea();
        assertTrue(area.createDeliveryArea(
            5, "Church Street", 1));
    }

    @Test
    void TC11() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.createDeliveryArea(
            25, "Church Street", 1));
    }

    @Test
    void TC12() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.createDeliveryArea(
            5, "", 1));
    }

    @Test
    void TC13() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.createDeliveryArea(
            5, "Church Street", 0));
    }

    @Test
    void WB01() {
        DeliveryArea area = new DeliveryArea();
        assertFalse(area.verifyAreaName(null));
    }

    @Test
    void WB02() {
        DeliveryArea area = new DeliveryArea(
            5, "Church Street", 1);

        assertEquals(5, area.getAreaNumber());
        assertEquals("Church Street", area.getAreaName());
        assertEquals(1, area.getDeliveryPersonId());
    }
}
