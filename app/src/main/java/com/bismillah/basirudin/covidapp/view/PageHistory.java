package com.bismillah.basirudin.covidapp.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bismillah.basirudin.covidapp.R;
import com.bismillah.basirudin.covidapp.adapter.AdapterHistory;
import com.bismillah.basirudin.covidapp.api.ApiService;
import com.bismillah.basirudin.covidapp.api.ApiUtil;
import com.bismillah.basirudin.covidapp.baseurl.Const;
import com.bismillah.basirudin.covidapp.baseurl.Func;
import com.bismillah.basirudin.covidapp.model.covid_02.Particular.list.ModelParticularList;
import com.bismillah.basirudin.covidapp.model.covid_02.Particular.list.StatByCountryItem;
import com.bismillah.basirudin.covidapp.model.covid_02.country.list.ModelCountryList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageHistory extends AppCompatActivity {
    @BindView(R.id.history_lay_reload)
    SwipeRefreshLayout layReload;
    @BindView(R.id.history_recycleview)
    RecyclerView rvHistory;
    @BindView(R.id.history_country)
    TextView txtCountry;
    @BindView(R.id.history_count)
    TextView txtCount;

    private ApiService apiService;
    private String country = "", count = "";
    private AdapterHistory adapterHistory;
    private List<StatByCountryItem> countryItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_history);
        ButterKnife.bind(this);
        apiService = ApiUtil.getService(Const.covid_02);
        countryItems = new ArrayList<>();

        if (getIntent().hasExtra(Const.send_country))
            country = getIntent().getStringExtra(Const.send_country);

        if (!country.isEmpty()) txtCountry.setText(country);
        else txtCountry.setText(Const.data_undefined);

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

        apiService.getParticular(country).enqueue(new Callback<ModelParticularList>() {
            @Override
            public void onResponse(Call<ModelParticularList> call, Response<ModelParticularList> response) {
                try {
                    if (response.isSuccessful()) {
                        if (countryItems.size() > 0)
                            countryItems.clear();

                        countryItems = response.body().getStatByCountry();
                        Collections.reverse(countryItems);
                        adapterHistory = new AdapterHistory(PageHistory.this, countryItems);
                        rvHistory.setLayoutManager(new LinearLayoutManager(PageHistory.this));
                        rvHistory.setAdapter(adapterHistory);
                        rvHistory.setHasFixedSize(true);
                        rvHistory.setItemAnimator(new DefaultItemAnimator());

                        count = "(" + countryItems.size() + " Update)";
                        txtCount.setText(count);

                        unload("");
                    } else {
                        unload(Const.msg_internet);
                    }
                } catch (Exception e) {
                    unload(Const.msg_internet);
                }
            }

            @Override
            public void onFailure(Call<ModelParticularList> call, Throwable t) {
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
        Func.snackbar(findViewById(R.id.page_history), msg);
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @OnClick(R.id.history_btn_back)
    void back() {
        finish();
    }
}
