package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    String baseurl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseurl);
    }

    @Test
    public void userShouldLoginWithValidCredentials() {
        // enter username
        driver.findElement(By.id("username")).sendKeys("tomsmith");

        // Find the password field and Type the password to password field
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("SuperSecretPassword!");
        //click on login button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        String expectedMessage = "Secure Area";
        String actualMessage = driver.findElement(By.xpath("//h2[contains(text(),'Secure Area')]")).getText();
        Assert.assertEquals("User was unable to login successfully.", expectedMessage, actualMessage);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        driver.findElement(By.id("username")).sendKeys("tosmith1");

        // Find the password field and Type the password to password field
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("SuperSecretPassword!");
        //click on login button
        driver.findElement(By.xpath("//button[@class = 'radius']")).click();

        String expectedMessage = "Your username is invalid!";
        String actualMessage = driver.findElement(By.xpath("//div[@class = 'flash error']")).getText().substring(0,25);
        Assert.assertEquals("Unable to verify user name error message.", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
