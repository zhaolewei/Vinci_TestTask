package com.zlw.vinci_testtask_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zlw.bean.Music;

import java.util.List;

/**
 * Created by zlw on 2016/10/31 0031.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter {

    private List<Music> list;

    public void setList(List<Music> list) {
        this.list = list;
    }

    public MyRecycleViewAdapter(List<Music> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mHolder = (MyViewHolder) holder;

        Music music = list.get(position);
        mHolder.tv_singer.setText(music.getSinger());
        mHolder.tv_song.setText(music.getSong());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView tv_singer;
        public TextView tv_song;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_singer = (TextView) itemView.findViewById(R.id.item_rv_singer);
            tv_song = (TextView) itemView.findViewById(R.id.item_rv_song);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
