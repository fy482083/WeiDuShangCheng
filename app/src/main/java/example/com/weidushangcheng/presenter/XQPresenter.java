package example.com.weidushangcheng.presenter;

import example.com.weidushangcheng.model.bean.XQBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.IXQView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class XQPresenter extends BasePresenter<IXQView> {

    private final RetrofitUtils retrofitUtils;

    public XQPresenter(){
        retrofitUtils = RetrofitUtils.getInstance();
    }

    public void getxq(String ids) {
         retrofitUtils.api.getxqdata(ids)
                .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<XQBean>() {
                     @Override
                     public void accept(XQBean xqBean) throws Exception {
                            getMview().onXQSuccess(xqBean);
                     }
                 });
    }
}
