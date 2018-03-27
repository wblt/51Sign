package cn.tthud.a51sign.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tthud.a51sign.R;
import cn.tthud.a51sign.zxing.encode.CodeCreator;

public class ReceivAddressActivity extends AppCompatActivity {

    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.et_number)
    EditText et_number;
    @BindView(R.id.contentIv)
    ImageView contentIv;
    @BindView(R.id.btn_copy_address)
    Button btn_copy_address;

    @BindView(R.id.top_right_text)
    TextView top_right_text;
    @BindView(R.id.top_center_text)
    TextView top_center_text;
    @BindView(R.id.top_left)
    ImageButton top_left;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiv_address);
        ButterKnife.bind(this);
        inittopbar();
        initview();
    }

    private void inittopbar() {
        top_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        top_right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReceivAddressActivity.this, "开发中", Toast.LENGTH_SHORT).show();
            }
        });
        top_right_text.setText("分享");
        top_center_text.setText("收款码");
    }


    private void initview() {
        String contentEtString = "0x94F9c5579Eb813065956E3832Ac4f6ff44439DF0";
        if (TextUtils.isEmpty(contentEtString)) {
            Toast.makeText(ReceivAddressActivity.this, "contentEtString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap bitmap = null;
        try {
            bitmap = CodeCreator.createQRCode(contentEtString, 1000, 1000, null);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        if (bitmap != null) {
            contentIv.setImageBitmap(bitmap);
        }
        tv_address.setText("0x94F9c5579Eb813065956E3832Ac4f6ff44439DF0");

    }

    @OnClick({R.id.btn_copy_address})
    void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_copy_address:
                Toast.makeText(ReceivAddressActivity.this, "开发中", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

}
