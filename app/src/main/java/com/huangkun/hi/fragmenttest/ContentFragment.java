package com.huangkun.hi.fragmenttest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hi on 2016/8/7.
 */
public class ContentFragment extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content, null);
        textView = (TextView) view.findViewById(R.id.tv_content);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int index = 0;// 初始化为0，也就是打开程序时，默认显示的是北京大学的资料

        //如果获取不到数据，则说明用户还没有点击任何子项，那么忽略获取数据这段代码
        try {
            Bundle args = getArguments();
            index = args.getInt("index", 0); //获取由标题类传递过来的用户点击的序列号
        } catch (Exception e) {

        }

        //打开对应的文本，获取数据并显示在TextView上
        InputStream is = null;
        try {
            is = getActivity().getAssets().open("university/university" + index + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
            textView.setBackgroundColor(Color.WHITE);//背景为白色则可以覆盖掉上一次的显示的内容，避免重叠
            textView.setText(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
