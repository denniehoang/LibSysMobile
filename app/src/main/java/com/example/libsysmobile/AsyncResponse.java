package com.example.libsysmobile;

import org.json.JSONException;
import org.json.JSONObject;

public interface AsyncResponse {
    void processFinish(JSONObject result) throws JSONException;
}
