package example.com.weidushangcheng.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.weidushangcheng.R;

public class MyActivity extends AppCompatActivity {

    @BindView(R.id.text_my_tou)
    TextView mTextMyTou;
    @BindView(R.id.img_my_touxiang)
    ImageView mImgMyTouxiang;
    @BindView(R.id.text_my_nicheng)
    TextView mTextMyNicheng;
    @BindView(R.id.text_my_right_nicheng)
    TextView mTextMyRightNicheng;
    @BindView(R.id.text_my_pwd)
    TextView mTextMyPwd;
    @BindView(R.id.text_my_right_pwd)
    TextView mTextMyRightPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.text_my_tou, R.id.img_my_touxiang, R.id.text_my_nicheng, R.id.text_my_right_nicheng, R.id.text_my_pwd, R.id.text_my_right_pwd})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.text_my_tou:
                break;
            case R.id.img_my_touxiang:
                break;
            case R.id.text_my_nicheng:
                break;
            case R.id.text_my_right_nicheng:
                break;
            case R.id.text_my_pwd:
                break;
            case R.id.text_my_right_pwd:
                break;
        }
    }
}
