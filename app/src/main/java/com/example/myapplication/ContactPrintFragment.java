package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.databinding.FragmentContactBinding;
import com.example.myapplication.databinding.FragmentContactPrintBinding;

public class ContactPrintFragment extends Fragment {

    public ContactPrintFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String firstname = ContactPrintFragmentArgs.fromBundle(getArguments()).getFirstname();
        String lastname = ContactPrintFragmentArgs.fromBundle(getArguments()).getLastname();
        String salutation = ContactPrintFragmentArgs.fromBundle(getArguments()).getSalutation();

        FragmentContactPrintBinding binding = FragmentContactPrintBinding.inflate(inflater);
        binding.contactInformationTV.setText(String.format("Hallo %s %s %s",  salutation, firstname, lastname));


        return binding.getRoot();
    }
}