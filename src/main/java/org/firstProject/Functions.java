package org.firstProject;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Functions {
    public static String getJson() throws Exception {
        URL url = new URL("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/eur.json");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpsURLConnection.HTTP_OK){
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            return sb.toString();

        }
        else{
            throw new Exception("Invalid response code: " + responseCode);
        }
    }
    public static String getCurrencyCode(Hashtable<String, Double> rates) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide a currency code");
        String providedString = scanner.nextLine().toLowerCase();

        while (!rates.containsKey(providedString)) {
            System.out.println("Please provide supported currency code");
            providedString = scanner.nextLine();
        }

        return providedString;
    }
    public static Double getCurrencyAmmount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide an ammount to convert");
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Please provide a number!");
                scanner.next();
            }
        }
    }
}
