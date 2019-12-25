package com.ok100.weather.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.R;
import com.ok100.weather.adapter.MessageAdapter;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.MessageListBean;
import com.ok100.weather.utils.SPObj;

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
public class NoticeCenter1Fragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recycle_post_assistant)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    MessageAdapter mAdapter;

    public NoticeCenter1Fragment() {
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_postassistant;
    }

    @Override
    protected void init(Bundle savedInstanceState, View contentView) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MessageAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        SPObj spObj = new SPObj(getContext(), "gh");
        MessageListBean message = spObj.getObject("messageList", MessageListBean.class);
        if (message == null || message.getList() == null || message.getList().size() == 0) return;
        mAdapter.setNewData(message.getList());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
