package example.com.weidushangcheng.view.activity;

import android.app.ActionBar;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.OneBean;
import example.com.weidushangcheng.model.bean.SearchBean;
import example.com.weidushangcheng.model.bean.TwoBean;
import example.com.weidushangcheng.presenter.OnePresenter;
import example.com.weidushangcheng.presenter.SearchPresenter;
import example.com.weidushangcheng.presenter.TwoPresenter;
import example.com.weidushangcheng.view.adapter.PopOneAdapter;
import example.com.weidushangcheng.view.adapter.SearchAdapter;
import example.com.weidushangcheng.view.adapter.TwoAdapter;
import example.com.weidushangcheng.view.interfaces.ISearchView;

public class SearchActivity extends AppCompatActivity implements ISearchView {

    @BindView(R.id.img_left_seaech)
    ImageView mImgLeftSeaech;
    @BindView(R.id.img_cuo)
   ImageView mimg_cuo;
    @BindView(R.id.text_suo_search)
    EditText mTextSuoSearch;
    @BindView(R.id.text_cuo)
    TextView mtextcuo;
    @BindView(R.id.img_cha_search)
    ImageView mImgChaSearch;
    @BindView(R.id.img_rightsearch)
    ImageView mImgRightsearch;
    @BindView(R.id.RelativeLayout)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.recyclerview_serch)
    XRecyclerView mRecyclerviewSerch;
    private SearchPresenter searchPresenter;
    private SearchBean searchBean;
    private List<SearchBean.ResultBean> result;
    private List<SearchBean.ResultBean> list;
    private RecyclerView pop_one;
    private RecyclerView pop_two;
    private PopupWindow mPopWindow;
    private OnePresenter onePresenter;
    private TwoPresenter twoPresenter;
    String firstCategoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        initData();
        initPoPupwindow();
    }

    private void initData() {
        //搜索
        searchPresenter = new SearchPresenter();
        searchPresenter.setView(this);
    //one
        onePresenter = new OnePresenter();
        onePresenter.setView(this);
        onePresenter.getOneData();
    //two
        twoPresenter = new TwoPresenter();
        twoPresenter.setView(this);



        //点击事件  点击删除
        mImgChaSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextSuoSearch.setText("");

            }
        });
    //点击搜索
        mImgRightsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                String name = mTextSuoSearch.getText().toString();
                searchPresenter.getSearchData(name);
            }
        });
        //点击分类
        mImgLeftSeaech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.showAsDropDown(v);
            }
        });
    }

    private void initPoPupwindow() {
        //新建initPoPupwindow
        View viewPop = View.inflate(SearchActivity.this, R.layout.pop_layout, null);
        //拿子布局中的ID
        pop_one = viewPop.findViewById(R.id.pop_one);
        pop_one.getBackground().setAlpha(200);//渐变
        pop_two = viewPop.findViewById(R.id.pop_two);
        pop_two.getBackground().setAlpha(180);
        mPopWindow = new PopupWindow(viewPop, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        //设置焦点
        mPopWindow.setFocusable(true);
        //设置是否可触摸
        mPopWindow.setTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);


    }
    @Override
    public void SearchSuccess(Object data) {
        searchBean = (SearchBean) data;
        result = searchBean.getResult();
        if ( result != null) {
            list.addAll(result);
        } else {
            mimg_cuo.setVisibility(View.VISIBLE);
            mtextcuo.setVisibility(View.VISIBLE);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerviewSerch.setLayoutManager(gridLayoutManager);
        SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this);
        mRecyclerviewSerch.setAdapter(searchAdapter);
        searchAdapter.setdata(list);
    }

    @Override
    public void OneSuccess(Object data) {
        OneBean oneBean = (OneBean) data;
        Log.e("oneBean", "TwoSuccess: "+oneBean.getResult().size() );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        pop_one.setLayoutManager(linearLayoutManager);
        PopOneAdapter popOneAdapter = new PopOneAdapter(SearchActivity.this);
        pop_one.setAdapter(popOneAdapter);
        popOneAdapter.setdata(oneBean);
        popOneAdapter.topOneCall(new PopOneAdapter.TopOneCall() {
            @Override
            public void getTopOne(String topOneCall) {
               twoPresenter.getTwoData(topOneCall);
            }
        });

    }

    @Override
    public void TwoSuccess(Object data) {
        TwoBean twoBean = (TwoBean) data;
        Log.e("twoBean", "TwoSuccess: "+twoBean );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        pop_two.setLayoutManager(linearLayoutManager);
        TwoAdapter twoAdapter = new TwoAdapter(SearchActivity.this);
        pop_two.setAdapter(twoAdapter);
        twoAdapter.setData(twoBean);

        twoAdapter.topTwoCall(new TwoAdapter.TopTwoCall() {
            @Override
            public void getTopTwo(String topTwoCall) {
                mTextSuoSearch.setText(topTwoCall);
                mPopWindow.dismiss();
            }
        });
    }
}
