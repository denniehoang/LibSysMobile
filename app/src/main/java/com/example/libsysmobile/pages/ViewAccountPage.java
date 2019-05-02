package com.example.libsysmobile.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.libsysmobile.R;
import com.example.libsysmobile.Rental;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QueryGetFine;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewAccountPage extends Page {

    private TextView feeOwed;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listOfRentals = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.account_page_member);
        alertDialogBuilder = new AlertDialog.Builder(this);
        feeOwed = findViewById(R.id.account_page_balance);
        listView = findViewById(R.id.listView_books_issued);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listOfRentals);
        listView.setAdapter(adapter);
        for (Rental rental : currentUser.currentRentalsList) {
            addItem(rental.title);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                alertDialogBuilder
                        .setTitle("Title: " + currentUser.currentRentalsList.get(position).title)
                        .setMessage("Loan ID: " + currentUser.currentRentalsList.get(position).loanID + "\nItem ID: " + currentUser.currentRentalsList.get(position).itemID + "\nDue Date: " + currentUser.currentRentalsList.get(position).dueDate + "\nInstance ID: " + currentUser.currentRentalsList.get(position).instanceItemID + "\n")
                        .setPositiveButton("Extend", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Extend the book
                            }
                        })
                ;
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        runQuery(currentUser.getMemberID());
    }

    public void addItem(String item) {
        adapter.add(item);
    }

    public void runQuery(String memberID) {
        DbQuery queryGetFine = new QueryGetFine(this);
        queryGetFine.delegate = this;
        queryGetFine.execute(memberID);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        String response = result.toString();
        double fine = result.getDouble("sum");
        feeOwed.setText(String.valueOf(fine));
    }
}



