package com.spuds.eventapp.EventDetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.spuds.eventapp.CreateComment.CreateCommentFragment;
import com.spuds.eventapp.InvitePeople.InvitePeopleFragment;
import com.spuds.eventapp.R;
import com.spuds.eventapp.Shared.Comment;
import com.spuds.eventapp.Shared.Event;
import com.spuds.eventapp.Shared.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsFragment extends Fragment {

    // Holds event details
    Event event;
    String eventId;

    // Views for event details
    ImageView eventPic;
    TextView eventName;
    TextView eventLocation;
    TextView eventDate;
    TextView eventAttendees;
    TextView eventCategories;
    TextView eventHost;
    TextView eventDescription;
    Button addComment;
    Button invitePeople;

    // Reference to itself
    Fragment eventDetailsFragment;

    // Comments
    RecyclerView rv;
    CommentsRVAdapter adapter;
    List<Comment> comments;

    public EventDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getArguments();
        event = (Event) extras.get(getString(R.string.event_details));
        if (event == null) {
            eventId = extras.getString(getString(R.string.event_id));

            // TODO: Fetch event using eventId
            // fake data
            ArrayList<String> categories = new ArrayList<>();
            categories.add("Social");
            categories.add("Concert");

            event = new Event("1", "2", "Sun God Festival", "spr lame", "RIMAC Field", "04.29.16", 1054,
                    "yj.jpg", categories, "UCSD");
        }
        eventDetailsFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);

        setUpEventInformation(view);

        setUpComments(view);

        //call refreshing function
        if(view != null) {
            refreshing(view);
        }
        return view;
    }
    //TODO: Needs database to finish
    public void refreshing(View view) {
        SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        if (mySwipeRefreshLayout != null) {
            mySwipeRefreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                        }
                    }
            );
        }
    }
    void setUpEventInformation(View view) {

        eventPic = (ImageView) view.findViewById(R.id.event_pic);
        eventName = (TextView) view.findViewById(R.id.event_name);
        eventLocation = (TextView) view.findViewById(R.id.event_loc);
        eventDate = (TextView) view.findViewById(R.id.event_date);
        eventAttendees = (TextView) view.findViewById(R.id.event_attendees);
        eventCategories = (TextView) view.findViewById(R.id.event_categories);
        eventHost = (TextView) view.findViewById(R.id.event_host);
        eventDescription = (TextView) view.findViewById(R.id.event_description);
        addComment = (Button) view.findViewById(R.id.button_add_comment);
        invitePeople = (Button) view.findViewById(R.id.button_invite_people);

        //TODO: picasso for event pic
        eventName.setText(event.getEventName());
        eventLocation.setText(event.getLocation());
        eventDate.setText(event.getDate());
        eventAttendees.setText(String.valueOf(event.getAttendees()));
        eventHost.setText(event.getHostName());
        eventDescription.setText(event.getDescription());

        // Categories
        String categories = "";
        for (int i = 0; i < event.getCategories().size() - 1; ++i) {
            categories += event.getCategories().get(i) + ", ";
        }
        categories += event.getCategories().get(event.getCategories().size() - 1);

        eventCategories.setText(categories);

        // Click listener for the Add Comment button
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creates Create Comment Fragment
                CreateCommentFragment createCommentFragment = new CreateCommentFragment();
                String createCommentFragmentTag = getString(R.string.fragment_create_comment);

                ((MainActivity) getActivity()).removeSearchToolbar();
                // Adds Create Comment Fragment to fragment manager
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_frame_layout, createCommentFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(createCommentFragmentTag)
                        .commit();

            }
        });

        invitePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).addSearchToolbar();
                InvitePeopleFragment invitePeopleFragment = new InvitePeopleFragment();
                // Add Event Details Fragment to fragment manager
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_frame_layout, invitePeopleFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("fragment_invite_people")
                        .commit();
            }
        });
    }

    void setUpComments(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv);

        //Set type of layout manager
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(llm);

        // TODO (M): Get comments from database
        comments = new ArrayList<>();
        comments.add(new Comment("1", null, "Tina Nguyen", "tina.jpg", "05.06.16", "I'm the bestest", false));
        comments.add(new Comment("2", null, "Reggie Wu", "reggie.jpg", "05.06.16", "This event is fun!", false));
        comments.add(new Comment("1", null, "Tina Nguyen", "tina.jpg", "05.06.16", "I'm the bestest", false));
        comments.add(new Comment("2", null, "Reggie Wu", "reggie.jpg", "05.06.16", "This event is fun!", false));
        comments.add(new Comment("1", null, "Tina Nguyen", "tina.jpg", "05.06.16", "I'm the bestest", false));
        comments.add(new Comment("2", null, "Reggie Wu", "reggie.jpg", "05.06.16", "This event is fun!", false));

        // Create adapter for comments
        adapter = new CommentsRVAdapter(comments, this);
        // Attach adapter to RecyclerView
        rv.setAdapter(adapter);
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
