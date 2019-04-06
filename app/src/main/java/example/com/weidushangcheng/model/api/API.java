package example.com.weidushangcheng.model.api;

import example.com.weidushangcheng.model.bean.BannerBean;
import example.com.weidushangcheng.model.bean.ChaXunCarBean;
import example.com.weidushangcheng.model.bean.Deng;
import example.com.weidushangcheng.model.bean.OneBean;
import example.com.weidushangcheng.model.bean.QuanZiBean;
import example.com.weidushangcheng.model.bean.SearchBean;
import example.com.weidushangcheng.model.bean.ShowBean;
import example.com.weidushangcheng.model.bean.TongBuBean;
import example.com.weidushangcheng.model.bean.TwoBean;
import example.com.weidushangcheng.model.bean.XQBean;
import example.com.weidushangcheng.model.bean.ZhuCe;
import example.com.weidushangcheng.model.bean.ZuJiBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface API {
    //登录
    @POST("small/user/v1/login")
    @FormUrlEncoded
    Observable<Deng> getDeng(@Field("phone") String phone,@Field("pwd") String pwd);
//注册
    @POST("small/user/v1/register")
    @FormUrlEncoded
    Observable<ZhuCe> getZhuCe(@Field("phone") String phone,@Field("pwd") String pwd);
//首页
    @GET("small/commodity/v1/commodityList")
    Observable<ShowBean> getShowData();
//banner
    @GET("small/commodity/v1/bannerShow")
    Observable<BannerBean> getBanneData();
//详情
    @GET("small/commodity/v1/findCommodityDetailsById")
    Observable<XQBean> getxqdata(@Query("commodityId") String id);
 //搜索
    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<SearchBean> getSeachData(@Query("keyword") String keyword,@Query("page")int page,@Query("count")int count);

 //圈子
    @GET("small/circle/v1/findCircleList")
    Observable<QuanZiBean> getQuanData(@Query("page")int page,@Query("count")int count);
//足迹
    @GET("small/commodity/verify/v1/browseList")
    Observable<ZuJiBean> getzujidata(@Query("page")int page,@Query("count")int count);
  //one
    @GET("small/commodity/v1/findFirstCategory")
    Observable<OneBean> getondata();
   //two
   @GET("small/commodity/v1/findSecondCategory")
    Observable<TwoBean> gettwodata(@Query("firstCategoryId") String firstCategoryId);
    //同步购物车
    @FormUrlEncoded
    @PUT("small/order/verify/v1/syncShoppingCart")
    Observable<TongBuBean> gettongdata(@Field("data") String data);
    //查询购物车
    @GET("small/order/verify/v1/findShoppingCart")
    Observable<ChaXunCarBean> getcahxundata();

}
