package example.com.weidushangcheng.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.ZhuCe;
import example.com.weidushangcheng.presenter.MainPresenter;
import example.com.weidushangcheng.view.interfaces.IMainView;

public class ZhuCeActivity extends BaseActivity implements IMainView {

    @BindView(R.id.edit_zhang_zhu)
    EditText mEditZhangZhu;
    @BindView(R.id.edit_mima_zhu)
    EditText mEditMimaZhu;
    @BindView(R.id.btn_deng_zhu)
    Button mBtnDengZhu;
    private String zhang_zhu;
    private String mima_zhu;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_zhu_ce;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_deng_zhu)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_deng_zhu:
                zhang_zhu = mEditZhangZhu.getText().toString();
               mima_zhu = mEditMimaZhu.getText().toString();
               if (TextUtils.isEmpty(zhang_zhu)||TextUtils.isEmpty(mima_zhu)){
                   Toast.makeText(ZhuCeActivity.this, "账号密码必能为空", Toast.LENGTH_LONG).show();
               }else {
                    mainPresenter.getZhuData(zhang_zhu,mima_zhu);
                   Intent intent = new Intent(ZhuCeActivity.this, MainActivity.class);
                   startActivity(intent);
               }
                break;
        }
    }


    @Override
    public void onSuccess(Object data) {
         if (data instanceof ZhuCe){
             ZhuCe zhuCe = (ZhuCe) data;
             if (zhuCe.getMessage().equals("注册成功")){
                 Intent intent = new Intent(ZhuCeActivity.this, MainActivity.class);
                  startActivity(intent);
             }
         }
    }
}
