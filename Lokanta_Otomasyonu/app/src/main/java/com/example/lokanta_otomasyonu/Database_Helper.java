package com.example.lokanta_otomasyonu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_Helper extends SQLiteOpenHelper {
    public Database_Helper(Context context  ) {
        super(context, "veritabani0.db", null, 1);
        SQLiteDatabase DB=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table yoneticiler(yoneticiid INTEGER PRIMARY KEY AUTOINCREMENT,yoneticiad TEXT, yoneticisifre TEXT)");
        DB.execSQL("create Table musteriler(musteriid INTEGER PRIMARY KEY AUTOINCREMENT,  musteriadsoyad TEXT,musteritc TEXT, musterigirissaat TEXT, mustericikissaat TEXT)");
        DB.execSQL("create Table yemekler(yemekid INTEGER PRIMARY KEY AUTOINCREMENT,yemekad TEXT, yemekfiyat TEXT)");
        DB.execSQL("create Table ascilar(asciid INTEGER PRIMARY KEY AUTOINCREMENT,asciadsoyad TEXT, ascitc TEXT,ascimaas TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop table if exists yoneticiler");
        DB.execSQL("drop table if exists musteriler");
        DB.execSQL("drop table if exists yemekler");
        DB.execSQL("drop table if exists ascilar");

    }

    public boolean yonetici_kaydett(String yoneticiad, String yoneticisifre){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("yoneticiad",yoneticiad);
        contentValues.put("yoneticisifre",yoneticisifre);

        long result=DB.insert("yoneticiler",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public boolean giriskontrolu(String yoneticiad, String yoneticisifre) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM yoneticiler WHERE yoneticiad=? AND yoneticisifre=?",new String[]{yoneticiad,yoneticisifre});
        if(c.getCount() <= 0) {
            return false;
        } else {
            return true;
        } }

    public boolean musteri_bilgisikaydet(String musteriadsoyad,String musteritc,  String musterigirissaat,String mustericikissaat){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("musteriadsoyad",musteriadsoyad);
        contentValues.put("musteritc",musteritc);
        contentValues.put("musterigirissaat",musterigirissaat);
        contentValues.put("mustericikissaat",mustericikissaat);
        long result=DB.insert("musteriler",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Integer musteri_bilgisisil(String musteriadsoyad){
        SQLiteDatabase DB=this.getWritableDatabase();
        return DB.delete("musteriler","musteriadsoyad=?",new String[]{musteriadsoyad});

    }
    public boolean musteri_bilgisiguncelle(String musteriadsoyad,String musteritc, String musterigirissaat, String mustericikissaat){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("musteriadsoyad",musteriadsoyad);
        contentValues.put("musteritc",musteritc);
        contentValues.put("musterigirissaat",musterigirissaat);
        contentValues.put("mustericikissaat",mustericikissaat);
        Cursor cursor=DB.rawQuery("Select *from musteriler where musteriadsoyad=?",new String[]{musteriadsoyad});
        DB.update("musteriler",contentValues,"musteriadsoyad=?",new String[]{musteriadsoyad});
        return  true;

    }


    public Cursor musteri_bilgilerinial(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select *from musteriler ",null);
        return  cursor;
    }

    public Cursor musteri_ara(String musteriadsoyad){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select *from musteriler where musteriadsoyad=? ",new String[]{musteriadsoyad});
        return  cursor;
    }

    public boolean yemek_ekle(String yemekad, String yemekfiyat){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("yemekad",yemekad);
        contentValues.put("yemekfiyat",yemekfiyat);

        long result=DB.insert("yemekler",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Integer yemek_sil(String yemekad){
        SQLiteDatabase DB=this.getWritableDatabase();
        return DB.delete("yemekler","yemekad=?",new String[]{yemekad});

    }

    public boolean yemek_guncelle(String yemekad, String yemekfiyat){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("yemekad",yemekad);
        contentValues.put("yemekfiyat",yemekfiyat);

        Cursor cursor=DB.rawQuery("Select *from yemekler where yemekad=?",new String[]{yemekad});
        DB.update("yemekler",contentValues,"yemekad=?",new String[]{yemekad});
        return  true;
    }


    public Cursor yemek_bilgilerinial(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select *from yemekler ",null);
        return  cursor;
    }

    public Cursor yemek_ara(String yemekad){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select *from yemekler where yemekad=? ",new String[]{yemekad});
        return  cursor;
    }

    public boolean asci_kaydet(String asciadsoyad, String ascitc, String ascimaas){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("asciadsoyad",asciadsoyad);
        contentValues.put("ascitc",ascitc);
        contentValues.put("ascimaas",ascimaas);
        long result=DB.insert("ascilar",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Integer asci_sil(String asciadsoyad){
        SQLiteDatabase DB=this.getWritableDatabase();
        return DB.delete("ascilar","asciadsoyad=?",new String[]{asciadsoyad});
    }

    public boolean asci_guncelle(String asciadsoyad, String ascitc, String ascimaas){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("asciadsoyad",asciadsoyad);
        contentValues.put("ascitc",ascitc);
        contentValues.put("ascimaas",ascimaas);
        Cursor cursor=DB.rawQuery("Select *from ascilar where asciadsoyad=?",new String[]{asciadsoyad});
        DB.update("ascilar",contentValues,"asciadsoyad=?",new String[]{asciadsoyad});
        return  true;
    }


    public Cursor asci_bilgilerinial(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from ascilar ",null);
        return  cursor;
    }

    public Cursor asci_ara(String asciadsoyad){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from ascilar where asciadsoyad=? ",new String[]{asciadsoyad});
        return  cursor;
    }

}
