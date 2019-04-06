package example.com.weidushangcheng.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.weidushangcheng.R;

public class SecondFragment extends Fragment {

    @BindView(R.id.img_dingdan)
    ImageView mImgDingdan;
    @BindView(R.id.img_daifukuan)
    ImageView mImgDaifukuan;
    @BindView(R.id.img_daishouhuo)
    ImageView mImgDaishouhuo;
    @BindView(R.id.img_daipingjia)
    ImageView mImgDaipingjia;
    @BindView(R.id.img_yiwancheng)
    ImageView mImgYiwancheng;
    @BindView(R.id.linear_layout)
    LinearLayout mLinearLayout;
    @BindView(R.id.text_dingdan)
    TextView mTextDingdan;
    @BindView(R.id.text_daifukuan)
    TextView mTextDaifukuan;
    @BindView(R.id.text_daishouhuo)
    TextView mTextDaishouhuo;
    @BindView(R.id.text_daipingjia)
    TextView mTextDaipingjia;
    @BindView(R.id.text_yiwancheng)
    TextView mTextYiwancheng;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
