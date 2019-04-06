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

import java.util.ArrayList;
import java.util.List;

import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.SearchBean;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Viewholder> {
Context context;
    List<SearchBean.ResultBean> list = new ArrayList<>();
    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void  setdata(List<SearchBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
         viewholder.search_text_name.setText(list.get(i).getCommodityName());
         viewholder.search_text_price.setText(list.get(i).getPrice()+"");
         Uri uri = Uri.parse(list.get(i).getMasterPic());
         viewholder.img_search.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private final ImageView img_search;
        private final TextView search_text_name,search_text_price;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img_search = itemView.findViewById(R.id.img_search);
            search_text_name = itemView.findViewById(R.id.search_text_name);
            search_text_price = itemView.findViewById(R.id.search_text_price);
        }
    }
}
