package com.stevechuls.test.mainviewfragment.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.stevechuls.test.mainviewfragment.fragment.recyclerview.dataset.CarRecyclerGridItem;
import com.stevechuls.test.viewpager.ADViewPagerAdapter;
import com.stevechuls.test.R;
import com.stevechuls.test.mainviewfragment.fragment.recyclerview.adapter.CartoonRecyclerGridAdapter;
import com.stevechuls.test.listener.ResponseListener;
import com.stevechuls.test.network.HttpRequest;
import com.stevechuls.test.dataset.JsonItem;

/**
 * Created by entermate_ksc on 2018. 4. 10..
 */

public class ShoppingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private String TAG = "[stevechulsdev] ShoppingFragment";

    private SwipeRefreshLayout swipeRefreshLayout;
    private CartoonRecyclerGridAdapter cartoonRecyclerGridAdapter;
    private HttpRequest httpRequest;
    private RecyclerView recyclerView;
    private int currentPage;
    private int TOT_PAGE_COUNT = 5;
    private Timer mTimer;
    private ViewPager mTopViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recyclerview, container, false);

        // SwipeRefreshLayout 참조 및 Refresh 리스너 등록
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        ADViewPagerAdapter ADViewPagerAdapter = new ADViewPagerAdapter(getContext(), getChildFragmentManager());

        mTopViewPager = rootView.findViewById(R.id.main_top_viewpager);
        mTopViewPager.setAdapter(ADViewPagerAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == TOT_PAGE_COUNT ) {
                    currentPage = 0;
                }
                mTopViewPager.setCurrentItem(currentPage++, true);
            }
        };

        mTimer= new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 0, 3000);


        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true); // RecyclerView 자체 크기가 변하지 않는다면, true 성능 개선

        // 서버로부터 데이터 요청
        httpRequest = new HttpRequest();
        httpRequest.callCartoonImageAPI(new ResponseListener() {
            @Override
            public void onSuccess(JsonItem[] jsonItem) {
                Log.d(TAG, "onCreateView() callCarImageAPI onSuccess > jsonItem length : " + jsonItem.length);

                // 서버로 부터 받은 데이터 가공
                ArrayList<CarRecyclerGridItem> arrayList = setListData(jsonItem);

                cartoonRecyclerGridAdapter = new CartoonRecyclerGridAdapter(getContext(), arrayList);

                // RecyclerGridView 세팅
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                recyclerView.setAdapter(cartoonRecyclerGridAdapter);
                // RecyclerGridView Item들 간의 간격을 중복 없이 일정하게 조정
                recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        super.getItemOffsets(outRect, view, parent, state);

                        int spanCount = 2;
                        int spacing = 20;

                        int position = parent.getChildAdapterPosition(view); // item position
                        int column = position % spanCount; // item column
                        outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                        outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                        if (position < spanCount)
                        { // top edge
                            outRect.top = spacing;
                        }
                        outRect.bottom = spacing; // item bottom
                    }
                });
            }

            @Override
            public void onFail(Throwable t)
            {
                Log.e(TAG, "onCreateView() callCarImageAPI error > message : " + t.toString());
            }
        });

        return rootView;
    }

    @Override
    public void onRefresh() {
        // [stevechulsdev] Refresh 할 때, 서버로 부터 데이터 재요청 180413
        httpRequest.callCartoonImageAPI(new ResponseListener() {
            @Override
            public void onSuccess(JsonItem[] jsonItem) {

                ArrayList<CarRecyclerGridItem> arrayList = setListData(jsonItem);

                cartoonRecyclerGridAdapter = new CartoonRecyclerGridAdapter(getContext(), arrayList);
                recyclerView.setAdapter(cartoonRecyclerGridAdapter);
            }

            @Override
            public void onFail(Throwable t)
            {
                Log.e(TAG, "onRefresh() callCarImageAPI error > message : " + t.toString());
            }

        });

        if(cartoonRecyclerGridAdapter != null)
        {
            // [stevechulsdev] 데이터 갱신 180413
            cartoonRecyclerGridAdapter.notifyDataSetChanged();
        }
        // [stevechulsdev] 데이터 갱신 후 Refresh Icon 제거 180413
        swipeRefreshLayout.setRefreshing(false);
    }

    private ArrayList<CarRecyclerGridItem> setListData(JsonItem[] jsonItem)
    {
        ArrayList<CarRecyclerGridItem> recyclerItemArrayList = new ArrayList<>();
        for(int i=1; i<=jsonItem.length; i++)
        {
            CarRecyclerGridItem recyclerItem = new CarRecyclerGridItem();
            recyclerItem.setImageUrl(jsonItem[i-1].getImagepath());
            recyclerItem.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.stevechuls));
            recyclerItem.setText(jsonItem[i-1].getTitle());
            recyclerItemArrayList.add(recyclerItem);
        }

        return recyclerItemArrayList;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mTimer == null)
        {
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == TOT_PAGE_COUNT ) {
                        currentPage = 0;
                    }
                    mTopViewPager.setCurrentItem(currentPage++, true);
                }
            };

            mTimer= new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 0, 3300);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        currentPage = 0;
        mTimer.cancel();
        mTimer = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        mTopViewPager.setCurrentItem(0, true);
    }
}