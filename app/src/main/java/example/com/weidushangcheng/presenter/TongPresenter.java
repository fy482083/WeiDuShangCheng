package example.com.weidushangcheng.presenter;


import example.com.weidushangcheng.model.bean.TongBuBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.ITongBuView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TongPresenter extends BasePresenter<ITongBuView> {

    private final RetrofitUtils instance;

    public TongPresenter(){
        instance = RetrofitUtils.getInstance();
   }

    public void getTongData(String s) {
        instance.api.gettongdata(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TongBuBean>() {
                    @Override
                    public void accept(TongBuBean tongBuBean) throws Exception {
                        getMview().tongbusuccess(tongBuBean);
                    }
                });
    }
}
