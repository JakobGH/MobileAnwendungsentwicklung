package com.example.myapplication;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import com.example.myapplication.databinding.FragmentListBinding;
import org.jetbrains.annotations.NotNull;

public class LayoutFragment extends ListFragment {

    LayoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setListAdapter(new MyAdapter());
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }
}