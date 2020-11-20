package com.example.doanvanhung_17059571_h51m08;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private RoomData database;
    private Button add_btn, remove_btn, update;
    private EditText  txtName;
    private ArrayAdapter adapter;
    private int index;
    private ListView lvName;
    private ArrayList nameList;
    private ArrayList idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idList = new ArrayList();
        nameList = new ArrayList();
        txtName = findViewById(R.id.txtName);
        add_btn = findViewById(R.id.btnAdd);
        remove_btn = findViewById(R.id.btnRemove);
        update = findViewById(R.id.btnUpdate);
        lvName = findViewById(R.id.name_lv);
        database= RoomData.getInstance(this);
        getAllList();
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, nameList);
        lvName.setAdapter(adapter);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User mainData= new User();
                mainData.setText(txtName.getText().toString());
                database.mainDao().insert(mainData);
                getAllList();
                adapter.notifyDataSetChanged();
            }
        });

        

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>
                                            adapterView, View view, int i, long l) {
                index = i;
            }
        });
        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setID((int)idList.get(index));
                database.mainDao().delete(user);
                getAllList();
                adapter.notifyDataSetChanged();
            }
        });
        
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        EditText editText = dialog.findViewById(R.id.edit_text);
        Button btUpdate = dialog.findViewById(R.id.bt_update);
        
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                EditText editText = dialog.findViewById(R.id.edit_text);
                Button btUpdate = dialog.findViewById(R.id.bt_update);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String uText = editText.getText()
                                .toString().trim();
                        database.mainDao().update((int) idList.get(index),editText.getText().toString());
                        getAllList();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private ArrayList getAllList(){

        nameList.clear();
        idList.clear();
        for (Iterator iterator = database.mainDao().getAll().iterator(); iterator.hasNext(); ) {
            User mainData = (User)  iterator.next();
            nameList.add(mainData.getText());
            idList.add(mainData.getID());
        }
        return nameList;
    }
}