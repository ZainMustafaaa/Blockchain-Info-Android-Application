/*
 * %W% %E% Zain-Ul-Abedin
 *
 * Copyright (c) 2017-2018. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of ZainMustafaaa.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * for learning purposes.
 *
 */
package market.bitcoin.com.bitcoinmarketplace;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by zainm on 14-Sep-17.
 */

public class GetDetails {

    /** 2D array for detail names for JSON and user */
    public static String[][] details = {{"market_price_usd", "hash_rate", "total_fees_btc", "n_btc_mined", "n_tx", "n_blocks_mined", "minutes_between_blocks", "totalbc",
            "n_blocks_total", "estimated_transaction_volume_usd", "blocks_size", "miners_revenue_usd", "nextretarget", "difficulty", "estimated_btc_sent",
            "miners_revenue_btc", "total_btc_sent", "trade_volume_btc", "trade_volume_usd"},

            {"USD Price: ", "Hash Rate: ", "Total Fee BTC: ", "No. of BTC Mined: ", "No. of Transactions: ", "No. of Blocks Mined: ", "Minutes b/w Blocks: ", "Total BC: ",
            "No. of total blocks: ", "Estimated Trans. Vol. USD: ", "Blocks Size: ", "Miners Revenue USD: ", "Next Tre-Target: ", "Difficulty: ", "Estimated BTC Sent: ",
            "Miners Revenue BTC: ", "Total BTC Sent: ", "Trade Vol. BTC: ", "Trade Vol. USD: "}
    };

    /** hashMap data structure for storing json results */
    static HashMap<String, String> blockchainDetails = new HashMap();

    /**
     * loadDetailsFromWeb method calls when activity start from onCreate override method
     * this method is used to load data from web services of blockchain
     * @throws IOException
     * @throws  JSONException
     * */
    public static void loadDetailsFromWeb(){

        try {
            /** initializing url object by passing url as parameter in constructor */
            URL url = new URL("https://blockchain.info/stats?format=json");
            /** opening url connection */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            /** getting input stream from connection instance or object */
            InputStream inputStream = connection.getInputStream();
            /** initializing buffer reader to read input streamed data */
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            /** initializing string buffer to store large string data */
            StringBuffer buffer = new StringBuffer();
            String n = "";
            /** while loop for reading line from received data from web */
            while ((n = bufferedReader.readLine()) != null) buffer.append(n);

            /** initializing json object */
            JSONObject jsonObject = new JSONObject(buffer.toString());
            /** for loop for storing data in HashMap data structure */
            for (int index = 0; index < details[0].length; index++) blockchainDetails.put(details[0][index], jsonObject.getString(details[0][index]));
        }catch (IOException | JSONException e){
            throw new IllegalArgumentException("Please check your internet connection");
        }
    }
}