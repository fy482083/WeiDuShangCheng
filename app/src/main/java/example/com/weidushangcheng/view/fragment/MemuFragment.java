package example.com.weidushangcheng.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.QuanZiBean;
import example.com.weidushangcheng.presenter.QuanPresenter;
import example.com.weidushangcheng.view.adapter.QuanAdapter;
import example.com.weidushangcheng.view.interfaces.IQuanView;


public class MemuFragment extends Fragment implements IQuanView {

    @BindView(R.id.recyclerview_quan)
    XRecyclerView mRecyclerviewQuan;
    private View view;
    private QuanPresenter quanPresenter;
    int userId;
    String sessionId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.memu_layout, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        quanPresenter = new QuanPresenter();
        quanPresenter.setView(this);
        quanPresenter.getquandata();
    }

    @Override
    public void quansuccess(Object data) {
        QuanZiBean quanZiBean = (QuanZiBean) data;
        Log.e("quanBean", "quansuccess: " + quanZiBean);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerviewQuan.setLayoutManager(linearLayoutManager);
        QuanAdapter quanAdapter = new QuanAdapter(getActivity());
        mRecyclerviewQuan.setAdapter(quanAdapter);
        quanAdapter.setdata(quanZiBean);
    }
}
