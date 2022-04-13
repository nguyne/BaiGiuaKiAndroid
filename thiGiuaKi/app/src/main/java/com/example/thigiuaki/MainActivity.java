package com.example.thigiuaki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Switch;

import com.example.thigiuaki.sv.SinhVien;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int id;
    EditText txtMaSv, txtHoTen, txtSoTC;
    SearchView searchView;
    Button btnXoaTrang, btnThem, btnXoa, btnChiTiet;
    int vt;
    ListView lst_listview;
    ArrayList<SinhVien> arrsinhvien;
    adapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        arrsinhvien = new ArrayList<SinhVien>();
        //khởi tạo adapter
        myadapter = new adapter(this,R.layout.item_layout,arrsinhvien);
        //set adapter cho list view
        lst_listview.setAdapter(myadapter);
        // đăng kí menu
        registerForContextMenu(lst_listview);
        lst_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                id = i;
                txtMaSv.setText(arrsinhvien.get(i).getMaSV());
                txtHoTen.setText(arrsinhvien.get(i).getHoTen());
                txtSoTC.setText(arrsinhvien.get(i).getSoTC());

            }
        });

        lst_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                vt=i;
                return false;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masv =txtMaSv.getText().toString();
                String hoten = txtHoTen.getText().toString();
                String soTC = txtSoTC.getText().toString();
                SinhVien sv = new SinhVien(masv,hoten,soTC);
                arrsinhvien.add(sv);
                myadapter.notifyDataSetChanged();
            }
        });
        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMaSv.getText().clear();
                txtHoTen.getText().clear();
                txtSoTC.getText().clear();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode) {
            case 1:
                if(data!=null){
                    SinhVien sv1 = (SinhVien) data.getSerializableExtra("data");
                    arrsinhvien.set(vt,sv1);
                    myadapter.notifyDataSetChanged();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void sendata (SinhVien sv1){
        Intent intent = new Intent(MainActivity.this,chuyendoi.class);

        intent.putExtra("du lieu", sv1);
        startActivityForResult(intent,1);
    }
    public void anhXa(){
        txtHoTen = findViewById(R.id.txt_HoTen);
        txtMaSv = findViewById(R.id.txt_MaSV);
        txtSoTC = findViewById(R.id.txt_SoTC);
        btnXoaTrang = findViewById(R.id.btn_xoatrang);
        btnThem = findViewById(R.id.btn_them);
        btnXoa=findViewById(R.id.menu_xoa);
        btnChiTiet = findViewById(R.id.menu_xemthongtin);
        lst_listview = findViewById(R.id.am_lst_listview);
        searchView= findViewById(R.id.searchView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getItemId()){
            case R.id.menu_xemthongtin:
                SinhVien sv = arrsinhvien.get(vt);
                sendata(sv);
                break;
            case R.id.menu_xoa:
                arrsinhvien.remove(index);
                myadapter.notifyDataSetChanged();
                break;
            default:
        }

        return super.onContextItemSelected(item);
    }
}