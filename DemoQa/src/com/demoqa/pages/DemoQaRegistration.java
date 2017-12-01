package com.demoqa.pages;

import org.openqa.selenium.support.PageFactory;

import com.demoqa.util.Driver;

public class DemoQaRegistration {
	public DemoQaRegistration() {
		PageFactory.initElements(Driver.getInstance(), this);

	}
}