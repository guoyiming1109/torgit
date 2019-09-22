package com.example.dell.b.callback;

import com.example.dell.b.bean.FuBean;

/**
 * Created by DELL on 2019/9/22.
 */

public interface ClickBack {
    void onService(FuBean fuBean);
    void onFail(String s);
}
