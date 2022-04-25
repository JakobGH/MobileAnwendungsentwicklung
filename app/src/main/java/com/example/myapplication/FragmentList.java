package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.example.myapplication.databinding.FragmentListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentList extends Fragment {

    private static final String ARGS_CONTENT = "content";

    private String content;

    public FragmentList() {
        // Required empty public constructor
    }

    public static FragmentList newInstance(String text) {
        FragmentList fragment = new FragmentList();
        Bundle args = new Bundle();
        args.putString(ARGS_CONTENT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.content = getArguments().getString(ARGS_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewGroup = inflater.inflate(R.layout.fragment_list, container, false);
        TextView view = viewGroup.findViewById(R.id.fragmentText);
        view.setText(this.content);

        FragmentListBinding fragmentList = FragmentListBinding.inflate(inflater);
        fragmentList.fragmentText.setText("Hallo Welt");
        viewGroup.setOnClickListener(l -> {

            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.remove(this);
            transaction.commit();

        });
        return viewGroup;
    }
}