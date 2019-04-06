package example.com.weidushangcheng.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.ChaXunCarBean;
import example.com.weidushangcheng.presenter.CarPresenter;
import example.com.weidushangcheng.view.adapter.CarAdapter;
import example.com.weidushangcheng.view.interfaces.IBaseView;
import example.com.weidushangcheng.view.interfaces.ICarView;

public class ShopCarFragment extends Fragment implements ICarView {

    @BindView(R.id.recyclerview_car)
    RecyclerView mRecyclerviewCar;
    @BindView(R.id.checkbox_quan)
    CheckBox mCheckboxQuan;
    @BindView(R.id.text_price)
    TextView mTextPrice;
    @BindView(R.id.btn_price)
    Button mBtnPrice;
    private View view;
    private CarPresenter carPresenter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_car_layout, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }
    private void initData() {
        carPresenter = new CarPresenter();
        carPresenter.setView(this);
        carPresenter.getcardata();
    }
    @Override
    public void carsuccess(Object data) {
        ChaXunCarBean chaXunCarBean = (ChaXunCarBean) data;
        String message = ((ChaXunCarBean) data).getMessage();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        Log.e("chaXunCarBean", "carsuccess: "+chaXunCarBean );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerviewCar.setLayoutManager(linearLayoutManager);
        CarAdapter carAdapter = new CarAdapter(getActivity());
        //checkbox
        carAdapter.setCheckBox(mCheckboxQuan);
        //计算总价
        carAdapter.setTextView(mTextPrice);
        mRecyclerviewCar.setAdapter(carAdapter);
        carAdapter.setData(chaXunCarBean);
    }
}
