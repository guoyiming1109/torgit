package com.example.dell.b;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dell.b.bean.FuBean;

import java.util.ArrayList;


/**
 * Created by DELL on 2019/9/22.
 */

class MainAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<FuBean.ResultsBean> resultsBean;

    public MainAdapter(Context context, ArrayList<FuBean.ResultsBean> resultsBean) {
        this.context = context;
        this.resultsBean = resultsBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Glide.with(context).load(this.resultsBean.get(i).getUrl()).into(holder.iv);

        final FuBean.ResultsBean resultsBean1 = new FuBean.ResultsBean();
        final ArrayList<FuBean.ResultsBean> list = new ArrayList<>();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null){
                    onClickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsBean.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }


    interface OnClickListener{
       void onItemClick(int position);
    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onItemClickListener) {
        this.onClickListener = onItemClickListener;
    }
}
