package com.example.anew.news.news.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2017/3/22.
 */

public class NetsWorkListeren<T> implements Response.Listener<T>,Response.ErrorListener {

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(T response) {

    }
}
