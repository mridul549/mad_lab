package com.example.lab_51;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // If user is not signed in, redirect to SignInActivity
        if (currentUser != null) {
            startActivity(new Intent(HomePageActivity.this, MainActivity.class));
        }
        // Otherwise, stay on the HomePageActivity as the user is already signed in
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        Button btnSignIn = findViewById(R.id.btnSignIn);

        // Navigate to SignUpActivity when the Sign Up button is clicked
        btnSignUp.setOnClickListener(v -> startActivity(new Intent(HomePageActivity.this, SignUpActivity.class)));

        // Navigate to SignInActivity when the Sign In button is clicked
        btnSignIn.setOnClickListener(v -> startActivity(new Intent(HomePageActivity.this, SignInActivity.class)));
    }
}
