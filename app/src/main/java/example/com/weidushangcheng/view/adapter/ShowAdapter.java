package example.com.weidushangcheng.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.BannerBean;
import example.com.weidushangcheng.model.bean.ShowBean;
import example.com.weidushangcheng.model.httputils.RetrofitUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    Context context;
    ShowBean showBean;
    final int TYPE1 = 0;
    final int TYPE2 = 1;
    final int TYPE3 = 2;
    final int TYPE4 = 3;

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE1;
        }else if (position==1){
            return TYPE2;
        }else if (position==2){
            return TYPE3;
        }else {
            return TYPE4;
        }
    }

    public ShowAdapter(Context context, ShowBean showBean) {
        this.context = context;
        this.showBean = showBean;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        View view;
        if (type==TYPE1){
            view = LayoutInflater.from(context).inflate(R.layout.banner_item, viewGroup, false);
            return new ViewHolderBanner(view);
        }
        else if (type == TYPE2) {
            view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
            return new ViewHolderRxxp(view);
        }else if(type==TYPE3){
            view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
            ViewHolderPzsh viewHolderPzsh = new ViewHolderPzsh(view);
            return viewHolderPzsh;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
            ViewHolderMlss viewHolderMlss = new ViewHolderMlss(view);
            return viewHolderMlss;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final XRecyclerView.ViewHolder holder, final int i) {
        int type = getItemViewType(i);
        if (type==TYPE1){
            RetrofitUtils.getInstance().api.getBanneData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<BannerBean>() {
                        @Override
                        public void accept(BannerBean bannerBean) throws Exception {
                         if (bannerBean instanceof BannerBean){
                            final ArrayList<String> images = new ArrayList<>();
                            for (int i = 0; i < bannerBean.getResult().size(); i++) {
                                images.add(bannerBean.getResult().get(i).getImageUrl());
                            }
                            ((ViewHolderBanner)holder).banner.setData(images, null);
                            ((ViewHolderBanner)holder).banner.loadImage(new XBanner.XBannerAdapter(){
                                @Override
                                public void loadBanner(XBanner banner, Object model, View view, int position) {
                                    Glide.with(context).load(images.get(position)).into((ImageView) view);
                                }
                            });
                            // 设置XBanner的页面切换特效
                            ((ViewHolderBanner)holder).banner.setPageTransformer(Transformer.Default);
                            // 设置XBanner页面切换的时间，即动画时长
                            ((ViewHolderBanner)holder).banner.setPageChangeDuration(1000);
                    }
                        }
                    });
        }else if(type==TYPE2){
            ViewHolderRxxp viewHolderRxxp = (ViewHolderRxxp) holder;
            viewHolderRxxp.text1.setText(showBean.getResult().getRxxp().getName());
//            viewHolderRxxp.img_sandian1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    EventBus.getDefault().post(showBean.getResult().getRxxp().getCommodityList().get(i).getMasterPic());
//                }
//            });
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
            viewHolderRxxp.RecyclerView1.setLayoutManager(gridLayoutManager);
            RxxpAdapter rxxpAdapter = new RxxpAdapter(context,showBean);
            viewHolderRxxp.RecyclerView1.setAdapter(rxxpAdapter);
        }else if (type==TYPE3){
            ViewHolderPzsh viewHolderPzsh = (ViewHolderPzsh) holder;
            viewHolderPzsh.text2.setText(showBean.getResult().getPzsh().getName());
//            viewHolderPzsh.img_sandian2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    EventBus.getDefault().post(showBean.getResult().getPzsh().getCommodityList().get(i).getMasterPic());
//                }
//            });
            viewHolderPzsh.RecyclerView2.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true));
            PzshAdapter pzshAdapter = new PzshAdapter(context,showBean);
            viewHolderPzsh.RecyclerView2.setAdapter(pzshAdapter);
        }else{
            ViewHolderMlss viewHolderMlss = (ViewHolderMlss) holder;
            viewHolderMlss.text3.setText(showBean.getResult().getMlss().getName());
//            viewHolderMlss.img_sandian3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    EventBus.getDefault().post(showBean.getResult().getMlss().getCommodityList().get(i).getMasterPic());
//                }
//            });
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            viewHolderMlss.RecyclerView3.setLayoutManager(gridLayoutManager);
            MlssAdapter mlssAdapter = new MlssAdapter(context,showBean);
            viewHolderMlss.RecyclerView3.setAdapter(mlssAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolderBanner extends XRecyclerView.ViewHolder {
        XBanner banner;
        public ViewHolderBanner(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class ViewHolderRxxp extends XRecyclerView.ViewHolder {
        XRecyclerView RecyclerView1;
        TextView text1;
       //private final ImageView img_sandian1;

        public ViewHolderRxxp(@NonNull final View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text);
         //  img_sandian1 = itemView.findViewById(R.id.img_sandian);
            RecyclerView1 = itemView.findViewById(R.id.RecyclerView);

        }
    }

    public class ViewHolderPzsh extends XRecyclerView.ViewHolder {
        TextView text2;
        XRecyclerView RecyclerView2;
      // private final ImageView img_sandian2;
        public ViewHolderPzsh(@NonNull View itemView) {
            super(itemView);
            text2 = itemView.findViewById(R.id.text);
           //img_sandian2 = itemView.findViewById(R.id.img_sandian);
            RecyclerView2= itemView.findViewById(R.id.RecyclerView);
        }
    }
    public class ViewHolderMlss extends XRecyclerView.ViewHolder {
        TextView text3;
        XRecyclerView RecyclerView3;
       // private final ImageView img_sandian3;
        public ViewHolderMlss(@NonNull View itemView) {
            super(itemView);
            text3 = itemView.findViewById(R.id.text);
         // img_sandian3 = itemView.findViewById(R.id.img_sandian);
            RecyclerView3= itemView.findViewById(R.id.RecyclerView);
        }
    }
}
