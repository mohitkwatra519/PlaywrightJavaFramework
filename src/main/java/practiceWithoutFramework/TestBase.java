package practiceWithoutFramework;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    Browser browser;
    Page page;
        Playwright playwright;
    BrowserContext browserContext;
    @BeforeMethod
    public void setup ()
    {
        playwright = Playwright.create();
        System.out.println("Hello WORLD");
        //Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
        PlaywrightAssertions.setDefaultAssertionTimeout(7000); // Global assertions for 7 seconds
        page.setDefaultTimeout(8000);


    }
}
