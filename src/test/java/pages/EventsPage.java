package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EventsPage {
    Page page;
    public EventsPage(Page page){
        this.page = page;
    }
    public void goTo(){
        page.locator("#nav-events").click();

    }

    public Locator waitForEventsToLoad(){
        Locator eventCards = page.getByTestId("event-card"); //{loc 1 , loc 2 , loc 3 , loc4}
        eventCards.first().waitFor();
        return eventCards;

    }

    public Locator findEventCard(String titleCard){
        Locator eventCards = waitForEventsToLoad();
        Locator targetCard =  eventCards.filter(new Locator.FilterOptions().setHasText(titleCard));
       assertThat(targetCard).isVisible();
        return  targetCard;
    }

    public int getSeatsCount(String titleCard  ){
       // assertThat(targetCard).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
       Locator targetCard = findEventCard(titleCard);
        String seats =  targetCard.getByText("seats").innerText();
        System.out.println(seats);
        return Integer.parseInt(seats.split(" ")[0]);
    }


public BookingFormPage proceedToBookingEvent(String titleCard){
    Locator targetCard = findEventCard(titleCard);
    targetCard.getByTestId("book-now-btn").click();
    return new BookingFormPage(page);
}

}
