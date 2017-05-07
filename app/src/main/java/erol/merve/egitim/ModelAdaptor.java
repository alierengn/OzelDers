package erol.merve.egitim;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ayazagan on 18.04.2017.
 */

public class ModelAdaptor extends BaseAdapter {

    private List<Model> mListe;
    private LayoutInflater mInflater;
    private Context context;

    public ModelAdaptor(Activity activity, List<Model> mListe) {
        this.context = activity;
        this.mListe = mListe;
        this.mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
    }

    @Override
    public int getCount() {
        return mListe.size();
    }

    @Override
    public Object getItem(int position) {
        return mListe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listeView;
        listeView = mInflater.inflate(R.layout.liste_layout,null);

        ImageView simgeView = (ImageView) listeView.findViewById(R.id.simge);
        TextView isimView = (TextView) listeView.findViewById(R.id.txt_isim);
        TextView egitimView = (TextView) listeView.findViewById(R.id.txt_egitim);
        TextView sehirView = (TextView) listeView.findViewById(R.id.txt_sehir);
        TextView dersView = (TextView) listeView.findViewById(R.id.txt_ders);
        RatingBar listeratingBar = (RatingBar) listeView.findViewById(R.id.listeratingBar);

        Model kisi = mListe.get(position);

        isimView.setText(kisi.getIsim());
        egitimView.setText(kisi.getEgitim());
        sehirView.setText(kisi.getSehir());
        dersView.setText(kisi.getDers());
        listeratingBar.setRating(Float.parseFloat(kisi.getRating()));
        Glide.with(context).load(kisi.getResim()).into(simgeView);

        return listeView;
    }
}
