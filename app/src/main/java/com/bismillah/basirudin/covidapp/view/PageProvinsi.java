package com.bismillah.basirudin.covidapp.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bismillah.basirudin.covidapp.R;
import com.bismillah.basirudin.covidapp.adapter.provinsi.AdapterProvinsi;
import com.bismillah.basirudin.covidapp.baseurl.Const;
import com.bismillah.basirudin.covidapp.baseurl.Func;
import com.bismillah.basirudin.covidapp.model.covid_03.api.ApiService;
import com.bismillah.basirudin.covidapp.model.covid_03.api.ApiUtil;
import com.bismillah.basirudin.covidapp.model.covid_03.provinsi.Attributes;
import com.bismillah.basirudin.covidapp.model.covid_03.provinsi.ModelProvinsi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageProvinsi extends AppCompatActivity {
    @BindView(R.id.provinsi_lay_reload)
    SwipeRefreshLayout layReload;
    @BindView(R.id.provinsi_total)
    TextView txtKasus;
    @BindView(R.id.provinsi_recycleview)
    RecyclerView rvProvinsi;

    private ApiService apiService;
    private List<Attributes> attributes;
    private AdapterProvinsi adapterProvinsi;
    private String totalKasus = "", totalPositif = "", totalSembuh = "", totalMati = "", sKasus = "";
    private int iPositif = 0, iSembuh = 0, iMati = 0;
    private String sNama = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_provinsi);
        ButterKnife.bind(this);
        apiService = ApiUtil.getService();
        attributes = new ArrayList<>();

        if (getIntent().hasExtra(Const.send_kasus)
                && getIntent().hasExtra(Const.send_positif)
                && getIntent().hasExtra(Const.send_sembuh)
                && getIntent().hasExtra(Const.send_mati)) {
            totalKasus = getIntent().getStringExtra(Const.send_kasus);
            totalPositif = getIntent().getStringExtra(Const.send_positif);
            totalSembuh = getIntent().getStringExtra(Const.send_sembuh);
            totalMati = getIntent().getStringExtra(Const.send_mati);
        }

        if (layReload.isRefreshing()) layReload.setRefreshing(false);
        layReload.setOnRefreshListener(this::getData);
        layReload.setColorScheme(R.color.colorPrimary);

        Log.d("AS", "onCreate: as " + ModelProvinsi.class.toString());

        getData();
    }

    private void getData() {
//        Attributes att;
//        for (int i = 0; i <20 ; i++) {
//            att=new Attributes(0,0,0,i,"Nama ",0);
//            attributes.add(att);
//        }
//
//        adapterProvinsi = new AdapterProvinsi(PageProvinsi.this, attributes, totalKasus);
//                        rvProvinsi.setLayoutManager(new LinearLayoutManager(PageProvinsi.this));
//                        rvProvinsi.setAdapter(adapterProvinsi);
//                        rvProvinsi.setItemAnimator(new DefaultItemAnimator());
//                        rvProvinsi.setHasFixedSize(true);
//                        adapterProvinsi.notifyDataSetChanged();
        load();
        if (!Func.internet(getApplicationContext())) {
            unload(Const.msg_internet);
            return;
        }

        apiService.getProvinsi().enqueue(new Callback<List<ModelProvinsi>>() {
            @Override
            public void onResponse(Call<List<ModelProvinsi>> call, Response<List<ModelProvinsi>> response) {
                try {
                    if (response.isSuccessful()) {
                        unload("");
                        if (attributes.size() > 0)
                            attributes.clear();

                        for (int i = 0; i < response.body().size(); i++) {
                            Attributes att = response.body().get(i).getAttributes();
                            Attributes attribute;
                            iPositif = att.getKasusPosi();
                            iSembuh = att.getKasusSemb();
                            iMati = att.getKasusMeni();
                            sNama = att.getProvinsi();

                            attribute = new Attributes(0, 0, iMati, iPositif, sNama, iSembuh);
                            attributes.add(attribute);
                        }

                        sKasus = "(" + attributes.size() + ")";

                        txtKasus.setText(sKasus);

                        adapterProvinsi = new AdapterProvinsi(PageProvinsi.this, attributes,
                                totalKasus, totalPositif, totalSembuh, totalMati);
                        rvProvinsi.setLayoutManager(new LinearLayoutManager(PageProvinsi.this));
                        rvProvinsi.setAdapter(adapterProvinsi);
                        rvProvinsi.setItemAnimator(new DefaultItemAnimator());
                        rvProvinsi.setHasFixedSize(true);
                        adapterProvinsi.notifyDataSetChanged();
                    } else {
                        Log.d("AS", "onResponse: else " + response.errorBody().string());
                    }
                } catch (Exception e) {
                    unload(Const.msg_internet);
                    Log.d("AS", "onResponse: " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<List<ModelProvinsi>> call, Throwable t) {
                unload(Const.msg_internet);
                Log.d("As", "onFailure: " + t.toString());
            }
        });
    }

    private void load() {
        layReload.setRefreshing(true);
    }

    private void unload(String msg) {
        layReload.setRefreshing(false);

        if (!msg.isEmpty()) snackbar(msg);
    }

    private void snackbar(String msg) {
        Func.snackbar(findViewById(R.id.page_provinsi), msg);
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @OnClick(R.id.provinsi_btn_back)
    void back() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
