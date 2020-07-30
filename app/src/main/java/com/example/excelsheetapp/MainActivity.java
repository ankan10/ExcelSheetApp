package com.example.excelsheetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    //libary to downoad file
    AsyncHttpClient client;
    Workbook workbook;
    List<String> year,y,w,r,l,k;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url="https://github.com/bharathirajatut/sample-excel-dataset/blob/master/airline.xls?raw=true";

        recyclerView=findViewById(R.id.recycledata);



        year=new ArrayList<>();
        y=new ArrayList<>();
        w=new ArrayList<>();
        r=new ArrayList<>();
        l=new ArrayList<>();
        k=new ArrayList<>();

        client=new AsyncHttpClient();
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(MainActivity.this,"Download Failed",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Toast.makeText(MainActivity.this,"Download Success",Toast.LENGTH_LONG).show();
                //Read the excel workbook using jxcel api. Create workbook Setting
                WorkbookSettings ws=new WorkbookSettings();
                ws.setGCDisabled(true);
                if (file!=null){
                    try {
                        workbook=Workbook.getWorkbook(file);
                        Sheet sheet=workbook.getSheet(0);
                        for (int i=0;i<sheet.getRows();i++){
                            Cell [] row=sheet.getRow(i);



                            year.add(row[0].getContents());
                            y.add(row[1].getContents());
                            w.add(row[2].getContents());
                            r.add(row[3].getContents());
                            l.add(row[4].getContents());
                            k.add(row[5].getContents());
                        }

                        showData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



    }

    private void showData() {
        adapter=new Adapter(this,year,y,w,r,l,k);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
