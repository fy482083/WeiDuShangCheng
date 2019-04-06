package example.com.weidushangcheng.presenter;

import example.com.weidushangcheng.model.bean.Deng;
import example.com.weidushangcheng.model.bean.ZhuCe;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.IMainView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter <IMainView> {

    private final RetrofitUtils instance;

    public MainPresenter(){
        instance = RetrofitUtils.getInstance();
    }


    public void getDengData(String phone,String pwd) {
        instance.api.getDeng(phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Deng>() {
                    @Override
                    public void accept(Deng deng) throws Exception {
                        getMview().onSuccess(deng);
                    }
                });
    }

    public void getZhuData(String phone,String pwd) {
        instance.api.getZhuCe(phone,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZhuCe>() {
                    @Override
                    public void accept(ZhuCe zhuCe) throws Exception {
                        getMview().onSuccess(zhuCe);
                    }
                });
    }

}
