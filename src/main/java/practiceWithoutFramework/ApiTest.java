package practiceWithoutFramework;

import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class ApiTest

{

    @Test
    public void e2eApiTest(){
        HashMap<Object , Object> map = new HashMap<>();
        map.put("email" , "mohitkwatra@gmail.com");
        map.put("password" , "Hyundai@10");



        Playwright playwright = Playwright.create();
        APIRequestContext apirequest =  playwright.request().newContext();
        APIResponse loginResponse = apirequest.post("https://api.eventhub.rahulshettyacademy.com/api/auth/login",
               RequestOptions.create().setData(map));
        Assert.assertTrue(loginResponse.ok());
        System.out.println(loginResponse.text());

        String token = JsonPath.read(loginResponse.text() , "$.token"  ); // II para is PATH Of JSON
        System.out.println(token);

        HashMap<Object , Object> creatEvent = new HashMap<>();
        creatEvent.put("title","Manoj Kumar birthday");
        creatEvent.put("description","Manoj Kumar birthday");
        creatEvent.put("category","Concert`");
        creatEvent.put("venue","Comany bagh");
        creatEvent.put("city","Muzaffarnagar");
        creatEvent.put("eventDate","2026-08-19T12:10:00.000Z");
        creatEvent.put("price","50");
        creatEvent.put("totalSeats","200");




        APIResponse eventResponse =  apirequest.post("https://api.eventhub.rahulshettyacademy.com/api/events",
                RequestOptions.create().setHeader("Authorization" , "Bearer "+ token)
                        .setData(creatEvent));

        //
        // 1. Pehle complete response print karo
        System.out.println("STATUS : " + eventResponse.status());
        System.out.println("RESPONSE : " + eventResponse.text());

        // 2. Check API successful hai
        Assert.assertTrue(eventResponse.ok(), eventResponse.text());


       // System.out.println("aa "+eventResponse.ok());
        //Assert.assertTrue(eventResponse.ok() , "Create event succeed");

        int eventId = JsonPath.read(eventResponse.text() , "$.data.id"); //To extarct reponse
        System.out.println(eventId);


        //Get EVENT

        APIResponse getResponse = apirequest.get("https://api.eventhub.rahulshettyacademy.com/api/events" ,
                RequestOptions.create().setQueryParam("limit","6").
                        setQueryParam(" page","1").
                        setHeader("Authorization" , "Bearer "+ token));

        Assert.assertTrue(getResponse.ok(), eventResponse.text());

       List<Integer> allEventIds = JsonPath.read(eventResponse.text() , "$.data[*].id");
        Assert.assertTrue(allEventIds.contains(eventId) , "Created event");




    }
}
