package example.com.weidushangcheng.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.view.fragment.HomeFragment;
import example.com.weidushangcheng.view.fragment.MemuFragment;
import example.com.weidushangcheng.view.fragment.MineFragment;
import example.com.weidushangcheng.view.fragment.SecondFragment;
import example.com.weidushangcheng.view.fragment.ShopCarFragment;

public class ShowActivity extends BaseActivity {

    @BindView(R.id.Frame_layout)
    FrameLayout mFrameLayout;
    @BindView(R.id.show_home)
    RadioButton mShowHome;
    @BindView(R.id.show_menu)
    RadioButton mShowMenu;
    @BindView(R.id.show_shop_car)
    RadioButton mShowShopCar;
    @BindView(R.id.show_second)
    RadioButton mShowSecond;
    @BindView(R.id.show_mine)
    RadioButton mShowMine;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    @BindView(R.id.show_ishop)
    ImageView mShowIshop;
    private HomeFragment homeFragment;
    private MemuFragment memuFragment;
    private ShopCarFragment shopCarFragment;
    private SecondFragment secondFragment;
    private MineFragment mineFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        homeFragment = new HomeFragment();
        memuFragment = new MemuFragment();
        shopCarFragment = new ShopCarFragment();
        secondFragment = new SecondFragment();
        mineFragment = new MineFragment();
        //开启事务
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //默认添加
        transaction.add(R.id.Frame_layout,homeFragment);
        transaction.commit();
        //切换
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.show_home:
                        fragmentTransaction.replace(R.id.Frame_layout,homeFragment);
                        break;
                    case R.id.show_menu:
                        fragmentTransaction.replace(R.id.Frame_layout,memuFragment);
                        break;
                    case R.id.show_shop_car:
                        fragmentTransaction.replace(R.id.Frame_layout,shopCarFragment);
                        break;
                    case R.id.show_second:
                        fragmentTransaction.replace(R.id.Frame_layout,secondFragment);
                        break;
                    case R.id.show_mine:
                        fragmentTransaction.replace(R.id.Frame_layout,mineFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
