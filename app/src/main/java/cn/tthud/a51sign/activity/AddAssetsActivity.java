package cn.tthud.a51sign.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.tthud.a51sign.R;
import cn.tthud.a51sign.adapter.AssetsAdapter;

public class AddAssetsActivity extends AppCompatActivity {

    private RecyclerView recyc_list;
    private List<String> list;
    private AssetsAdapter adapter;
    private ImageButton top_right_icon;
    private TextView top_center_text;
    private ImageButton top_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assets);
        inittopbar();
        initView();
    }

    private void inittopbar() {
        top_right_icon = findViewById(R.id.top_right_icon);
        top_right_icon.setImageResource(R.mipmap.sousuo);
        top_right_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddAssetsActivity.this,"开发中", Toast.LENGTH_SHORT).show();
            }
        });

        top_center_text = findViewById(R.id.top_center_text);
        top_center_text.setText("添加新资产");
        top_left = findViewById(R.id.top_left);
    }

    private void initView() {
        recyc_list = findViewById(R.id.recyc_list);
        list = new ArrayList<>();
        list.add("BTC");
        list.add("ETH");
        list.add("EOS");
        list.add("OMG");
        list.add("BTC");
        list.add("ETH");
        list.add("EOS");
        list.add("OMG");
        list.add("BTC");
        list.add("ETH");
        list.add("EOS");
        list.add("OMG");
        recyc_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssetsAdapter(list,this);
        recyc_list.setAdapter(adapter);
    }
}
