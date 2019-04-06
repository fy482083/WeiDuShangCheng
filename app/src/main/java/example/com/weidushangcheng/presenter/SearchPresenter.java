package example.com.weidushangcheng.presenter;

import io.reactivex.functions.Consumer;

import example.com.weidushangcheng.model.bean.SearchBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.ISearchView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends  BasePresenter<ISearchView> {

    private final RetrofitUtils retrofitUtils;

    public SearchPresenter(){
        retrofitUtils = RetrofitUtils.getInstance();
    }

    public void getSearchData(String name) {
        retrofitUtils.api.getSeachData(name,1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchBean>() {
                    @Override
                    public void accept(SearchBean searchBean) throws Exception {
                        getMview().SearchSuccess(searchBean);
                    }
                });
    }
}
