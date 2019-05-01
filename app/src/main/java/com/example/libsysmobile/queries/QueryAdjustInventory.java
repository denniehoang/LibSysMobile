package com.example.libsysmobile.queries;

import android.content.Context;

public class QueryAdjustInventory extends DbQuery {

    private String urlApi;
    private String type;

    public QueryAdjustInventory(Context context, String type) {
        super(context);
        switch (type) {
            case "add":
                urlApi = "http://ec2-18-218-197-217.us-east-2.compute.amazonaws.com:8084/api/v1/items/add";
                break;
            case "remove":
                // TODO: Remove API
                urlApi = "";
                break;
        }
    }


}
