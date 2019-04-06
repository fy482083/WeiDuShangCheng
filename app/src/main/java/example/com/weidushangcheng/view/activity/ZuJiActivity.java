package example.com.weidushangcheng.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.ZuJiBean;
import example.com.weidushangcheng.presenter.ZuJiPresenter;
import example.com.weidushangcheng.view.adapter.ZuJiAdapter;
import example.com.weidushangcheng.view.interfaces.IZuJiView;

public class ZuJiActivity extends BaseActivity implements IZuJiView {

    @BindView(R.id.recyclerview_zuji)
    XRecyclerView mRecyclerviewZuji;
    private ZuJiPresenter zuJiPresenter;
    int userId;
    String sessionId;
    private List<ZuJiBean.ResultBean> zuJiBeanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_zu_ji;
    }

    @Override
    protected void initView() {

    }

    protected void initData() {
        zuJiPresenter = new ZuJiPresenter();
        zuJiPresenter.setView(this);
        zuJiPresenter.getzujidata();

    }

    @Override
    public void zujisuccess(Object data) {
        ZuJiBean zuJiBean = (ZuJiBean) data;
        zuJiBeanResult = zuJiBean.getResult();
        Log.e("zuJiBean", "zujisuccess: " + zuJiBean);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerviewZuji.setLayoutManager(staggeredGridLayoutManager);
        ZuJiAdapter zuJiAdapter = new ZuJiAdapter(ZuJiActivity.this,zuJiBeanResult);
        Log.e("zuJiAdapter", "zujisuccess: "+zuJiAdapter );
        mRecyclerviewZuji.setAdapter(zuJiAdapter);


    }
}
