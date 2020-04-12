package com.bismillah.basirudin.covidapp.adapter.provinsi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bismillah.basirudin.covidapp.R;
import com.bismillah.basirudin.covidapp.baseurl.Func;
import com.bismillah.basirudin.covidapp.model.covid_03.provinsi.Attributes;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AdapterProvinsi extends RecyclerView.Adapter<AdapterProvinsi.ViewHolder> {
    private Context context;
    private List<Attributes> attributes;
    private String totalKasus = "", totalSembuh = "", totalMati = "", totalPosiitif = "";

    public AdapterProvinsi(Context context, List<Attributes> attributes, String totalKasus, String totalPosiitif, String totalSembuh, String totalMati) {
        this.context = context;
        this.attributes = attributes;
        this.totalKasus = totalKasus;
        this.totalPosiitif = totalPosiitif;
        this.totalSembuh = totalSembuh;
        this.totalMati = totalMati;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_provinsi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attributes att = attributes.get(position);

        holder.nama = att.getProvinsi();
        holder.positif = String.valueOf(att.getKasusPosi());
        holder.sembuh = String.valueOf(att.getKasusSemb());
        holder.mati = String.valueOf(att.getKasusMeni());
        holder.kasus = String.valueOf(att.getKasusPosi() + att.getKasusSemb() + att.getKasusMeni());

        holder.perKasus = Func.persen(totalKasus, holder.kasus, "2");
        holder.perPositif = Func.persen(totalPosiitif, holder.positif, "2");
        holder.perSembuh = Func.persen(totalSembuh, holder.sembuh, "2");
        holder.perMati = Func.persen(totalMati, holder.mati, "2");

        if (holder.perKasus.equalsIgnoreCase("0.00"))
            holder.perKasus = Func.persen(totalKasus, holder.kasus, "3");
        if (holder.perPositif.equalsIgnoreCase("0.00"))
            holder.perPositif = Func.persen(totalPosiitif, holder.positif, "3");
        if (holder.perSembuh.equalsIgnoreCase("0.00"))
            holder.perSembuh = Func.persen(totalSembuh, holder.sembuh, "3");
        if (holder.perMati.equalsIgnoreCase("0.00"))
            holder.perMati = Func.persen(totalMati, holder.mati, "3");

        if (holder.perKasus.equalsIgnoreCase("0.000"))
            holder.perKasus = Func.persen(totalKasus, holder.kasus, "2");
        if (holder.perPositif.equalsIgnoreCase("0.000"))
            holder.perPositif = Func.persen(totalPosiitif, holder.positif, "2");
        if (holder.perSembuh.equalsIgnoreCase("0.000"))
            holder.perSembuh = Func.persen(totalSembuh, holder.sembuh, "2");
        if (holder.perMati.equalsIgnoreCase("0.000"))
            holder.perMati = Func.persen(totalMati, holder.mati, "2");

        holder.txtNama.setText(holder.nama);
        holder.txtKasus.setText(Func.number(holder.kasus) + " Kasus");
        holder.txtPositif.setText(Func.number(holder.positif));
        holder.txtSembuh.setText(Func.number(holder.sembuh));
        holder.txtMati.setText(Func.number(holder.mati));

        holder.txtPerKasus.setText(holder.perKasus + "%");
        holder.txtPerPositif.setText(holder.perPositif + "%");
        holder.txtPerSembuh.setText(holder.perSembuh + "%");
        holder.txtPerMati.setText(holder.perMati + "%");
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_provinsi_nama)
        TextView txtNama;
        @BindView(R.id.list_provinsi_kasus)
        TextView txtKasus;
        @BindView(R.id.list_provinsi_positif)
        TextView txtPositif;
        @BindView(R.id.list_provinsi_sembuh)
        TextView txtSembuh;
        @BindView(R.id.list_provinsi_mati)
        TextView txtMati;
        @BindView(R.id.list_provinsi_kasus_persen)
        TextView txtPerKasus;
        @BindView(R.id.list_provinsi_positif_persen)
        TextView txtPerPositif;
        @BindView(R.id.list_provinsi_sembuh_persen)
        TextView txtPerSembuh;
        @BindView(R.id.list_provinsi_mati_persen)
        TextView txtPerMati;


        private String nama = "", kasus = "", positif = "", sembuh = "", mati = "",
                perKasus = "", perPositif = "", perSembuh = "", perMati = "";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
