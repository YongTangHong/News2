package com.example.anew.news.news.network;

import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/3/21.
 */

public class NewsRequest<T> extends JsonRequest<T> {
    private static final String TAG = "NewsRequest";
    private Gson mGson = new Gson();
    private Class<T> mClass;


    /**
     *
     * @param method  请求方法 GET POSt
     * @param url     网络请求地址
     * @param requestBody   请求体
     * @param listener   接口回调
     * @param errorListener   网络错误的回调
     */
    public NewsRequest(Class clazz, String url,NetsWorkListeren<T> listener) {
        super(Method.GET,url,null,listener,listener);
        mClass = clazz;
    }

    /**
     * 网络请求成功后才会调用,解析网络结果
     * @param response
     * @return
     */
    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {

        try {
            //将网络响应的字节数组转换成字符串
            String result = new String(response.data,PROTOCOL_CHARSET);
            Log.d(TAG, "parseNetworkResponse: " + result);
            //将字符串转成JavaBean
/*
            CategoriesBean categoriesBean = mGson.fromJson(result, CategoriesBean.class);
            Log.d(TAG, "parseNetworkResponse: "+ categoriesBean.getData().get(0).getTitle());*/

            T bean = mGson.fromJson(result,mClass); //解析成javabean
            Cache.Entry entry = HttpHeaderParser.parseCacheHeaders(response);
            return Response.success(bean, entry);

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }


        return null;
    }
}
