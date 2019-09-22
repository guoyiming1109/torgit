package com.example.dell.a;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.a.bean.DbBean;
import com.example.dell.a.bean.ListBean;
import com.example.dell.a.persenter.ImpClickPersenter;
import com.example.dell.a.view.ImpClickView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ImpClickView {

    /**
     * 列表
     */
    private TextView mTv;
    private Toolbar mTb;
    private RecyclerView mRl;
    private ArrayList<ListBean.DatasBean> list;
    private MainAdapter adapter;
    private ImpClickPersenter persenter;
    private DbBean dbBean;

    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        persenter = new ImpClickPersenter(this);
        persenter.getData();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mTb = (Toolbar) findViewById(R.id.tb);
        mRl = (RecyclerView) findViewById(R.id.rl);
        mRl.setLayoutManager(new LinearLayoutManager(this));
        // 设置toolbar
        mTb.setTitle("");
        setSupportActionBar(mTb);
        // 创建集合
        list = new ArrayList<>();
        // 创建适配器
        adapter = new MainAdapter(this, list);
        // 绑定适配器
        mRl.setAdapter(adapter);
        // 接口回调
        //adapter.setOnClickItem(this);

        adapter.setOnClick(new MainAdapter.onClick() {

            @Override
            public void onClick(int i) {

                mPosition = i;
                DbBean dbBean = new DbBean();
                dbBean.setUrl(list.get(i).getAvatar());
                dbBean.setTitle(list.get(i).getAuthor());
                dbBean.setDesc(list.get(i).getTitle());
                long insert = DbUtil.getDbUtil().insert(dbBean);
                if (insert > 0){
                    Toast.makeText(MainActivity.this,"插入成功",Toast.LENGTH_SHORT) .show();
                }else {
                    Toast.makeText(MainActivity.this,"插入失败",Toast.LENGTH_SHORT) .show();
                }
            }

            @Override
            public void onClickLong(int position) {
                DbBean dbBean = DbUtil.getDbUtil().query().get(position);
                //DbBean dbBean = new DbBean();
                boolean deltet = DbUtil.getDbUtil().deltet(dbBean);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccuss(ListBean listBean) {
        list.addAll(listBean.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    /*@Override
    public void onClickItem(int i) {
        mPosition = i;
        DbBean dbBean = new DbBean();
        dbBean.setUrl(list.get(i).getAvatar());
        dbBean.setTitle(list.get(i).getAuthor());
        dbBean.setDesc(list.get(i).getTitle());
        long insert = DbUtil.getDbUtil().insert(dbBean);
        if (insert > 0){
            Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show();
        }
    }*/
}
