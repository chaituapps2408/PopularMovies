package com.example.chaiy.popularmovies.network;

import com.example.chaiy.popularmovies.BuildConfig;
import com.example.chaiy.popularmovies.helper.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class RequestParams {

    private String queryParamPage;

    private final Map<String, String> queryMap = new HashMap<>();

    public RequestParams() {
        queryMap.put(Constants.REQUEST_PARAMS.API_KEY, BuildConfig.POPULAR_MOVIES_API_KEY);
    }

    public void addQueryParam(String key, String value) {
        queryMap.put(key, value);
    }

    public Map<String, String> getQueryMap() {
        return queryMap;
    }
}
