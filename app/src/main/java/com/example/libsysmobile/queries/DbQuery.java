package com.example.libsysmobile.queries;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.libsysmobile.AsyncResponse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DbQuery extends AsyncTask<String, String, String> implements AsyncResponse {

    public BufferedReader bufferedReader;
    public BufferedWriter bufferedWriter;
    public OutputStream outputStream;
    public InputStream inputStream;
    public String post_data;
    public final String encodingType = "UTF-8";
    List<String> resultList = new ArrayList<>();
    public String result = "";
    public ProgressDialog progressDialog;
    public AsyncResponse delegate = null;

    // Constructor to create progress dialog
    public DbQuery(Context context) {
        this.progressDialog = new ProgressDialog(context);
    }

    // Returns results to the main thread upon thread complete
    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        delegate.processFinish(this.resultList);
    }

    // Connecting to the database, running the query, and returning the result
    public void connect(String filePath, String requestMethod, Boolean doOutput, Boolean doInput) {
        try {
            URL url = new URL(filePath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setDoOutput(doOutput);
            httpURLConnection.setDoInput(doInput);
            outputStream = httpURLConnection.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, encodingType));
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            outputStream.close();
            inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            delimitResult(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

    // Setting POST data for the php script
    protected void setPost_data(String post_data) {
        this.post_data = post_data;
    }

    // Interface method, overridden in main thread to return result
    @Override
    public void processFinish(List<String> resultList) {
    }


    private void delimitResult(String result){
        String results[] = result.split("<br>");
        for(String entity: results){
            resultList.add(entity);
        }
    }
}
