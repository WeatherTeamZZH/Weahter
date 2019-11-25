package com.ok100.weather.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.activity.NoticeDetatilActivity;
import com.ok100.weather.adapter.NoticeMainFragmentAdapter;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.NoticeMainListBean;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.presenter.NewsListPresenterImpl;
import com.ok100.weather.presenter.NoticeMainListPresenterImpl;
import com.ok100.weather.utils.ChooseTypeUtils;
import com.ok100.weather.view.CustomLoadMoreViewNews;
import com.ok100.weather.view.MyLinearLayoutManager1;
import com.ok100.weather.view.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class NoticeMainFragment1 extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener, ReturnDataView<Object>, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, View.OnTouchListener {

    @BindView(R.id.recycle)
    MyRecyclerView mRecyclerView;
    Unbinder unbinder;

    //    private SwipeRefreshLayout mSwipeRefreshLayout;

    private NoticeMainFragmentAdapter noticeMainFragmentAdapter;

    private int page = 1;
    private int pageNubmer = 10;
    private String departmentId = "100";
    public String type = "";
    private NoticeMainListPresenterImpl noticeMainListPresenterImpl;
    private ArrayList<NoticeMainListBean> listBeenData = new ArrayList<>();

    NewsListBean newsListBean;
    NewsListPresenterImpl newsListPresenterImpl;

    public boolean isXiaoyulin() {
        return xiaoyulin;
    }

    public void setXiaoyulin(boolean xiaoyulin) {
        this.xiaoyulin = xiaoyulin;
    }

    public boolean xiaoyulin = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news_vanlian;
    }


    @Override
    public void returnData(String responseCode, Object o) {
        switch (responseCode) {
            case "getNewsList":
                newsListBean = (NewsListBean) o;
                newsListBean.getResult().getData();
                if (page == 1) {
                    noticeMainFragmentAdapter.setNewData(newsListBean.getResult().getData());
//            listBeenData.addAll(noticeMainListBean.getList());
//            noticeMainFragmentAdapter.setNewData(listBeenData);
//            mSwipeRefreshLayout.setRefreshing(false);
                } else {
//            listBeenData.addAll(noticeMainListBean.getList());
//            noticeMainFragmentAdapter.setNewData(listBeenData);
                    noticeMainFragmentAdapter.addData(newsListBean.getResult().getData());
                    noticeMainFragmentAdapter.loadMoreComplete();
                }
                break;
        }


//        if (noticeMainListBean.getList().size() < pageNubmer) {
//            noticeMainFragmentAdapter.loadMoreEnd();
//            noticeMainFragmentAdapter.removeAllFooterView();
//        }

    }

    @Override
    public void showError(String msg) {
//        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    protected void init(Bundle savedInstanceState, View contentView) {
        Log.e("init", "+++"+departmentId+"+++"+type);
        findView();
        initAdapter();
        recyvleViewScrollLister();
        noticeMainListPresenterImpl = new NoticeMainListPresenterImpl(this);

    }


    private void http() {
        newsListPresenterImpl = new NewsListPresenterImpl(this);
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("type", ChooseTypeUtils.getNewType(type));
        newsListPresenterImpl.getNewsList(getActivity(), stringStringHashMap);
    }


    public boolean isTabVisible = true;
    private int totalDy = 0;


    private void recyvleViewScrollLister() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!recyclerView.canScrollVertically(1)) {
//                        callback.onScrollToBottom();
                    }
                    if (!recyclerView.canScrollVertically(-1)) {
                        Log.e("stopScroll", "stopScroll");
                        mRecyclerView.stopScroll();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalDy -= dy;
                Log.e("totalDy", totalDy + "---"+departmentId+"---"+type);
                if (totalDy < 0) {
                    if (xiaoyulin) {
                        mRecyclerView.stopScroll();
                        bootomVisibleListener.setBootomVisible(true);
                        taybarVisibleListener.setTaybarVisible(false);
                        xiaoyulin = false;
                    }

                } else {
                    mRecyclerView.stopScroll();
                    xiaoyulin = true;
//                    bootomVisibleListener.setBootomVisible(false);
//                    taybarVisibleListener.setTaybarVisible(true);
                    //滑到顶部
                }


            }
        });

    }

    MyLinearLayoutManager1 linearLayoutManager;

    private void initAdapter() {
        linearLayoutManager = new MyLinearLayoutManager1(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        noticeMainFragmentAdapter = new NoticeMainFragmentAdapter(getActivity());
//        mRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
        mRecyclerView.setAdapter(noticeMainFragmentAdapter);


        //设置加载
//        noticeMainFragmentAdapter.setEnableLoadMore(true);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(87, 136, 255));
        noticeMainFragmentAdapter.setOnLoadMoreListener(this, mRecyclerView);
        noticeMainFragmentAdapter.disableLoadMoreIfNotFullPage();
        noticeMainFragmentAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        noticeMainFragmentAdapter.setOnItemClickListener(this);
        noticeMainFragmentAdapter.setOnItemChildClickListener(this);
        noticeMainFragmentAdapter.setOnItemLongClickListener(this);
        noticeMainFragmentAdapter.setLoadMoreView(new CustomLoadMoreViewNews());
//        noticeMainFragmentAdapter.setEmptyView(getEmptyView());
//        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_news_main_footview, null);
//        noticeMainFragmentAdapter.setFooterView(layout);

        noticeMainFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsListBean.ResultBean.DataBean newsListBean = (NewsListBean.ResultBean.DataBean) adapter.getData().get(position);
                String url = newsListBean.getUrl();
                Intent intent = new Intent(getActivity(), NoticeDetatilActivity.class);
                intent.putExtra("url", url);
                getActivity().startActivity(intent);

            }
        });

    }


    private void findView() {
//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout_vanlian_new);
        if (getArguments() != null) {
            departmentId = getArguments().getString("departmentId");
            type = getArguments().getString("type");
        }
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        onRefresh();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onRefresh() {
//        mSwipeRefreshLayout.setRefreshing(true);
        page = 1;
        http();
//        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
//        onRefresh();
    }

//    private View getEmptyView() {
//        View empty = LayoutInflater.from(getActivity()).inflate(R.layout.list_empty_view, (ViewGroup) mRecyclerView.getParent(), false);
//        return empty;
//    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        http();

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        NoticeMainListBean.ListBean listBean = listBeenData.get(position);
        Intent intent = new Intent(getActivity(), NoticeDetatilActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("onStart", "onStart");
    }

    public MainFragment.BootomVisibleListener bootomVisibleListener;

    public void setBootomVisibleListener(MainFragment.BootomVisibleListener bootomVisibleListener) {
        this.bootomVisibleListener = bootomVisibleListener;
    }

    public MainFragment.TaybarVisibleListener taybarVisibleListener;

    public void setTaybarVisibleListener(MainFragment.TaybarVisibleListener taybarVisibleListener) {
        this.taybarVisibleListener = taybarVisibleListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private GestureDetector mGestureDetector;
    @Override
    public void onDestroyView() {
        Log.e("onDestroyView", "+++"+departmentId+"+++"+type);
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        Log.e("onDestroyView", "+++"+departmentId+"+++"+type);
        super.onDestroy();
    }

    public void resateRecycle() {
//        RecyclerView recyclerView  = (RecyclerView) findViewById(R.id.recycle);
        Log.e("totalDy", "+++"+departmentId+"+++"+type);
        if (mRecyclerView != null) {
            Log.e("mainFragment", "有");
            mRecyclerView.scrollBy(0, totalDy - 100);
        }

    }

}
