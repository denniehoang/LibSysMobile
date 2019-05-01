package com.example.libsysmobile.queries;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.libsysmobile.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class DbQuery extends AsyncTask<String, String, String> implements AsyncResponse {
    public JSONObject result;
    public ProgressDialog progressDialog;
    public AsyncResponse delegate = null;

    // Constructor to create progress dialog
    public DbQuery(Context context) {
        this.progressDialog = new ProgressDialog(context);
    }

    // Connecting to the database, running the query, and returning the result
    public void connect(String filePath, String requestMethod, Boolean doOutput, Boolean doInput, JSONObject cred) {

        try {
            URL url = new URL(filePath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setDoOutput(doOutput);
            httpURLConnection.setDoInput(doInput);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(cred.toString());
            wr.flush();
            StringBuilder sb = new StringBuilder();
            int HttpResult = httpURLConnection.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
            }
            result = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Processing the thread. Overridden by sub classes.
    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    // Description used for the progress dialog
    protected void runProgressDialog(String display) {
        progressDialog.setMessage(display);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    // Returns results to the main thread upon thread complete
    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        try {
            delegate.processFinish(this.result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Interface method, overridden in main thread to return result
    @Override
    public void processFinish(JSONObject result) {
    }
}
