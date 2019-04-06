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

public class RxxpAdapter extends XRecyclerView.Adapter<RxxpAdapter.ViewHoder> {
    Context context;
  ShowBean showBean;

    public RxxpAdapter(Context context, ShowBean showBean) {
        this.context = context;
        this.showBean = showBean;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rxxp_item, viewGroup, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, final int i) {
   viewHoder.rxxp_text_name.setText(showBean.getResult().getRxxp().getCommodityList().get(i).getCommodityName());
   viewHoder.rxxp_text_price.setText("￥："+showBean.getResult().getRxxp().getCommodityList().get(i).getPrice()+"");
  // Glide.with(context).load(showBean.getResult().getRxxp().getCommodityList().get(i).getMasterPic()).into(viewHoder.img_rxxp);
        Uri uri = Uri.parse(showBean.getResult().getRxxp().getCommodityList().get(i).getMasterPic());
        viewHoder.img_rxxp.setImageURI(uri);
    //事件处理
        viewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(showBean.getResult().getRxxp().getCommodityList().get(i).getCommodityId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return showBean.getResult().getRxxp().getCommodityList().size();
    }

    public class ViewHoder extends XRecyclerView.ViewHolder {

         SimpleDraweeView img_rxxp;
         TextView rxxp_text_name,rxxp_text_price;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            img_rxxp = itemView.findViewById(R.id.img_rxxp);
            rxxp_text_name = itemView.findViewById(R.id.rxxp_text_name);
            rxxp_text_price = itemView.findViewById(R.id.rxxp_text_price);
        }
    }
}
