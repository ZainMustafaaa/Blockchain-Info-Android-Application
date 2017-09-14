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
package market.bitcoin.com.bitcoinmarketplace.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.HashMap;
import market.bitcoin.com.bitcoinmarketplace.GetDetails;
import market.bitcoin.com.bitcoinmarketplace.R;

/**
 * Created by zainm on 14-Sep-17.
 */

public class CustomAdapter extends BaseAdapter {

    /** declaring variables for context and HashMap data structures*/
    private Context context;
    private HashMap<String, String> details;

    /**
     * constructor of custom adapter calls when initializing object
     * @param context
     * @param details
     * */
    public CustomAdapter(Context context, HashMap<String, String> details){
        this.context = context;
        this.details = details;
    }

    /**
     * getCount method to get details size
     * @return details.size()
     * */
    @Override
    public int getCount() {
        return details.size();
    }

    /**
     * getItem method to get object
     * by item id
     * @return Object
     * */
    @Override
    public Object getItem(int i) {
        return details.get(GetDetails.details[0][i]);
    }

    /**
     * method to get itemId
     * @return long
     * */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * getView method
     * @param i
     * @param view
     * @param viewGroup
     * @return View
     * */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /** condition for if view is null then initialize view from resource file */
        if(view == null) view = view.inflate(context, R.layout.details_list, null);
        /** initializing textView */
        TextView listTextView = (TextView) view.findViewById(R.id.listTextView);
        /** setting results to listView textView */
        listTextView.setText(GetDetails.details[1][i] + details.get(GetDetails.details[0][i]));
        return view;
    }
}
