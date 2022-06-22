package com.chm.mproj.common.tab;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.chm.m.ui.tab.bottom.MTabBottomInfo;

import java.util.List;

/**
 * @Desc : 管理View适配器
 * @Author: chenhongmou
 * @Time: 2022/6/22 9:56
 */
public class MTAbViewAdapter {

    private List<MTabBottomInfo<?>> mInfoList;
    private Fragment mCurFragment;
    private FragmentManager mFragmentManager;

    public MTAbViewAdapter(FragmentManager fragmentManager , List<MTabBottomInfo<?>> infoList) {
        this.mInfoList = infoList;
        this.mFragmentManager = fragmentManager;
    }

    /**
     * 实例化以及显示指定位置的fragment
     * @param container
     * @param position
     */
    public void instantiateItem (View container , int position){
        FragmentTransaction mCurTransaction = mFragmentManager.beginTransaction();

        //隐藏当前fragment
        if (mCurTransaction != null){
            mCurTransaction.hide(mCurFragment);
        }

        //添加tag
        String name = container.getId()+":"+position;
        Fragment fragment  = mFragmentManager.findFragmentByTag(name);

        if(fragment!=null){
            mCurTransaction.show(fragment);
        }else{
            fragment = getItem(position);
            if (!fragment.isAdded()){
                mCurTransaction.add(container.getId(),fragment,name);
            }
        }
        mCurFragment = fragment;
        mCurTransaction.commitAllowingStateLoss();
    }

    public Fragment getCureentFragment(){
        return mCurFragment;
    }

    public Fragment getItem(int postion){
        try {
            mInfoList.get(postion).fragment.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取列表长度
    public int getCount(){
        return mInfoList==null?0:mInfoList.size();
    }

}
