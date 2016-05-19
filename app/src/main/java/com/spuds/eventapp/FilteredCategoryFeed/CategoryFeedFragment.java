package com.spuds.eventapp.FilteredCategoryFeed;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spuds.eventapp.R;
import com.spuds.eventapp.Shared.Event;
import com.spuds.eventapp.Shared.EventsFeedRVAdapter;

import java.util.ArrayList;
import java.util.List;


public class CategoryFeedFragment extends Fragment {

    private List<Event> events;
    public EventsFeedRVAdapter adapter;
    String tabType;
    String categoryFilter;

    public CategoryFeedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getArguments();
        categoryFilter = extras.getString(getString(R.string.filter_category));
        tabType = extras.getString(getString(R.string.tab_tag));

        // TODO (M): Get arraylist of events based on tab type & category filter
        // Fake data
        events = new ArrayList<>();
        events.add(new Event("1", "yj.jpg", "Sun God Festival", "RIMAC Field", "04.29.16", 1054,
                "Social", "Concert", "UCSD", "spr lame"));
        events.add(new Event("2", "foosh.jpg", "Foosh Show", "Muir", "04.28.16", 51,
                "Social", null, "Foosh Improv Comedy Club", "spr funny"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);
        RecyclerView rv=(RecyclerView) view.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);



        adapter = new EventsFeedRVAdapter(events, this, getString(R.string.fragment_category_feed));
        rv.setAdapter(adapter);

        //call refreshing function
        refreshing(view);
        return view;
    }
    //TODO: Needs database to finish
    public void refreshing(View view) {
        SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                    }
                }
        );
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
