package com.example.josepablo.foursquareapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josepablo.foursquareapp.objects.Ubicacion;

public class DetailActivity extends AppCompatActivity {

    private Ubicacion curUbicacion;
    private CollapsingToolbarLayout toolbarLayout;
    private TextView tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        curUbicacion = intent.getExtras().getParcelable("parcelable_location");

        // TODO E.03: Parse Additional Data from URL
        //
        //

        Toast.makeText(this, curUbicacion.getName(), Toast.LENGTH_SHORT).show();

        tvDetail = (TextView) findViewById(R.id.tvDetail);

        tvDetail.setText(curUbicacion.getDescription());

        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        toolbar.setTitle(curUbicacion.getName());
        toolbarLayout.setBackground(getDrawable(curUbicacion.getImg()));
        toolbarLayout.setTitle(curUbicacion.getName());


    }
}
