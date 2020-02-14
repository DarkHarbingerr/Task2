package com.komurdzhiev.task2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    GridView gridview = null;
    Films filmPage = null;
    String startPageURL = "https://kudago.com/public-api/v1.4/movies/?page=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadData(startPageURL);

        Button btnNext = (Button)findViewById(R.id.btn_next);
        Button btnPrev = (Button)findViewById(R.id.btn_prev);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filmPage != null)
                    if(filmPage.getNext() != null)
               LoadData(filmPage.getNext().toString());
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filmPage != null)
                    if(filmPage.getPrevious() != null)
                        LoadData(filmPage.getPrevious().toString());
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), new FilmCellAdapter(getApplicationContext(), filmPage).getItemTitle(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return filmPage;
    }

    private void LoadData(String url){
        try {
            filmPage = (Films) getLastCustomNonConfigurationInstance();
            if(filmPage == null)
                filmPage = new HttpRequest().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(new FilmCellAdapter(getApplicationContext(), filmPage));
    }
}
