package com.example.nmpetry.myapp.tools;
import android. content. Context;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Ferramentas {

    public static String ultimoErro;
    public static SimpleDateFormat sdf;
    private static String TAG = "Ferramentas";

    public static void exibirMensagem(Context context, String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean validarFormatoData(String dat)
    {
        String data = dat;
        String pattern = "dd/MM/yyyy";
        sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(data);
            // Data formatada corretamente
            return true;
        } catch (ParseException e) {
            // Erro de parsing!!
            e.printStackTrace();
            return false;
        }
    }

    public static String converterDataAMD(String data)
    {
        String formatoInicial = "yyyy-MM-dd";
        String formatoDesejado = "dd/MM/yyyy";
        String wDataConvertida = "";
        try {
            java.text.DateFormat parser = new SimpleDateFormat(formatoInicial, Locale.getDefault());
            java.text.DateFormat formatter = new SimpleDateFormat(formatoDesejado, Locale.getDefault());

            wDataConvertida = formatter.format(parser.parse(data));

        } catch (java.text.ParseException ex) {
            ultimoErro = ex.getMessage();
            Log.e(TAG, ex.getMessage());
        } catch (Exception ex){
            ultimoErro = ex.getMessage();
            Log.e(TAG, ex.getMessage());
        }finally {
            return wDataConvertida;
        }
    }

    public static String converterDataDMA(String data)
    {
        String formatoInicial = "dd/MM/yyyy";
        String formatoDesejado = "yyyy-MM-dd";
        String wDataConvertida = "";
        try {
            java.text.DateFormat parser = new SimpleDateFormat(formatoInicial, Locale.getDefault());
            java.text.DateFormat formatter = new SimpleDateFormat(formatoDesejado, Locale.getDefault());

            wDataConvertida = formatter.format(parser.parse(data));

        } catch (java.text.ParseException ex) {
            System.out.println(ex.getMessage().toString());
        } catch (Exception ex){
            System.out.println(ex.getMessage().toString());
        }finally {
            return wDataConvertida;
        }
    }

    public static String obterDiaSemana(String data){
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            java.util.Date dateParse = myFormat.parse(data);
            Calendar calendar = Calendar.getInstance();
            calendar = Calendar.getInstance();
            calendar.setTime(dateParse);
            int day = calendar.get(Calendar.DAY_OF_WEEK);

            switch (day) {
                case Calendar.SUNDAY:
                    return "Domingo";
                case Calendar.MONDAY:
                    return "Segunda-Feira";
                case Calendar.TUESDAY:
                    return "Terça-Feira";
                case Calendar.WEDNESDAY:
                    return "Quarta-Feira";
                case Calendar.THURSDAY:
                    return "Quinta-Feira";
                case Calendar.FRIDAY:
                    return "Sexta-Feira";
                case Calendar.SATURDAY:
                    return "Sábado";
            }

            return "";

        }catch (Exception ex){
            return "";
        }
    }

    public static void selecionaSpinner(Spinner spnr, Object campo)
    {
        try{
            for (int i = 0; i < spnr.getAdapter().getCount(); i++) {
                String wNome = spnr.getItemAtPosition(i).toString().toLowerCase();
                //if (obj == campo) {
                if (wNome.equals(campo.toString().toLowerCase())) {
                    spnr.setSelection(i);
                    break;
                }
            }

        }catch(Exception ex){
            Log.e("SELEC_SPINNER", ex.getMessage());
        }
    }
}
