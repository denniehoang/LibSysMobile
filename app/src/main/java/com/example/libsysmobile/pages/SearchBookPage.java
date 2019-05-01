package com.example.libsysmobile.pages;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.libsysmobile.R;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QuerySearchBook;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchBookPage extends Page {
    private ArrayList<String> listOfResults = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView resultsView;
    private int clickCounter = 0;
    private EditText et_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.search_book);
        resultsView = findViewById(R.id.listView_searchBookResults);
        et_title = findViewById(R.id.et_search_title);
        alertDialogBuilder = new AlertDialog.Builder(this);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listOfResults);
        resultsView.setAdapter(adapter);
    }

    public void searchBookOnClick(View view) {
        String title = et_title.getText().toString();
        runQuery(title);
        // addItems("potato");
    }

    //Insert result into List View
    public void addItems(String line) {
        adapter.add(line + clickCounter++);
    }

    public void runQuery(String title) {
        DbQuery querySearchBook = new QuerySearchBook(this);
        querySearchBook.delegate = this;
        querySearchBook.execute(title);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
        //  String title = result.get("title").toString();
        // int itemID = result.getInt("item_ID");
        // Item item = new Item(title,itemID);
        //    addItems(item.getTitle());
    }

    private class Item {
        private String title;
        private int itemID;

        public Item(String title, int itemID) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public int getItemID() {
            return itemID;
        }
    }
}



