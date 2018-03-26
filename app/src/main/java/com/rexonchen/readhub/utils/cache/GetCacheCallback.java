package com.rexonchen.readhub.utils.cache;


public interface GetCacheCallback {

    int NoData = 0;
    int GetDataError = 1;
    void onSuccess(String cache);

    void onFail(int code);
}
