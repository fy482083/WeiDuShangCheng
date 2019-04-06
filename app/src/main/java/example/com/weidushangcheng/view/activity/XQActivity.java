package example.com.weidushangcheng.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.AddBuyBean;

import example.com.weidushangcheng.model.bean.ChaXunCarBean;
import example.com.weidushangcheng.model.bean.TongBuBean;
import example.com.weidushangcheng.model.bean.XQBean;
import example.com.weidushangcheng.presenter.CarPresenter;
import example.com.weidushangcheng.presenter.TongPresenter;
import example.com.weidushangcheng.presenter.XQPresenter;
import example.com.weidushangcheng.view.interfaces.ICarView;
import example.com.weidushangcheng.view.interfaces.ITongBuView;
import example.com.weidushangcheng.view.interfaces.IXQView;

public class XQActivity extends AppCompatActivity implements IXQView,ITongBuView,ICarView{


    @BindView(R.id.xq_text_back)
    ImageView mXqTextBack;
    @BindView(R.id.XQxbanner)
    XBanner mXQxbanner;
    @BindView(R.id.xq_text_price)
    TextView mXqTextPrice;
    @BindView(R.id.xq_text_jian)
    TextView mXqTextJian;
    @BindView(R.id.xq_text_xiang)
    TextView mXqTextXiang;
    @BindView(R.id.xq_text_xiangqing)
    TextView mXqTextXiangqing;
    @BindView(R.id.xq_iamg_xiang)
    ImageView mXqIamgXiang;
    @BindView(R.id.xq_text_chanpin)
    TextView mXqTextChanpin;
    @BindView(R.id.xq_text_chanpinxiang)
    TextView mXqTextChanpinxiang;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.img_car_buy)
    ImageView mImgCarBuy;
    @BindView(R.id.img_buy)
    ImageView mImgBuy;
    private XQPresenter xqPresenter;
    private String ids;
    private String picture;
    private List<String> list;
    private XQBean xqBean;
    private TongPresenter tongPresenter;
    private CarPresenter carPresenter;
    private CarPresenter carPresenter1;
    private ArrayList<AddBuyBean> lists;
    private int i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ids = intent.getStringExtra("mid");
        Log.i("qqqqqqq", "onCreate: " + ids);
        initData();
        mImgCarBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carPresenter1.getcardata();

            }
        });
    }

    private void initData() {
        xqPresenter = new XQPresenter();
        xqPresenter.setView(this);
        xqPresenter.getxq(ids);

        tongPresenter = new TongPresenter();
        tongPresenter.setView(this);

        carPresenter1 = new CarPresenter();
        carPresenter1.setView(this);


    }


    @Override
    public void onXQSuccess(Object data) {
        xqBean = (XQBean) data;
        //轮播截取字符串
        picture = xqBean.getResult().getPicture();
        list = Arrays.asList(picture.split(","));
        //webview
        String details = xqBean.getResult().getDetails();
        mWebView.loadDataWithBaseURL(null, details, "text/html", "UTF-8", null);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebViewClient(new WebViewClient());
        xqData();
    }

    private void xqData() {
        //详情轮播
        mXQxbanner.setData(list, null);
        mXQxbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(XQActivity.this).load(list.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效
        mXQxbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        mXQxbanner.setPageChangeDuration(1000);

        mXqTextPrice.setText("价格￥：" + xqBean.getResult().getPrice() + "");
        mXqTextJian.setText("已售出" + xqBean.getResult().getCommentNum() + "件" + "");
        mXqTextXiang.setText("产品：" + xqBean.getResult().getCategoryName());
        mXqTextXiangqing.setText("产品详情：" + xqBean.getResult().getCommodityName());
        Glide.with(XQActivity.this).load(xqBean.getResult().getPicture().split(",")[0]).into(mXqIamgXiang);
        mXqTextChanpinxiang.setText("    " + xqBean.getResult().getDescribe());
    }

    @OnClick({R.id.xq_text_back, R.id.img_car_buy, R.id.img_buy})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.xq_text_back:
                Intent intent = new Intent(XQActivity.this, ShowActivity.class);
                startActivity(intent);
                break;
            case R.id.img_car_buy:
                break;
            case R.id.img_buy:
                break;
        }
    }

    @Override
    public void tongbusuccess(Object data) {
        Log.i("fy", "tongbusuccess: "+data);
        TongBuBean tongBuBean = (TongBuBean) data;
        if (tongBuBean.getStatus().equals("0000")){
            Toast.makeText(XQActivity.this,"同步成功",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(XQActivity.this,"同步失败",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void carsuccess(Object data) {
        ChaXunCarBean chaXunCarBean = (ChaXunCarBean) data;
        Log.e("chaXunCarBean", "carsuccess: "+chaXunCarBean );
        List<ChaXunCarBean.ResultBean> result = chaXunCarBean.getResult();
        //创建集合
        lists = new ArrayList<>();

        //同步购物车步骤
        // 1 . for循环把查询购物车的数据添加到bean类集合里面
        for (int i = 0; i < result.size(); i++) {
            ChaXunCarBean.ResultBean resultBean = result.get(i);
            lists.add(new AddBuyBean(resultBean.getCommodityId(),resultBean.getCount()));
        }
        //同步购物车方法
        addCar(lists);
    }

    private void addCar(List<AddBuyBean> listCar) {
        for (int i = 0; i< listCar.size(); i++){
            //判断如果加入商品的id和集合里有相同的就count+1
            i1 = Integer.parseInt(ids);
            if (i1 == listCar.get(i).getCommodityId()){
                int count = listCar.get(i).getCount();
                count++;
                listCar.get(i).setCount(count);
                break;
                //如果遍历完毕没有相同的商品，就把当前的商品加入到购物车
            }else if (i== listCar.size()-1){
                listCar.add(new AddBuyBean(i1,1));
                break;
            }
        }//for

        //当购物车一条都没有的话 , 执行这个判断
        if (0== listCar.size()){
            listCar.add(new AddBuyBean(i1,1));
        }
        Gson gson = new Gson();
        String s = gson.toJson(listCar);
        tongPresenter.getTongData(s);
    }
}
