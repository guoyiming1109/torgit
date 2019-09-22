package com.example.dell.a.callback;

import com.example.dell.a.bean.ListBean;

/**
 * Created by DELL on 2019/9/20.
 */

public interface ClickBack {
    void onSuccuss(ListBean listBean);
    void onFail(String s);
}
