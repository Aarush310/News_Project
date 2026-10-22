package com.newsagent.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class PublicationTest {

	// ===== Black-Box Tests (TC01 - TC27) =====
	@Test
	void validNameMinBoundary_TC01() {
		Publication p = new Publication();
		assertTrue(p.verifyName("RT"));
	}

	@Test
	void validNameMaxBoundary_TC02() {
		Publication p = new Publication();
		assertTrue(p.verifyName("A".repeat(50)));
	}

	@Test
	void validName_TC03() {
		Publication p = new Publication();
		assertTrue(p.verifyName("Irish Times"));
	}

	@Test
	void invalidNameTooShort_TC04() {
		Publication p = new Publication();
		assertFalse(p.verifyName("A"));
	}

	@Test
	void invalidNameTooLong_TC05() {
		Publication p = new Publication();
		assertFalse(p.verifyName("A".repeat(51)));
	}

	@Test
	void invalidNameBlank_TC06() {
		Publication p = new Publication();
		assertFalse(p.verifyName(""));
	}

	@Test
	void validTypeNewspaper_TC07() {
		Publication p = new Publication();
		assertTrue(p.verifyType("newspaper"));
	}

	@Test
	void validTypeMagazine_TC08() {
		Publication p = new Publication();
		assertTrue(p.verifyType("magazine"));
	}

	@Test
	void invalidTypeOther_TC09() {
		Publication p = new Publication();
		assertFalse(p.verifyType("comic"));
	}

	@Test
	void invalidTypeBlank_TC10() {
		Publication p = new Publication();
		assertFalse(p.verifyType(""));
	}

	@Test
	void validFrequencyDaily_TC11() {
		Publication p = new Publication();
		assertTrue(p.verifyFrequency("daily"));
	}

	@Test
	void validFrequencyWeekly_TC12() {
		Publication p = new Publication();
		assertTrue(p.verifyFrequency("weekly"));
	}

	@Test
	void validFrequencyMonthly_TC13() {
		Publication p = new Publication();
		assertTrue(p.verifyFrequency("monthly"));
	}

	@Test
	void invalidFrequencyOther_TC14() {
		Publication p = new Publication();
		assertFalse(p.verifyFrequency("yearly"));
	}

	@Test
	void validPriceMinBoundary_TC15() {
		Publication p = new Publication();
		assertTrue(p.verifyPrice(0.01));
	}

	@Test
	void validPrice_TC16() {
		Publication p = new Publication();
		assertTrue(p.verifyPrice(2.50));
	}

	@Test
	void invalidPriceZero_TC17() {
		Publication p = new Publication();
		assertFalse(p.verifyPrice(0.00));
	}

	@Test
	void invalidPriceNegative_TC18() {
		Publication p = new Publication();
		assertFalse(p.verifyPrice(-1.50));
	}

	@Test
	void validStockMinBoundary_TC19() {
		Publication p = new Publication();
		assertTrue(p.verifyQuantityInStock(0));
	}

	@Test
	void validStock_TC20() {
		Publication p = new Publication();
		assertTrue(p.verifyQuantityInStock(100));
	}

	@Test
	void invalidStockNegative_TC21() {
		Publication p = new Publication();
		assertFalse(p.verifyQuantityInStock(-1));
	}

	@Test
	void createValidPublication_TC22() {
		Publication p = new Publication();
		assertTrue(p.createPublication("Irish Times", "newspaper", "daily", 2.50, 100));
	}

	@Test
	void createPublicationInvalidName_TC23() {
		Publication p = new Publication();
		assertFalse(p.createPublication("A", "newspaper", "daily", 2.50, 100));
	}

	@Test
	void createPublicationInvalidType_TC24() {
		Publication p = new Publication();
		assertFalse(p.createPublication("Irish Times", "comic", "daily", 2.50, 100));
	}

	@Test
	void createPublicationInvalidFrequency_TC25() {
		Publication p = new Publication();
		assertFalse(p.createPublication("Irish Times", "newspaper", "yearly", 2.50, 100));
	}

	@Test
	void createPublicationInvalidPrice_TC26() {
		Publication p = new Publication();
		assertFalse(p.createPublication("Irish Times", "newspaper", "daily", 0.00, 100));
	}

	@Test
	void createPublicationInvalidStock_TC27() {
		Publication p = new Publication();
		assertFalse(p.createPublication("Irish Times", "newspaper", "daily", 2.50, -1));
	}

	// ===== White-Box Tests (WB01 - WB02) =====
	@Test
	void invalidNameNull_WB01() {
		Publication p = new Publication();
		assertFalse(p.verifyName(null));
	}

	@Test
	void createdPublicationStoresDetails_WB02() {
		Publication p = new Publication();
		p.createPublication("Irish Times", "newspaper", "daily", 2.50, 100);
		assertEquals("Irish Times", p.getName());
		assertEquals("newspaper", p.getType());
		assertEquals("daily", p.getFrequency());
		assertEquals(2.50, p.getPrice());
		assertEquals(100, p.getQuantityInStock());
	}
}
