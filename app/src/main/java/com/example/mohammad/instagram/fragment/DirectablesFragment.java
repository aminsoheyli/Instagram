package com.example.mohammad.instagram.fragment;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammad.instagram.ProfileInformations;
import com.example.mohammad.instagram.R;
import com.example.mohammad.instagram.activity.MainActivity;
import com.example.mohammad.instagram.recycler_view.profile.ProfileAdapter;
import com.example.mohammad.instagram.recycler_view.profile.ProfileCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
 * Fragment than the current user can click on the
 * username of each post and be directed to the profile
 * page of clicked user.
 *
 * Consist of:
 *
 *           I.     Saved fragment
 *           II.    Global fragment
 *           III.   Home fragment*/

public class DirectablesFragment extends Fragment {
    private RecyclerView recyclerViewProfileImages;


    public static ProfileFragment newInstance(ProfileInformations profileInformations) {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_directables, container, false);
        initials(rootView);
        onClickListeners();

        return rootView;
    }

    private void initials(View rootView) {
        recyclerViewProfileImages = rootView.findViewById(R.id.recycler_view_profile_images);
        prepareGlobalImagesRecyclerView();
    }


    private void prepareGlobalImagesRecyclerView() {
        recyclerViewProfileImages.setNestedScrollingEnabled(false);
        recyclerViewProfileImages.setHasFixedSize(true);
        ArrayList<ProfileCard> informations;
        informations = prepareInformations();


        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewProfileImages.setLayoutManager(llm);
        ProfileAdapter adapter = new ProfileAdapter(informations);
        recyclerViewProfileImages.setAdapter(adapter);

    }

    private ArrayList<ProfileCard> prepareInformations() {
        ArrayList<ProfileCard> information = new ArrayList<>();
        //Query --> posts.add(Post)
//        Cursor c = MainActivity.db.rawQuery("select * from post order by post_date desc;", null);

        Cursor c = MainActivity.db.rawQuery("select * from post where user_id !='" + MainActivity.currentUserId + "' order by post_date desc;", null);
        if (c.moveToFirst()) {
            Cursor cc = MainActivity.db.rawQuery("select count(user_id) from likes;", null);
//                Cursor ccc = MainActivity.db.rawQuery("select count(user_id) from likes where user_id = '" + MainActivity.currentUserId + "' order by post_date desc;", null);
//                Cursor cccc = MainActivity.db.rawQuery("select count(user_id) from save where user_id = '" + MainActivity.currentUserId + "' order by post_date desc;", null);
            boolean liked = false;
            boolean saved = false;
//                if (ccc.moveToFirst()) {
//                    liked = !ccc.getString(0).matches("0");
//                }
//                if (cccc.moveToFirst()) {
//                    saved = !cccc.getString(0).matches("0");
//                }
            if (cc.moveToFirst()) {
                ProfileCard temp =
                        new ProfileCard(c.getString(0), BitmapFactory.decodeByteArray(c.getBlob(3), 0, c.getBlob(3).length),
                                c.getString(1),
                                cc.getString(0),
                                c.getString(4),
                                c.getString(2),
                                liked,
                                saved
                        );
                information.add(temp);

            }
            while (c.moveToNext()) {
                cc = MainActivity.db.rawQuery("select count(user_id) from post order by post_date desc;", null);
//                    ccc = MainActivity.db.rawQuery("select count(user_id) from likes where user_id = '" + MainActivity.currentUserId + "' order by post_date desc;", null);
//                    cccc = MainActivity.db.rawQuery("select count(user_id) from save where user_id = '" + MainActivity.currentUserId + "' order by post_date desc;", null);
                liked = false;
                saved = false;
//                    if (ccc.moveToFirst()) {
//                        liked = !ccc.getString(0).matches("0");
//                    }
//                    if (cccc.moveToFirst()) {
//                        saved = !cccc.getString(0).matches("0");
//                    }
                if (cc.moveToFirst()) {
                    ProfileCard temp =
                            new ProfileCard(c.getString(0),
                                    BitmapFactory.decodeByteArray(c.getBlob(3), 0, c.getBlob(3).length),
                                    c.getString(1),
                                    cc.getString(0),
                                    c.getString(4),
                                    c.getString(2),
                                    liked,
                                    saved
                            );
                    information.add(temp);
                }
                cc.close();
            }
        }
        c.close();

        return information;
    }


    private void onClickListeners() {

    }


    // Query to specify whether the user has a biography or not.
    private String hasBiography() {
        return null;
    }


    int SumHashItem(HashMap<Integer, Integer> hashMap) {
        int sum = 0;

        for (Map.Entry<Integer, Integer> myItem : hashMap.entrySet()) {
            sum += myItem.getValue();
        }

        return sum;
    }

//    @Override
//    public void HeightChange(int position, int height) {
//        itemHeight.put(position, height);
//        sumHeight = SumHashItem(itemHeight);
//
//        float density = ProfileFragment.this.getResources().getDisplayMetrics().density;
//        float viewHeight = sumHeight * density;
//        recyclerViewProfileImages.getLayoutParams().height = (int) sumHeight;
//
//        int i = recyclerViewProfileImages.getLayoutParams().height;
//    }
}