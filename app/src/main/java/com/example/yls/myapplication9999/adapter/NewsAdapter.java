package com.example.yls.myapplication9999.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yls.myapplication9999.NewsEntity;
import com.example.yls.myapplication9999.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yls on 2017/6/28.
 */

public class NewsAdapter extends BaseAdapter {
    private List<NewsEntity.ResultBean> listDatas;
    private Context context;
    private NewsEntity.ResultBean mInfo;

    public NewsAdapter(List<NewsEntity.ResultBean> listDatas, Context context) {
        this.listDatas = listDatas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (listDatas == null) ? 0 : listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        mInfo = (NewsEntity.ResultBean) getItem(position);
        if (itemViewType==ITEM_TYPE_WITH_1_IMAGE){

            if (convertView ==null){
                convertView=View.inflate(context , R.layout.item_news_1,null);

            } ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            TextView tvSource = (TextView) convertView.findViewById(R.id.tv_source);
            TextView tvComment = (TextView) convertView.findViewById(R.id.tv_comment);

            // 显示列表item中的子控件
            tvTitle.setText(mInfo.getTitle());
            tvSource.setText(mInfo.getSource());
            tvComment.setText(mInfo.getReplyCount() + "跟帖");
            Picasso.with(context).load(mInfo.getImgsrc()).into(ivIcon);
        }else{  if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_news_2, null);
        }

            // 查找列表item中的子控件
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            TextView  tvComment = (TextView) convertView.findViewById(R.id.tv_comment);
            ImageView iv01 = (ImageView) convertView.findViewById(R.id.iv_01);
            ImageView iv02 = (ImageView) convertView.findViewById(R.id.iv_02);
            ImageView iv03 = (ImageView) convertView.findViewById(R.id.iv_03);

            // 显示列表item中的子控件
            tvTitle.setText(mInfo.getTitle());
            tvComment.setText(mInfo.getReplyCount() + "跟帖");
            try {
                Picasso.with(context).load(mInfo.getImgsrc()).into(iv01);
                Picasso.with(context).load(mInfo.getImgextra().get(0).getImgsrc())
                        .into(iv02);
                Picasso.with(context).load(mInfo.getImgextra().get(1).getImgsrc())
                        .into(iv03);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }


        return convertView;
    }
    private static final int ITEM_TYPE_WITH_1_IMAGE = 0;
    private static final int ITEM_TYPE_WITH_3_IMAGE = 1;

    @Override
    public int getItemViewType(int position) {
        NewsEntity.ResultBean item = (NewsEntity.ResultBean) getItem(position);
        if (item.getImgextra()==null ||item.getImgextra().size()==0){
            return ITEM_TYPE_WITH_1_IMAGE;
        }else{
            return ITEM_TYPE_WITH_3_IMAGE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
