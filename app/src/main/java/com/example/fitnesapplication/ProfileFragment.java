package com.example.fitnesapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitnesapplication.Yoga.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {

    private EditText fio;
    private EditText username;
    private EditText email;
    private EditText contactPhone;
    private EditText address;
    private EditText country;
    private EditText bin;
    private Button saveButton;

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Инициализация Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Инициализация полей
        fio = view.findViewById(R.id.editTextFio);
        username = view.findViewById(R.id.editTextUsername);
        email = view.findViewById(R.id.editTextEmail);
        contactPhone = view.findViewById(R.id.editTextPhone);
        address = view.findViewById(R.id.editTextAddress);
        country = view.findViewById(R.id.editTextCountry);
        bin = view.findViewById(R.id.editTextBIN);
        saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });

        return view;
    }

    private void saveUserProfile() {
        String userFio = fio.getText().toString().trim();
        String userUsername = username.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userContactPhone = contactPhone.getText().toString().trim();
        String userAddress = address.getText().toString().trim();
        String userCountry = country.getText().toString().trim();
        String userBin = bin.getText().toString().trim();

        if (TextUtils.isEmpty(userFio) || TextUtils.isEmpty(userUsername) || TextUtils.isEmpty(userEmail)) {
            Toast.makeText(getContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = databaseReference.push().getKey();
        User user = new User(userFio, userUsername, userEmail, userContactPhone, userAddress, userCountry, userBin);

        if (userId != null) {
            databaseReference.child(userId).setValue(user);
            Toast.makeText(getContext(), "User profile saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error saving profile", Toast.LENGTH_SHORT).show();
        }
    }
}