package cn.tthud.a51sign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.tthud.a51sign.bitaddress.BitAddress;
import cn.tthud.a51sign.ecdsa.ECDSA;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView address = (TextView) findViewById(R.id.adress);
        TextView gerorateo = (TextView) findViewById(R.id.gerorateo);
        TextView sign_action = (TextView) findViewById(R.id.sign_action);
        TextView sign_data = (TextView) findViewById(R.id.sign_data);

        gerorateo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitAddress s = new BitAddress();
                String privateKey = "5502767ae95def4039ef1ee9f4ce46d25b0b1b240621e8b9d7743afa2a3eb5ae";//ECDSA.generatePrivateKey();//
                String publicKey = ECDSA.computePublicKeyWithoutCompressed(privateKey);
                System.out.println(publicKey);
                String result = s.calculateAddress(publicKey);
                System.out.println("0x"+result);
                address.setText("钱包地址："+"0x"+result);
            }
        });

        sign_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ECDSA test = new ECDSA();
                // 生成随机数私钥
                String privateKey0 = test.generatePrivateKey();

                // 将私钥保存到本地
//		test.savePrivateKeyToLocal("E:/privateKey.dat", privateKey);

                // 从本地读取私钥
                String privateKey = "5502767ae95def4039ef1ee9f4ce46d25b0b1b240621e8b9d7743afa2a3eb5ae";//test.loadPrivateKeyFormLocal("E:/privateKey.dat");//

                // 根据私钥计算公钥
                String publicKey = test.computePublicKey(privateKey);
                System.out.println("长度："+publicKey.length()+":"+publicKey);

                // 根据私钥计算非压缩公钥
                String publicKey2 = test.computePublicKeyWithoutCompressed(privateKey);
                System.out.println("长度："+publicKey2.length()+":"+publicKey2);

                String data = "Hello ECC";
                // 生成数字签名
                String signdata = test.sign(data.getBytes(), privateKey);
                // 验证签名
                boolean result = test.verify(data.getBytes(), signdata, publicKey);

                System.out.println(result);

                sign_data.setText("签名数据："+signdata + "  "+  "验证签名"+result);

            }
        });


    }
}