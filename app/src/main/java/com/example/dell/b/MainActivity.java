package com.example.dell.b;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.b.bean.FuBean;
import com.example.dell.b.persenter.ImpClickPersener;
import com.example.dell.b.view.ImpClickView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImpClickView, MainAdapter.OnClickListener {

    private static final String TAG = "aaa";
    private RecyclerView mRl;
    private ArrayList<FuBean.ResultsBean> list;
    private MainAdapter adapter;
    private ImpClickPersener persener;
    private int mPosition;
    private ViewPager mVp;
    private TextView mTxt;
    private ArrayList<View> viewList;
    private MainPagerAdapter pagerAdapter;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initData() {
        persener = new ImpClickPersener(this);
        persener.getData();
    }

    private void initView() {
        mRl = (RecyclerView) findViewById(R.id.rl);
        mVp = (ViewPager) findViewById(R.id.vp);
        mTxt = (TextView) findViewById(R.id.txt);

        // 创建集合
        list = new ArrayList<>();
        viewList = new ArrayList<>();
        // 创建适配器
        adapter = new MainAdapter(this, list);
        mRl.setAdapter(adapter);
        // 设置布局管理器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mRl.setLayoutManager(layoutManager);
        // pagerAdapter
        pagerAdapter = new MainPagerAdapter(this, viewList);
        mVp.setAdapter(pagerAdapter);
        // 接口
        adapter.setOnClickListener(this);
        // viewPager的监听方法
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mTxt.setText(i+1+"/"+20);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onService(FuBean fuBean) {
        list.addAll(fuBean.getResults());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        mPosition = position;
        // 点击条目让多布局隐藏，vp和txt显示
        mRl.setVisibility(View.GONE);
        mVp.setVisibility(View.VISIBLE);
        mTxt.setVisibility(View.VISIBLE);
        for (int i = 0; i < list.size(); i++) {
            // 自定义布局，用于显示图片
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_image, null);
            img = view.findViewById(R.id.iv2);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 点击图片时让多布局显示，vp和txt隐藏
                    mRl.setVisibility(View.VISIBLE);
                    mVp.setVisibility(View.GONE);
                    mTxt.setVisibility(View.GONE);

                }
            });
            // 点击哪处图片显示哪个图片
            mVp.setCurrentItem(mPosition);
            Glide.with(this).load(list.get(i).getUrl()).into(img);
            viewList.add(view);
        }
        pagerAdapter.notifyDataSetChanged();
    }


}