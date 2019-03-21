package org.d3ifcool.finpro.activities;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.finpro.adapters.MahasiswaPagerAdapter;
import org.d3ifcool.mahasiswa.activities.MahasiswaPemberitahuanActivity;
import org.d3ifcool.mahasiswa.activities.MahasiswaProfilActivity;
import org.d3ifcool.service.interfaces.MahasiswaLoginView;
import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.service.presenters.DataLoginPresenter;

public class MahasiswaMainActivity extends AppCompatActivity implements MahasiswaLoginView {

    private MenuItem prevMenuItem = null;
    private ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;
    private SessionManager sessionManager;
    private DataLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_main);

        setTitle(R.string.title_informasi);
        getSupportActionBar().setElevation(0);
        // -----------------------------------------------------------------------------------------
        mViewPager = findViewById(R.id.act_mhs_home_viewpager);
        bottomNavigationView = findViewById(R.id.act_mhs_home_bottom_navigation);
        MahasiswaPagerAdapter mPagerAdapter = new MahasiswaPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        sessionManager = new SessionManager(this);
        presenter = new DataLoginPresenter(this, MahasiswaMainActivity.this);
        presenter.getDataMahasiswaLogin(sessionManager.getSessionUsername());
        // -----------------------------------------------------------------------------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.bottom_menu_mhs_informasi:
                        mViewPager.setCurrentItem(0);
                        setTitle(R.string.title_informasi);
                        break;
                    case R.id.bottom_menu_mhs_proyekakhir:
                        mViewPager.setCurrentItem(1);
                        setTitle(R.string.title_proyekakhir);
                        break;
                    case R.id.bottom_menu_mhs_judulpa:
                        mViewPager.setCurrentItem(2);
                        setTitle(R.string.title_judulpa);
                        break;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

                switch (position) {
                    case 0:
                        setTitle(R.string.title_informasi);
                        break;
                    case 1:
                        setTitle(R.string.title_proyekakhir);
                        break;
                    case 2:
                        setTitle(R.string.title_judulpa);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toolbar_menu_pemberitahuan:
                Intent intentPemberitahuan = new Intent(MahasiswaMainActivity.this, MahasiswaPemberitahuanActivity.class);
                startActivity(intentPemberitahuan);
                break;
            case R.id.toolbar_menu_profil:
                Intent intentProfil = new Intent(MahasiswaMainActivity.this, MahasiswaProfilActivity.class);
                startActivity(intentProfil);
                break;
            case R.id.toolbar_menu_keluar:
                Intent intentKeluar = new Intent(MahasiswaMainActivity.this, LoginActivity.class);
                startActivity(intentKeluar);
                sessionManager.removeSession();
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onRequestSuccess(String message, Mahasiswa mahasiswa) {
        sessionManager.createSessionDataMahasiswa(mahasiswa);
    }

    @Override
    public void onRequestError(String message) {

    }

}