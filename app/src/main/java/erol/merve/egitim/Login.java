package erol.merve.egitim;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    Button kaydet;
    EditText isim, egitim, yas, ders, sehir, tel, bilgi;
    private Context context;
    FirebaseAuth firebaseAuth;
    Uri photoUri;
    String name;
    ImageView profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){

            name = user.getDisplayName();
            photoUri = user.getPhotoUrl();
        }

        profil = (ImageView) findViewById(R.id.simge);
        Glide.with(getApplicationContext()).load(photoUri).into(profil);

        isim = (EditText) findViewById(R.id.edit_isim);
        egitim = (EditText) findViewById(R.id.edit_egitim);
        yas = (EditText) findViewById(R.id.Edit_yas);
        ders = (EditText) findViewById(R.id.Edit_alan);
        sehir = (EditText) findViewById(R.id.edit_sehir);
        tel = (EditText) findViewById(R.id.edit_tel);
        bilgi = (EditText) findViewById(R.id.edit_acıklama);

        kaydet = (Button) findViewById(R.id.btn_login);


        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isim.getText().toString().isEmpty() || egitim.getText().toString().isEmpty() || yas.getText().toString().isEmpty() || ders.getText().toString().isEmpty() || sehir.getText().toString().isEmpty() || tel.getText().toString().isEmpty()) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Uyarı").setMessage("Lütfen tüm alanları doldurunuz").show();

                }
                else {

                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Users");
                    Model model = new Model();
                    model.isim = isim.getText().toString();
                    model.egitim = egitim.getText().toString();
                    model.sehir = sehir.getText().toString();
                    model.yas = yas.getText().toString();
                    model.tel = tel.getText().toString();
                    model.bilgi = bilgi.getText().toString();
                    model.ders = ders.getText().toString();
                    model.rating = "0";// PROBLEM VAR!
                    model.resim = photoUri.toString();
                    //transaction ogren

                    dbRef.push().setValue(model);
                    Toast.makeText(getApplicationContext(),"KaydetmeBaşarılı..",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }

            }
        });

    }


}