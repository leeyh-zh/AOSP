package com.lyh.coordinator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScrollingFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    private List<String> mDatas;
    private static final String ARG_TITLE = "title";
    private String mTitle;
    Activity activity;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    int statusColor;

    public ScrollingFragment getInstance(String title, @NonNull Activity activity, AppBarLayout appBarLayout, CollapsingToolbarLayout collapsingToolbarLayout,
                                         Toolbar toolbar, @ColorInt int statusColor) {
        ScrollingFragment fra = new ScrollingFragment();
        this.activity = activity;
        this.appBarLayout = appBarLayout;
        this.collapsingToolbarLayout = collapsingToolbarLayout;
        this.toolbar = toolbar;
        this.statusColor = statusColor;
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scrolling_main, container, false);

        initData();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(mAdapter = new RecyclerAdapter(mRecyclerView.getContext(), mDatas));

        return v;
    }

    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add(mTitle + (char) i);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //if (!hidden) {
            //StatusBarCompat.translucentStatusBar(getActivity(), true);
            StatusBarCompat.setStatusBarColorForCollapsingToolbar(getActivity(),appBarLayout,collapsingToolbarLayout,toolbar,statusColor);
       // }
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
        private Context mContext;
        private List<String> mDatas;

        public RecyclerAdapter(Context context, List<String> datas) {
            mContext = context;
            mDatas = datas;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.fragment_scrolling, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.textview);
            }
        }
    }
}
