package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminEventsPage {
    Page page ;
    private static final String eventTitle= "#event-title-input";
    private static final String desc = "#admin-event-form textarea";
    private static final String categpry = "Category" ;
    private static final String city = "City" ;
    private static final String venue = "Venue";
    private static final String time = "Event Date & Time";
    private static final String price = "Price ($)";
    private static final String seat_Count = "Total Seats";
    private static final String eventCreated = "Event Created";


    public  AdminEventsPage(Page page){
        this.page = page;
    }
    public void goTo(){
        page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
    }
//
    public void createEvent(String title , String description , String category , String cityName ,
                            String VenueName ,String dateTime ,String kharcha ,String totalSeats){
        page.locator(eventTitle).fill(title);
        page.waitForTimeout(3000);
        page.locator(desc).fill(description);
        page.getByLabel(categpry).selectOption(category);
        page.getByLabel(city).fill(cityName);
        page.getByLabel(venue).fill(VenueName);
        page.getByLabel(time).fill(dateTime);
        page.getByLabel(price).fill(kharcha);
        page.getByLabel(seat_Count).fill(totalSeats);
        page.getByRole(AriaRole.BUTTON , new Page.GetByRoleOptions().setName("+ Add Event")).click(new Locator.ClickOptions().setTimeout(10000));

        // wait for 2 - 3 seconds
        assertThat(page.getByText(eventCreated)).isVisible();



    }
}
