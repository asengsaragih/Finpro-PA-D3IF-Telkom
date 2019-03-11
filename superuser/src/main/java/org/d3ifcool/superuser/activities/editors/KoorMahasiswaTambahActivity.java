package org.d3ifcool.superuser.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.d3ifcool.superuser.R;

public class KoorMahasiswaTambahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_tambah);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_mahasiswa_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}