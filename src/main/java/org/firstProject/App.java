package org.firstProject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws Exception
    {
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
        Hashtable<String, Double> eurConvertionRate = new Hashtable<>();
        String[] dictItem;


        for(String s : values){
            dictItem = s.split(":");
            eurConvertionRate.put(dictItem[0], Double.parseDouble(dictItem[1]) );
        }


        // print hashtable items
        eurConvertionRate.forEach(
                (key, value) -> System.out.println(key + " -> " + value)
        );
    }
}
