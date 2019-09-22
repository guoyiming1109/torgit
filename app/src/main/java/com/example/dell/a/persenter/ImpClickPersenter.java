package com.example.dell.a.persenter;

import com.example.dell.a.bean.ListBean;
import com.example.dell.a.callback.ClickBack;
import com.example.dell.a.model.ImpClickModel;
import com.example.dell.a.view.ImpClickView;

/**
 * Created by DELL on 2019/9/20.
 */

public class ImpClickPersenter implements ClickPersenter, ClickBack {
    private ImpClickView impClickView;
    private ImpClickModel impClickModel;

    public ImpClickPersenter(ImpClickView impClickView) {
        this.impClickView = impClickView;
        impClickModel = new ImpClickModel();
    }

    @Override
    public void getData() {
        if (impClickModel != null){
            impClickModel.getData(this);
        }
    }

    @Override
    public void onSuccuss(ListBean listBean) {
        if (impClickView != null){
            impClickView.onSuccuss(listBean);
        }
    }

    @Override
    public void onFail(String s) {
        if (impClickView != null){
            impClickView.onFail(s);
        }
    }
}
