package example.com.weidushangcheng.presenter;

import example.com.weidushangcheng.model.bean.ZuJiBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import example.com.weidushangcheng.view.interfaces.IZuJiView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ZuJiPresenter extends BasePresenter<IZuJiView> {

      private final RetrofitUtils instance;

      public ZuJiPresenter(){
            instance = RetrofitUtils.getInstance();
      }

      public void getzujidata() {

            instance.api.getzujidata(1,10)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ZuJiBean>() {
                          @Override
                          public void accept(ZuJiBean zuJiBean) throws Exception {
                                getMview().zujisuccess(zuJiBean);
                          }
                    });
      }
}
