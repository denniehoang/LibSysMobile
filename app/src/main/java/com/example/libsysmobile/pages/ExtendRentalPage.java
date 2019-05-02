package com.example.libsysmobile.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.libsysmobile.Item;
import com.example.libsysmobile.R;
import com.example.libsysmobile.Rental;
import com.example.libsysmobile.user_home_pages.MemberHomePage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExtendRentalPage extends Page {

    private ArrayList<Item> listOfItems = new ArrayList<>();
    private ArrayList<String> listOfResults = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView resultsView;
    private EditText et_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.extend_rental);
        resultsView = findViewById(R.id.listView_rentalResults);
        et_title = findViewById(R.id.et_search_book_title);
        et_title.setVisibility(View.INVISIBLE);
        alertDialogBuilder = new AlertDialog.Builder(this);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listOfResults);
        resultsView.setAdapter(adapter);
        populateRentals();
        if (isRentalsEmpty()) {
            alertDialogBuilder
                    .setTitle("Extension Error:")
                    .setMessage("No current rentals found.")
                    .setPositiveButton("Return", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            changePage(ExtendRentalPage.this, MemberHomePage.class);
                        }
                    });
            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void populateRentals() {
        if (currentUser.currentRentalsList.size() > 0) {
            for (Rental rental : currentUser.currentRentalsList) {
                addItems(rental.title);
            }
        }

    }

    private boolean isRentalsEmpty() {
        return adapter.getCount() == 0;
    }

    public void extendOnClick(View view) {
        // extension query
        // runQuery();
    }

    //Insert result into List View
    public void addItems(String line) {
        adapter.add(line);
    }

    public void runQuery(String title) {

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

