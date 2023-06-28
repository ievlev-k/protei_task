import org.example.Place;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiTest {

    static String searchUrl = "https://nominatim.openstreetmap.org/search";
    static String reverseUrl = "https://nominatim.openstreetmap.org/reverse";

    // В этом метом тесте проверяются (limit max)  и (limit default)
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 30, 50, 70, Integer.MAX_VALUE})
    public void valLimitTest(Integer len) {

        Map<String, String> map = new HashMap<>();
        map.put("amenity", "pub");
        map.put("limit", len.toString());
        map.put("format", "json");
        Place[] examples =
                given().queryParams(map)
                        .when().get(searchUrl)
                        .then().statusCode(200).extract().body().as(Place[].class);

        System.out.println(examples.length);

        if (len > 50) {
            assertEquals(examples.length, 50);
        } else {
            assertEquals(examples.length, len.intValue());
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {"src/resources/cdek.txt"})
    public void getLimitSdek(String pathToFile) {
        File file = new File(pathToFile);
        Boolean resFlag = true;
        String line = "";
        try {
            Scanner scanner = new Scanner(file);
            String nameCity;
            String nameStreet;
            String numberHouse;

            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String[] fileLine = line.trim().split(",");
                if (fileLine.length != 3) {
                    System.out.println("Неверная строка!");
                    break;
                }

                nameCity = fileLine[0].trim();
                nameStreet = fileLine[1].trim();
                numberHouse = fileLine[2].trim();

                Map<String, String> map = new HashMap<>();

                map.put("q", line);
                map.put("format", "json");
//                map.put("city", nameCity);
//                map.put("street", nameStreet);
//                map.put("house_number", numberHouse);
                String response =
                        given().queryParams(map).when().get(searchUrl)
                                .then().extract().body().asString();
//                System.out.println(response);

                Boolean findSDEK = response.contains("\"display_name\":\"СДЭК");

                if (!findSDEK) {
                    resFlag = false;
                    System.out.println("На улице: " + line + " нет СДЕКА!");
                }

            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.getStackTrace();
        }

        assertTrue(resFlag);

    }


    //здесь проверяются значения по умолчанию
    @Test
    public void defaultLimitTest() {
        Map<String, String> map = new HashMap<>();
        map.put("amenity", "pub");
        map.put("format", "json");

        Place[] examples =
                given().queryParams(map)
                        .when().get(searchUrl)
                        .then().statusCode(200).extract().body().as(Place[].class);

        System.out.println(examples.length);

        assertEquals(examples.length, 10);

    }

}
