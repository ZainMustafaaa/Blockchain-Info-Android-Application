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

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import market.bitcoin.com.bitcoinmarketplace.adapter.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    /** declaring listView and button variable */
    ListView listView;
    Button refreshButton;

    /**
     * @override method call first when application start
     * @param savedInstanceState
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** setting strict moode policy */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /** initializing listView and refresh button */
        listView = (ListView) findViewById(R.id.mainListView);
        refreshButton = (Button) findViewById(R.id.refreshButton);

        /**
         * setOnItemClickListener this method calls when any item selected from listView item menu
         * @param OnItemClickListener
         * */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * onItemClick method calls when item click on listView
             * @param adapterView
             * @param view
             * @param i
             * @param l
             * */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /** initializing alertDialog to show details */
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle(GetDetails.details[1][i]);
                alertDialog.setMessage(GetDetails.blockchainDetails.get(GetDetails.details[0][i]));
                alertDialog.show();
            }
        });

        /**
         * initializing setOnCLickListener calls when click on listView
         * @param OnCLickListener
         * */
        refreshButton.setOnClickListener(new View.OnClickListener() {
            /**
             * @override method onClick calls when button click action performed
             * @param view
             * */
            @Override
            public void onClick(View view) {
                /**
                 * initializing progress dialog to show user waiting
                 * @param context
                 * */
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Please wait");
                progressDialog.setMessage("Fetching...");
                progressDialog.show();
                loadDetails();
                progressDialog.cancel();
            }
        });

        loadDetails();
    }

    /**
     * loadDetails method calls to get details from blockchain web service
     * @throws IllegalArgumentException
     * */
    void loadDetails(){
        try {
            GetDetails.loadDetailsFromWeb();
        }catch (IllegalArgumentException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
        /**
         * initializing customadapter
         * @param context
         * @param blockchainDetails
         * */
        CustomAdapter customAdapter = new CustomAdapter(this, GetDetails.blockchainDetails);
        /** setting up custom adapter object */
        listView.setAdapter(customAdapter);
    }
}