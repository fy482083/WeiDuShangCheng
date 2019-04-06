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

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.QuanZiBean;


public class QuanAdapter extends XRecyclerView.Adapter<QuanAdapter.ViewHolder> {
    Context context;
    QuanZiBean quanZiBean;

    public QuanAdapter(Context context) {
        this.context = context;
    }

    public void setdata(QuanZiBean quanZiBean) {
        this.quanZiBean = quanZiBean;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.quan_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.quan_text_name.setText(quanZiBean.getResult().get(i).getNickName()                                                  );
        viewHolder.quan_text_time.setText(quanZiBean.getResult().get(i).getCreateTime()+"");
        viewHolder.quan_text_name_two.setText(quanZiBean.getResult().get(i).getNickName());
        Glide.with(context).load(quanZiBean.getResult().get(i).getImage()).into(viewHolder.img_quan);
        Glide.with(context).load(quanZiBean.getResult().get(i).getHeadPic()).into(viewHolder.quan_img);
    }

    @Override
    public int getItemCount() {
        return quanZiBean.getResult().size();
    }

    public class ViewHolder extends XRecyclerView.ViewHolder {
        private final ImageView quan_img,img_quan;
        private final TextView quan_text_name,quan_text_name_two,quan_text_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             quan_img = itemView.findViewById(R.id.quan_img);
            img_quan = itemView.findViewById(R.id.img_quan);
            quan_text_name = itemView.findViewById(R.id.quan_text_name);
            quan_text_name_two = itemView.findViewById(R.id.quan_text_name_two);
            quan_text_time = itemView.findViewById(R.id.quan_text_time);

        }
    }
}
