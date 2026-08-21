package practiceWithoutFramework;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UIValidationsContinuedTest {

    Browser browser;
    Page page;
    Playwright playwright;
    BrowserContext browserContext;
    @BeforeMethod
    public void setup ()
    {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://rahulshettyacademy.com/AutomationPractice/");
        PlaywrightAssertions.setDefaultAssertionTimeout(7000); // Global assertions for 7 seconds


    }

    @Test
    public void popupValidations(){

        assertThat(page.getByPlaceholder("Hide/Show Example")).isVisible();
        page.locator("#hide-textbox").click();
        assertThat(page.getByPlaceholder("Hide/Show Example")).isHidden();

        //Alert

//        page.onDialog(dialog -> dialog.accept());
//        page.locator("#alertbtn").click();

        FrameLocator framePage =   page.frameLocator("#courses-iframe");
        framePage.getByRole(AriaRole.LINK , new FrameLocator.GetByRoleOptions().setName("Learning paths")).click();
        String t =  framePage.locator(".inner-box h1").textContent();
        System.out.println(t);
        Assert.assertEquals(t ,"LEARNING PATHS" );
    }

    @Test
    public void ScreenshotTest(){
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("pageScreenshot.png"))); // Entire page locator

        Locator a = page.getByPlaceholder("Hide/Show Example");
        a.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("aa.png"))); // Locator Screenshot\

    }
}
