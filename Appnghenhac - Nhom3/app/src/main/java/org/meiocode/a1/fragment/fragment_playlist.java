package org.meiocode.a1.fragment;

import android.content.Intent;
import android.icu.util.Measure;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.meiocode.a1.R;
import org.meiocode.a1.activity.DanhSachBaiHatActivity;
import org.meiocode.a1.adapter.PlayListAdapter;
import org.meiocode.a1.model.PlayList;
import org.meiocode.a1.service.APIRetrofitClient;
import org.meiocode.a1.service.APIService;
import org.meiocode.a1.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_playlist extends Fragment {
    View view;
    ListView listviewPlayList;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> mangplaylist;
    TextView txttitlePlayList, txtxemthemPlayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        GetData();
        listviewPlayList = view.findViewById(R.id.listviewPlayList);
        txttitlePlayList = view.findViewById(R.id.textviewtitlePlayList);
//        txtxemthemPlayList = view.findViewById(R.id.textviewmoreplaylist);
        return view;
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
    private void GetData() {
        Dataservice dataservice = APIService.getServices();
        Call<List<PlayList>> callback = dataservice.GetPlayListForCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mangplaylist= (ArrayList<PlayList>) response.body();
                playListAdapter = new PlayListAdapter(getActivity(),android.R.layout.simple_list_item_1,mangplaylist);
                listviewPlayList.setAdapter(playListAdapter);
                setListViewHeightBasedOnChildren(listviewPlayList);
                listviewPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                        intent.putExtra("itemplaylist",mangplaylist.get(position));
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
}
