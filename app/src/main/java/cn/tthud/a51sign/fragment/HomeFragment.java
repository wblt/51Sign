package cn.tthud.a51sign.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mylhyl.zxing.scanner.common.Scanner;

import java.util.ArrayList;
import java.util.List;

import cn.tthud.a51sign.AddAssetsActivity;
import cn.tthud.a51sign.R;
import cn.tthud.a51sign.adapter.HomeAdapter;
import cn.tthud.a51sign.scanner.ScannerActivity;

/**
 * Created by yh on 2018/3/26.
 */

public class HomeFragment extends Fragment {

    private RecyclerView recyc_list;
    private List<String> list;
    private HomeAdapter adapter;
    private ImageView iv_add;
    private ImageButton top_right_icon;
    private TextView top_center_text;
    private ImageButton top_left;
    private int laserMode, scanMode;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        inittopbar(view);
        initView(view);
        return view;
    }

    private void inittopbar(View view) {
        top_right_icon = view.findViewById(R.id.top_right_icon);
        top_right_icon.setImageResource(R.mipmap.icon_scaner);
        top_right_icon.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 60);
                } else {
                    //权限已经被授予，在这里直接写要执行的相应方法即可
                    laserMode = ScannerActivity.EXTRA_LASER_LINE_MODE_0;
                    scanMode = ScannerActivity.EXTRA_SCAN_MODE_0;
                    ScannerActivity.gotoActivity(getActivity(),
                            false, laserMode, scanMode, false
                            , false, false);
                }
            }
        });

        top_center_text = view.findViewById(R.id.top_center_text);
        top_center_text.setText("51钱包");

        top_left = view.findViewById(R.id.top_left);
        top_left.setVisibility(View.INVISIBLE);
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


        iv_add = view.findViewById(R.id.iv_add);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddAssetsActivity.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {
//            if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
//                if (data != null) {
//                    String stringExtra = data.getStringExtra(Scanner.Scan.RESULT);
//                    Toast.makeText(getActivity(),stringExtra, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {
            if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
                if (data != null) {
                    String stringExtra = data.getStringExtra(Scanner.Scan.RESULT);
                    Toast.makeText(getActivity(),stringExtra, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted准许
                Toast.makeText(getActivity(),"已获得授权！",Toast.LENGTH_SHORT).show();
            } else {
                // Permission Denied拒绝
                Toast.makeText(getActivity(),"未获得授权！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
