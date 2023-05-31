import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try {
            Main.call_me();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void call_me() throws Exception {
        String url = "https://rawg.io/api/games/1?key=5f47aed0a396479694448d927c7d5688";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("name# "+myResponse.getString("name"));
        System.out.println("description_raw# "+myResponse.getString("description_raw").replaceAll("\\n", " "));
        System.out.println("released# "+myResponse.getString("released"));
        System.out.println("back_image# "+myResponse.getString("background_image"));
        JSONArray jsonArray = (JSONArray) myResponse.get("publishers");
        System.out.println("publishers# "+ jsonArray.toString());
        Object result = jsonArray.get(0);
        System.out.println(result);
//        System.out.println("developers# "+myResponse.getJSONArray("developers"));
//        System.out.println("genres# "+myResponse.getJSONArray("genres"));
    }

}