package com.example.lab_51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logOutButton = findViewById(R.id.button);
        logOutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut(); // Log out from Firebase

            // Optionally, start the login activity and clear the activity stack
            Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        Button resetPasswordButton = findViewById(R.id.button2);
        resetPasswordButton.setOnClickListener(v -> {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser(); // Get the currently logged-in user

            if (user != null) {
                String emailAddress = user.getEmail(); // Get the email address

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Password reset email sent.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle the failure case
                                Toast.makeText(MainActivity.this, "Failed to send password reset email.", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                // No user is logged in
                Toast.makeText(MainActivity.this, "No user is logged in.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}