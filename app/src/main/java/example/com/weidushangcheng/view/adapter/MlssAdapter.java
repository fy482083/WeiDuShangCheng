package example.com.weidushangcheng.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.ShowBean;
import example.com.weidushangcheng.model.event.MessageEvent;

public class MlssAdapter extends XRecyclerView.Adapter<MlssAdapter.ViewHoder> {
    Context context;
    ShowBean showBean;

    public MlssAdapter(Context context, ShowBean showBean) {
        this.context = context;
        this.showBean = showBean;
    }

    @NonNull
    @Override
    public MlssAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mlss_tem, viewGroup, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MlssAdapter.ViewHoder viewHoder, final int i) {
        viewHoder.mlss_text_name.setText(showBean.getResult().getMlss().getCommodityList().get(i).getCommodityName());
        viewHoder.mlss_text_price.setText("￥："+showBean.getResult().getMlss().getCommodityList().get(i).getPrice()+"");
       // Glide.with(context).load(showBean.getResult().getMlss().getCommodityList().get(i).getMasterPic()).into(viewHoder.img_mlss);
        Uri uri = Uri.parse(showBean.getResult().getMlss().getCommodityList().get(i).getMasterPic());
        viewHoder.img_mlss.setImageURI(uri);
        //事件处理
        viewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(showBean.getResult().getMlss().getCommodityList().get(i).getCommodityId()+"");

            }
        });
    }

    @Override
    public int getItemCount() {
        return showBean.getResult().getMlss().getCommodityList().size();
    }

    public class ViewHoder extends XRecyclerView.ViewHolder {

         SimpleDraweeView img_mlss;
         TextView mlss_text_name,mlss_text_price;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            img_mlss = itemView.findViewById(R.id.img_mlss);
            mlss_text_name = itemView.findViewById(R.id.mlss_text_name);
            mlss_text_price = itemView.findViewById(R.id.mlss_text_price);
        }
    }
}