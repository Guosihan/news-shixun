package com.example.yls.myapplication9999.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yls.myapplication9999.R;
import com.example.yls.myapplication9999.VideoEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by yls on 2017/6/29.
 */

public class VideoAdapter extends  RecyclerView.Adapter<ViewHolder>{
    private List<VideoEntity.ResultBean> listDatas;;
    private Context context;

    public VideoAdapter(List<VideoEntity.ResultBean> listDatas, Context context) {
        this.listDatas = listDatas;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new MyViewHolder(item);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyViewHolder holder1= (MyViewHolder) holder;
        final VideoEntity.ResultBean video = listDatas.get(position);
        // 预加载缩略图
        Picasso.with(context).load(video.getCover()).into(holder1.ivVideoImage);

        // 显示标题
        holder1.tvVideoTitle.setText(listDatas.get(position).getTitle());
        // 显示视频播放时长
        System.out.println("-----duration: " + video.getLength());
        String durationStr = DateFormat.format("mm:ss", video.getLength() * 1000).toString();
        holder1.tvVideoDuration.setText(durationStr);
        holder1.videplayer.setUp(video.getMp4_url(),video.getTitle());
        // 显示播放次数
        holder1.tvPlayCount.setText(video.getPlayCount() + "");
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra("video_url", video.getMp4_url());
                context.startActivity(intent);
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return listDatas == null ? 0 : listDatas.size();
    }
    private class MyViewHolder extends ViewHolder{
        private ImageView ivVideoImage;
        private TextView tvVideoTitle;
        private TextView tvVideoDuration;
        private TextView tvPlayCount;
        private JCVideoPlayerStandard videplayer;
        public MyViewHolder(View itemView) {
            super(itemView);

            ivVideoImage = (ImageView) itemView.findViewById(R.id.iv_video_image);
            tvVideoTitle = (TextView) itemView.findViewById(R.id.tv_video_title);
            tvVideoDuration = (TextView) itemView.findViewById(R.id.tv_video_duration);
            tvPlayCount = (TextView) itemView.findViewById(R.id.tv_play_count);
            videplayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer_standard);
        }
    }
}
