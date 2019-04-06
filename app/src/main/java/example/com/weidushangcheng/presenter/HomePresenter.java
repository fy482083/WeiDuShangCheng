package example.com.weidushangcheng.presenter;

import example.com.weidushangcheng.model.bean.SearchBean;
import example.com.weidushangcheng.model.bean.ShowBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.IHomeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<IHomeView> {

    private final RetrofitUtils retrofitUtils;

    public HomePresenter(){
        retrofitUtils = RetrofitUtils.getInstance();
        }
    public void getShowData() {
         retrofitUtils.api.getShowData()
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<ShowBean>() {
                     @Override
                     public void accept(ShowBean showBean) throws Exception {
                         getMview().ShowSuccess(showBean);
                     }
                 });
    }


}

