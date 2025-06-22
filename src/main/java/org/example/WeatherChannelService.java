package org.example;


import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale; // Importar Locale

public class WeatherChannelService implements ClimaOnline {

    private static final String API_KEY = "d780836254316e53876368e1bfd1e43c"; // Tu API Key real
    private static final String CITY_RAW = "Trelew,Argentina"; // O "Buenos Aires,Argentina"
    private static final String UNITS = "metric";
    private static final String API_BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";

    @Override
    public String temperatura() {
        String encodedCity;
        try {
            encodedCity = URLEncoder.encode(CITY_RAW, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            System.err.println("Error al codificar la ciudad: " + e.getMessage());
            return "Error: City encoding failed";
        }

        String API_URL = API_BASE_URL + "q=" + encodedCity + "&units=" + UNITS + "&APPID=" + API_KEY;

        System.out.println("URL de la API: " + API_URL);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            System.out.println("Respuesta cruda de la API: " + responseBody);

            JSONObject jsonResponse = new JSONObject(responseBody);

            if (jsonResponse.has("main") && jsonResponse.getJSONObject("main").has("temp")) {
                double tempCelsius = jsonResponse.getJSONObject("main").getDouble("temp");
                // **CAMBIO AQUÍ: Usar Locale.US para asegurar el punto decimal**
                return String.format(Locale.US, "%.1f", tempCelsius) + " c";
            } else {
                System.err.println("Advertencia: No se pudo encontrar la temperatura en la respuesta JSON.");
                return "Error: temp not found";
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al conectar con OpenWeatherMap: " + e.getMessage());
            return "Error: " + e.getMessage();
        } catch (org.json.JSONException e) {
            System.err.println("Error al parsear JSON de OpenWeatherMap: " + e.getMessage());
            return "Error: JSON parsing failed";
        }
    }
}