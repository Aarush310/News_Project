package com.newsagent.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class PublicationTest {

	// ===== Black-Box Tests (TC01 - TC27) =====
	// TC01
	// Test Objective: Verify name is accepted when exactly 2 characters long
	// Input(s): Name = "RT"
	// Expected Output(s): Name accepted
	@Test
	void validNameMinBoundary_TC01() {
		Publication p = new Publication();
		assertTrue(p.verifyName("RT"));
	}

	// TC02
	// Test Objective: Verify name is accepted when exactly 50 characters long
	// Input(s): Name = "A" x 50 (50 characters)
	// Expected Output(s): Name accepted
	@Test
	void validNameMaxBoundary_TC02() {
		Publication p = new Publication();
		assertTrue(p.verifyName("A".repeat(50)));
	}

	// TC03
	// Test Objective: Verify name is accepted when between 2 and 50 characters
	// Input(s): Name = "Irish Times"
	// Expected Output(s): Name accepted
	@Test
	void validName_TC03() {
		Publication p = new Publication();
		assertTrue(p.verifyName("Irish Times"));
	}

	// TC04
	// Test Objective: Verify name is rejected when less than 2 characters
	// Input(s): Name = "A"
	// Expected Output(s): Error message: "Invalid Name"
	@Test
	void invalidNameTooShort_TC04() {
		Publication p = new Publication();
		assertFalse(p.verifyName("A"));
	}

	// TC05
	// Test Objective: Verify name is rejected when more than 50 characters
	// Input(s): Name = "A" x 51 (51 characters)
	// Expected Output(s): Error message: "Invalid Name"
	@Test
	void invalidNameTooLong_TC05() {
		Publication p = new Publication();
		assertFalse(p.verifyName("A".repeat(51)));
	}

	// TC06
	// Test Objective: Verify name is rejected when blank
	// Input(s): Name = ""
	// Expected Output(s): Error message: "Invalid Name"
	@Test
	void invalidNameBlank_TC06() {
		Publication p = new Publication();
		assertFalse(p.verifyName(""));
	}

	// TC07
	// Test Objective: Verify type is accepted when newspaper
	// Input(s): Type = "newspaper"
	// Expected Output(s): Type accepted
	@Test
	void validTypeNewspaper_TC07() {
		Publication p = new Publication();
		assertTrue(p.verifyType("newspaper"));
	}

	// TC08
	// Test Objective: Verify type is accepted when magazine
	// Input(s): Type = "magazine"
	// Expected Output(s): Type accepted
	@Test
	void validTypeMagazine_TC08() {
		Publication p = new Publication();
		assertTrue(p.verifyType("magazine"));
	}

	// TC09
	// Test Objective: Verify type is rejected when not newspaper or magazine
	// Input(s): Type = "comic"
	// Expected Output(s): Error message: "Invalid Type"
	@Test
	void invalidTypeOther_TC09() {
		Publication p = new Publication();
		assertFalse(p.verifyType("comic"));
	}

	// TC10
	// Test Objective: Verify type is rejected when blank
	// Input(s): Type = ""
	// Expected Output(s): Error message: "Invalid Type"
	@Test
	void invalidTypeBlank_TC10() {
		Publication p = new Publication();
		assertFalse(p.verifyType(""));
	}

	// TC11
	// Test Objective: Verify frequency is accepted when daily
	// Input(s): Frequency = "daily"
	// Expected Output(s): Frequency accepted
	@Test
	void validFrequencyDaily_TC11() {
		Publication p = new Publication();
		assertTrue(p.verifyFrequency("daily"));
	}

	// TC12
	// Test Objective: Verify frequency is accepted when weekly
	// Input(s): Frequency = "weekly"
	// Expected Output(s): Frequency accepted
	@Test
	void validFrequencyWeekly_TC12() {
		Publication p = new Publication();
		assertTrue(p.verifyFrequency("weekly"));
	}

	// TC13
	// Test Objective: Verify frequency is accepted when monthly
	// Input(s): Frequency = "monthly"
	// Expected Output(s): Frequency accepted
	@Test
	void validFrequencyMonthly_TC13() {
		Publication p = new Publication();
		assertTrue(p.verifyFrequency("monthly"));
	}

	// TC14
	// Test Objective: Verify frequency is rejected when not daily, weekly or monthly
	// Input(s): Frequency = "yearly"
	// Expected Output(s): Error message: "Invalid Frequency"
	@Test
	void invalidFrequencyOther_TC14() {
		Publication p = new Publication();
		assertFalse(p.verifyFrequency("yearly"));
	}

	// TC15
	// Test Objective: Verify price is accepted at the smallest amount above 0
	// Input(s): Price = 0.01
	// Expected Output(s): Price accepted
	@Test
	void validPriceMinBoundary_TC15() {
		Publication p = new Publication();
		assertTrue(p.verifyPrice(0.01));
	}

	// TC16
	// Test Objective: Verify price is accepted when a normal positive amount
	// Input(s): Price = 2.50
	// Expected Output(s): Price accepted
	@Test
	void validPrice_TC16() {
		Publication p = new Publication();
		assertTrue(p.verifyPrice(2.50));
	}

	// TC17
	// Test Objective: Verify price is rejected when exactly 0
	// Input(s): Price = 0.00
	// Expected Output(s): Error message: "Invalid Price"
	@Test
	void invalidPriceZero_TC17() {
		Publication p = new Publication();
		assertFalse(p.verifyPrice(0.00));
	}

	// TC18
	// Test Objective: Verify price is rejected when negative
	// Input(s): Price = -1.50
	// Expected Output(s): Error message: "Invalid Price"
	@Test
	void invalidPriceNegative_TC18() {
		Publication p = new Publication();
		assertFalse(p.verifyPrice(-1.50));
	}

	// TC19
	// Test Objective: Verify quantity in stock is accepted when exactly 0
	// Input(s): Quantity in stock = 0
	// Expected Output(s): Quantity accepted
	@Test
	void validStockMinBoundary_TC19() {
		Publication p = new Publication();
		assertTrue(p.verifyQuantityInStock(0));
	}

	// TC20
	// Test Objective: Verify quantity in stock is accepted when more than 0
	// Input(s): Quantity in stock = 100
	// Expected Output(s): Quantity accepted
	@Test
	void validStock_TC20() {
		Publication p = new Publication();
		assertTrue(p.verifyQuantityInStock(100));
	}

	// TC21
	// Test Objective: Verify quantity in stock is rejected when less than 0
	// Input(s): Quantity in stock = -1
	// Expected Output(s): Error message: "Invalid Quantity"
	@Test
	void invalidStockNegative_TC21() {
		Publication p = new Publication();
		assertFalse(p.verifyQuantityInStock(-1));
	}

	// TC22
	// Test Objective: Verify publication is created when all details are valid
	// Input(s): Irish Times, newspaper, daily, 2.50, 100
	// Expected Output(s): Publication created successfully
	@Test
	void createValidPublication_TC22() {
		Publication p = new Publication();
		assertTrue(p.createPublication("Irish Times", "newspaper", "daily", 2.50, 100));
	}

	// TC23
	// Test Objective: Verify publication creation fails with invalid name
	// Input(s): A, newspaper, daily, 2.50, 100
	// Expected Output(s): Publication not created
	@Test
	void createPublicationInvalidName_TC23() {
		Publication p = new Publication();
		assertFalse(p.createPublication("A", "newspaper", "daily", 2.50, 100));
	}

	// TC24
	// Test Objective: Verify publication creation fails with invalid type
	// Input(s): Irish Times, comic, daily, 2.50, 100
	// Expected Output(s): Publication not created
	@Test
	void createPublicationInvalidType_TC24() {
		Publication p = new Publication();
		assertFalse(p.createPublication("Irish Times", "comic", "daily", 2.50, 100));
	}

	// TC25
	// Test Objective: Verify publication creation fails with invalid frequency
	// Input(s): Irish Times, newspaper, yearly, 2.50, 100
	// Expected Output(s): Publication not created
	@Test
	void createPublicationInvalidFrequency_TC25() {
		Publication p = new Publication();
		assertFalse(p.createPublication("Irish Times", "newspaper", "yearly", 2.50, 100));
	}

	// TC26
	// Test Objective: Verify publication creation fails with invalid price
	// Input(s): Irish Times, newspaper, daily, 0.00, 100
	// Expected Output(s): Publication not created
	@Test
	void createPublicationInvalidPrice_TC26() {
		Publication p = new Publication();
		assertFalse(p.createPublication("Irish Times", "newspaper", "daily", 0.00, 100));
	}

	// TC27
	// Test Objective: Verify publication creation fails with invalid quantity in stock
	// Input(s): Irish Times, newspaper, daily, 2.50, -1
	// Expected Output(s): Publication not created
	@Test
	void createPublicationInvalidStock_TC27() {
		Publication p = new Publication();
		assertFalse(p.createPublication("Irish Times", "newspaper", "daily", 2.50, -1));
	}

	// ===== White-Box Tests (WB01 - WB02) =====
	// WB01
	// Test Objective: Verify name is rejected when null (covers the null check branch in verifyName)
	// Input(s): Name = null
	// Expected Output(s): Error message: "Invalid Name"
	@Test
	void invalidNameNull_WB01() {
		Publication p = new Publication();
		assertFalse(p.verifyName(null));
	}

	// WB02
	// Test Objective: Verify a created publication stores its details (covers the getter methods)
	// Input(s): Irish Times, newspaper, daily, 2.50, 100
	// Expected Output(s): getName() = "Irish Times", getType() = "newspaper", getFrequency() = "daily", getPrice() = 2.50, getQuantityInStock() = 100
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
