package com.example.dell.b.model;

import android.util.Log;

import com.example.dell.b.bean.FuBean;
import com.example.dell.b.callback.ClickBack;
import com.example.dell.b.service.FuService;

import javax.security.auth.callback.Callback;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DELL on 2019/9/22.
 */

public class ImpClickModel implements ClickModel {
    private static final String TAG = "tag";

    @Override
    public void getData(final ClickBack clickBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FuService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        FuService service = retrofit.create(FuService.class);
        Observable<FuBean> data = service.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuBean fuBean) {
                        clickBack.onService(fuBean);
                        Log.e(TAG, "onNext: "+fuBean.toString() );
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
