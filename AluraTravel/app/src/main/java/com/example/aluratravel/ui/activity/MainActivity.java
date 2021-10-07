package com.example.aluratravel.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.aluratravel.R;
import com.example.aluratravel.ui.adapter.PackagesListAdapter;
import com.example.aluratravel.dao.PackagesDao;
import com.example.aluratravel.model.Packages;
import com.example.aluratravel.ui.constants.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Alura Viagens";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(APPBAR_TITLE);

        configureAdapter();
    }

    private void configureAdapter() {
        ListView packagesList = findViewById(R.id.listView);
        List<Packages> packages = new PackagesDao().list();
        packagesList.setAdapter(new PackagesListAdapter(packages, this));

        packagesList.setOnItemClickListener((adapterView, view, position, id) -> renderPackageSummaryActivity(position, packages));
    }

    private void renderPackageSummaryActivity(int position, List<Packages> packages) {
        Packages selectedPackage = packages.get(position);
        Intent intent = new Intent(MainActivity.this, PackageSummaryActivity.class);
        intent.putExtra(Constants.PACKAGE, selectedPackage);
        startActivity(intent);
    }
}