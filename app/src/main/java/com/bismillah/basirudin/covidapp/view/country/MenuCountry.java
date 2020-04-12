package com.bismillah.basirudin.covidapp.view.country;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bismillah.basirudin.covidapp.R;
import com.bismillah.basirudin.covidapp.api.ApiService;
import com.bismillah.basirudin.covidapp.api.ApiUtil;
import com.bismillah.basirudin.covidapp.baseurl.Const;
import com.bismillah.basirudin.covidapp.baseurl.Func;
import com.bismillah.basirudin.covidapp.model.covid_02.country.list.CountriesStatItem;
import com.bismillah.basirudin.covidapp.model.covid_02.country.list.ModelCountryList;
import com.bismillah.basirudin.covidapp.view.PageHistory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuCountry extends BottomSheetDialogFragment {
    @BindView(R.id.menu_country_kasus)
    TextView txtKasus;
    @BindView(R.id.menu_country_positif)
    TextView txtPositif;
    @BindView(R.id.menu_country_sembuh)
    TextView txtSembuh;
    @BindView(R.id.menu_country_mati)
    TextView txtMati;
    @BindView(R.id.menu_country_positif_new)
    TextView txtNewPositif;
    @BindView(R.id.menu_country_mati_new)
    TextView txtNewMati;
    @BindView(R.id.menu_country_populasi)
    TextView txtPopulasi;
    @BindView(R.id.menu_country_kritis)
    TextView txtKritis;
    @BindView(R.id.menu_country_persentase_mati)
    TextView txtPerMati;
    @BindView(R.id.menu_country_persentase_kasus)
    TextView txtPerKasus;
    @BindView(R.id.menu_country_persentase_sembuh)
    TextView txtPerSembuh;
    @BindView(R.id.menu_country_nama)
    TextView txtNama;
    @BindView(R.id.menu_country_progressbar)
    ProgressBar pbCountry;

    private Context context;
    private String country = "";
    private BottomSheetBehavior sheetBehavior;
    private ApiService apiService;
    private String sPositif = "", sSembuh = "", sKasus = "", sMati = "",
            sNewPositif = "", sNewMati = "", sPopulasi = "", sKritis = "",
            sPerMati = "", sPerKasus = "", sPerSembuh = "";
    private String totalKasus = "";
    private List<CountriesStatItem> statItems;
    private View view;

    public void getData(String country, String totalKasus) {
        this.country = country;
        this.totalKasus = totalKasus;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog sheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(context).inflate(R.layout.menu_country, null);
        sheetDialog.setContentView(view);
        ButterKnife.bind(this, view);
        apiService = ApiUtil.getService(Const.covid_02);
        statItems = new ArrayList<>();

        sheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_COLLAPSED == newState
                        || BottomSheetBehavior.STATE_HIDDEN == newState)
                    dismiss();
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        setData();

        return sheetDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void setData() {
        load();
        if (!Func.internet(context)) {
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

                        for (int i = 0; i < statItems.size(); i++) {
                            String negara = statItems.get(i).getCountryName();
                            if (negara.equalsIgnoreCase(country)) {
                                sKasus = Func.number(Func.removeCharacter(statItems.get(i).getCases()));
                                sSembuh = Func.number(Func.removeCharacter(statItems.get(i).getTotalRecovered()));
                                sMati = Func.number(Func.removeCharacter(statItems.get(i).getDeaths()));
                                sPositif = Func.number(Func.removeCharacter(statItems.get(i).getActiveCases()));
                                sNewPositif = "(+" + Func.number(Func.removeCharacter(statItems.get(i).getNewCases())) + ")";
                                sNewMati = "(+" + Func.number(Func.removeCharacter(statItems.get(i).getNewDeaths())) + ")";
                                sPopulasi = Func.number(Func.removeCharacter(statItems.get(i).getTotalCasesPer1mPopulation()));
                                sKritis = Func.number(Func.removeCharacter(statItems.get(i).getSeriousCritical()));

                                sPerKasus = "(" + Func.persen(totalKasus, statItems.get(i).getCases(), "2") + "% dari Global)";
                                sPerSembuh = "(" + Func.persen(statItems.get(i).getCases(), statItems.get(i).getTotalRecovered(), "2") + "%)";
                                sPerMati = "(" + Func.persen(statItems.get(i).getCases(), statItems.get(i).getDeaths(), "2") + "%)";
                            }
                        }

                        txtKasus.setText(sKasus);
                        txtPositif.setText(sPositif);
                        txtSembuh.setText(sSembuh);
                        txtMati.setText(sMati);
                        txtNewPositif.setText(sNewPositif);
                        txtNewMati.setText(sNewMati);
                        txtPopulasi.setText(sPopulasi);
                        txtKritis.setText(sKritis);
                        txtPerKasus.setText(sPerKasus);
                        txtPerSembuh.setText(sPerSembuh);
                        txtPerMati.setText(sPerMati);

                        if (country.isEmpty()) country = Const.data_undefined;
                        txtNama.setText(country);

                        unload("");
                    } else {
                        unload(Const.msg_internet);
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

    @OnClick(R.id.menu_country_btn_close)
    void close() {
        dismiss();
    }

    @OnClick(R.id.menu_country_btn_reload)
    void reload() {
        setData();
    }

    @OnClick(R.id.menu_country_btn_riwayat)
    void riwayat() {
        if (country.isEmpty()) {
            toast("Data belum siap");
            return;
        }

        if (country.equalsIgnoreCase(Const.data_undefined)) {
            toast("Negara tidak diketahui");
            return;
        }

        dismiss();
        Intent intent = new Intent(context, PageHistory.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Const.send_country, country);
        startActivity(intent);
    }

    private void load() {
        pbCountry.setVisibility(View.VISIBLE);
    }

    private void unload(String msg) {
        if (pbCountry.getVisibility() == View.VISIBLE)
            pbCountry.setVisibility(View.GONE);

        if (!msg.isEmpty()) toast(msg);
    }

    private void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
