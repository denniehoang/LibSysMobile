package com.example.libsysmobile.queries;

import android.content.Context;

public class QueryBrowseBooks extends DbQuery {

    private String urlApi = mainURL + "/items/search";

    public QueryBrowseBooks(Context context) {
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
        String title = params[0];
        super.connectJQuery(urlApi, "GET", false, true);
        return null;
    }
}
