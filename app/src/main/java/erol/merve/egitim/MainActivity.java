package erol.merve.egitim;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;

public class MainActivity extends AppCompatActivity {
    ListView listViewGecmis;
    ArrayList<Model> egitimData;
    FirebaseAuth firebaseAuth;
    ArrayList<String> Uid = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        listViewGecmis = (ListView) findViewById(R.id.lvEgitmen);
           TumVeriyiAl();

    }




    private void TumVeriyiAl() {

        egitimData = new ArrayList<>();;
        Uid= new ArrayList<>();

        DatabaseReference dbRefEgitim = FirebaseDatabase.getInstance().getReference().child("Users");

        dbRefEgitim.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dspEgitim : dataSnapshot.getChildren()) {

                    Model model = new Model();

                    model.isim = dspEgitim.child("isim").getValue().toString();
                    model.yas = dspEgitim.child("yas").getValue().toString();
                    model.tel = dspEgitim.child("tel").getValue().toString();
                    model.sehir = dspEgitim.child("sehir").getValue().toString();
                    model.egitim = dspEgitim.child("egitim").getValue().toString();
                    model.ders = dspEgitim.child("ders").getValue().toString();
                    model.resim = dspEgitim.child("resim").getValue().toString();
                    model.rating = dspEgitim.child("rating").getValue().toString();
                        Uid.add(dspEgitim.getKey());
                    egitimData.add(model);
                }

                ModelAdaptor madaptor = new ModelAdaptor(MainActivity.this, egitimData);
                madaptor.notifyDataSetChanged();
                listViewGecmis.setAdapter(madaptor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        listViewGecmis.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                 String idKisi= Uid.get(position).toString();
                 Intent intent = new Intent(getApplicationContext(),Details.class);
                 intent.putExtra("User",idKisi);
                 startActivity(intent);
             //  Toast.makeText(getApplicationContext(),idKisi,Toast.LENGTH_LONG).show();

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                //sign out
                AuthUI.getInstance().signOut(this);
                Intent intent = new Intent(getApplicationContext(), Account.class);
                startActivity(intent);

                return true;

            case R.id.ders_kayit:
                Intent intent2 = new Intent(getApplicationContext(), Login.class);
                startActivity(intent2);

                return true;
            default:


                return super.onOptionsItemSelected(item);
        }}
}
