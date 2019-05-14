package org.d3ifcool.base.presenters;

import org.d3ifcool.base.interfaces.objects.DosenPembimbingView;
import org.d3ifcool.base.interfaces.objects.DosenReviewerView;
import org.d3ifcool.base.interfaces.objects.DosenView;
import org.d3ifcool.base.interfaces.works.DosenWorkView;
import org.d3ifcool.base.interfaces.lists.DosenListView;
import org.d3ifcool.base.models.Dosen;
import org.d3ifcool.base.networks.bridge.ApiClient;
import org.d3ifcool.base.networks.api.ApiInterfaceDosen;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 3/2/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class DosenPresenter {

    private DosenListView viewResult;
    private DosenWorkView viewEditor;
    private DosenView viewObject;
    private DosenPembimbingView viewObjectPembimbing;
    private DosenReviewerView viewObjectReviewer;


    public DosenPresenter(DosenListView viewResult) {
        this.viewResult = viewResult;
    }

    public DosenPresenter(DosenListView viewResult, DosenWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public DosenPresenter(DosenWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public DosenPresenter(DosenView viewObject) {
        this.viewObject = viewObject;
    }

    public DosenPresenter(DosenListView viewResult, DosenView viewObject) {
        this.viewResult = viewResult;
        this.viewObject = viewObject;
    }

    public DosenPresenter(DosenPembimbingView viewObjectPembimbing, DosenReviewerView viewObjectReviewer) {
        this.viewObjectPembimbing = viewObjectPembimbing;
        this.viewObjectReviewer = viewObjectReviewer;
    }

    public DosenPresenter(DosenReviewerView viewObjectReviewer) {
        this.viewObjectReviewer = viewObjectReviewer;
    }

    public DosenPresenter(DosenPembimbingView viewObjectPembimbing) {
        this.viewObjectPembimbing = viewObjectPembimbing;
    }

    public DosenPresenter(DosenListView viewResult, DosenReviewerView viewObjectReviewer) {
        this.viewResult = viewResult;
        this.viewObjectReviewer = viewObjectReviewer;
    }

    public void getDosen(){
        viewResult.showProgress();

        ApiInterfaceDosen apiInterface = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<List<Dosen>> call = apiInterface.getDosen();
        call.enqueue(new Callback<List<Dosen>>() {
            @Override
            public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                viewResult.hideProgress();
                viewResult.onGetListDosen(response.body());
            }

            @Override
            public void onFailure(Call<List<Dosen>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void createDosen(String nip, String nama, String kode, String kontak, String email){
        viewEditor.showProgress();

        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.createDosen(nip,nama,kode,kontak,email);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void deleteDosen(String nip){
        viewEditor.showProgress();

        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.deleteDosen(nip);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }


    public void updateDosen(String nip_dosen, String dsn_nama, String dsn_kode, String dsn_kontak, String dsn_email) {
        viewEditor.showProgress();
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.updateDosen(nip_dosen, dsn_nama, dsn_kode, dsn_kontak, dsn_email);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }
    public void updateDosen(String nip_dosen, String dsn_nama, String dsn_kode, String dsn_kontak,String dsn_foto, String dsn_email) {
        viewEditor.showProgress();
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.updateDosen(nip_dosen, dsn_nama, dsn_kode, dsn_kontak, dsn_foto,dsn_email);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getDosenByParameter(String dsn_nip){
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewObject.hideProgress();
                viewObject.onGetObjectDosen(response.body());
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewObject.hideProgress();
                viewObject.onFailed(t.getLocalizedMessage());
            }
        });
    }


    public void getDosenPembimbing(String dsn_nip){
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewObjectPembimbing.hideProgress();
                viewObjectPembimbing.onGetObjectDosenPembimbing(response.body());
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewObjectPembimbing.hideProgress();
                viewObjectPembimbing.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getDosenReviewer(String dsn_nip){
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewObjectReviewer.hideProgress();
                viewObjectReviewer.onGetObjectDosenReviewer(response.body());
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewObjectReviewer.hideProgress();
                viewObjectReviewer.onFailed(t.getLocalizedMessage());
            }
        });
    }
}