package com.ok100.weather.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.R;
import com.ok100.weather.activity.NoticeDetatilActivity;
import com.ok100.weather.adapter.NoticeCenter1adapter;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.NoticeCenter1Bean;
import com.ok100.weather.bean.NoticeCenter2Bean;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.presenter.NewsListPresenterImpl;
import com.ok100.weather.utils.ChooseTypeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * description: 岗位助
 *
 * @autour: ZHANGZH
 * date: 2017-12-6 16:43
 * update: 2017-12-6
 * version:
 */
public class NoticeCenter1Fragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener , ReturnDataView<Object> {

    List<Map<String, String>> mlist = new ArrayList<>();
    @BindView(R.id.recycle_post_assistant)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    NoticeCenter1adapter mAdapter ;

    public NoticeCenter1Fragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_postassistant;
    }

    @Override
    protected void init(Bundle savedInstanceState, View contentView) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setLayoutManager(manager);
        int spacing = (int) getResources().getDimension(R.dimen.x30);
        mAdapter = new NoticeCenter1adapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsListBean.ResultBean.DataBean newsListBean = (NewsListBean.ResultBean.DataBean) adapter.getData().get(position);
                String url = newsListBean.getUrl();
                Intent intent = new Intent(getActivity(), NoticeDetatilActivity.class);
                intent.putExtra("url",url);
                getActivity().startActivity(intent);

            }
        });
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        http();
    }

    private void initData() {

    }

    private List<NoticeCenter1Bean> getListData() {
        List<NoticeCenter1Bean> noticeCenter1Beans = new ArrayList<>();
        NoticeCenter1Bean noticeCenter1Bean = new NoticeCenter1Bean();
        noticeCenter1Beans.add(noticeCenter1Bean);
        noticeCenter1Beans.add(noticeCenter1Bean);
        noticeCenter1Beans.add(noticeCenter1Bean);
        noticeCenter1Beans.add(noticeCenter1Bean);
        return noticeCenter1Beans;
    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtil.makeShortText(getActivity(), "不知道跳到哪儿");
//        Intent intent = new Intent(getActivity(), PostAssistantListActivity.class);
//        intent.putExtra("positionId", position + 1);
//        Map<String, String> map = mAdapter.getData().get(position);
//        intent.putExtra("title", map.get("name"));
//        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void returnData(String responseCode, Object o) {
        switch (responseCode){
            case "getNewsList":
                newsListBean = (NewsListBean) o;
                newsListBean.getResult().getData();
                if (page == 1) {
                    mAdapter.setNewData(newsListBean.getResult().getData());
//            listBeenData.addAll(noticeMainListBean.getList());
//            noticeMainFragmentAdapter.setNewData(listBeenData);
//            mSwipeRefreshLayout.setRefreshing(false);
                } else {
//            listBeenData.addAll(noticeMainListBean.getList());
//            noticeMainFragmentAdapter.setNewData(listBeenData);
                    mAdapter.addData(newsListBean.getResult().getData());
                    mAdapter.loadMoreComplete();
                }
                break;
        }
    }

    @Override
    public void showError(String msg) {

    }
    private int page = 1;
    NewsListBean newsListBean ;
    NewsListPresenterImpl newsListPresenterImpl ;
    private String type = "";

    private void http() {
        newsListPresenterImpl =  new NewsListPresenterImpl(this);
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("type", ChooseTypeUtils.getNewType(type));
        newsListPresenterImpl.getNewsList(getActivity(),stringStringHashMap);
    }

}
