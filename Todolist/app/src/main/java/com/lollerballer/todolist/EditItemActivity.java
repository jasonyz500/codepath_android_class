package com.lollerballer.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText editText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String description = getIntent().getStringExtra("description");
        position = getIntent().getIntExtra("position", -1);
        editText = (EditText) findViewById(R.id.editText);
        editText.setText(description);
    }

    public void onSubmit(View v) {
        Intent data = new Intent();
        System.out.println(editText.getText().toString());
        data.putExtra("name", editText.getText().toString());
        data.putExtra("position", position);
        setResult(RESULT_OK, data);
        this.finish();
    }

    public void onAddItem(View view) {
        onSubmit(view);
    }
}
