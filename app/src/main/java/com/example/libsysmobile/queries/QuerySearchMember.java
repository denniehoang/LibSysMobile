package com.example.libsysmobile.queries;

import android.content.Context;

public class QuerySearchMember extends DbQuery {
    String urlApi = mainURL + "/users/by/memberID/";


    public QuerySearchMember(Context context) {
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
        String id = params[0];
        int memberID = Integer.parseInt(id);
        urlApi = urlApi + memberID;
        super.connectJQuery(urlApi, "GET", false, true);
        return null;
    }

}
