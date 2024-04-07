package org.firstProject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.text.NumberFormatter;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;


public class App
{
    public static void main( String[] args ) throws Exception
    {
        // Gathering data from json file

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/main/java/org/firstProject/curr.json");

        // contains every exchange rate with euro as base currency
        JSONObject obj = (JSONObject) jsonParser.parse(reader);
        String eur = obj.get("eur").toString();

        // removing {}s
        eur = eur.substring(1);
        eur = eur.substring(0, eur.length()-1);
        String[] values =  eur.split(",");

        // add exchange rates to a hashtable
        Hashtable<String, Double> rates = new Hashtable<>();
        String[] dictItem;

        rates.put("eur", 1d);
        for(String s : values){
            dictItem = s.split(":");
            // removing " " from a string...
            dictItem[0] = dictItem[0].substring(1, dictItem[0].length()-1);
            rates.put(dictItem[0], Double.parseDouble(dictItem[1]) );
        }

        //
        String currFrom = getCurrencyCode(rates);
        String currTo = getCurrencyCode(rates);
        Double currAmmount = getCurrencyAmmount();

        Double convertedAmmount = rates.get(currTo)/rates.get(currFrom)*currAmmount;

        DecimalFormat formatter = new DecimalFormat();
        formatter.setMaximumFractionDigits(12);
        //
        System.out.println("");
        System.out.println(currAmmount + " " + currFrom.toUpperCase() + " = " + formatter.format(convertedAmmount) + " " + currTo.toUpperCase()  );


    }
    private static String getCurrencyCode(Hashtable<String, Double> rates) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide a currency code");
        String providedString = scanner.nextLine().toLowerCase();

        while (!rates.containsKey(providedString)) {
            System.out.println("Please provide a supported currency code");
            providedString = scanner.nextLine();
        }

        return providedString;
    }
    private static Double getCurrencyAmmount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide an ammount");
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
