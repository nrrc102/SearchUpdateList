package com.example.searchupdatelist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText txtSearch;
    ListView listView;
    ArrayList<MyItem> sourcelist = new ArrayList<>();
    ArrayList<MyItem> displaylist = new ArrayList<>();
    itemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //demo data to populate the listView
//        sourcelist.add(new MyItem(null, "alpha"));
//        sourcelist.add(new MyItem(null, "brave"));
//        sourcelist.add(new MyItem(null, "charlie"));
//        sourcelist.add(new MyItem(null, "delta"));
//        copySourceToDisplay();
        //
        txtSearch = findViewById(R.id.editTextSearch);
        listView = findViewById(R.id.listView1);
        adapter = new itemAdapter(this, displaylist);
        listView.setAdapter(adapter);
        //
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }   

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                displaylist.clear();
                String search = s.toString();
                Pattern pattern = Pattern.compile(search);
                for(MyItem item: sourcelist){
                    Matcher matcher = pattern.matcher(item.getName());
                    if(matcher.find()){
                        displaylist.add(item);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //
    private void copySourceToDisplay(){
        if(sourcelist.size() > 0 ){
            for(MyItem item: sourcelist)
                displaylist.add(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //explicit intent
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, 0);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 0){
                //retrieve the data sent from the blind intent
                Bundle b = data.getExtras();
                Uri image = b.getParcelable("image");
                String name = b.getString("name");
                sourcelist.add(new MyItem(image, name));
                displaylist.add(new MyItem(image, name));
                adapter.notifyDataSetChanged();
            }
        }
    }
}