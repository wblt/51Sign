package cn.tthud.a51sign;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apaches.commons.codec.binary.Hex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tthud.a51sign.bitaddress.BitAddress;
import cn.tthud.a51sign.ecdsa.ECDSA;
import cn.tthud.a51sign.fragment.HomeFragment;
import cn.tthud.a51sign.fragment.MineFragment;
import cn.tthud.a51sign.helper.BIP39;
import cn.tthud.a51sign.helper.BtcPrivateKeyConverter;
import cn.tthud.a51sign.helper.ByteUtils;
import cn.tthud.a51sign.helper.ResourcesUtil;

import static junit.framework.Assert.assertTrue;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_home) TextView tv_home;

    @BindView(R.id.tv_mine) TextView tv_mine;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        test();

        initView();
        tv_home.performClick();
//        TextView address = (TextView) findViewById(R.id.adress);
//        TextView gerorateo = (TextView) findViewById(R.id.gerorateo);
//        TextView sign_action = (TextView) findViewById(R.id.sign_action);
//        TextView sign_data = (TextView) findViewById(R.id.sign_data);
//
//        gerorateo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BitAddress s = new BitAddress();
//                String privateKey = "5502767ae95def4039ef1ee9f4ce46d25b0b1b240621e8b9d7743afa2a3eb5ae";//ECDSA.generatePrivateKey();//
//                String publicKey = ECDSA.computePublicKeyWithoutCompressed(privateKey);
//                System.out.println(publicKey);
//                String result = s.calculateAddress(publicKey);
//                System.out.println("0x"+result);
//                address.setText("钱包地址："+"0x"+result);
//            }
//        });
//
//        sign_action.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ECDSA test = new ECDSA();
//                // 生成随机数私钥
//                String privateKey0 = test.generatePrivateKey();
//
//                // 将私钥保存到本地
////		test.savePrivateKeyToLocal("E:/privateKey.dat", privateKey);
//
//                // 从本地读取私钥
//                String privateKey = "5502767ae95def4039ef1ee9f4ce46d25b0b1b240621e8b9d7743afa2a3eb5ae";//test.loadPrivateKeyFormLocal("E:/privateKey.dat");//
//
//                // 根据私钥计算公钥
//                String publicKey = test.computePublicKey(privateKey);
//                System.out.println("长度："+publicKey.length()+":"+publicKey);
//
//                // 根据私钥计算非压缩公钥
//                String publicKey2 = test.computePublicKeyWithoutCompressed(privateKey);
//                System.out.println("长度："+publicKey2.length()+":"+publicKey2);
//
//                String data = "Hello ECC";
//                // 生成数字签名
//                String signdata = test.sign(data.getBytes(), privateKey);
//                // 验证签名
//                boolean result = test.verify(data.getBytes(), signdata, publicKey);
//
//                System.out.println(result);
//
//                sign_data.setText("签名数据："+signdata + "  "+  "验证签名"+result);
//
//            }
//        });
    }


    private void initView() {

        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.fg_content,mineFragment)
                .add(R.id.fg_content,homeFragment)
                .hide(homeFragment)
                .hide(mineFragment)
                .commitAllowingStateLoss();

        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected();
                tv_home.setSelected(true);
                FragmentTransaction fTransaction = getFragmentManager().beginTransaction();
                fTransaction.hide(mineFragment);
                fTransaction.show(homeFragment);
                fTransaction.commitAllowingStateLoss();
            }
        });

        tv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelected();
                tv_mine.setSelected(true);

                FragmentTransaction fTransaction = getFragmentManager().beginTransaction();
                fTransaction.hide(homeFragment);
                fTransaction.show(mineFragment);
                fTransaction.commitAllowingStateLoss();
            }
        });
    }
    
    private void setSelected() {
        tv_home.setSelected(false);
        tv_mine.setSelected(false);
    }

    /**
     * bip39test
     */
    private void test() {
        BIP39 bip39 = new BIP39(this);
        byte [] random  = SecureRandom.getSeed(16);
        String e = bip39.encode(random);
        Log.e("tag","");
        byte [] data = bip39.decode(e);
        Log.e("tag","");
    }

}
