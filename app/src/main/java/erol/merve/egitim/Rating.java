package erol.merve.egitim;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;

/**
 * Created by ayazagan on 21.04.2017.
 */

public class Rating extends AppCompatActivity {
    TextView txtRate;
    RatingBar mBar;
    String uid;
    Button oyla;
    Float rdeger;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_layout);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                uid = null;
            } else {
                uid = extras.getString("User");
            }
        } else {
            uid = (String) savedInstanceState.getSerializable("User");//kayıtlı deger varsa onu alır.
        }

        mBar = (RatingBar) findViewById(R.id.myratingBar);
        txtRate = (TextView) findViewById(R.id.txtRate);
        oyla = (Button) findViewById(R.id.btn_oyla);

        mBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {

                txtRate.setText("" + rating);
                rdeger = rating;

            }
        });

        oyla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Users");
                dbRef.child(uid).child("rating").setValue(rdeger);
                Toast.makeText(getApplicationContext(), "Oylama Başarılı..", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
