package com.sevenlearn.googlemapsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user on 2/8/2018.
 */

public class TripInfoFragment extends BottomSheetDialogFragment {
    private static final String KEY_DISTANCE = "distance";
    private static final String KEY_ARRIVE_TIME = "time";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_info, container, false);
        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        TextView distanceTextView = view.findViewById(R.id.tv_tripInfo_destination);
        distanceTextView.setText(getArguments().getString(KEY_DISTANCE));
        TextView arrivialTime = view.findViewById(R.id.tv_tripInfo_arrivalTime);
        arrivialTime.setText(getArguments().getString(KEY_ARRIVE_TIME));

    }

    public static TripInfoFragment newInstance(String distance, String arrivialTime) {

        Bundle args = new Bundle();
        args.putString(KEY_DISTANCE, distance);
        args.putString(KEY_ARRIVE_TIME, arrivialTime);
        TripInfoFragment fragment = new TripInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
