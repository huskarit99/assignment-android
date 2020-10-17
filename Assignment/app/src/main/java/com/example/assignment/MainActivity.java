package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Info> listInfo;
    TextView edtSelectedName;
    ListView lvInfo;
    CustomAdapterListInfo customAdapterListInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        mapping();
        init();
        customAdapterListInfo = new CustomAdapterListInfo();
        lvInfo.setAdapter(customAdapterListInfo);

        lvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtSelectedName.setText(listInfo.get(position).getName());
            }
        });
    }

    private void init() {
        listInfo = new ArrayList<>();
        listInfo.add(new Info("Nguyen Thai Hoc", "0974748525"));
        listInfo.add(new Info("Nguyen Tan Phuc", "1298377219"));
        listInfo.add(new Info("Nguyen Minh Thuc", "89234798324"));
        listInfo.add(new Info("Le Nguyen An Khang", "1283798432"));
        listInfo.add(new Info("Le Nguyen Nhut Truong", "45680992345"));
        listInfo.add(new Info("Luong Thai Anh Duy", "3920092345"));
        listInfo.add(new Info("Le Hoang Minh", "93458792324"));
        listInfo.add(new Info("Dinh Nho Liem", "23857798598"));
        listInfo.add(new Info("Bao Toan", "823784978932"));
    }

    class CustomAdapterListInfo extends BaseAdapter {

        @Override
        public int getCount() {
            return listInfo.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.row_info, null);
            TextView name = view.findViewById(R.id.edtName);
            TextView phoneNumber = view.findViewById(R.id.edtPhoneNumber);

            name.setText(listInfo.get(position).getName());
            phoneNumber.setText(listInfo.get(position).getPhoneNumber());
            return view;
        }
    }

    private void mapping() {
        edtSelectedName = findViewById(R.id.edtSelectedName);
        lvInfo = findViewById(R.id.lvInfo);
    }
}