package com.example.yls.myapplication9999.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.yls.myapplication9999.NewsDetailActivity;
import com.example.yls.myapplication9999.NewsEntity;
import com.example.yls.myapplication9999.R;
import com.example.yls.myapplication9999.URLManager;
import com.example.yls.myapplication9999.adapter.NewsAdapter;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;


/**
 * 显示一个类别下的新闻
 *
 * @author WJQ
 */
public class NewsItemFragment extends BaseFragment {

   // private TextView textView;

    /** 新闻类别id */
    private String newsCategoryId;
    private ListView mListView;
    private NewsEntity newsData;

    /** 设置新闻类别id */
    public void setNewsCategoryId(String newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news_item;
    }

    @Override
    public void initView() {
        //textView = (TextView) mRootView.findViewById(R.id.tv_01);
        //textView.setText("类别id：" + newsCategoryId);
        mListView= (ListView) mRootView.findViewById(R.id.list_view);
    }

    @Override
    public void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsEntity.ResultBean newsBean = (NewsEntity.ResultBean)
                        parent.getItemAtPosition(position);

                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("news",newsBean);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        getDataForServer();
    }

    private void getDataForServer() {
        String url= URLManager.getUrl(newsCategoryId);
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json=responseInfo.result;
                System.out.println("----服务器返回的json数据:" + json);
                json=json.replace(newsCategoryId,"result");
                Gson gson=new Gson();
                newsData = gson.fromJson(json, NewsEntity.class);
                System.out.println("----解析json:" + newsData.getResult().size());
                showData(newsData);

            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }

    private void showData(NewsEntity newsDatas) {
        if (newsDatas == null
                || newsDatas.getResult() == null
                || newsDatas.getResult().size() == 0) {
            System.out.println("----没有获取到服务器的新闻数据");
            return;
        }
        List<NewsEntity.ResultBean.AdsBean>  ads=newsDatas.getResult().get(0).getAds();
        if (ads !=null &&ads.size()>0){
            View headView = View.inflate(mActivity, R.layout.news_head_view, null);
            SliderLayout sliderLayout = (SliderLayout)
                    headView.findViewById(R.id.slider_layout);

            for (int i=0;i<ads.size();i++){
                NewsEntity.ResultBean.AdsBean adBean = ads.get(i);

                TextSliderView sliderView = new TextSliderView(mActivity);
                sliderView.description(adBean.getTitle()).image(adBean.getImgsrc());
                // 添加子界面
                sliderLayout.addSlider(sliderView);

                // 设置点击事件
                sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        showToast(slider.getDescription());
                    }
                });
            }mListView.addHeaderView(headView);
        }
        mListView.setAdapter(new NewsAdapter(newsDatas.getResult(),mActivity));
    }
}
