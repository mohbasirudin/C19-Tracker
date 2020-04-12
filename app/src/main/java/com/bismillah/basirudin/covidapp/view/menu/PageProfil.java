package com.bismillah.basirudin.covidapp.view.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bismillah.basirudin.covidapp.R;
import com.bismillah.basirudin.covidapp.adapter.AdapterKontak;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PageProfil extends AppCompatActivity {
    @BindView(R.id.profil_recycleview)
    RecyclerView rvKontak;
    @BindView(R.id.profil_about)
    TextView txtAbout;
    @BindView(R.id.profil_projek)
    TextView txtProjek;
    @BindView(R.id.profil_lay_image)
    RelativeLayout layImage;

    private AdapterKontak adapterKontak;
    private String about = "",projek="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_profil);
        ButterKnife.bind(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int w = metrics.widthPixels;
        layImage.getLayoutParams().height = w / 2;

        adapterKontak = new AdapterKontak(this);
        rvKontak.setLayoutManager(new LinearLayoutManager(this));
        rvKontak.setAdapter(adapterKontak);
        rvKontak.setItemAnimator(new DefaultItemAnimator());
        rvKontak.setHasFixedSize(true);
        adapterKontak.notifyDataSetChanged();

        about = "Sebuah aplikasi sederhana yang dibuat untuk mengisi <i>kegabutan yang haqiqi</i> di waktu luang dan sekaligus sebagai media pembelajaran bagi pembuat." +
                "<br><br>Mohon maaf, untuk data di Indonesia hanya sampai ke tingkat Provinsi, tidak sampai ke tingkat Kabupaten bahkan ke daerah." +
                " Dan untuk luar Indonesia, data hanya sebatas tingkat Nasional.";
        projek="Untuk yang mau mengembangkan lagi agar tidak terlihat sesederhana ini. Silahkan, buatlah semenarik mungkin dan sedetail mungkin. Kreasikan sesukamu!!! Untuk projeknya bisa didownload di Github.";

        txtAbout.setText(Html.fromHtml(about));
        txtProjek.setText(projek);
    }

    @OnClick(R.id.profil_btn_back)
    void back() {
        finish();
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @OnClick(R.id.profil_btn_github)
    void github() {
        Uri uri = Uri.parse("https://github.com/mohbasirudin/Covid-Tracker");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
