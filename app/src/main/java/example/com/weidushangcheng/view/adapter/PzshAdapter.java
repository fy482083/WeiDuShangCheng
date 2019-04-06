package example.com.weidushangcheng.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.ShowBean;
import example.com.weidushangcheng.model.event.MessageEvent;

public class PzshAdapter extends XRecyclerView.Adapter<PzshAdapter.ViewHoder> {
    Context context;
    ShowBean showBean;

    public PzshAdapter(Context context, ShowBean showBean) {
        this.context = context;
        this.showBean = showBean;
    }

    @NonNull
    @Override
    public PzshAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.pzsh_item, viewGroup, false);
        return new PzshAdapter.ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PzshAdapter.ViewHoder viewHoder, final int i) {
        viewHoder.pzsh_text_name.setText(showBean.getResult().getPzsh().getCommodityList().get(i).getCommodityName());
        viewHoder.pzsh_text_price.setText("￥："+showBean.getResult().getPzsh().getCommodityList().get(i).getPrice()+"");
        //Glide.with(context).load(showBean.getResult().getPzsh().getCommodityList().get(i).getMasterPic()).into(viewHoder.img_pzsh);
        Uri uri = Uri.parse(showBean.getResult().getPzsh().getCommodityList().get(i).getMasterPic());
         viewHoder.img_pzsh.setImageURI(uri);

        //事件处理
        viewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(showBean.getResult().getPzsh().getCommodityList().get(i).getCommodityId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return showBean.getResult().getPzsh().getCommodityList().size();
    }

    public class ViewHoder extends XRecyclerView.ViewHolder {


        SimpleDraweeView img_pzsh;
        TextView pzsh_text_name, pzsh_text_price;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            img_pzsh = itemView.findViewById(R.id.img_pzsh);
            pzsh_text_name = itemView.findViewById(R.id.pzsh_text_name);
            pzsh_text_price = itemView.findViewById(R.id.pzsh_text_price);
        }
    }
}