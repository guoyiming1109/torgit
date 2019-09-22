package com.example.dell.b.service;

import com.example.dell.b.bean.FuBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by DELL on 2019/9/22.
 */

public interface FuService {
    String baseUrl = "http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<FuBean> getData();
}
