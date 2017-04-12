package com.example.sonpham.questionmarks;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Son Pham on 4/12/2017.
 */

public class QuanLyCauHoi extends SQLiteOpenHelper{
    private static String DB_PATH="/data/data/com.example.sonpham.questionmarks/databases/";
    private static String DB_NAME="yes_or_no.sqlite";
    private static int DATABASE_VERSION=1;
    private static String TABLE_NAME="table_question";
    private static String ID="_id";
    private static String CAU_HOI="cau_hoi";
    private static String CAU_A="A";
    private static String CAU_B="B";
    private static String DAP_AN="dap_an";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    public QuanLyCauHoi(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //database chua ton tai
        }

        if(checkDB != null)
            checkDB.close();

        return checkDB != null ? true :false;
    }

    private void copyDataBase() throws IOException {

        //mo db trong thu muc assets nhu mot input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        //duong dan den db se tao
        String outFileName = DB_PATH + DB_NAME;

        //mo db giong nhu mot output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //truyen du lieu tu inputfile sang outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0)
        {
            myOutput.write(buffer, 0, length);
        }

        //Dong luon
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDataBase(); //kiem tra db

        if(dbExist){
            //khong lam gi ca, database da co roi
        }
        else{
            this.getReadableDatabase();
            try {
                copyDataBase(); //chep du lieu
            }
            catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }



/*    public Cursor laytatcacauhoi()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor contro=db.rawQuery("select * from tablecauhoi", null);
        return contro;
    }*/

    public ArrayList<cauhoi> layNcaungaunhien(int n)
    {
        ArrayList<cauhoi> ds_cauhoi=new ArrayList<cauhoi>();
        SQLiteDatabase db=this.getReadableDatabase();
        String limit="0,"+n;
        Cursor contro=db.query(
                TABLE_NAME,
                null,//mang chuoi cac cot can lay
                null, //menh de where vd: "level=?"
                null,//doi so menh de where vd: new String[]{"1"}
                null, //group by
                null, //having (dieu kien cho group by
                "random()",//order by (xap xep thu tu)
                limit//gioi han so cau vd: "0,5"
        );
        if(contro.moveToFirst())
        {
            do{
                cauhoi x=new cauhoi();
                x.setID(Integer.parseInt(contro.getString(0)));
                x.setCauhoi(contro.getString(1));
                x.setDap_an(contro.getString(4));
                ds_cauhoi.add(x);


            }while (contro.moveToNext());
        }
        return ds_cauhoi;
    }

}
