package com.example.libsysmobile.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.libsysmobile.R;
import com.example.libsysmobile.queries.DbQuery;
import com.example.libsysmobile.queries.QuerySearchMember;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchMemberPage extends Page {

    private EditText et_memberID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.newPage(R.layout.search_for_member);
        alertDialogBuilder = new AlertDialog.Builder(this);
        et_memberID = findViewById(R.id.search_member_id);
    }

    public void searchMemberOnClick(View view) {
        String memberID = et_memberID.getText().toString();
        runQuery(memberID);
    }


    public void runQuery(String memberID) {
        DbQuery querySearchMember = new QuerySearchMember(this);
        querySearchMember.delegate = this;
        querySearchMember.execute(memberID);
    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        if (result != null) {
            alertDialogBuilder
                    .setTitle("Search Member: Success!")
                    .setMessage("Member ID " + et_memberID.getText().toString() + " exists.")
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Reserve the book
                        }
                    });

        } else {
            alertDialogBuilder
                    .setTitle("Search Member: Unsuccessful!")
                    .setMessage("Member ID " + et_memberID.getText().toString() + " does not exists.")
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Reserve the book
                        }
                    });

        }
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        et_memberID.getText().clear();
        String response = result.toString();
    }

}
