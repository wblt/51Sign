package cn.tthud.a51sign.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import butterknife.BindView;
import cn.tthud.a51sign.R;
import cn.tthud.a51sign.zxing.encode.CodeCreator;

public class ReceivAddressActivity extends AppCompatActivity {

    @BindView(R.id.tv_address)
    private TextView tv_address;
    @BindView(R.id.et_number)
    private EditText et_number;
    @BindView(R.id.contentIv)
    private ImageView contentIv;
    @BindView(R.id.btn_copy_address)
    private Button btn_copy_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiv_address);
        initview();
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


}
