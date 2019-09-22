package com.example.dell.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dell.a.bean.DbBean;
import com.example.dell.a.bean.ListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/9/20.
 */

class MainAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<ListBean.DatasBean> list;
    private DbUtil dbUtil = DbUtil.getDbUtil();
    private Button btn;
    private DbBean dbBean;

    public MainAdapter(Context context, ArrayList<ListBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        final ListBean.DatasBean datasBean = list.get(i);
        Glide.with(context).load(list.get(i).getAvatar()).apply(new RequestOptions().circleCrop()).into(holder.iv);
        holder.title.setText(list.get(i).getAuthor());
        holder.desc.setText(list.get(i).getTitle());

        // 对关注进行监听
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    dbBean = new DbBean(0,list.get(i).getAvatar(), list.get(i).getAuthor(), list.get(i).getTitle());
//                    long insert = dbUtil.insert(dbBean);
//                    if (insert > 0){
//                        Toast.makeText(context, "插入成功", Toast.LENGTH_SHORT).show();
//                        btn = holder.btn;
//                        btn.setText("取消");
//                        /*List<DbBean> query = dbUtil.query();
//                        Log.e("TAG", "onClick: "+ query.toString());*/
//                    }else {
//                        Toast.makeText(context, "插入失败", Toast.LENGTH_SHORT).show();
//
//                    }
                String string = holder.btn.getText().toString();



                if (string.matches("关注")){
                    holder.btn.setText("取消");
                    onClick.onClick(i);
                }else{
                    holder.btn.setText("关注");
                    onClick.onClickLong(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView title;
        TextView desc;
        Button btn;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            title = itemView.findViewById(R.id.title1);
            desc = itemView.findViewById(R.id.tv_desc);
            btn = itemView.findViewById(R.id.btn);
        }
    }
    public interface onClick{
        void onClick(int position);
        void onClickLong(int position);
    }
    private onClick onClick;

    public void setOnClick(MainAdapter.onClick onClick) {
        this.onClick = onClick;
    }
}
