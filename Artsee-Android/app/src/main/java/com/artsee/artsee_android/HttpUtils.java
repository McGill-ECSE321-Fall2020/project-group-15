package com.artsee.artsee_android;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;

/**
 * HTTP request class containing all the HTTP methods
 */
public class HttpUtils {
    public static final String DEFAULT_BASE_URL = "https://artsee-backend.herokuapp.com/";

    private static String baseUrl;
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        baseUrl = DEFAULT_BASE_URL;
    }

    /**
     * get the base URL
     * @return
     */
    public static String getBaseUrl() {
        return baseUrl;
    }

    /**
     * set the base URL
     * @param baseUrl
     */
    public static void setBaseUrl(String baseUrl) {
        HttpUtils.baseUrl = baseUrl;
    }

    /**
     * get request
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * post request
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * post request
     * @param context
     * @param url
     * @param entity
     * @param contentType
     * @param responseHandler
     */
    public static void post(Context context, String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler){
        client.post(context, getAbsoluteUrl(url), entity, contentType, responseHandler);
    }

    /**
     * get reqesut bu URL
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    /**
     * post request by URL
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void postByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    /**
     * get absolute URL
     * @param relativeUrl
     * @return
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        return baseUrl + relativeUrl;
    }

}
