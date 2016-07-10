package com.example.chaiy.popularmovies.presenter;

import com.example.chaiy.popularmovies.events.NetworkResponseListener;
import com.example.chaiy.popularmovies.network.NetworkManager;
import com.example.chaiy.popularmovies.network.RequestParams;

/**
 * Created by Chaiy on 7/9/2016.
 */
public class BasePresenter {

    NetworkResponseListener responseListener;
    NetworkManager mNetworkManager;
    RequestParams mRequestParams;

    public BasePresenter(NetworkResponseListener responseListener) {

        this.responseListener = responseListener;
        mNetworkManager = new NetworkManager();
        mRequestParams = new RequestParams();
    }


}
