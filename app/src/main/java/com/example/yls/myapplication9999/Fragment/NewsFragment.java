package com.example.yls.myapplication9999.Fragment;

import android.widget.TextView;

import com.example.yls.myapplication9999.R;

/**
 * Created by gsh on 2017/6/27.
 */

public class NewsFragment extends BaseFragment {
    private String channeId;
    private TextView textView;

    public void setChannelId(String channelId) {
        this.channeId = channelId;
    }
    public int getLayoutRes() {
        return R.layout.newsfragment;
    }

    @Override
    public void initView() {
        textView = (TextView) mRootView.findViewById(R.id.tv_01);
        textView.setText(channeId);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
