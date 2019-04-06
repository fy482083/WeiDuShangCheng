package example.com.weidushangcheng.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.Deng;
import example.com.weidushangcheng.presenter.MainPresenter;
import example.com.weidushangcheng.view.interfaces.IMainView;

public class MainActivity extends BaseActivity implements IMainView {


    @BindView(R.id.edit_zhang)
    EditText mEditZhang;
    @BindView(R.id.edit_mima)
    EditText mEditMima;
    @BindView(R.id.checkbox_mima)
    CheckBox mCheckboxMima;
    @BindView(R.id.text_zhuce)
    TextView mTextZhuce;
    @BindView(R.id.btn_deng)
    Button mBtnDeng;
    private MainPresenter mainPresenter;
    private String zhang;
    private String pwd;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        sp = getSharedPreferences("sp", MODE_PRIVATE);
        boolean key = sp.getBoolean("key", false);
        mCheckboxMima.setChecked(key);
        if (key) {
            String name = sp.getString("name", "");
            mEditZhang.setText(name);
            String pwd = sp.getString("pwd", "");
            mEditMima.setText(pwd);
        }

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);
    }

    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDettchView();
    }


    @Override
    public void onSuccess(Object data) {

        if (data instanceof Deng) {
            Deng deng = (Deng) data;
            SharedPreferences.Editor edit = sp.edit();
            if (mCheckboxMima.isChecked()) {
                edit.putBoolean("key", true);
                edit.putString("name", zhang);
                edit.putString("pwd", pwd);
            } else {
                edit.putBoolean("key", false);
            }
            if (deng.getMessage().equals("登录成功")) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);

                String sessionId = deng.getResult().getSessionId();
                int userId = deng.getResult().getUserId();
                  edit.putString("userId",userId+"");
                  edit.putString("sessionId",sessionId);



            }
            edit.commit();
        }
    }
    @OnClick({R.id.btn_deng,R.id.text_zhuce})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_deng:
                zhang = mEditZhang.getText().toString();
                pwd = mEditMima.getText().toString();
                if (TextUtils.isEmpty(zhang) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "账号密码必能为空", Toast.LENGTH_LONG).show();
                } else {
                    mainPresenter.getDengData(zhang, pwd);
                }
                break;
            case R.id.text_zhuce:
                Intent intent = new Intent(MainActivity.this, ZhuCeActivity.class);
                startActivity(intent);
                break;
        }
    }

}
