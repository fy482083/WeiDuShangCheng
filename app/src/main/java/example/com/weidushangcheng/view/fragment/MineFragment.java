package example.com.weidushangcheng.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.view.activity.MyActivity;
import example.com.weidushangcheng.view.activity.ZuJiActivity;

public class MineFragment extends Fragment {


    @BindView(R.id.img_one)
    ImageView mImgOne;
    @BindView(R.id.ed_one)
    EditText mEdOne;
    @BindView(R.id.img_two)
    ImageView mImgTwo;
    @BindView(R.id.ed_two)
    EditText mEdTwo;
    @BindView(R.id.img_three)
    ImageView mImgThree;
    @BindView(R.id.ed_three)
    EditText mEdThree;
    @BindView(R.id.img_fore)
    ImageView mImgFore;
    @BindView(R.id.ed_fore)
    EditText mEdFore;
    @BindView(R.id.img_five)
    ImageView mImgFive;
    @BindView(R.id.ed_five)
    EditText mEdFive;
    @BindView(R.id.relative)
    RelativeLayout mRelative;
    @BindView(R.id.img_tou)
    ImageView mImgTou;
    @BindView(R.id.text_name)
    TextView mTextName;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, container, false);
        ButterKnife.bind(this, view);
        mImgThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ZuJiActivity.class);
                startActivity(intent);
            }
        });
        mImgOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
