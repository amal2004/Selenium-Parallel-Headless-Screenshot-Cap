package com.amalw.pages;

import org.openqa.selenium.By;

import com.amalw.config.ConfigManager;

/* Provides actions to interact with registration form elements */

public class RegisterPage extends BasePage {

	// Locators
	private By genderMale = By.id("gender-male");
	private By genderFemale = By.id("gender-female");
	private By firstName = By.id("FirstName");
	private By lastName = By.id("LastName");
	private By email = By.id("Email");
	private By company = By.id("Company");
	private By password = By.id("Password");
	private By confirmPassword = By.id("ConfirmPassword");
	private By registerButton = By.id("register-button");
	private By successMsg = By.cssSelector("div.result");
	private By emailError = By.id("Email-error");

	// Navigate to registration page
	public void open() {
		navigateTo(ConfigManager.get("base.url")+"/register");
	}

	// Select gender radio button based on input value
	public void selectGender(String gender) {
		if (gender.equalsIgnoreCase("male"))
			click(genderMale);
		else if (gender.equalsIgnoreCase("female"))
			click(genderFemale);
	}

	// Fill registration form fields with user data
	public RegisterPage fillForm(String fName, String lName, String emailAddr, String comp, String pwd, String conPwd) {
		type(firstName, fName);
		type(lastName, lName);
		type(email, emailAddr);
		type(company, comp);
		type(password, pwd);
		type(confirmPassword, conPwd);
		return this;
	}

	// Submit registration form
	public RegisterPage submit() {
		click(registerButton);
		return this;
	}

	// Check if registration success message is displayed
	public boolean isRegistrationSuccessful() {
		return isElementDisplayed(successMsg, 5);
	}

	// Check if email validation error is displayed
	public boolean isEmailErrorDisplayed() {
		return isElementDisplayed(emailError, 5);
	}

	// Get success confirmation message text
	public String getConfirmationMessage() {
		return getText(successMsg);
	}
}