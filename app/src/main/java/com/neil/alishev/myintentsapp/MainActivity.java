package com.neil.alishev.myintentsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText textEntry;
    private Button changeActivityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        changeActivityButton = findViewById(R.id.change_activity);
        textEntry = findViewById(R.id.et_text_entry);
        changeActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEntered = textEntry.getText().toString();
                Context context = MainActivity.this;
                Class destinationActivity = ChildActivity.class;

                Intent childActivityIntent = new Intent(context, destinationActivity);
                childActivityIntent.putExtra(Intent.EXTRA_TEXT, textEntered);

                startActivity(childActivityIntent);

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void onClickOpenWebpageButton(View view) {
        openWebPage("https://www.google.com");
//        Toast.makeText(this,"Открыть веб страницу", Toast.LENGTH_SHORT).show();
    }

    public void onClickOpenMapButton(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri addressUri = Uri.parse("geo:0,0")
                .buildUpon()
                .appendQueryParameter("q", "Тверская улица,1")
                .build();
        intent.setData(addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        Toast.makeText(this,"Открыть карту", Toast.LENGTH_SHORT).show();
    }

    public void yourOwnImplicitIntent(View view) {

        Toast.makeText(this, "Ваш неявный Intent", Toast.LENGTH_SHORT).show();

    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}