package com.example.pandas.homes.homelivechina;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

import com.example.pandas.R;
import com.example.pandas.base.BaseFragment;
import com.example.pandas.model.datebean.livechina.LiveChinaBean;
import com.example.pandas.model.datebean.livechina.SceneryBean;
import com.example.pandas.personal.PersonalCenterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by 联想 on 2017/7/10.
 */

public class LiveChinaMain extends BaseFragment implements LiveChinaContract.View, View.OnClickListener {


    @Bind(R.id.LiveChina_login)
    ImageView LiveChinaLogin;
    @Bind(R.id.liveChina_tablayout)
    TabLayout liveChinaTablayout;
    @Bind(R.id.sure)
    ImageButton sure;
    @Bind(R.id.liveChina_viewpager)
    ViewPager liveChinaViewpager;
    private LCPpageAdapter lcPpageAdapter;
    private ArrayList<BaseFragment> list;
    private ArrayList<String> strings;
    private PopupWindow popupWindow;
    private View view1;
    private LiveChinaContract.Presenter presenter;
    private ArrayList<SceneryBean.AlllistBean> list1;
    private GridViewAdapter gridViewAdapter;
    private GridView gridview;
    private DragAdapter dragAdapter;
    private ArrayList<String> strings1;
    private ArrayList<String> urls;


    @Override
    protected int getLayoutId() {
        return R.layout.main_livechina;
    }

    @Override
    protected void init(View view) {
        list = new ArrayList<BaseFragment>();

        strings = new ArrayList<String>();
        lcPpageAdapter = new LCPpageAdapter(getActivity().getSupportFragmentManager(), list, strings);
        liveChinaViewpager.setAdapter(lcPpageAdapter);
        liveChinaTablayout.setupWithViewPager(liveChinaViewpager);
        liveChinaTablayout.setTabMode(TabLayout.MODE_FIXED);
        liveChinaTablayout.setMinimumWidth(200);

        view1 = View.inflate(getActivity(), R.layout.live_china_popupwindow, null);
        popupWindow = new PopupWindow(view1);

        popupWindow.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewPager.LayoutParams.MATCH_PARENT);
        ImageButton popupwindowButton = (ImageButton) view1.findViewById(R.id.live_china_pwindow_button);
        Button but = (Button) view1.findViewById(R.id.but);
        setDragGridView();
        but.setOnClickListener(this);
        popupwindowButton.setOnClickListener(this);
    }

    private void setDragGridView() {

        DragGridView dragGridView = (DragGridView) view1.findViewById(R.id.live_china_pwindow_DragGridview);
        strings1 = new ArrayList<String>();

        dragAdapter = new DragAdapter(getActivity(), strings1);
        dragGridView.setAdapter(dragAdapter);


        list1 = new ArrayList<SceneryBean.AlllistBean>();
        gridview = (GridView) view1.findViewById(R.id.live_china_gridview);
        gridViewAdapter = new GridViewAdapter(list1, getActivity());
        gridview.setAdapter(gridViewAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strings1.add(list1.get(position).getTitle());
                urls.add(list1.get(position).getUrl());
                list1.remove(position);
                gridViewAdapter.notifyDataSetChanged();
                dragAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void loadData() {
        presenter.strat();
        urls = new ArrayList<>();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(LiveChinaBean netBean) {

    }

    @Override
    public void setResult2(SceneryBean netBean) {
        List<SceneryBean.AlllistBean> alllist = netBean.getAlllist();
        list1.addAll(alllist);
        gridViewAdapter.notifyDataSetChanged();
        setGridViewHeightBasedOnChildren(gridview);

        List<SceneryBean.TablistBean> tablist = netBean.getTablist();
        for (int i = 0; i < tablist.size(); i++) {
            String title = tablist.get(i).getTitle();
            urls.add(tablist.get(i).getUrl());
            strings1.add(title);
            dragAdapter.notifyDataSetChanged();
        }
        for (int i = 0; i < list1.size(); i++) {
            for (int i1 = 0; i1 < strings1.size(); i1++) {
                if (list1.get(i).getTitle().equals(strings1.get(i1))) {
                    list1.remove(list1.get(i));
                    gridViewAdapter.notifyDataSetChanged();
                }
            }
        }


        settablayout();
    }


    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(LiveChinaContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @OnClick({R.id.LiveChina_login, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.LiveChina_login:
                startActivity(new Intent(getActivity(), PersonalCenterActivity.class));
                break;
            case R.id.sure:
                popupWindow.showAtLocation(view1, Gravity.CENTER, 0, 0);

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_china_pwindow_button:


                settablayout();
                popupWindow.dismiss();
                break;
            case R.id.but:
                break;
        }
    }

    private void settablayout() {
        list.clear();
        strings.clear();
        for(int i = 0; i <strings1.size() ; i++) {
            PageFragment pageFragment = new PageFragment();
            pageFragment.s=urls.get(i);
            list.add(pageFragment);
            new LiveChinaPresenter(pageFragment);
            strings.add(strings1.get(i));
            lcPpageAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 动态设置GridView的高度
     */
    public static void setGridViewHeightBasedOnChildren(GridView gridView) {
        if (gridView == null) return;

        ListAdapter listAdapter = gridView.getAdapter();

        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = (totalHeight + (gridView.getMeasuredHeight() * (listAdapter.getCount() - 1))) / 3;
        gridView.setLayoutParams(params);
    }

}
