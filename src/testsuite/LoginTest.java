package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {

        driver.findElement(By.name("username")).sendKeys("tomsmith");  // Enter the username in username field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");       // Enter the Password in password field
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();          // Click on login button
        String expectedSuccessfulLoginTextMessage = "Secure Area";                              // Verify the user is login successfully
        WebElement actualSuccessfulLoginTextElement = driver.findElement(By.xpath("//h2[text()= ' Secure Area' ]"));
        String actualSuccessfulLoginTextMessage = actualSuccessfulLoginTextElement.getText();
        Assert.assertEquals("Error Message", expectedSuccessfulLoginTextMessage, actualSuccessfulLoginTextMessage);       // Verify the excepted and actual message match

    }

    @Test
    public void verifyTheUsernameErrorMessage() {

        driver.findElement(By.name("username")).sendKeys("tomsmith1");  // Enter the username in username field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");       // Enter the Password in password field
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();          // Click on login button
        String expectedUserNameErrorMessage = "Your username is invalid!\n×";
        WebElement actualUsernameErrorTextElement = driver.findElement(By.xpath("//div[contains(text(),'Your username is invalid!')]"));
        String actualUsernameErrorTextMessage = actualUsernameErrorTextElement.getText();
        Assert.assertEquals("Error Message", expectedUserNameErrorMessage, actualUsernameErrorTextMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {

        driver.findElement(By.name("username")).sendKeys("tomsmith");  // Enter the username in username field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");       // Enter the Password in password field
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();          // Click on login button
        String expectedPasswordErrorMessage = "Your password is invalid!\n×";
        WebElement actualPasswordErrorTextElement = driver.findElement(By.xpath("//div[contains(text(),'Your password is invalid!')]"));
        String actualPasswordErrorTextMessage = actualPasswordErrorTextElement.getText();
        Assert.assertEquals("Error Message", expectedPasswordErrorMessage, actualPasswordErrorTextMessage);
    }

    @After
    public void tearDoem() {
        driver.close();
    }


}
