package web.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginServiceTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //sleep(5);
        System.out.println(System.getProperty("user.dir") + "/../pages/login.html");
        driver.get("file:///Users/sushi/Downloads/8.1P-resources/pages/login.html");
        sleep(2);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginSuccess() {
        // Test Login with correct credentials
        performLogin("shreya", "shreya_pass", "05/01/2024");
        verifySuccess();
    }

    @Test
    public void testInvalidUsername() {
        // Test Login with invalid username
        performLogin("invalid_user", "password", "05/01/2024");
        verifyFailure();
    }

    @Test
    public void testInvalidPassword() {
        // Test Login with invalid password
        performLogin("shreya", "invalid_pass", "05/01/2024");
        verifyFailure();
    }

    @Test
    public void testInvalidDOBFormat() {
        // Test Login with invalid date of birth format
        performLogin("shreya", "shreya_pass", "2024-01-05");
        verifyFailure();
    }

    @Test
    public void testEmptyUsername() {
        // Test Login with empty username field
        performLogin("", "shreya_pass", "05/01/2024");
        verifyFailure();
    }

    @Test
    public void testEmptyPassword() {
        // Test Login with empty password field
        performLogin("shreya", "", "05/01/2024");
        verifyFailure();
    }

    @Test
    public void testEmptyDOB() {
        // Test Login with empty date of birth field
        performLogin("shreya", "shreya_pass", "");
        verifyFailure();
    }

    @Test
    public void testLoginWithCorrectCredentials() {
        // Test Login with correct credentials
        performLogin("shreya", "shreya_pass", "05/01/2024");
        verifySuccess();
    }
    
    

    private void performLogin(String username, String password, String dob) {
        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys(username);

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys(password);

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dob);

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();

        sleep(2);
    }

    private void verifySuccess() {
        String title = driver.getTitle();
        Assert.assertEquals("success", title);

        WebElement ele = driver.findElement(By.name("status"));
        Assert.assertEquals("success", ele.getText());
    }

    private void verifyFailure() {
        String title = driver.getTitle();
        Assert.assertEquals("fail", title);

        WebElement ele = driver.findElement(By.name("status"));
        Assert.assertEquals("fail", ele.getText());
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
