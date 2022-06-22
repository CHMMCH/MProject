package com.chm.mproj.common.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Desc :1.将Fragment的操作内聚 2.提供给统用api
 * @Author: chenhongmou
 * @Time: 2022/6/22 15:55
 */
public class MFragmentTabView extends FrameLayout {

    private MTAbViewAdapter mAdapter;
    private int currentPosition;

    public MFragmentTabView(@NonNull Context context) {
        super(context);
    }

    public MFragmentTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MFragmentTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MTAbViewAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(MTAbViewAdapter adapter) {
        if (this.mAdapter !=null || adapter == null ){
            return;
        }
        this.mAdapter = adapter;
        //初始化值，不对应任何fragment
        currentPosition = -1;
    }

    //选中条目
    public void setCurrentItem(int position){
        //防止数组越界
        if (position<0||position>mAdapter.getCount()){
            return;
        }

        if (currentPosition != position){
            currentPosition = position;
            mAdapter.instantiateItem(this,position);
        }
    }

    //返回当前条目
    public int getCurrentItem(){
        return currentPosition;
    }

    //放回当前fragment
    public Fragment getCurrentFragment(){
        if (this.mAdapter == null){
            throw new IllegalArgumentException("please call setAdapter first");
        }
        return mAdapter.getCureentFragment();
    }



}
