package br.uff.ihc.trabalhoihc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {


    private Button logar, face, criar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        face = (Button) findViewById(R.id.faceButton);
        logar = (Button) findViewById(R.id.logar);
        criar = (Button) findViewById(R.id.criarConta);

        setuplogin();
    }

    public void setuplogin(){

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fazer alguma coisa
            }
        });


        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fazer alguma coisa
            }
        });

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fazer alguma coisa
            }
        });
    }

}
