package com.example.libsysmobile.queries;

import android.content.Context;

public class QueryItemID extends DbQuery {

    String urlApi = mainURL + "/itemInstances/by/ID/";

    public QueryItemID(Context context) {
        super(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.runProgressDialog("Searching...");
    }

    // Runs script to database
    @Override
    protected String doInBackground(String... params) {
        String itemInstanceID = params[0];
        urlApi = urlApi + itemInstanceID;
        super.connectJQuery(urlApi, "GET", false, true);
        return null;
    }
}
