package example.com.weidushangcheng.view.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.api.API;
import example.com.weidushangcheng.model.bean.SearchBean;
import example.com.weidushangcheng.model.bean.ShowBean;

import example.com.weidushangcheng.presenter.HomePresenter;
import example.com.weidushangcheng.view.activity.SearchActivity;
import example.com.weidushangcheng.view.activity.XQActivity;
import example.com.weidushangcheng.view.adapter.ShowAdapter;
import example.com.weidushangcheng.view.interfaces.IHomeView;

public class HomeFragment extends Fragment implements IHomeView {

    @BindView(R.id.img_cha)
    ImageView mImgCha;
    private View view;
    private ImageView mImgLeft;
    /**
     * 请输入要搜索的内容
     */
    private EditText mTextSuo;
    private ImageView mImgRight;
    private HomePresenter homePresenter;
    private XRecyclerView mRecyclerview;
    private PopupWindow popupWindow;
    private RecyclerView pop_one;
    private RecyclerView pop_two;
    private PopupWindow mPopWindow;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_layout, container, false);
        //注册
        EventBus.getDefault().register(this);
        initView();
        initData();
        return view;

    }


    private void initView() {

        mImgLeft = (ImageView) view.findViewById(R.id.img_left);
        mTextSuo = (EditText) view.findViewById(R.id.text_suo);
        mImgRight = (ImageView) view.findViewById(R.id.img_right);
        mRecyclerview = (XRecyclerView) view.findViewById(R.id.recyclerview);
        mImgCha = (ImageView) view.findViewById(R.id.img_cha);


    mTextSuo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);

        }
    });

    }


    private void initData() {
        homePresenter = new HomePresenter();
        homePresenter.setView(this);
        homePresenter.getShowData();
    }

    //处理事件
    @Subscribe
    public void Event(String message) {
        Intent intent = new Intent(getActivity(), XQActivity.class);
        intent.putExtra("mid", message);
        startActivity(intent);
        Toast.makeText(getActivity(), message + "", Toast.LENGTH_LONG).show();
    }



    @Override
    public void ShowSuccess(Object data) {
        final ShowBean showBean = (ShowBean) data;
        Log.e("my", "ShowSuccess: " + showBean);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        ShowAdapter showAdapter = new ShowAdapter(getActivity(), showBean);
        mRecyclerview.setAdapter(showAdapter);

        //设置下拉加载上拉刷新监听  假的  加载不出数据
//        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecyclerview.refreshComplete();
//                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
//                    }
//                },2000);
//
//            }
//
//            @Override
//            public void onLoadMore() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecyclerview.loadMoreComplete();
//                        Toast.makeText(getActivity(), "加载完成", Toast.LENGTH_SHORT).show();
//                    }
//                },2000);
//            }
//        });


    }

    @Override
    public void onesuccess(Object data) {

    }

    @Override
    public void twosuccess(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除注册
        EventBus.getDefault().unregister(this);
        homePresenter.onDettchView();
    }



}
