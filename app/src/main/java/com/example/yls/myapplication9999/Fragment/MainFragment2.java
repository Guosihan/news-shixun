package com.example.yls.myapplication9999.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yls.myapplication9999.R;
import com.example.yls.myapplication9999.URLManager;
import com.example.yls.myapplication9999.VideoEntity;
import com.example.yls.myapplication9999.adapter.VideoAdapter;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by yls on 2017/6/27.
 */

public class MainFragment2 extends BaseFragment {
    @Override
    public int getLayoutRes() {
        return R.layout.fragment2;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        getVideoData();
    }

    private void getVideoData() {
        String videoURL = URLManager.VideoURL;
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, videoURL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                json =  json.replace("V9LG4B3A0", "result");
                Gson gson=new Gson();
                VideoEntity newsDatas  = gson.fromJson(json, VideoEntity.class);
                List<VideoEntity.ResultBean> listDatas = newsDatas.getResult();
                showDatas(newsDatas);
            }

            public void onFailure(HttpException error, String msg) {

            }
        });
    }

    private void showDatas(VideoEntity newsDatas) {
        RecyclerView recyclerView = (RecyclerView)mRootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(new VideoAdapter( newsDatas.getResult(),mActivity));
    }


}
