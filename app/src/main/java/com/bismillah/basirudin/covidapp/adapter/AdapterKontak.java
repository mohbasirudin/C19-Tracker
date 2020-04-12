package com.bismillah.basirudin.covidapp.adapter;

import android.content.Context;
import android.media.tv.TvView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bismillah.basirudin.covidapp.R;
import com.bismillah.basirudin.covidapp.baseurl.Const;
import com.bismillah.basirudin.covidapp.baseurl.Func;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterKontak extends RecyclerView.Adapter<AdapterKontak.ViewHolder> {
    private Context context;
    private String[] dataKontak = {Const.kontak_wa, Const.kontak_ig, Const.kontak_github};

    public AdapterKontak(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_kontak, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nama = dataKontak[position];
        holder.txtNama.setText(holder.nama);
    }

    @Override
    public int getItemCount() {
        return dataKontak.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_kontak_nama)
        TextView txtNama;

        private String nama = "";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> Func.openUrl(context, nama));
        }
    }
}
