package com.example.dell.b.persenter;

import com.example.dell.b.bean.FuBean;
import com.example.dell.b.callback.ClickBack;
import com.example.dell.b.model.ImpClickModel;
import com.example.dell.b.view.ImpClickView;

/**
 * Created by DELL on 2019/9/22.
 */

public class ImpClickPersener implements ClickPersenter, ClickBack {
    private ImpClickModel impClickModel;
    private ImpClickView impClickView;

    public ImpClickPersener(ImpClickView impClickView) {
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
    public void onService(FuBean fuBean) {
        if (impClickView != null){
            impClickView.onService(fuBean);
        }
    }

    @Override
    public void onFail(String s) {
        if (impClickView != null){
            impClickView.onFail(s);
        }
    }
}
