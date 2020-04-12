package com.bismillah.basirudin.covidapp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

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
    @BindView(R.id.provinsi_piechart)
    PieChart pieChart;
    @BindView(R.id.provinsi_lay_chart)
    CardView layChart;
    @BindView(R.id.provinsi_recycleview)
    RecyclerView rvProvinsi;

    private ApiService apiService;
    private List<Attributes> attributes;
    private ArrayList<PieEntry> yValue;
    private ArrayList<Integer> listColor;
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

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int w = metrics.widthPixels;
        layChart.getLayoutParams().height = w - 14;

        yValue = new ArrayList<>();
        if (yValue.size() > 0) yValue.clear();
        yValue.add(new PieEntry(Float.parseFloat(Func.removeCharacter(totalPositif)), "Positif"));
        yValue.add(new PieEntry(Float.parseFloat(Func.removeCharacter(totalSembuh)), "Sembuh"));
        yValue.add(new PieEntry(Float.parseFloat(Func.removeCharacter(totalMati)), "Meninggal"));
        listColor = new ArrayList<>();
        if (listColor.size() > 0) listColor.clear();
        listColor.add(getResources().getColor(R.color.colorRed));
        listColor.add(getResources().getColor(R.color.colorGreen));
        listColor.add(getResources().getColor(R.color.colorGrey));

        PieDataSet pieDataSet = new PieDataSet(yValue, "");
        pieDataSet.setColors(listColor);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(12);
        pieData.setValueTextColor(getResources().getColor(R.color.colorWhite));
        pieChart.setDrawHoleEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.invalidate();
        pieChart.setData(pieData);
        Description description = new Description();
        description.setText("Total kasus " + totalKasus);
        description.setTextSize(12);
        pieChart.setDescription(description);
        Legend legend = new Legend();
        legend.setTextSize(12);

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
                    }
                } catch (Exception e) {
                    unload(Const.msg_internet);
                }
            }

            @Override
            public void onFailure(Call<List<ModelProvinsi>> call, Throwable t) {
                unload(Const.msg_internet);
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
