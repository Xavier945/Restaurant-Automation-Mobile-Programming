package com.example.lokanta_otomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText yoneticiad,yoneticisifre;
    Button yoneticigrs_buton,musterigrs_buton,yonetici_kayit;
    Database_Helper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yoneticiad=(EditText)findViewById(R.id.yoneticiad);
        yoneticisifre=(EditText)findViewById(R.id.yoneticisifre);
        yoneticigrs_buton=(Button)findViewById(R.id.yoneticigrs_buton);
        musterigrs_buton=(Button)findViewById(R.id.musterigrs_buton);
        yonetici_kayit=(Button)findViewById(R.id.yonetici_kayit);

        DB= new Database_Helper(this);

        yoneticigrs_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yoneticiadTXT = yoneticiad.getText().toString();
                String yoneticisifreTXT = yoneticisifre.getText().toString();
                if(yoneticiadTXT.equals("") || yoneticisifreTXT.equals("")){
                    Toast.makeText(getApplicationContext(), "Alanların Doldurulması Lazım.", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean gecerliKullanici = DB.giriskontrolu(yoneticiadTXT, yoneticisifreTXT);
                    if (gecerliKullanici == true) {
                        Toast.makeText(getApplicationContext(), "Giriş Yapıldı.", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this, yonetici_islemleri.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Yanlıs sifre veya isim girildi.", Toast.LENGTH_SHORT).show();
                    }
                }
            }});

            yonetici_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yonetici_adTXT = yoneticiad.getText().toString();
                String yonetici_sifreTXT = yoneticisifre.getText().toString();
                if(yonetici_adTXT.trim().isEmpty() ||yonetici_sifreTXT.trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Alanlarin doldurulmasi lazim.", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean kaydedilmismibak = DB.yonetici_kaydett(yonetici_adTXT,yonetici_sifreTXT);
                    if(kaydedilmismibak==true){ Toast.makeText(MainActivity.this, "Kayit yapıldi", Toast.LENGTH_SHORT).show();}
                    else{ Toast.makeText(MainActivity.this, "Kayıt yapilmadi", Toast.LENGTH_SHORT).show();} }
            }});

        musterigrs_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, musteri_islemleri.class);
                startActivity(intent);
            }
        });

    }
}