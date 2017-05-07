package erol.merve.egitim;

/**
 * Created by ayazagan on 20.04.2017.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Details extends AppCompatActivity{

    String uid;
    Model detayData;
    private TextView txtSehir,txtIsim,txtBilgi,txtTel,txtEgitim,txtYas,txtDers;
    private ImageView image;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        txtSehir = (TextView)findViewById(R.id.lbl_sehir);
        txtIsim=(TextView)findViewById(R.id.lbl_isim);
        txtBilgi=(TextView)findViewById(R.id.lbl_bilgi);
        txtTel=(TextView)findViewById(R.id.lbl_tel);
        txtEgitim=(TextView)findViewById(R.id.lbl_egitim);
        txtYas=(TextView)findViewById(R.id.lbl_yas);
        txtDers=(TextView)findViewById(R.id.lbl_ders);
        image=(ImageView)findViewById(R.id.simge);


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

        TumVeriyiAl();

       Button  btnRate= (Button) findViewById(R.id.oyla);
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Rating.class);
                intent.putExtra("User",uid);
                startActivity(intent);
            }
        });

   }


    private void TumVeriyiAl() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRefEgitim = database.getReference().child("Users").child(uid);

        dbRefEgitim.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                detayData = new Model();
                detayData.isim = dataSnapshot.child("isim").getValue().toString();
                detayData.sehir = dataSnapshot.child("sehir").getValue().toString();
                detayData.yas = dataSnapshot.child("yas").getValue().toString();
                detayData.egitim = dataSnapshot.child("egitim").getValue().toString();
                detayData.bilgi = dataSnapshot.child("bilgi").getValue().toString();
                detayData.tel = dataSnapshot.child("tel").getValue().toString();
                detayData.resim = dataSnapshot.child("resim").getValue().toString();


                txtSehir.setText("Şehir :"+detayData.sehir);
                txtDers.setText("Ders  :"+detayData.ders);
                txtYas.setText("Yaş  :"+detayData.yas);
                txtBilgi.setText("Bilgi  :"+detayData.bilgi);
                txtEgitim.setText("Egitim  :"+detayData.egitim);
                txtIsim.setText(detayData.isim);
                txtTel.setText("Tel  :"+detayData.tel);
                Glide.with(getApplicationContext()).load(detayData.getResim()).into(image);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


   }
}
