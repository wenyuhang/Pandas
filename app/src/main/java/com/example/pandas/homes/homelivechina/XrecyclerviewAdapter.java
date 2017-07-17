package com.example.pandas.homes.homelivechina;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pandas.R;
import com.example.pandas.model.datebean.livechina.LiveChinaBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 联想 on 2017/7/15.
 */

public class XrecyclerviewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<LiveChinaBean.LiveBean> list;

    public XrecyclerviewAdapter(Context context, List<LiveChinaBean.LiveBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.live_china_item, null);
        LinearLayout.LayoutParams params=
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new ViewHplder(view);
    }

    class ViewHplder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.livechina_adapter_imge)
        ImageView livechinaAdapterImge;
        @Bind(R.id.livechina_adapter_title)
        TextView livechinaAdapterTitle;
        @Bind(R.id.livechina_adapter_conimag)
        CheckBox livechinaAdapterConimag;
        @Bind(R.id.livechina_ada_linear_jianjie)
        LinearLayout livechinaAdaLinearJianjie;
        @Bind(R.id.livechina_adapter_jianjie)
        TextView livechinaAdapterJianjie;
        public ViewHplder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            livechinaAdapterConimag.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.livechina_adapter_conimag:
                    boolean checked = livechinaAdapterConimag.isChecked();
                    if(checked){
                        livechinaAdapterJianjie.setVisibility(View.VISIBLE);
                    }else {
                        livechinaAdapterJianjie.setVisibility(View.GONE);
                    }
                    break;

            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHplder mholder= (ViewHplder) holder;
        mholder.livechinaAdapterConimag.setChecked(false);
        mholder.livechinaAdapterTitle.setText(list.get(position).getTitle());
        mholder.livechinaAdapterJianjie.setText(list.get(position).getBrief());
        Glide.with(context).load(list.get(position).getImage()).into(mholder.livechinaAdapterImge);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
