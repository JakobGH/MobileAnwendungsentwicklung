package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {


    private TextView welcomeTV;
    private EditText firstnameET;
    private EditText lastnameET;
    private EditText phoneNumberET;
    private Button submitBTN;
    private Button openPhoneBTN;
    private Button permissionBTN;
    private Button startSecondActivity;

    private static final String permissionReadContact = Manifest.permission.READ_CONTACTS;
    private static final int requestCode = 12345;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (MainActivity.requestCode == requestCode) {
            if (PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                welcomeTV.setText("Erfolgreich die Permissions erteilt");
            } else {
                welcomeTV.setText("Permission nicht erteilt.");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PackageManager.PERMISSION_GRANTED == checkSelfPermission(permissionReadContact)) {
                this.welcomeTV.setText("Permission erteilt.");
            } else {
                if (shouldShowRequestPermissionRationale(permissionReadContact)) {
                    welcomeTV.setText("Permission manuell erteilen");
                } else {
                    requestPermissions(new String[]{permissionReadContact}, requestCode);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startup();
    }

    private void startup() {
        this.welcomeTV = findViewById(R.id.welcomeTf);
        this.firstnameET = findViewById(R.id.firstnameEf);
        this.lastnameET = findViewById(R.id.lastnameEf);
        this.submitBTN = findViewById(R.id.submitBtn);
        this.phoneNumberET = findViewById(R.id.phonenumber);
        this.submitBTN = findViewById(R.id.submitBtn);
        this.openPhoneBTN = findViewById(R.id.openPhoneBtn);
        this.permissionBTN = findViewById(R.id.permission);
        this.startSecondActivity = findViewById(R.id.changeActivity);

        //Speichern eines Kontakts
        this.submitBTN.setOnClickListener(event -> {
            if (!TextUtils.isEmpty(firstnameET.getText())
                    && !TextUtils.isEmpty(lastnameET.getText())
                    && !TextUtils.isEmpty(phoneNumberET.getText())) {
                welcomeTV.setText(String.format("Hallo %s %s", firstnameET.getText().toString(), lastnameET.getText().toString()));

                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, String.format("%s %s", firstnameET.getText().toString(), lastnameET.getText().toString()))
                        .putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumberET.getText());
                intent.putExtra("finishActivityOnSaveCompleted", true);
                startActivity(intent);

                firstnameET.setText("");
                lastnameET.setText("");
                phoneNumberET.setText("");
            } else {
                Toast.makeText(this, R.string.emptyTF, Toast.LENGTH_SHORT).show();
            }
        });

        //Öffnen des Telefons mit der eingegebenen Nummer
        this.openPhoneBTN.setOnClickListener(event -> {
            if (!TextUtils.isEmpty(phoneNumberET.getText())) {
                Intent openPhone = new Intent(Intent.ACTION_DIAL);
                openPhone.setData(Uri.parse(String.format("tel:%s", phoneNumberET.getText().toString())));
                startActivity(openPhone);
            } else {
                Toast.makeText(this, R.string.noPhoneNumber, Toast.LENGTH_SHORT).show();
            }
        });

        //Permissions erteilen
        this.permissionBTN.setOnClickListener(listener -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{permissionReadContact}, requestCode);
            }
        });

        //Start einer weiteren Activity
        this.startSecondActivity.setOnClickListener(l -> {
            startActivity(new Intent(this, NavigationActivity.class));
        });
    }
}