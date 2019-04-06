package example.com.weidushangcheng.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.TwoBean;

public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.ViewHolder> {
   Context context;
   TwoBean twoBean;

    public TwoAdapter(Context context) {
        this.context = context;
    }
    public void setData(TwoBean twoBean){
        this.twoBean=twoBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.two_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
          viewHolder.text_two.setText(twoBean.getResult().get(i).getName());
          viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                topTwoCall.getTopTwo(twoBean.getResult().get(i).getName());
              }
          });
    }

    @Override
    public int getItemCount() {
        return twoBean.getResult().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView text_two;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_two = itemView.findViewById(R.id.text_two);
        }
    }


    TopTwoCall topTwoCall;
    public void topTwoCall(TopTwoCall topTwoCall){
        this.topTwoCall=topTwoCall;
    }
    public interface TopTwoCall{
        void getTopTwo(String topTwoCall);
    }

}
