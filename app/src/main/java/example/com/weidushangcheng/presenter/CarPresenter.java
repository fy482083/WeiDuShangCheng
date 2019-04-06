package example.com.weidushangcheng.presenter;

import example.com.weidushangcheng.model.bean.ChaXunCarBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.ICarView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CarPresenter extends BasePresenter<ICarView> {

    private final RetrofitUtils instance;

    public CarPresenter(){
        instance = RetrofitUtils.getInstance();
      }

    public void getcardata() {
        instance.api.getcahxundata()
                . subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChaXunCarBean>() {
                    @Override
                    public void accept(ChaXunCarBean chaXunCarBean) throws Exception {
                          getMview().carsuccess(chaXunCarBean);
                    }
                });
    }
}
