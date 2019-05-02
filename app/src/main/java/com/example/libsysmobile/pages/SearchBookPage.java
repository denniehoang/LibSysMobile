package com.example.libsysmobile.pages;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.libsysmobile.Item;
import com.example.libsysmobile.R;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QuerySearchBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchBookPage extends Page {
    private ArrayList<Item> listOfItems = new ArrayList<>();
    private ArrayList<String> listOfResults = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView resultsView;
    private EditText et_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.search_for_book);
        resultsView = findViewById(R.id.listView_bookResults);
        et_title = findViewById(R.id.et_search_book_title);
        alertDialogBuilder = new AlertDialog.Builder(this);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listOfResults);
        resultsView.setAdapter(adapter);
    }

    public void searchBookOnClick(View view) {
        String title = et_title.getText().toString();
        runQuery(title);
    }

    //Insert result into List View
    public void addItems(String line) {
        adapter.add(line);
    }

    public void runQuery(String title) {
        DbQuery querySearchBook = new QuerySearchBook(this);
        querySearchBook.delegate = this;
        querySearchBook.execute(title);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        JSONArray itemsArray = result.getJSONArray("items");
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject item = itemsArray.getJSONObject(i);
            int itemID = item.getInt("item_ID");
            String title = item.getString("title");
            int isbn = item.getInt("ISBN");
            String upc = item.getString("UPC");
            Item book = new Item(itemID, title, isbn, upc);
            listOfItems.add(book);
            addItems(title);
        }
    }
}



