package example.com.weidushangcheng.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.List;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.ZuJiBean;

public class ZuJiAdapter extends XRecyclerView.Adapter<ZuJiAdapter.ViewHolder> {
    Context context;
    List<ZuJiBean.ResultBean> list ;

    public ZuJiAdapter(Context context, List<ZuJiBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(context).inflate(R.layout.zuji_item, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
          viewHolder.text_name_zuji.setText(list.get(i).getCommodityName());
          viewHolder.text_price_zuji.setText(list.get(i).getPrice()+"");
          Uri uri = Uri.parse(list.get(i).getMasterPic());
          viewHolder.img_zuji.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        Log.e("sss", "getItemCount: "+list.size());
        return list.size();
    }

    public class ViewHolder extends XRecyclerView.ViewHolder {
        private final TextView text_name_zuji,text_price_zuji;
        private final SimpleDraweeView img_zuji;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_name_zuji = itemView.findViewById(R.id.text_name_zuji);
            text_price_zuji = itemView.findViewById(R.id.text_price_zuji);
            img_zuji = itemView.findViewById(R.id.img_zuji);
        }
    }
}
