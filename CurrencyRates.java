import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyRates {
    private static final String API_URL = "https://currate.ru/api/?get=rates&pairs=USDRUB,EURRUB,GBPJPY&key=7b2b6c2f49aa1871292fe75ae8a91b66";

    public static void main(String[] args) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // разбор ответа от API и создание коллекции с курсами валют
            Map<String, Double> currencyRates = parseCurrencyRatesFromApiResponse(response.toString());

            // вывод курсов валют
            for (Map.Entry<String, Double> entry : currencyRates.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Double> parseCurrencyRatesFromApiResponse(String response) {
        Map<String, Double> currencyRates = new HashMap<>();

        // разбор JSON-ответа и извлечение курсов валют
        // здесь нужно использовать библиотеку для работы с JSON, например, Gson или Jackson
        // ниже приведен пример с использованием библиотеки Gson
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        JsonObject rates = jsonObject.getAsJsonObject("data").getAsJsonObject("rates");

        for (Map.Entry<String, JsonElement> entry : rates.entrySet()) {
            String currencyCode = entry.getKey();
            double currencyRate = entry.getValue().getAsDouble();
            currencyRates.put(currencyCode, currencyRate);
        }

        return currencyRates;
    }
}