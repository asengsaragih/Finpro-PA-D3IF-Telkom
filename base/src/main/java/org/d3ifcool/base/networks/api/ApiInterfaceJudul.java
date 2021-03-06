package org.d3ifcool.base.networks.api;

import org.d3ifcool.base.models.Judul;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER_1;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER_2;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY_1;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY_2;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_DELETE;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_JUDUL;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_MAHASISWA;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_SEARCH;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_STATUS;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_UPDATE;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_JUDUL_PA;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.VAR_JUDUL;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.VAR_PARAMS;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.VAR_QUERY;


/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 26/02/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public interface ApiInterfaceJudul {

    @FormUrlEncoded
    @POST(URL_JUDUL_PA)
    Call<Judul> createJudul(
            @Field("judul_nama") String judul_nama,
            @Field("kategori_id") int kategori_id,
            @Field("judul_deskripsi") String judul_deskripsi,
            @Field("dsn_nip") String dsn_nip,
            @Field("judul_status") String judul_status
    );

    @FormUrlEncoded
    @POST(URL_JUDUL_PA + PATH_UPDATE + PARAMETER_JUDUL)
    Call<Judul> updateJudul(
            @Path(VAR_JUDUL) int judul_id,
            @Field("judul_nama") String judul_nama,
            @Field("kategori_id") int kategori_id,
            @Field("judul_deskripsi") String judul_deskripsi
    );

    @GET(URL_JUDUL_PA)
    Call<List<Judul>> getJudul();

    @POST(URL_JUDUL_PA + PATH_DELETE + PARAMETER_JUDUL)
    Call<Judul> deleteJudul(@Path(VAR_JUDUL) int judul_id);

    @FormUrlEncoded
    @POST(URL_JUDUL_PA + PATH_UPDATE + PATH_STATUS + PARAMETER_JUDUL)
    Call<Judul> updateStatusJudul(@Path(VAR_JUDUL) int judul_id,
                                  @Field("judul_status") String judul_status);

    @GET(URL_JUDUL_PA + PATH_SEARCH + BASE_PARAMETER + PARAMETER_QUERY)
    Call<List<Judul>> searchJudulBy(
            @Path(VAR_PARAMS) String parameter,
            @Path(VAR_QUERY) String query
    );

    @GET(URL_JUDUL_PA + PATH_SEARCH + BASE_PARAMETER_1 + PARAMETER_QUERY_1 + BASE_PARAMETER_2 + PARAMETER_QUERY_2)
    Call<List<Judul>> searchJudulByTwo(
            @Path(VAR_PARAMS+"1") String parameter1,
            @Path(VAR_QUERY+"1") String query1,
            @Path(VAR_PARAMS+"2") String parameter2,
            @Path(VAR_QUERY+"2") String query2
    );

    @GET(URL_JUDUL_PA + PATH_SEARCH + PATH_MAHASISWA + BASE_PARAMETER + PARAMETER_QUERY)
    Call<List<Judul>> searchJudulMahasiswaBy(
            @Path(VAR_PARAMS) String parameter,
            @Path(VAR_QUERY) String query
    );

}
