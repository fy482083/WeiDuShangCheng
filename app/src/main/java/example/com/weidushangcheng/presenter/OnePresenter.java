package example.com.weidushangcheng.presenter;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import example.com.weidushangcheng.model.bean.OneBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.ISearchView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OnePresenter  extends BasePresenter<ISearchView> {

    private final RetrofitUtils instance;

    public OnePresenter(){
        instance = RetrofitUtils.getInstance();
    }


    public void getOneData() {
        instance.api.getondata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OneBean>() {
                    @Override
                    public void accept(OneBean oneBean) throws Exception {
                      getMview().OneSuccess(oneBean);
                        Log.e("aaa", "accept: "+oneBean );
                    }
                });
    }
}
