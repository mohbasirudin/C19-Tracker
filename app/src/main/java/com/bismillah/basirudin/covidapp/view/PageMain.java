package com.bismillah.basirudin.covidapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bismillah.basirudin.covidapp.R;
import com.bismillah.basirudin.covidapp.adapter.main.AdapterMainCountry;
import com.bismillah.basirudin.covidapp.api.ApiService;
import com.bismillah.basirudin.covidapp.api.ApiUtil;
import com.bismillah.basirudin.covidapp.baseurl.Const;
import com.bismillah.basirudin.covidapp.baseurl.Func;
import com.bismillah.basirudin.covidapp.model.covid_02.country.detail.LatestStatByCountryItem;
import com.bismillah.basirudin.covidapp.model.covid_02.country.detail.ModelCountryDetail;
import com.bismillah.basirudin.covidapp.model.covid_02.country.list.CountriesStatItem;
import com.bismillah.basirudin.covidapp.model.covid_02.country.list.ModelCountryList;
import com.bismillah.basirudin.covidapp.model.covid_02.total.ModelTotal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageMain extends AppCompatActivity {
    @BindView(R.id.main_lay_reload)
    SwipeRefreshLayout layReload;
    //    global
    @BindView(R.id.main_kasus)
    TextView txtKasus;
    @BindView(R.id.main_positif)
    TextView txtPositif;
    @BindView(R.id.main_sembuh)
    TextView txtSembuh;
    @BindView(R.id.main_mati)
    TextView txtMati;
    @BindView(R.id.main_positif_new)
    TextView txtNewPositif;
    @BindView(R.id.main_mati_new)
    TextView txtNewMati;
    @BindView(R.id.main_update)
    TextView txtUpdate;
    @BindView(R.id.main_country_total)
    TextView txtCountryTotal;
    @BindView(R.id.main_recycleview)
    RecyclerView rvCountry;
    //    indonesia
    @BindView(R.id.main_ina_kasus)
    TextView txtInaKasus;
    @BindView(R.id.main_ina_positif)
    TextView txtInaPositif;
    @BindView(R.id.main_ina_sembuh)
    TextView txtInaSembuh;
    @BindView(R.id.main_ina_mati)
    TextView txtInaMati;
    @BindView(R.id.main_ina_positif_new)
    TextView txtInaNewPositif;
    @BindView(R.id.main_ina_mati_new)
    TextView txtInaNewMati;
    @BindView(R.id.main_ina_populasi)
    TextView txtInaPopulasi;
    @BindView(R.id.main_ina_kritis)
    TextView txtInaKritis;
    @BindView(R.id.main_ina_persentase_mati)
    TextView txtInaPerMati;
    @BindView(R.id.main_ina_persentase_kasus)
    TextView txtInaPerKasus;
    @BindView(R.id.main_ina_persentase_sembuh)
    TextView txtInaPerSembuh;

    private List<CountriesStatItem> statItems;
    private AdapterMainCountry adapterMainCountry;
    private ApiService apiService;
    //    global
    private String sPositif = "", sSembuh = "", sKasus = "", sMati = "", sUpdate = "",
            sNewPositif = "", sNewMati = "", sCountryTotal = "";
    private int iKasus = 0, iSembuh = 0, iMati = 0, iPositif = 0;
    //    indonesia
    private String sInaPositif = "", sInaSembuh = "", sInaKasus = "", sInaMati = "",
            sInaNewPositif = "", sInaNewMati = "", sInaPopulasi = "", sInaKritis = "",
            sInaPerMati = "", sInaPerKasus = "", sInaPerSembuh = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_main);
        ButterKnife.bind(this);
        apiService = ApiUtil.getService(Const.covid_02);
        statItems = new ArrayList<>();

        if (layReload.isRefreshing()) layReload.setRefreshing(false);
        layReload.setOnRefreshListener(this::getData);
        layReload.setColorScheme(R.color.colorPrimary);

        getData();
    }

    private void getData() {
        load();
        if (!Func.internet(getApplicationContext())) {
            unload(Const.msg_internet);
            return;
        }

        getTotal();
        getCountry();
    }

    private void getTotal() {
        if (!Func.internet(getApplicationContext())) {
            unload(Const.msg_internet);
            return;
        }

        apiService.getTotal().enqueue(new Callback<ModelTotal>() {
            @Override
            public void onResponse(Call<ModelTotal> call, Response<ModelTotal> response) {
                try {
                    if (response.isSuccessful()) {
                        iKasus = Integer.valueOf(Func.removeCharacter(response.body().getTotalCases()));
                        iSembuh = Integer.valueOf(Func.removeCharacter(response.body().getTotalRecovered()));
                        iMati = Integer.valueOf(Func.removeCharacter(response.body().getTotalDeaths()));
                        iPositif = iKasus - (iSembuh + iMati);

                        sKasus = Func.number(Func.removeCharacter(response.body().getTotalCases()));
                        sSembuh = Func.number(Func.removeCharacter(response.body().getTotalRecovered()));
                        sMati = Func.number(Func.removeCharacter(response.body().getTotalDeaths()));
                        sNewPositif = "+" + Func.number(Func.removeCharacter(response.body().getNewCases()));
                        sNewMati = "+" + Func.number(Func.removeCharacter(response.body().getNewDeaths()));
                        sPositif = Func.number(String.valueOf(iPositif));
                        sUpdate = "Diperbarui pada " + Func.getTimezon(response.body().getStatisticTakenAt());

                        txtKasus.setText(sKasus);
                        txtPositif.setText(sPositif);
                        txtSembuh.setText(sSembuh);
                        txtMati.setText(sMati);
                        txtNewPositif.setText(sNewPositif);
                        txtNewMati.setText(sNewMati);
                        txtUpdate.setText(sUpdate);
                    } else {
                        unload(Const.msg_internet);
                    }
                } catch (Exception e) {
                    unload(Const.msg_internet);
                }
            }

            @Override
            public void onFailure(Call<ModelTotal> call, Throwable t) {
                unload(Const.msg_internet);
            }
        });
    }

    private void getCountry() {
        if (!Func.internet(getApplicationContext())) {
            unload(Const.msg_internet);
            return;
        }

        apiService.getCountry().enqueue(new Callback<ModelCountryList>() {
            @Override
            public void onResponse(Call<ModelCountryList> call, Response<ModelCountryList> response) {
                try {
                    if (response.isSuccessful()) {
                        if (statItems.size() > 0)
                            statItems.clear();
                        statItems = response.body().getCountriesStat();
                        sCountryTotal = "(" + statItems.size() + ")";

                        for (int i = 0; i < statItems.size(); i++) {
                            String negara = statItems.get(i).getCountryName();
                            if (negara.equalsIgnoreCase(Const.country_indonesia)) {
                                sInaKasus = Func.number(Func.removeCharacter(statItems.get(i).getCases()));
                                sInaSembuh = Func.number(Func.removeCharacter(statItems.get(i).getTotalRecovered()));
                                sInaMati = Func.number(Func.removeCharacter(statItems.get(i).getDeaths()));
                                sInaPositif = Func.number(Func.removeCharacter(statItems.get(i).getActiveCases()));
                                sInaNewPositif = "(+" + Func.number(Func.removeCharacter(statItems.get(i).getNewCases())) + ")";
                                sInaNewMati = "(+" + Func.number(Func.removeCharacter(statItems.get(i).getNewDeaths())) + ")";
                                sInaPopulasi = Func.number(Func.removeCharacter(statItems.get(i).getTotalCasesPer1mPopulation()));
                                sInaKritis = Func.number(Func.removeCharacter(statItems.get(i).getSeriousCritical()));

                                sInaPerKasus = "(" + Func.persen(sKasus, statItems.get(i).getCases(), "2") + "% dari Global)";
                                sInaPerSembuh = "(" + Func.persen(statItems.get(i).getCases(), statItems.get(i).getTotalRecovered(), "2") + "%)";
                                sInaPerMati = "(" + Func.persen(statItems.get(i).getCases(), statItems.get(i).getDeaths(), "2") + "%)";
                            }
                        }

                        txtInaKasus.setText(sInaKasus);
                        txtInaPositif.setText(sInaPositif);
                        txtInaSembuh.setText(sInaSembuh);
                        txtInaMati.setText(sInaMati);
                        txtInaNewPositif.setText(sInaNewPositif);
                        txtInaNewMati.setText(sInaNewMati);
                        txtInaPopulasi.setText(sInaPopulasi);
                        txtInaKritis.setText(sInaKritis);
                        txtInaPerKasus.setText(sInaPerKasus);
                        txtInaPerSembuh.setText(sInaPerSembuh);
                        txtInaPerMati.setText(sInaPerMati);
                        txtCountryTotal.setText(sCountryTotal);

                        adapterMainCountry = new AdapterMainCountry(PageMain.this, statItems, sKasus);
                        rvCountry.setLayoutManager(new GridLayoutManager(PageMain.this, 2));
                        rvCountry.setAdapter(adapterMainCountry);
                        rvCountry.setItemAnimator(new DefaultItemAnimator());
                        rvCountry.setHasFixedSize(true);

                        unload("");
                    }
                } catch (Exception e) {
                    unload(Const.msg_internet);
                }
            }

            @Override
            public void onFailure(Call<ModelCountryList> call, Throwable t) {
                unload(Const.msg_internet);
            }
        });
    }

    @OnClick(R.id.main_ina_btn_detail)
    void inaRiwayat() {
        snackbar("detail");
    }

    @OnClick(R.id.main_ina_btn_provinsi)
    void inaProvinsi() {
        Intent intent = new Intent(getApplicationContext(), PageProvinsi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Const.send_kasus, sInaKasus);
        intent.putExtra(Const.send_positif, sInaPositif);
        intent.putExtra(Const.send_sembuh, sInaSembuh);
        intent.putExtra(Const.send_mati, sInaMati);
        startActivity(intent);
    }

    @OnClick(R.id.main_btn_info)
    void info() {
        snackbar("info");
    }

    private void load() {
        layReload.setRefreshing(true);
    }

    private void unload(String msg) {
        layReload.setRefreshing(false);

        if (!msg.isEmpty()) snackbar(msg);
    }

    private void snackbar(String msg) {
        Func.snackbar(findViewById(R.id.page_main), msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
