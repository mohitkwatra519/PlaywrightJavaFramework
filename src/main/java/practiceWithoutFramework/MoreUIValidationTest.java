package practiceWithoutFramework;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.microsoft.playwright.BrowserContext;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class MoreUIValidationTest {
    Browser browser;
    Page page;
    Playwright playwright;
    BrowserContext browserContext;

    @BeforeMethod
    public void setup ()
    {
        playwright = Playwright.create();
        System.out.println("Hello WORLD");

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();

        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = browserContext.newPage();
        page.navigate("https://rahulshettyacademy.com/loginpagePractise/");


    }

    @AfterMethod
    public void tearDown(){
        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
    }

    @Test
    public void childWindowHandle(){
       Locator blinkingText =  page.locator(".blinkingText");

        Page newPage = browserContext.waitForPage(()->  blinkingText.first().click());
        newPage.waitForLoadState();
        String childText =  newPage.locator(".im-para.red").textContent();
        System.out.println(childText);

        String email = childText.split("at")[1].trim().split(" ")[0].trim();
        newPage.waitForTimeout(2000);
        System.out.println("1");
        page.getByLabel("Username:").fill(email);
        System.out.println("2");
        page.waitForTimeout(2000);
        page.getByLabel("Password:").fill("22222");
        System.out.println("3");
    }
    @Test
    public void UIControls(){
       //RadioButton
        Locator userRdBtn =  page.getByRole(AriaRole.RADIO , new Page.GetByRoleOptions().setName("User"));
        userRdBtn.click();


        page.getByRole(AriaRole.BUTTON , new Page.GetByRoleOptions().setName("Okay")).click();
        Assert.assertTrue(userRdBtn.isChecked());

        //checkbox
        Locator checkBoxTerms = page.getByRole(AriaRole.CHECKBOX , new Page.GetByRoleOptions().setName("I Agree to the terms and conditions"));
        checkBoxTerms.check();
        Assert.assertTrue(checkBoxTerms.isChecked());

        //Dropdown
        page.getByRole(AriaRole.COMBOBOX).selectOption("Teacher") ; // it works when we have 1 DD
        page.waitForTimeout(3000);

        //Alert popup


    }
}
