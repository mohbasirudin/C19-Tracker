package com.bismillah.basirudin.covidapp.baseurl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Func {
    public static void snackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static String timeConverter(String tanggal, String fAwal, String fAkhir) {
        SimpleDateFormat formatAwal, formatAkhir;
        formatAwal = new SimpleDateFormat(fAwal);
        formatAkhir = new SimpleDateFormat(fAkhir);

        String hasil = "";

        try {
            Date date = formatAwal.parse(tanggal);
            hasil = formatAkhir.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hasil;
    }

    public static boolean internet(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnectedOrConnecting()) {
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo data = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi != null && wifi.isConnectedOrConnecting() || data != null && data.isConnectedOrConnecting()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String removeCharacter(String data) {
        return data.replace(",", "")
                .replace(".", "");
    }

    public static String number(String harga) {
        double dHarga = Double.valueOf(harga);
        Locale locale = new Locale("in", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(dHarga).replace("Rp", "");
    }

    public static String getTimezon(String date) throws ParseException {
        Calendar mCalendar = new GregorianCalendar();
        TimeZone mTimeZone = mCalendar.getTimeZone();
        int mGMTOffset = mTimeZone.getRawOffset();
        long timeZone = TimeUnit.HOURS.convert(mGMTOffset, TimeUnit.MILLISECONDS);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(Const.date_02, Locale.US);
        cal.setTime(sdf.parse(date));
        cal.add(Calendar.HOUR, Integer.parseInt(String.valueOf(timeZone)));
        String time = String.valueOf(cal.getTime());

        SimpleDateFormat formatAwal, formatAkhir;
        formatAwal = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        formatAkhir = new SimpleDateFormat(Const.date_02);
        Date dateTime = formatAwal.parse(time);
        return formatAkhir.format(dateTime);
    }

    public static String persen(String pembagi, String pembilang, String i) {
        double a = Double.parseDouble(Func.removeCharacter(pembagi));
        double b = Double.parseDouble(Func.removeCharacter(pembilang));
        double hasil = (b / a) * 100;
        return String.format("%." + i + "f", hasil);
    }
}
