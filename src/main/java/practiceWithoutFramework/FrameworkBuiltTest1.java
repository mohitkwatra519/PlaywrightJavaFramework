//package practiceWithoutFramework;
//
//import com.microsoft.playwright.Locator;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.Test;
//
//import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
//
//public class FrameworkBuiltTest1 extends TestBase1 {
//
//
//    private static final Logger log = LoggerFactory.getLogger(FrameworkBuiltTest1.class);
//
//    @Test(description = "Create Event - book event and verify it is booked")
//    public void DemoTest()
//    {
//        String eventName = "Playwright Framework";
//        LoginPage login = new LoginPage(page ,base_url);
//        DashboardPage dashboard =  login.loginToApplication();
//        dashboard.waitForEventsToLoad();
//
//        AdminEventsPage admin = new AdminEventsPage(page);
//        admin.goTo();
//        admin.createEvent(eventName,
//                "Playwright workshop",
//                "Concert" ,
//                "Mysore" ,"Palasa", "2026-08-25T17:54","100",
//        "251");
//
//        // Auto wait for 5 seconds
//
//
//
//
//        //Step 2 -- Check NEW Event is created
//        EventsPage eventPage = new EventsPage(page);
//        eventPage.goTo();
//        eventPage.findEventCard(eventName);
//        int seatNumberBeforeBooking = eventPage.getSeatsCount(eventName);
//        BookingFormPage bookingFormPage = eventPage.proceedToBookingEvent(eventName);
//
//        bookingFormPage.fillAndConfirm("AAKANKSHA THAKUR" ,
//                "AAKANKSHATHAKUR0304@gmail.com" , "8383885694");
//
//
//
//
//
//        String bookingRef= page.locator(".booking-ref").innerText();
//        System.out.println("booking reference id is " + bookingRef);
//        page.getByText("View My Bookings").click();
//        page.waitForTimeout(5000);
//        //String bookingRef1= page.locator(".booking-ref").innerText();
//
//        //Verify in Booking history
//        Locator bookID = page.locator(".booking-ref");
//        Locator targetBook =  bookID.filter(new Locator.FilterOptions().setHasText(bookingRef));
//
//        assertThat(targetBook).isVisible();
//
//        page.locator("#nav-events").click();
//        page.waitForTimeout(1000);
//
//        Locator eventCardsAfterBooking = page.getByTestId("event-card"); //{loc 1 , loc 2 , loc 3 , loc4}
//
//
//        //System.out.println(eventCards.count());
//
//        Locator targetCardAfterBooking =  eventCardsAfterBooking.filter(new Locator.FilterOptions().setHasText("Playwright Framework Test"));
//
//        String seatsAfterBooking =  targetCardAfterBooking.getByText("seats").innerText();
//        System.out.println(seatsAfterBooking);
//        String seatsList[] = seatsAfterBooking.split(" ");
//        String remainingSeats = seatsList[0];
//        int seatsAfterBookings = Integer.parseInt(remainingSeats);
//
//        Assert.assertTrue(seatNumberBeforeBooking > seatsAfterBookings);
//
//
//    }
//
//    @AfterMethod
//    public void tearDown(){
////        browserContext.tracing().stop(new Tracing.StopOptions()
////                .setPath(Paths.get("trace.zip")));
//    }
//}
