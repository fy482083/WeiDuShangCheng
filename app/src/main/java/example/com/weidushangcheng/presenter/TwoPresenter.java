package example.com.weidushangcheng.presenter;

import example.com.weidushangcheng.model.bean.TwoBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.ISearchView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TwoPresenter extends BasePresenter<ISearchView> {

    private final RetrofitUtils instance;

    public TwoPresenter(){
        instance = RetrofitUtils.getInstance();
    }
    public void getTwoData(String firstCategoryId) {
        instance.api.gettwodata(firstCategoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TwoBean>() {
                    @Override
                    public void accept(TwoBean twoBean) throws Exception {
                        getMview().TwoSuccess(twoBean);
                    }
                });

    }
}
