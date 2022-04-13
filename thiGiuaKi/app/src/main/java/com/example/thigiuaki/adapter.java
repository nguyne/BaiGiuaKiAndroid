package com.example.thigiuaki;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.thigiuaki.sv.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class adapter extends BaseAdapter implements Filterable {
    Activity activity;
    int layout;
    List<SinhVien> datalist;

    List<SinhVien> datalistOld;

    public adapter(Activity activity, int layout, List<SinhVien> datalist){
        this.activity = activity;
        this.layout= layout;
        this.datalist= datalist;
    }
    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        view = layoutInflater.inflate(R.layout.item_layout,null);
        TextView masv = (TextView) view.findViewById(R.id.item_masv);
        TextView hoten = (TextView) view.findViewById(R.id.item_hoten);
        TextView sotc = (TextView) view.findViewById(R.id.item_TC);

        SinhVien sv =datalist.get(i);

        masv.setText(sv.getMaSV());
        hoten.setText(sv.getHoTen());
        sotc.setText(sv.getSoTC());
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    datalist= datalistOld;
                }else{
                    List<SinhVien> lstSV= new ArrayList<>();
                    for(SinhVien sv : datalistOld){
                        if(sv.getHoTen().toLowerCase().contains(strSearch.toLowerCase())){
                            lstSV.add(sv);
                        }
                    }
                    datalist=lstSV;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = datalist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                datalist = (List<SinhVien>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
