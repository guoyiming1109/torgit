package com.example.dell.a.service;

import com.example.dell.a.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by DELL on 2019/9/20.
 */

public interface ListService {
    String baseUrl = "http://static.owspace.com/";
    @GET("?c=api&a=getList&page_id=0")
    Observable<ListBean> getData();
}
