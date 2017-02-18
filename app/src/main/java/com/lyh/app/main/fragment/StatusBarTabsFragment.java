package com.lyh.app.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lyh.app.R;
import com.lyh.app.statusbarTabs.StatusBarCompat.activity.StatusBarCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyh on 2017/2/18.
 */

public class StatusBarTabsFragment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.statusBarCompat)
    LinearLayout statusBarCompat;
    @BindView(R.id.coordinatorTabLayout2)
    LinearLayout coordinatorTabLayout2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatusBarTabsFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static StatusBarTabsFragment newInstance(String param1, String param2) {
        StatusBarTabsFragment fragment = new StatusBarTabsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statusbar_tabs, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.statusBarCompat)
    public void onClick() {
        startActivity(new Intent(getContext(), StatusBarCompatActivity.class));
    }
}
