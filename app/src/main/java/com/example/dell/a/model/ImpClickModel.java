package com.example.dell.a.model;

import android.util.Log;

import com.example.dell.a.bean.ListBean;
import com.example.dell.a.callback.ClickBack;
import com.example.dell.a.service.ListService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DELL on 2019/9/20.
 */

public class ImpClickModel implements ClickModel {
    @Override
    public void getData(final ClickBack clickBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ListService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ListService service = retrofit.create(ListService.class);
        Observable<ListBean> data = service.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        clickBack.onSuccuss(listBean);
                        Log.e("TAG", "onNext: "+listBean.toString() );
                    }

                    @Override
                    public void onError(Throwable e) {
                        clickBack.onFail("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
