package API;
//package API;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class InstagramAPI {

    private static final String ACCESS_TOKEN = "AQC2qxQnvykd0nWlu0glIwxmBTVaZ_sFDxFvP0M29pneKRscau48PAfnTmZcZDdit1XKKrSShd7cWgg0AIkn-0-ZG78lRgf9t7efgUUhdRcp9oliOylKAEB7J9PN8-AT0DPEkqpGCYTPEXSWEkY3ST_BjhkKX7NCEkvXblItDQhtkmw2xp0pp6LbjHc2_IegLlMCL2gqXvrHgOXtMft-Wg0GDlP1e6PGZNklWQPLY-N9UQ";
    private static final String USER_ID = "6125871670874898";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        // Obtener el perfil del usuario
        String userProfileEndpoint = "https://www.instagram.com/darkslayer2099/" + USER_ID + "/?access_token=" + ACCESS_TOKEN;
        WebTarget userProfileTarget = client.target(userProfileEndpoint);
        Response userProfileResponse = userProfileTarget.request(MediaType.APPLICATION_JSON).get();
        String userProfileJson = userProfileResponse.readEntity(String.class);

        System.out.println("Perfil del usuario:");
        System.out.println(userProfileJson);

        // Obtener el contenido multimedia del usuario
        String userMediaEndpoint = "https://www.instagram.com/darkslayer2099/" + USER_ID + "/media/recent/?access_token=" + ACCESS_TOKEN;
        WebTarget userMediaTarget = client.target(userMediaEndpoint);
        Response userMediaResponse = userMediaTarget.request(MediaType.APPLICATION_JSON).get();
        String userMediaJson = userMediaResponse.readEntity(String.class);

        System.out.println("Contenido multimedia del usuario:");
        System.out.println(userMediaJson);

        client.close();
    }
}
