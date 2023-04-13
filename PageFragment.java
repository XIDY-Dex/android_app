package com.example.worldskills;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class PageFragment extends Fragment {
    int pageCounter;
    ArrayList<EnterScreenPart> screens = new ArrayList<>();

    public int getPageCounter() {
        return pageCounter;
    }
    String[] textLabels = getActivity().getResources().getStringArray(R.array.labels);
    String[] subTextLabels = getActivity().getResources().getStringArray(R.array.sub_labels);
    Drawable image1 = getActivity().getResources().getDrawable(R.drawable.start_screen_first_img);
    Drawable image2 = getActivity().getResources().getDrawable(R.drawable.start_screen_second_img);
    Drawable image3 = getActivity().getResources().getDrawable(R.drawable.start_screen_third_image);

    Drawable[] images = {image1, image2, image2};

    public PageFragment() {}

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageCounter = getArguments() != null ? getArguments().getInt("num") : 1;
        for(int i = 0; i < 3; i++) {
            screens.add(new EnterScreenPart(textLabels[i], subTextLabels[i], images[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView label, subLabel;
        ImageView labelImg;
        label = view.findViewById(R.id.labelText);
        EnterScreenPart screen = screens.get(pageCounter);
        label.setText(screen.getText());
        subLabel = view.findViewById(R.id.subLabelText);
        subLabel.setText(screen.getSubText());
        labelImg = view.findViewById(R.id.labelImg);
        labelImg.setImageDrawable(screen.getImage());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}