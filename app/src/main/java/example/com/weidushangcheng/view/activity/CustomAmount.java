package example.com.weidushangcheng.view.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import example.com.weidushangcheng.R;

public class CustomAmount extends LinearLayout {

    private int num = 1;
    private OnAmountLisenter lisenter;
    private Button btn_jia,btn_jian;
    private EditText edit_text_num;

    public interface OnAmountLisenter {
        void onAmount(int num);
    }

    public void setOnAmountLisenter(OnAmountLisenter lisenter) {
        this.lisenter = lisenter;
    }
    public CustomAmount(Context context) {
        super(context);
    }

    public CustomAmount(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomAmount(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.num_item, null,false);
        addView(view);
        btn_jia = view.findViewById(R.id.btn_jia);
        btn_jian = view.findViewById(R.id.btn_jian);
        edit_text_num = view.findViewById(R.id.edit_text_num);
        edit_text_num.setText(num+"");
         btn_jian.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (num==1){
                     Toast.makeText(context,"数量不能少于1",Toast.LENGTH_LONG).show();
                       return;
                 }
                 num--;
                 edit_text_num.setText(num+"");
                 if (lisenter != null) {
                     lisenter.onAmount(num);
                 }
             }
         });
         btn_jia.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 num++;
                 edit_text_num.setText(num+"");
                 if (lisenter!=null){
                     lisenter.onAmount(num);
                 }
             }
         });

    }


}
