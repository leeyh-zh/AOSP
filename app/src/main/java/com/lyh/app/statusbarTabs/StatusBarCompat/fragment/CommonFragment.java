package com.lyh.app.statusbarTabs.StatusBarCompat.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.lyh.app.R;
import com.lyh.app.statusbarTabs.StatusBarCompat.activity.StatusBarCompatActivity;
import com.lyh.app.utils.StatusBarCompat;

/**
 * Common Layout, test for setStatusBarColor
 */
public class CommonFragment extends Fragment {

    public CommonFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statusbar_common, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStatusBar();
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seek_bar);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                StatusBarCompat.setStatusBarColor(getActivity(), StatusBarCompatActivity.DEFAULT_COLOR, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setStatusBar();
        }
    }

    private void setStatusBar() {
        StatusBarCompat.setStatusBarColor(getActivity(), Color.GREEN);
        StatusBarCompat.setStatusBarColor(getActivity(), StatusBarCompatActivity.DEFAULT_COLOR);
    }
}
