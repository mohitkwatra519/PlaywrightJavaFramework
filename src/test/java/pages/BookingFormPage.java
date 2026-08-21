package pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BookingFormPage {
    Page page;
    private static final String FULL_NAME_LABEL = "Full Name";
    private static final String CUSTOMER_EMAIL_ID = "you@email.com";
    private static final String PHONE_NO = "Phone Number";
    private static final String confirmBook = "#confirm-booking";

    public BookingFormPage(Page page){
        this.page = page;
    }
    public void fillAndConfirm(String fullName  , String email, String phoneno){
        page.getByLabel(FULL_NAME_LABEL).fill(fullName);
        page.getByPlaceholder(CUSTOMER_EMAIL_ID).fill(email);
        page.getByLabel(PHONE_NO).fill(phoneno);
        page.locator(confirmBook).click();

        String a = page.getByText("Your tickets are reserved.").innerText();
        System.out.println(a);
        assertThat(page.getByText("Your tickets are reserved."))
                .isVisible();
    }
}
