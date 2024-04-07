package org.firstProject;

import org.json.JSONObject;
import java.util.Hashtable;
import static org.firstProject.Functions.getCurrencyAmmount;
import static org.firstProject.Functions.getCurrencyCode;


public class App
{
    public static void main( String[] args ) throws Exception
    {
        String json = Functions.getJson();

        JSONObject obj = new JSONObject(json);
        String exchangeRatesBasedOnEuro = obj.get("eur").toString();

        // removing {}s
        exchangeRatesBasedOnEuro = exchangeRatesBasedOnEuro.substring(1);
        exchangeRatesBasedOnEuro = exchangeRatesBasedOnEuro.substring(0, exchangeRatesBasedOnEuro.length()-1);
        String[] values =  exchangeRatesBasedOnEuro.split(",");

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

        Double calculatedExchangeRate = rates.get(currTo) / rates.get(currFrom);
        Double convertedAmmount = calculatedExchangeRate * currAmmount;


        // final output
        System.out.println("Exchange rate: " + calculatedExchangeRate );
        System.out.println(currAmmount + " " + currFrom.toUpperCase() + " = " + convertedAmmount + " " + currTo.toUpperCase()  );


    }
}
