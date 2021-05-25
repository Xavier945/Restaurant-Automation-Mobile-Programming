package com.example.lokanta_otomasyonu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class yonetici_islemleri extends AppCompatActivity {
    EditText asci_adsoyad, asci_tc, asci_maas, yemek_ad, yemek_fiyat;
    Button asci_ekle, asci_sil, asci_goruntule,asci_guncelle,butongeridon,yemek_ara,asci_ara;
    Button yemek_ekle, yemek_sil,yemek_guncelle,yemek_goruntule;
    Database_Helper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici_islemleri);
        asci_adsoyad =findViewById(R.id.asci_adsoyad);
        asci_tc =findViewById(R.id.asci_tc);
        asci_maas =findViewById(R.id.asci_maas);
        yemek_ad =findViewById(R.id.yemek_ad);
        yemek_fiyat=findViewById(R.id.yemek_fiyat);
        asci_ekle =findViewById(R.id.asci_ekle);
        asci_sil =findViewById(R.id.asci_sil);
        asci_guncelle=findViewById(R.id.asci_guncelle);
        asci_goruntule =findViewById(R.id.asci_goruntule);
        asci_ara =findViewById(R.id.asci_ara);
        yemek_ekle =findViewById(R.id.yemek_ekle);
        yemek_sil =findViewById(R.id.yemek_sil);
        yemek_guncelle=findViewById(R.id.yemek_guncelle);
        yemek_goruntule =findViewById(R.id.yemek_goruntule);
        yemek_ara =findViewById(R.id.yemek_ara);
        butongeridon =findViewById(R.id.butongeridon);

        DB = new Database_Helper(this);

        butongeridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        yemek_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yemekadTXT = yemek_ad.getText().toString();
                String yemekfiyatTXT = yemek_fiyat.getText().toString();
                if(yemekadTXT.trim().isEmpty() ||yemekfiyatTXT.trim().isEmpty()){
                    Toast.makeText(yonetici_islemleri.this, "Alanlarin doldurulmasi lazim.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean kaydedilmismibak = DB.yemek_ekle(yemekadTXT,yemekfiyatTXT);
                    if(kaydedilmismibak==true){ Toast.makeText(yonetici_islemleri.this, "Kayit yapildi", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(yonetici_islemleri.this, "Kayit yapilamadi", Toast.LENGTH_SHORT).show(); }
                }
            }        });

        yemek_sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yemekadTXT = yemek_ad.getText().toString();

                if(yemekadTXT.trim().isEmpty() ){
                    Toast.makeText(yonetici_islemleri.this, "Alanlarin doldurulmasi lazim.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Integer deletedRows=DB.yemek_sil(yemekadTXT);
                    if(deletedRows>0){ Toast.makeText(yonetici_islemleri.this, "Yemek Bilgileri Silindi", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(yonetici_islemleri.this, "Yemek Bilgileri Silinemedi", Toast.LENGTH_SHORT).show(); }
                }
            }        });

        yemek_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yemekadTXT = yemek_ad.getText().toString();
                String yemekfiyatTXT = yemek_fiyat.getText().toString();
                if(yemekadTXT.trim().isEmpty() ||yemekfiyatTXT.trim().isEmpty()){
                    Toast.makeText(yonetici_islemleri.this, "Ad/fiyat alaninin doldurulmasi lazim.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean guncellenmismibak = DB.yemek_guncelle(yemekadTXT,yemekfiyatTXT);
                    if(guncellenmismibak==true){ Toast.makeText(yonetici_islemleri.this, "Guncelleme yapildi", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(yonetici_islemleri.this, "Guncelleme yapilmadi", Toast.LENGTH_SHORT).show();}
                }
            }        });

        yemek_goruntule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=DB.yemek_bilgilerinial();
                if (res.getCount()==0) {
                    //birsey yoksa
                    showMessage("Hata","veri bulunamadi");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Yemek id: "+res.getString(0)+"\n");
                    buffer.append("Yemek ad: "+res.getString(1)+"\n");
                    buffer.append("Yemek fiyat: "+res.getString(2)+"\n \n");
                }

                showMessage("Yemekler",buffer.toString());

              }        });

        yemek_ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yemekadTXT = yemek_ad.getText().toString();
                Cursor res=DB.yemek_ara(yemekadTXT);
                if (res.getCount()==0) {
                    //birsey yoksa
                    showMessage("Hata","Yemek bulunamadi");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Yemek id: "+res.getString(0)+"\n");
                    buffer.append("Yemek ad: "+res.getString(1)+"\n");
                    buffer.append("Yemek fiyat: "+res.getString(2)+"\n \n");
                }

                showMessage("Aranan Yemek",buffer.toString());

            }
        });

        asci_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String asciadsoyadTXT = asci_adsoyad.getText().toString();
                String ascitcTXT = asci_tc.getText().toString();
                String ascimaasTXT = asci_maas.getText().toString();
                if(asciadsoyadTXT.trim().isEmpty() ||ascitcTXT.trim().isEmpty()||ascimaasTXT.trim().isEmpty()){
                    Toast.makeText(yonetici_islemleri.this, "Alanlarin doldurulmasi lazim.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean kaydedilmismibak = DB.asci_kaydet(asciadsoyadTXT,ascitcTXT, ascimaasTXT);
                    if(kaydedilmismibak==true) {Toast.makeText(yonetici_islemleri.this, "Kayit yapildi", Toast.LENGTH_SHORT).show();}
                    else {Toast.makeText(yonetici_islemleri.this, "Kayit yapimaldi", Toast.LENGTH_SHORT).show();}
                }
            }        });

        asci_sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String asciadsoyadTXT = asci_adsoyad.getText().toString();


                if(asciadsoyadTXT.trim().isEmpty() ) {
                    Toast.makeText(yonetici_islemleri.this, "Ad/soyad alaninin doldurulmasi lazim", Toast.LENGTH_SHORT).show();

                }else{
                    Integer deletedRows=DB.asci_sil(asciadsoyadTXT);
                    if(deletedRows>0){ Toast.makeText(yonetici_islemleri.this, "Asci Bilgileri Silindi", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(yonetici_islemleri.this, "Asci Bilgileri Silinemedi", Toast.LENGTH_SHORT).show(); }
                }
            }        });

        asci_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String asciadsoyadTXT = asci_adsoyad.getText().toString();
                String ascitcTXT = asci_tc.getText().toString();
                String ascimaasTXT = asci_maas.getText().toString();
                if(asciadsoyadTXT.trim().isEmpty() ||ascitcTXT.trim().isEmpty()||ascimaasTXT.trim().isEmpty()) {
                    Toast.makeText(yonetici_islemleri.this, "Alanlarin doldurulmasi lazim.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean guncellenmismibak = DB.asci_guncelle(asciadsoyadTXT,ascitcTXT, ascimaasTXT);
                    if(guncellenmismibak==true){ Toast.makeText(yonetici_islemleri.this, "Guncellendi", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(yonetici_islemleri.this, "Guncellenmedi", Toast.LENGTH_SHORT).show();}

                }
            }        });

        asci_goruntule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=DB.asci_bilgilerinial();
                if (res.getCount()==0) {
                    //birsey yoksa
                    showMessage("Hata","veri bulunamadi");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Asci id: "+res.getString(0)+"\n");
                    buffer.append("Asci ad soyad: "+res.getString(1)+"\n");
                    buffer.append("Asci  TC: "+res.getString(2)+"\n");
                    buffer.append("Asci maas: "+res.getString(3)+"\n \n");
                }
                //tum verileri goster
                showMessage("Veriler",buffer.toString());
            }        });


        asci_ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String asciadsoyadTXT = asci_adsoyad.getText().toString();
                Cursor res=DB.asci_ara(asciadsoyadTXT);
                if (res.getCount()==0) {
                    //birsey yoksa
                    showMessage("Hata","Asci bulunamadi");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Asci id: "+res.getString(0)+"\n");
                    buffer.append("Asci ad soyad: "+res.getString(1)+"\n");
                    buffer.append("Asci  TC: "+res.getString(2)+"\n");
                    buffer.append("Asci maas: "+res.getString(3)+"\n \n");
                }

                showMessage("Aranan Asci ",buffer.toString());

            }});

    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
