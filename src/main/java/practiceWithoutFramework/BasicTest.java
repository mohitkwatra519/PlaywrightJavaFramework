package practiceWithoutFramework;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BasicTest {
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

//    browserContext = browser.newContext();
//    page = browserContext.newPage();
//
//    browserContext.tracing().start(new Tracing.StartOptions()
//            .setScreenshots(true)
//            .setSnapshots(true)
//            .setSources(true));
//    page = browserContext.newPage();
}


    @Test(description = "Create Event - book event and verify it is booked")
    public void DemoTest()
    {

        System.out.println(page.title());
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        //page.getByLabel("Email").fill("mohitkwatra@gmail.com");
        page.getByPlaceholder("you@email.com").fill("mohitkwatra@gmail.com");
        page.getByLabel("Password").fill("Hyundai@10");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        assertThat(
                page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →")))
                .isVisible();
        page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
        page.locator("#event-title-input").fill("Cognizant");
        page.locator("#admin-event-form textarea").fill("Event for Celebration of Indian Railways");
        page.getByLabel("Category").selectOption("Concert");
        page.getByLabel("City").fill("Dehradoon");
        page.getByLabel("Venue").fill("Palasa");
        page.getByLabel("Event Date & Time").fill("2026-08-25T17:54");
        page.getByLabel("Price ($)").fill("500");
        page.getByLabel("Total Seats").fill("750");
        page.getByRole(AriaRole.BUTTON , new Page.GetByRoleOptions().setName("+ Add Event")).click(new Locator.ClickOptions().setTimeout(10000));

        // wait for 2 - 3 seconds
        assertThat(page.getByText("Event Created")).isVisible(); // Auto wait for 5 seconds




        //Step 2 -- Check NEW Event is created
        page.locator("#nav-events").click();
        Locator eventCards = page.getByTestId("event-card"); //{loc 1 , loc 2 , loc 3 , loc4}
        eventCards.first().waitFor();

        System.out.println(eventCards.count());

        Locator targetCard =  eventCards.filter(new Locator.FilterOptions().setHasText("Cognizant"));
        assertThat(targetCard).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
        String seats =  targetCard.getByText("seats").innerText();
        System.out.println(seats);

        int seatsBeforeBooking = Integer.parseInt(seats.split(" ")[0]);
        targetCard.getByTestId("book-now-btn").click();

        //Step3
        page.getByLabel("Full Name").fill("AAKANKSHA THAKUR");
        page.getByPlaceholder("you@email.com").fill("AAKANKSHATHAKUR0304@gmail.com");
        page.getByLabel("Phone Number").fill("8383885694");
        page.locator("#confirm-booking").click();
        String a = page.getByText("Your tickets are reserved.").innerText();
        System.out.println(a);
        assertThat(page.getByText("Your tickets are reserved."))
                .isVisible();
        String bookingRef= page.locator(".booking-ref").innerText();
        System.out.println("booking reference id is " + bookingRef);
        page.getByText("View My Bookings").click();
        page.waitForTimeout(5000);
        //String bookingRef1= page.locator(".booking-ref").innerText();

        //Verify in Booking history
         Locator bookID = page.locator(".booking-ref");
        Locator targetBook =  bookID.filter(new Locator.FilterOptions().setHasText(bookingRef));

        assertThat(targetBook).isVisible();

        page.locator("#nav-events").click();
        page.waitForTimeout(1000);

        Locator eventCardsAfterBooking = page.getByTestId("event-card"); //{loc 1 , loc 2 , loc 3 , loc4}


        System.out.println(eventCards.count());

        Locator targetCardAfterBooking =  eventCardsAfterBooking.filter(new Locator.FilterOptions().setHasText("Indian Railways 75th Event"));

        String seatsAfterBooking =  targetCardAfterBooking.getByText("seats").innerText();
        System.out.println(seatsAfterBooking);
        String seatsList[] = seatsAfterBooking.split(" ");
        String remainingSeats = seatsList[0];
        int seatsAfterBookings = Integer.parseInt(remainingSeats);

        Assert.assertTrue(seatsBeforeBooking > seatsAfterBookings);


    }

    @AfterMethod
    public void tearDown(){
//        browserContext.tracing().stop(new Tracing.StopOptions()
//                .setPath(Paths.get("trace.zip")));
    }
}
