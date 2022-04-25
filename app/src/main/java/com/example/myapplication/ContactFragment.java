package com.example.myapplication;

import android.app.Notification;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.example.myapplication.databinding.FragmentContactBinding;
import org.jetbrains.annotations.NotNull;

public class ContactFragment extends Fragment {

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentContactBinding binding = FragmentContactBinding.inflate(inflater);

        ArrayAdapter<CharSequence> salutationValues = ArrayAdapter.createFromResource(getContext(), R.array.salutation, android.R.layout.simple_spinner_item);
        binding.spinnerFragment.setAdapter(salutationValues);

        Button submitBtn = binding.submitFragmentBtn;
        submitBtn.setOnClickListener(l -> {
            if (!TextUtils.isEmpty(binding.inputFirstnameFragmentET.getText()) && !TextUtils.isEmpty(binding.inputLastnameFragmentET.getText())) {
                //Start des zweiten Fragments mit Safeargs
                NavDirections action = ContactFragmentDirections.fromInputToOutput(binding.inputFirstnameFragmentET.getText().toString(), binding.inputLastnameFragmentET.getText().toString(), binding.spinnerFragment.getSelectedItem().toString());
                Navigation.findNavController(binding.getRoot()).navigate(action);
            } else {
                Toast.makeText(getContext(), R.string.emptyTF, Toast.LENGTH_LONG).show();
            }
        });

        return binding.getRoot();
    }
}