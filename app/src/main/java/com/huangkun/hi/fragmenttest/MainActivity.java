package com.huangkun.hi.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements TitleFragment.ItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取TitleFragment的对象，并调用setItemClickListener方法，主要就是为了传递接口的引用
        TitleFragment titleFragment = (TitleFragment) getSupportFragmentManager().findFragmentById(R.id.title_fragment);
        titleFragment.setItemClickListener(this);

        //在刚启动程序时，默认打开第一个文本即北京大学
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new ContentFragment();
        transaction.add(R.id.container, fragment);
        transaction.commit();

    }

    @Override
    public void getIndex(int index) {
        Fragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt("index", index); //传递序列号给内容类
        fragment.setArguments(args);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }
}
