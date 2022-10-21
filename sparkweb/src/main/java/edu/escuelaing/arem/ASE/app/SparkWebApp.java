package edu.escuelaing.arem.ASE.app;

import spark.Request;
import spark.Response;
import static spark.Spark.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class SparkWebApp {

    static Queue<String> queue = new LinkedList<String>();

    public static void main(String[] args) {
        queue.add(System.getProperty("front"));
        queue.add(System.getProperty("back"));
        port(getPort());
        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/calculata", (req, res) -> {
            res.type("application/json");
            return connect(req.queryParams("value"));
        });
    }

    private static String inputDataPage(Request req, Response res) {
        String pageContent = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>HTML Forms</h2>"
                + "<form action=\"/calculata\">"
                + "  <br>"
                + "  Numero:<br>"
                + "  <input type=\"text\" name=\"value\" value=\"\">"
                + "  <br><br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "<p>If you click the \"Submit\" button, the form-data will be sent to a page called \"/calculata\".</p>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001;
    }

    private static String connect(String n) throws IOException {
        URL obj = new URL("http://" + destino() + ":5000/calculata?value=" + n);
        System.out.println(obj);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        // The following invocation perform the connection implicitly before getting the
        // code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();
        return response.toString();
    }

    static String destino() {
        String tmp = queue.peek();
        queue.add(tmp);
        return tmp;
    }

}
