package cn.tthud.a51sign.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.tthud.a51sign.R;
import cn.tthud.a51sign.adapter.HomeAdapter;

/**
 * Created by yh on 2018/3/26.
 */

public class HomeFragment extends Fragment {

    private RecyclerView recyc_list;
    private List<String> list;
    private HomeAdapter adapter;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment,container,false);

        initView(view);
        return view;
    }

    private void initView(View view) {

        recyc_list = view.findViewById(R.id.recyc_list);
        list = new ArrayList<>();
        list.add("BTC");
        list.add("ETH");
        list.add("EOS");
        list.add("OMG");

        recyc_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter(list,getActivity());
        //recyc_list.setLayoutManager(new GridLayoutManager(this,5)); // 九宫格形式
        recyc_list.setAdapter(adapter);

    }
}
