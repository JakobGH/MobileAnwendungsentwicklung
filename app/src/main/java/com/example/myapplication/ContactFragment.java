package com.example.myapplication;

import android.app.*;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.example.myapplication.databinding.FragmentContactBinding;
import com.google.android.material.snackbar.Snackbar;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ContactFragment extends Fragment {

    public ContactFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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
                Snackbar.make(binding.getRoot(), "Das ist ein Text", Snackbar.LENGTH_LONG).setAction("Klick mich", li -> {
                    Toast.makeText(getContext(), "Toast...brot", Toast.LENGTH_SHORT).show();
                    sendNoti();
                }).show();
            }
        });

        registerForContextMenu(binding.submitFragmentBtn);
        return binding.getRoot();
    }

    private void sendNoti() {
        String chanelID = "channel01";
        int notificationID = 123;

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Objects.requireNonNull(getContext()));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(chanelID, "Hallo", NotificationManager.IMPORTANCE_DEFAULT);
            managerCompat.createNotificationChannel(channel);
        }


        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("extra", "Hallo Welt");
        PendingIntent intent2 = PendingIntent.getActivity(getContext(), 0,
                intent.setAction(Intent.ACTION_VIEW), PendingIntent.FLAG_UPDATE_CURRENT + PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), chanelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(intent2)
                .setContentTitle("Titel")
                .setContentText("Text");

        NotificationManagerCompat managerCompat1 = NotificationManagerCompat.from(getContext());
        managerCompat1.notify(notificationID, builder.build());
    }

}