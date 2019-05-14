package org.d3ifcool.koor.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.base.interfaces.lists.InformasiListView;
import org.d3ifcool.base.models.Informasi;
import org.d3ifcool.base.presenters.InformasiPresenter;
import org.d3ifcool.koor.R;
import org.d3ifcool.koor.activities.editors.KoorInformasiTambahActivity;
import org.d3ifcool.koor.adapters.KoorInformasiViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorInformasiFragment extends Fragment implements InformasiListView {

    private RecyclerView rv_informasi;
    private FloatingActionButton fab_informasi;
    private ArrayList<Informasi> arrayList = new ArrayList<>();
    private KoorInformasiViewAdapter adapter;
    private ProgressDialog progressDialog;
    private InformasiPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;

    public KoorInformasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_koor_informasi, container, false);

        rv_informasi = rootView.findViewById(R.id.frg_koor_info_home_recyclerview);
        fab_informasi = rootView.findViewById(R.id.frg_koor_info_home_fab);
        presenter = new InformasiPresenter(this);
        swipeRefreshLayout = rootView.findViewById(R.id.frg_koor_info_home_swiperefresh);
        empty_view = rootView.findViewById(R.id.view_emptyview);

        adapter = new KoorInformasiViewAdapter(getContext());
        adapter.setLayoutType(R.layout.content_list_all_informasi);
        rv_informasi.setLayoutManager(new LinearLayoutManager(getContext()));

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        presenter.getInformasi();

        fab_informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorInformasiTambahActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getInformasi();
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getInformasi();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onGetListInformasi(List<Informasi> informasi) {
        arrayList.clear();
        arrayList.addAll(informasi);

        adapter.addItem(arrayList);
        swipeRefreshLayout.setRefreshing(false);
        rv_informasi.setAdapter(adapter);

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}