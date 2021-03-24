package com.example.searchupdatelist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.net.URI;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;
    EditText txtName;
    Button btnSave, btnCancel;
    //
    Uri imageUri;
    ImageButton ibtnSave, ibtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        //
//        imageView = findViewById(R.id.imageView2);
        txtName = findViewById(R.id.editTextName);
//        btnSave = findViewById(R.id.button);
//        btnCancel = findViewById(R.id.button2);
          ibtnSave = findViewById(R.id.imageButton);
          ibtnCancel = findViewById(R.id.imageButton2);
        //assign an event listener to the buttons and also to the imageView
//        imageView.setOnClickListener(this);
//        btnSave.setOnClickListener(this);
//        btnCancel.setOnClickListener(this);
          ibtnSave.setOnClickListener(this);
          ibtnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        switch (id){
//            case R.id.imageView2:
//                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, 100);
//                break;
            case R.id.imageButton: //save - here we are going to create the blind intent

                String name = txtName.getText().toString();
                //add validation
                if(!name.equals("")){
                    intent = new Intent(); //blind intent
                    intent.putExtra("name", name);
                    intent.putExtra("image", imageUri);
                    setResult(Activity.RESULT_OK, intent);
                }
            case R.id.imageButton2:
                finish(); //terminate this activity and return to the default/calling activity
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 100){
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
        }
    }
}