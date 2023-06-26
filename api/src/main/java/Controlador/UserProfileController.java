package Controlador;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Modelo.Perfil;
import Modelo.UserMedia;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userProfileController")
public class UserProfileController extends HttpServlet {
    private static final String ACCESS_TOKEN = "AQC2qxQnvykd0nWlu0glIwxmBTVaZ_sFDxFvP0M29pneKRscau48PAfnTmZcZDdit1XKKrSShd7cWgg0AIkn-0-ZG78lRgf9t7efgUUhdRcp9oliOylKAEB7J9PN8-AT0DPEkqpGCYTPEXSWEkY3ST_BjhkKX7NCEkvXblItDQhtkmw2xp0pp6LbjHc2_IegLlMCL2gqXvrHgOXtMft-Wg0GDlP1e6PGZNklWQPLY-N9UQ";
    private static final String USER_ID = "6125871670874898";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String PerfilEndpoint = "https://api.instagram.com/v1/users/" + USER_ID + "/?access_token=" + ACCESS_TOKEN;
        String PerfilJson = makeHttpGetRequest(PerfilEndpoint);
        Perfil perfil = parsePerfilJson(PerfilJson);

        String userMediaEndpoint = "https://api.instagram.com/v1/users/" + USER_ID + "/media/recent/?access_token="
                + ACCESS_TOKEN;
        String userMediaJson = makeHttpGetRequest(userMediaEndpoint);
        List<UserMedia> userMediaList = parseUserMediaJson(userMediaJson);
        request.setAttribute("userProfile", userProfile);
        request.setAttribute("userMediaList", userMediaList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userProfileView.jsp");
        dispatcher.forward(request, response);
    }

    private String makeHttpGetRequest(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }

    private Perfil parsePerfilJson(String PerfilJson) {
        JsonObject jsonObject = JsonParser.parseString(PerfilJson).getAsJsonObject();
        String userId = jsonObject.get("id").getAsString();
        String username = jsonObject.get("username").getAsString();
        // Obtener otros campos del perfil y crear un objeto UserProfile
        Perfil perfil = new Perfil(userId, username);
        return perfil;
    }

    private List<UserMedia> parseUserMediaJson(String userMediaJson) {
        JsonObject jsonObject = JsonParser.parseString(userMediaJson).getAsJsonObject();
        JsonArray mediaArray = jsonObject.getAsJsonArray("data");
        List<UserMedia> userMediaList = new ArrayList<>();
        for (JsonElement element : mediaArray) {
            JsonObject mediaObject = element.getAsJsonObject();
            String mediaId = mediaObject.get("id").getAsString();
            String imageUrl = mediaObject.get("image_url").getAsString();
            String caption = mediaObject.get("caption").getAsString();
            UserMedia userMedia = new UserMedia(mediaId, imageUrl, caption);
            userMediaList.add(userMedia);
        }
        return userMediaList;
    }
}

