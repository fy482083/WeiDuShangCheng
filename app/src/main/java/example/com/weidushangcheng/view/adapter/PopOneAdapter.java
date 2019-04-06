package example.com.weidushangcheng.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.OneBean;

public class PopOneAdapter extends RecyclerView.Adapter<PopOneAdapter.ViewHolder> {
 Context context;
 OneBean oneBean;

    public PopOneAdapter(Context context) {
        this.context = context;
    }
    public void setdata(OneBean oneBean){
        this.oneBean=oneBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
         viewHolder.text_one.setText(oneBean.getResult().get(i).getName());
         viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 topOneCall.getTopOne(oneBean.getResult().get(i).getId()+"");
             }
         });
    }

    @Override
    public int getItemCount() {
        return oneBean.getResult().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView text_one;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_one = itemView.findViewById(R.id.text_one);

        }

    }
    //传值
    TopOneCall topOneCall;
    //声明
    public void topOneCall(TopOneCall topOneCall){
        this.topOneCall = topOneCall;
    }
    //接口
    public interface TopOneCall{
        void getTopOne(String topOneCall);
    }
}
