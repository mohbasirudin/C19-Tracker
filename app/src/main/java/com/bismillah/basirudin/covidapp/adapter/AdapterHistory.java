package com.bismillah.basirudin.covidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bismillah.basirudin.covidapp.R;
import com.bismillah.basirudin.covidapp.baseurl.Func;
import com.bismillah.basirudin.covidapp.model.covid_02.Particular.list.StatByCountryItem;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {
    private Context context;
    private List<StatByCountryItem> countryItems;

    public AdapterHistory(Context context, List<StatByCountryItem> countryItems) {
        this.context = context;
        this.countryItems = countryItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StatByCountryItem item = countryItems.get(position);
        holder.kasus = Func.number(item.getTotalCases());
        holder.positif = Func.number(item.getActiveCases());
        holder.newPositif = "+" + Func.number(item.getNewCases());
        holder.sembuh = Func.number(item.getTotalRecovered());
        holder.mati = Func.number(item.getTotalDeaths());
        holder.newMati = "+" + Func.number(item.getNewDeaths());
        try {
            holder.waktu = Func.getTimezon(item.getRecordDate());
        } catch (ParseException e) {
            holder.waktu = "";
        }

        holder.txtWaktu.setText(holder.waktu);
        holder.txtKasus.setText(holder.kasus);
        holder.txtPositif.setText(holder.positif);
        holder.txtNewPositif.setText(holder.newPositif);
        holder.txtSembuh.setText(holder.sembuh);
        holder.txtMati.setText(holder.mati);
        holder.txtNewMati.setText(holder.newMati);
    }

    @Override
    public int getItemCount() {
        return countryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_history_kasus)
        TextView txtKasus;
        @BindView(R.id.list_history_positif)
        TextView txtPositif;
        @BindView(R.id.list_history_positif_new)
        TextView txtNewPositif;
        @BindView(R.id.list_history_sembuh)
        TextView txtSembuh;
        @BindView(R.id.list_history_mati)
        TextView txtMati;
        @BindView(R.id.list_history_mati_new)
        TextView txtNewMati;
        @BindView(R.id.list_history_waktu)
        TextView txtWaktu;

        private String kasus = "", positif = "", newPositif = "",
                sembuh = "", mati = "", newMati = "", waktu = "";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
