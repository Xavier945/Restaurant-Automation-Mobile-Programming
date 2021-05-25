package com.example.lokanta_otomasyonu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class musteri_islemleri extends AppCompatActivity {
    EditText musteri_adsoyad,musteri_tc,musteri_cikissaat,musteri_gelissaat;
    Button musteri_ekle,musteri_sil,musteri_guncelle,musteri_goster,buttongeridon,musteri_ara;

    Database_Helper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteri_islemleri);

        musteri_adsoyad = (EditText) findViewById(R.id.musteri_adsoyad);
        musteri_tc = (EditText) findViewById(R.id.musteri_tc);
        musteri_cikissaat=(EditText) findViewById(R.id.musteri_cikissaat);
        musteri_gelissaat =(EditText) findViewById(R.id.musteri_gelissaat);
        musteri_ekle = (Button)findViewById(R.id.musteri_ekle);
        musteri_sil = (Button)findViewById(R.id.musteri_sil);
        musteri_guncelle = (Button)findViewById(R.id.musteri_guncelle);
        musteri_goster = (Button)findViewById(R.id.musteri_goster);
        musteri_ara = (Button)findViewById(R.id.musteri_ara);
        buttongeridon =(Button) findViewById(R.id.buttongeridon);
        DB= new Database_Helper(this);


        buttongeridon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        musteri_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String musteri_adsoyadTXT = musteri_adsoyad.getText().toString();
                String musteri_tcTXT = musteri_tc.getText().toString();
                String musterigelissaatTXT = musteri_gelissaat.getText().toString();
                String mustericikissaatTXT = musteri_cikissaat.getText().toString();
                if(musteri_adsoyadTXT.trim().isEmpty() ||musteri_tcTXT.trim().isEmpty()||musterigelissaatTXT.trim().isEmpty() ||mustericikissaatTXT.trim().isEmpty()){
                    Toast.makeText(musteri_islemleri.this, "Alanlarin doldurulmasi lazim.", Toast.LENGTH_SHORT).show();

                }else{
                    Boolean kaydedilmismibak = DB.musteri_bilgisikaydet(musteri_adsoyadTXT, musteri_tcTXT, musterigelissaatTXT,mustericikissaatTXT);
                    if(kaydedilmismibak==true){ Toast.makeText(musteri_islemleri.this, "Kayit yapildi", Toast.LENGTH_SHORT).show();}
                    else {Toast.makeText(musteri_islemleri.this, "Kayit yapilmadi", Toast.LENGTH_SHORT).show();}
                }
            }        });


        musteri_sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String musteri_adsoyadTXT = musteri_adsoyad.getText().toString();

                if(musteri_adsoyadTXT.trim().isEmpty() ){
                    Toast.makeText(musteri_islemleri.this, "Ad/soyad alaninin doldurulmasi lazim.", Toast.LENGTH_SHORT).show();

                }else{
                    Integer deletedRows=DB.musteri_bilgisisil(musteri_adsoyadTXT);
                    if(deletedRows>0){ Toast.makeText(musteri_islemleri.this, "Kayıt Silindi", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(musteri_islemleri.this, "Kayıt Silinemedi", Toast.LENGTH_SHORT).show(); }

                }
            }        });

        musteri_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String musteri_adsoyadTXT = musteri_adsoyad.getText().toString();
                String musteri_tcTXT = musteri_tc.getText().toString();
                String musterigelissaatTXT = musteri_gelissaat.getText().toString();
                String mustericikissaatTXT = musteri_cikissaat.getText().toString();
                if(musteri_adsoyadTXT.trim().isEmpty() ||musteri_tcTXT.trim().isEmpty()||musterigelissaatTXT.trim().isEmpty()||mustericikissaatTXT.trim().isEmpty()){
                    Toast.makeText(musteri_islemleri.this, "Alanlarin doldurulmasi lazim.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean guncellenmismibak = DB.musteri_bilgisiguncelle(musteri_adsoyadTXT, musteri_tcTXT, musterigelissaatTXT,mustericikissaatTXT);
                    if(guncellenmismibak==true){ Toast.makeText(musteri_islemleri.this, "Guncellendi", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(musteri_islemleri.this, "Guncellenmedi", Toast.LENGTH_SHORT).show(); }
                }
            }        });

        musteri_goster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=DB.musteri_bilgilerinial();
                if (res.getCount()==0) {
                    //birsey yoksa
                    showMessage("Hata","veri bulunamadi");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Musteri id: "+res.getString(0)+"\n");
                    buffer.append("Musteri adsoyad: "+res.getString(1)+"\n");
                    buffer.append("Musteri TC: "+res.getString(2)+"\n");
                    buffer.append("Gelis saat: "+res.getString(3)+"\n");
                    buffer.append("Cikis saat: "+res.getString(4)+"\n \n");
                }
                //tum verileri goster
                showMessage("Veriler",buffer.toString());
            }        });


        musteri_ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String musteri_adsoyadTXT = musteri_adsoyad.getText().toString();
                Cursor res=DB.musteri_ara(musteri_adsoyadTXT);
                if (res.getCount()==0) {
                    //birsey yoksa
                    showMessage("Hata","veri bulunamadi");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Musteri id: "+res.getString(0)+"\n");
                    buffer.append("Musteri adsoyad: "+res.getString(1)+"\n");
                    buffer.append("Musteri TC: "+res.getString(2)+"\n");
                    buffer.append("Gelis saat: "+res.getString(3)+"\n");
                    buffer.append("Cikis saat: "+res.getString(4)+"\n \n");
                }
                //tum verileri goster
                showMessage("Aranan Musteri",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}