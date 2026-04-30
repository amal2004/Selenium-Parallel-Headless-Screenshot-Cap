package com.amalw.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amalw.base.BaseTest;
import com.amalw.driver.DriverFactory;
import com.amalw.pages.RegisterPage;

/*RegistrationTest validates user registration functionality using multiple data sets.*/

public class RegistrationTest extends BaseTest {

	// DataProvider supplying multiple registration test data
	@DataProvider(name = "registrationData", parallel = true)
	public Object[][] getRegistrationData() {
		return new Object[][] { { "John", "Doe", "male", "ABC", "Pass123!", "Pass123!" },
				{ "Emma", "Stone", "female", "CDE", "Pass123!", "Pass123!" },
				{ "Tim", "James", "Male", "GHTG", "Pass123!", "Pass123!" },
				{ "Liam", "Brown", "male", "EFG", "Pass123!", "Pass123!" } };
	}

	// Test method for user registration using different input data sets
	@Test(dataProvider = "registrationData")
	public void testRegistration(String firstName, String lastName, String gender, String company, String password,
			String conPassword) {

		// Generate unique email to avoid duplication issues
		String email2 = UUID.randomUUID() + "@example.com";
		
		// Create page object instance
		RegisterPage registerPage = new RegisterPage();

		// open url and select gender option
		registerPage.open();
		registerPage.selectGender(gender);

		// Fill up the regisration form fields
		registerPage.fillForm(firstName, lastName, email2, company, password, conPassword);
		registerPage.submit();

		// Validate registration success
		Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration failed!");

		// Validate success message text
		Assert.assertTrue(registerPage.getConfirmationMessage().contains("registration completed"));
	}
}