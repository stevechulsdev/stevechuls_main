package com.stevechuls.test.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.stevechuls.test.R;
import com.stevechuls.test.fragment.recyclerview.RecyclerGridAdapter;
import com.stevechuls.test.fragment.recyclerview.RecyclerGridItem2;
import com.stevechuls.test.listener.ResponseListener2;
import com.stevechuls.test.network.HttpRequest;
import com.stevechuls.test.network.JsonItem2;

/**
 * Created by entermate_ksc on 2018. 4. 11..
 */

public class MainFragment2 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerGridAdapter recyclerGridAdapter;
    private HttpRequest httpRequest;
    private static final String TAG = "[stevechulsdev]";

    /**
     * 세 번째 Fragment를 생성
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recyclerview2, container, false);

        // SwipeRefreshLayout 참조 및 Refresh 리스너 등록
        swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        // Recyclerview2 참조
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);

        // 서버로부터 데이터 요청
        httpRequest = new HttpRequest();
        httpRequest.callCartoonImageAPI(new ResponseListener2() {
            @Override
            public void onSuccess(JsonItem2[] jsonItem) {
                Log.d(TAG, "onCreateView() callCarImageAPI onSuccess > jsonItem length : " + jsonItem.length);

                // 서버로 부터 받은 데이터 가공
                ArrayList<RecyclerGridItem2> arrayList = setListData(jsonItem);

                recyclerGridAdapter = new RecyclerGridAdapter(getContext(), arrayList);

                // RecyclerGridView 세팅
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                recyclerView.setAdapter(recyclerGridAdapter);
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

    /**
     * 해당 Recyclerview에서 Refresh를 시도 했을 때,
     * 서버로 부터 데이터를 재요청하고, 데이터 갱신 및 Refresh Icno 제거
     */
    @Override
    public void onRefresh() {
        // [stevechulsdev] Refresh 할 때, 서버로 부터 데이터 재요청 180413
        httpRequest.callCartoonImageAPI(new ResponseListener2() {
            @Override
            public void onSuccess(JsonItem2[] jsonItem) {

                ArrayList<RecyclerGridItem2> arrayList = setListData(jsonItem);

                recyclerGridAdapter = new RecyclerGridAdapter(getContext(), arrayList);
                recyclerView.setAdapter(recyclerGridAdapter);
            }

            @Override
            public void onFail(Throwable t)
            {
                Log.e(TAG, "onRefresh() callCarImageAPI error > message : " + t.toString());
            }

        });

        if(recyclerGridAdapter != null)
        {
            // [stevechulsdev] 데이터 갱신 180413
            recyclerGridAdapter.notifyDataSetChanged();
        }
        // [stevechulsdev] 데이터 갱신 후 Refresh Icon 제거 180413
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 서버로 부터 받은 데이터를 RecyclerGridItem에 저장 후 ArrayList에 담아서 리턴
     * @param jsonItem 서버로 부터 전달 받은 data
     * @return 전달 받은 데이터 가공 후 ArrayList 리턴
     */
    private ArrayList<RecyclerGridItem2> setListData(JsonItem2[] jsonItem)
    {
        ArrayList<RecyclerGridItem2> recyclerItemArrayList = new ArrayList<>();
        for(int i=1; i<=jsonItem.length; i++)
        {
            RecyclerGridItem2 recyclerItem = new RecyclerGridItem2();
            recyclerItem.setImageUrl(jsonItem[i-1].getImagepath());
            recyclerItem.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.stevechuls));
            recyclerItem.setText(jsonItem[i-1].getTitle());
            recyclerItemArrayList.add(recyclerItem);
        }

        return recyclerItemArrayList;
    }
}