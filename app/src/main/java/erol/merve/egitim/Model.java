package erol.merve.egitim;

import android.net.Uri;

/**
 * Created by ayazagan on 18.04.2017.
 */

public class Model {
    String isim;
    String egitim;
    String sehir;
    String yas;
    String tel;
    String bilgi;
    String ders;
    String resim;
    String rating;


    public Model(String isim, String egitim, String sehir, String yas, String tel, String bilgi, String ders, String resim,String r) {
        this.isim = isim;
        this.egitim = egitim;
        this.sehir = sehir;
        this.yas = yas;
        this.tel = tel;
        this.bilgi = bilgi;
        this.ders = ders;
        this.resim = resim;
        this.rating = r;

    }

    public Model() {

    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Model(String isim) {
        this.isim = isim;
    }

    public String getDers() {
        return ders;
    }

    public void setDers(String ders) {
        this.ders = ders;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }


    public String getEgitim() {
        return egitim;
    }

    public void setEgitim(String egitim) {
        this.egitim = egitim;
    }


    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }


    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBilgi() {
        return bilgi;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }

   public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

}
