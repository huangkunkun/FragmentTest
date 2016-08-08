package com.huangkun.hi.fragmenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by hi on 2016/8/7.
 */
public class TitleFragment extends Fragment {

    private ListView listView;
    private String[] data = {"北京大学", "清华大学", "复旦大学", "浙江大学", "武汉大学"};
    public ItemClickListener itemClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title, null);
        listView = (ListView) view.findViewById(R.id.lv_title);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        ItemClick listener = new ItemClick();
        listView.setOnItemClickListener(listener);
    }

    private class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            itemClickListener.getIndex(position); //通过接口的引用将点击的序列号作为参数传递进去
        }
    }

    interface ItemClickListener{
        void getIndex(int index);
    }

    //该方法用于设置当前参数中接口的引用和操作序列号的接口引用相同，然后在主Activity中获得用于点击列表后操作序列号的那个引用
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
