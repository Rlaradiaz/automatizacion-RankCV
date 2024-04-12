package com.ejemplo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
       
        driver = DriverFactory.getDriver();

        driver.get("https://www.rankcv.com/");

        
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
       
        DriverFactory.quitDriver();
    }
}
