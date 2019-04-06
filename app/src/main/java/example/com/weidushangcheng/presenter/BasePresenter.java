package example.com.weidushangcheng.presenter;

import example.com.weidushangcheng.view.activity.MainActivity;
import example.com.weidushangcheng.view.interfaces.IBaseView;

public class BasePresenter<V extends IBaseView> {
    private V mview;
    public void setView(V v) {
        this.mview=v;
    }

    public V getMview() {
        return mview;
    }
    public void onDettchView(){
        mview=null;
    }
}
