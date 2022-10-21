package edu.escuelaing.arem.ASE.app;

import spark.Request;
import spark.Response;
import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

public class spark {
    public static void main(String[] args) {
        port(getPort());
        get("/calculata", (req, res) -> {res.type("application/json")});
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    public static String calcula(int n) {
        List<Integer> list = new ArrayList<Integer>();
        calculate(n, list);
        String res = "{\"operation\": \"collatzsequence\",}";
        return res;
    }

    private static List<Integer> calculate(int n, List<Integer> lista) {
        int tipo = n % 2;
        lista.add(n);
        if (n != 1) {

            if (tipo == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            calculate(n, lista);
        }
        return lista;
    }
}
