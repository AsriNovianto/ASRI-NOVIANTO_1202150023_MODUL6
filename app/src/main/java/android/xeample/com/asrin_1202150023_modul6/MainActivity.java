package android.xeample.com.asrin_1202150023_modul6;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.RemoteMessage;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    EditText mEmail;
    EditText mPass;
    Button mDaftar;
    Button mMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }
    public void login(View view) {

    }

    public void daftar(View view) {
        mDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String pass = mPass.getText().toString();

                if (email.equals("")){
                    mEmail.setError("Required");
                    return;
                }

                if (pass.equals("")){
                    mPass.setError("Required");
                    return;

                }
                mFirebaseAnalytics.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user = mFirebaseAnalytics.getCurrentUser();
                                    assert user != null;
                                    Toast.makeText(MainActivity.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MainActivity.this, "Registrasi gagal!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        mMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String pass = mPass.getText().toString();

                if (email.equals("")){
                    mEmail.setError("Required");
                    return;
                }

                if (pass.equals("")){
                    mPass.setError("Required");
                    return;

                }
                mFirebaseAnalytics.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.d("MainActivity", "berhasil");
                                    FirebaseUser user = mFirebaseAnalytics.getCurrentUser();
                                    loginUpdate(user);
                                }else {
                                    Log.d("MainActivity", "gagal!", task.getException());
                                    loginUpdate(null);
                                }
                            }
                        });
            }
        });

    }
    private void loginUpdate(FirebaseUser user){
        if (user != null){
            Toast.makeText(MainActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(MainActivity.this, "gagal!", Toast.LENGTH_SHORT).show();
    }
}