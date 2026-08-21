package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class DataProviderUtil
{

    public static Object[][] getJsonDataToMap(String filePath) throws IOException {
        // 1. Read JSON file as String
        System.out.println(System.getProperty("user.dir")  + filePath);
        String jsonContent = new String(Files.readAllBytes(
                Paths.get(System.getProperty("user.dir") + filePath)
        ));


        // 2. Convert JSON to List<HashMap<String, String>>
        Type type = new TypeToken<List<HashMap<String, String>>>() {}.getType();
        List<HashMap<String, String>> list = new Gson().fromJson(jsonContent, type);

        // 3. Convert List to Object[][] for TestNG
        Object[][] table = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            table[i][0] = list.get(i); // Each test gets 1 HashMap
        }
        return table;
    }
}
