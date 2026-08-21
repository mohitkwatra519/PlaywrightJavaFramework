package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase1 {

    Browser browser;
    Page page;
        Playwright playwright;
    String base_url;
    BrowserContext browserContext;
    @BeforeMethod
    public void setup () throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\mohit\\IdeaProjects\\PlaywrightFramework\\src\\test\\resources\\config.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser")
                : prop.getProperty("browser");
        //String browserName = prop.getProperty("browser");

        playwright = Playwright.create();

        if (browserName.equals("chrome")){
            //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            browser = playwright.chromium().launch();
        }
        else if(browserName.equals("firefox")){
            browser = playwright.firefox().launch();
        }


        System.out.println("Hello WORLD");
        //Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));

        page = browser.newPage();
        base_url = prop.getProperty("qa.baseurl");

        PlaywrightAssertions.setDefaultAssertionTimeout(7000); // Global assertions for 7 seconds
        page.setDefaultTimeout(8000);


    }
}
