package com.bismillah.basirudin.covidapp.adapter.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bismillah.basirudin.covidapp.R;
import com.bismillah.basirudin.covidapp.baseurl.Func;
import com.bismillah.basirudin.covidapp.model.covid_02.country.list.CountriesStatItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMainCountry extends RecyclerView.Adapter<AdapterMainCountry.ViewHolder> {
    private Context context;
    private List<CountriesStatItem> statItems;
    private String totalKasus = "";

    public AdapterMainCountry(Context context, List<CountriesStatItem> statItems, String totalKasus) {
        this.context = context;
        this.statItems = statItems;
        this.totalKasus = totalKasus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_main_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CountriesStatItem item = statItems.get(position);

        holder.sNama = item.getCountryName();
        holder.sKasus = Func.number(Func.removeCharacter(item.getCases()));
        holder.sNewKasus = "(+" + Func.number(Func.removeCharacter(item.getNewCases())) + ")";
        holder.sPerKasus = Func.persen(totalKasus, holder.sKasus, "2") + "%";
        if (holder.sPerKasus.equalsIgnoreCase("0.0%")
                || holder.sPerKasus.equalsIgnoreCase("0.00%"))
            holder.sPerKasus = Func.persen(totalKasus, holder.sKasus, "4") + "%";

        holder.txtNama.setText(holder.sNama);
        holder.txtKasus.setText(holder.sKasus);
        holder.txtNewKasus.setText(holder.sNewKasus);
        holder.txtPerKase.setText(holder.sPerKasus);

        holder.btnDetail.setOnClickListener(v -> Toast.makeText(context, "Detail", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return statItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_main_country_nama)
        TextView txtNama;
        @BindView(R.id.list_main_country_kasus)
        TextView txtKasus;
        @BindView(R.id.list_main_country_kasus_new)
        TextView txtNewKasus;
        @BindView(R.id.list_main_country_kasus_persentase)
        TextView txtPerKase;
        @BindView(R.id.list_main_country_kasus_btn_detail)
        ImageButton btnDetail;

        private String sKasus = "", sNama = "", sNewKasus = "", sPerKasus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v ->
                    Toast.makeText(context, sNama, Toast.LENGTH_SHORT).show());
        }
    }
}
