package cn.tthud.a51sign.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.tthud.a51sign.R;

/**
 * Created by yh on 2018/3/26.
 */

public class MineFragment extends Fragment {
    public MineFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment,container,false);
        return view;
    }
}
