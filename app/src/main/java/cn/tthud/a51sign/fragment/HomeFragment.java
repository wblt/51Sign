package cn.tthud.a51sign.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.WriterException;

import java.util.ArrayList;
import java.util.List;

import cn.tthud.a51sign.AddAssetsActivity;
import cn.tthud.a51sign.R;
import cn.tthud.a51sign.adapter.HomeAdapter;
import cn.tthud.a51sign.zxing.android.CaptureActivity;
import cn.tthud.a51sign.zxing.bean.ZxingConfig;
import cn.tthud.a51sign.zxing.common.Constant;
import cn.tthud.a51sign.zxing.encode.CodeCreator;

import static android.app.Activity.RESULT_OK;

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
    private int REQUEST_CODE_SCAN = 111;
    private LinearLayout ll_code_address;

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
                    Intent intent = new Intent(getActivity(), CaptureActivity.class);
                                /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
                                * 也可以不传这个参数
                                * 不传的话  默认都为默认不震动  其他都为true
                                * */
                    ZxingConfig config = new ZxingConfig();
                    config.setPlayBeep(true);
                    config.setShake(true);
                    config.setShowbottomLayout(false);
                    intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
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

        ll_code_address = view.findViewById(R.id.ll_code_address);
        ll_code_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "开发中", Toast.LENGTH_SHORT).show();
//                String contentEtString = "dfjdfkjdfkdjfkdjfdkjfkdfjd";
//                if (TextUtils.isEmpty(contentEtString)) {
//                    Toast.makeText(getActivity(), "contentEtString不能为空", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Bitmap bitmap = null;
//                try {
//                    bitmap = CodeCreator.createQRCode(contentEtString, 1000, 1000, null);
//                } catch (WriterException e) {
//                    e.printStackTrace();
//                }
//                if (bitmap != null) {
//                    contentIv.setImageBitmap(bitmap);
//                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Toast.makeText(getActivity(),content, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_SCAN) {
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
