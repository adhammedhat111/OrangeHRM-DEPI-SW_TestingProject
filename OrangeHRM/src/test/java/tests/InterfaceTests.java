package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InterfacePage;

import java.time.Duration;

public class InterfaceTests extends BaseTest
{
    WebDriver driver;
    InterfacePage interfacePage;
    BaseTest baseTest;

    // Locators
    By userDropDownButton = By.xpath("//img[@class='oxd-userdropdown-img']");
    By AboutButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[1]");


    // Setup method to initialize WebDriver and open the login page
    @BeforeMethod
    public void setUp()
    {
        // Initialize ChromeDriver (Selenium 4 manages drivers automatically)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // Initialize the InterfacePage object
        interfacePage = new InterfacePage(driver);
        baseTest = new BaseTest(driver);

    }

    // Test case: LVerity Functionality of Profile Icon
    @Test(priority = 1, description = "Verity Functionality of Profile Icon")
    public void verifyFunctionalityOfProfileIcon()
    {
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the dropdown menu to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton));


        // Assert both items are found
        Assert.assertTrue(interfacePage.isDropdownListFull(), "'About' item is not found in the dropdown");
    }

    // Test case: Verify Functionality of Upgrade Button
    @Test(priority = 2, description = "Verify Functionality of Upgrade Button")
    public void verifyUpgradeButtonFunctionality()
    {
        String originalWindow;
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUpgradeButton();


        String upgradeUrl = interfacePage.getCurrentPageUrl();

        Assert.assertTrue(upgradeUrl.contains("orangehrm.com"), "The page URL is incorrect!");
    }

    // Test case: Verify Functionality of Side Bar Button
    @Test(priority = 3, description = "Verify Functionality of Side Bar Button")
    public void verifySideBarButtonFunctionality()
    {
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickSideBarButton();
        interfacePage.clickSideBarButton();
        interfacePage.clickSideBarButton();

        Assert.assertTrue(interfacePage.getCurrentPageUrl().contains("dashboard"), "The side Bar isn't work correctly");
    }

    // Test case: Verify Functionality of Search text
    @Test(priority = 4, description = "Verify Functionality of Search text")
    public void verifySearchFunctionality()
    {
        baseTest.login(AdminAccount, AdminPassword);
        boolean searchResult = false;

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        searchResult = interfacePage.searchAndVerify("Admin");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Admin");

        searchResult = interfacePage.searchAndVerify("PIM");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - PIM");

        searchResult = interfacePage.searchAndVerify("Leave");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Leave");

        searchResult = interfacePage.searchAndVerify("Time");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Time");

        searchResult = interfacePage.searchAndVerify("Recruitment");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Recruitment");

        searchResult = interfacePage.searchAndVerify("Performance");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Performance");

        searchResult = interfacePage.searchAndVerify("Directory");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Directory");

        searchResult = interfacePage.searchAndVerify("Maintenance");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Maintenance");

        searchResult = interfacePage.searchAndVerify("Buzz");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Buzz");

        searchResult = interfacePage.searchAndVerify("My Info");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - My Info");

        searchResult = interfacePage.searchAndVerify("Claim");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Claim");
    }

    // Test case: Verify Functionality of About Button - Admin Role
    @Test(priority = 5, description = "Verify Functionality of About Button - Admin Role")
    public void verifyAboutButtonAdminFunctionality()
    {
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickAboutButton();

        Assert.assertTrue(interfacePage.isAboutHeaderDisplayed(), "The About button is not working correctly");

        interfacePage.clickCloseAboutButton();
        Assert.assertFalse(interfacePage.isAboutHeaderDisplayed(), "The Close About button is not working correctly");

    }

    // Test case: Verify Functionality of About Button - ESS Role
    @Test(priority = 6, description = "Verify Functionality of About Button - ESS Role")
    public void verifyAboutButtonEssFunctionality()
    {
        baseTest.login(EssUsernameEnabled, EssPasswordEnabled);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickAboutButton();

        Assert.assertTrue(interfacePage.isAboutHeaderDisplayed(), "The About button is not working correctly");
    }

    // Test case: Verify of Support Button - Admin Role
    @Test(priority = 7, description = "Verify of Support Button - Admin Role")
    public void verifySupportButtonAdminFunctionality()
    {
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickSupportButton();

        String supportUrl = interfacePage.getCurrentPageUrl();

        Assert.assertTrue(supportUrl.contains("support"), "The support button is not working correctly");

    }

    // Test case: Verify of Support Button - ESS Role
    @Test(priority = 8, description = "Verify of Support Button - ESS Role")
    public void verifyAboutSupportEssFunctionality()
    {
        baseTest.login(EssUsernameEnabled, EssPasswordEnabled);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickSupportButton();

        String supportUrl = interfacePage.getCurrentPageUrl();

        Assert.assertTrue(supportUrl.contains("support"), "The support button is not working correctly");
    }






    // After each test, quit the browser
    @AfterMethod
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }
    }
}
