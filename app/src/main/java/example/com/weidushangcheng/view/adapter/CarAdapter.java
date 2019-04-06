package example.com.weidushangcheng.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import example.com.weidushangcheng.R;
import example.com.weidushangcheng.model.bean.ChaXunCarBean;
import example.com.weidushangcheng.view.activity.CustomAmount;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    Context context;
    ChaXunCarBean chaXunCarBean;
     CheckBox checkBox;
     int price;

    public CarAdapter(Context context) {
        this.context = context;
    }
    public void setData(ChaXunCarBean chaXunCarBean){
        this.chaXunCarBean=chaXunCarBean;
    }
    //计算总价
     TextView priceTotal;
    public void setTextView(TextView textView){
        this.priceTotal = textView;
        notifyDataSetChanged();
    }
    //shopCarfragment 的 checkbox
    public void setCheckBox(CheckBox mCheckboxQuan) {
        this.checkBox = mCheckboxQuan;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkbox = (CheckBox) v;
                boolean checked = checkbox.isChecked();
                selectAll(checked);
            }

            private void selectAll(boolean checked) {
                for (int i = 0; i < chaXunCarBean.getResult().size(); i++) {
                    ChaXunCarBean.ResultBean resultBean = chaXunCarBean.getResult().get(i);
                    resultBean.setIscheck(checked);
                }
                notifyDataSetChanged();
            }

        });
    }

        @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
           viewHolder.text_car_name.setText(chaXunCarBean.getResult().get(i).getCommodityName());
           viewHolder.text_car_price.setText(chaXunCarBean.getResult().get(i).getPrice()+"");
           Glide.with(context).load(chaXunCarBean.getResult().get(i).getPic()).into(viewHolder.img_car);
           viewHolder.check_item.setChecked(chaXunCarBean.getResult().get(i).isIscheck());
           viewHolder.custom.setOnAmountLisenter(new CustomAmount.OnAmountLisenter() {
            @Override
            public void onAmount(int num) {
                chaXunCarBean.getResult().get(i).setCount(num);
                jiSuanPrice();
                notifyDataSetChanged();
            }
        });
           viewHolder.check_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   chaXunCarBean.getResult().get(i).setIscheck(isChecked);
                   boolean selectAll = isSelectAll();
                   checkBox.setChecked(selectAll);
                   jiSuanPrice();

               }
               });
  }

    private void jiSuanPrice() {
        for (int i = 0; i <chaXunCarBean.getResult().size() ; i++) {
            int totalPrice=0;
            boolean ischeck = chaXunCarBean.getResult().get(i).isIscheck();
            if (ischeck){
                totalPrice+=chaXunCarBean.getResult().get(i).getPrice()*chaXunCarBean.getResult().get(i).getCount();
            }
            priceTotal.setText(totalPrice+"");
        }
    }

    private boolean isSelectAll() {
        for (int i = 0; i < chaXunCarBean.getResult().size(); i++) {
            boolean checked = chaXunCarBean.getResult().get(i).isIscheck();
            if (!checked){
                return false;
            }
        }
        return true;
    }
    @Override
    public int getItemCount() {
        return chaXunCarBean.getResult().size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView text_car_name,text_car_price;
        private final ImageView img_car;
        private final CustomAmount custom;
        private final CheckBox check_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_car_name = itemView.findViewById(R.id.text_car_name);
            text_car_price = itemView.findViewById(R.id.text_car_price);
            img_car = itemView.findViewById(R.id.img_car);
            check_item = itemView.findViewById(R.id.check_item);
            custom = itemView.findViewById(R.id.custom);
        }
    }
}
