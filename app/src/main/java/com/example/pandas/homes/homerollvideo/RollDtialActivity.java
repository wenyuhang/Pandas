package com.example.pandas.homes.homerollvideo;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.pandas.R;
import com.example.pandas.base.BaseActivity;
import com.example.pandas.model.datebean.RollvideoBean;

import butterknife.Bind;

public class RollDtialActivity extends BaseActivity implements RollVideoContract.View {

    @Bind(R.id.rollvideo_details_show_image)
    ImageView rollvideoDetailsShowImage;
    @Bind(R.id.visibility_linear)
    LinearLayout visibilityLinear;
    Boolean flag=true;


    @Override
    public int getLayoutId() {
        return R.layout.activity_roll_dtial;
    }




    @Override
    public void initview() {
        rollvideoDetailsShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    rollvideoDetailsShowImage.setImageResource(R.drawable.lpanda_show);

                }else{
                    rollvideoDetailsShowImage.setImageResource(R.drawable.lpanda_off);
                }
                flag=false;
                int i=0;
                i=visibilityLinear.getVisibility();
                if(i==8){
                    visibilityLinear.setVisibility(View.VISIBLE);

                } else {
                    visibilityLinear.setVisibility(View.GONE);
                }
            }

        });
        rollvideoDetailsShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    rollvideoDetailsShowImage.setImageResource(R.drawable.lpanda_off);
                }else{
                    rollvideoDetailsShowImage.setImageResource(R.drawable.lpanda_show);
                }
                flag=false;
            }
        });
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(RollvideoBean rollvideoBean) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(RollVideoContract.Presenter presenter) {

    }
}
