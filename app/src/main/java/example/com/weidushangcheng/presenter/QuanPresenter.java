package example.com.weidushangcheng.presenter;

import example.com.weidushangcheng.model.bean.QuanZiBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.IQuanView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class QuanPresenter extends BasePresenter<IQuanView> {

    private final RetrofitUtils instance;

    public QuanPresenter(){
        instance = RetrofitUtils.getInstance();
    }

    public void getquandata() {
           instance.api.getQuanData(3,100)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Consumer<QuanZiBean>() {
                       @Override
                       public void accept(QuanZiBean quanZiBean) throws Exception {
                               getMview().quansuccess(quanZiBean);
                       }
                   });
    }
}
