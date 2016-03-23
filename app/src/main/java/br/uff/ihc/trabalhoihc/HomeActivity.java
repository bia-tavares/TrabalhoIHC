package br.uff.ihc.trabalhoihc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import com.facebook.FacebookSdk;

public class HomeActivity extends AppCompatActivity {

    /**
     * Variáveis da activity_home
     */
    FloatingActionButton pesquisar, anunciar, inicio, favoritos, perfil;

    /**
     * Variáveis de content_home
     */
    LinearLayout homeRecentesLayout, homeFavoritosLayout, homeMaisProcuradosLayout;

    /**
     * Variáveis de content_pesquisar
     */
    Spinner spinnerEsporte;
    LinearLayout layoutRadioModalidade;
    RadioButton radioMasculino, radioFeminino;
    EditText editTextMin, editTextMax;
    Button buttonPesquisar;

    /**
     * Variáveis de content_resultpesquisar
     */
    Spinner spinnerOrdenar;
    ListView listViewIngressos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pesquisar = (FloatingActionButton) findViewById(R.id.pesquisar);
        anunciar = (FloatingActionButton)findViewById(R.id.anunciar);
        inicio = (FloatingActionButton) findViewById(R.id.inicio);
        favoritos = (FloatingActionButton) findViewById(R.id.favoritos);
        perfil = (FloatingActionButton) findViewById(R.id.perfil);

        homeRecentesLayout = (LinearLayout) findViewById(R.id.recentesLayout);
        homeFavoritosLayout = (LinearLayout) findViewById(R.id.favoritosLayout);
        homeMaisProcuradosLayout = (LinearLayout) findViewById(R.id.maisProcuradosLayout);

        spinnerEsporte = (Spinner) findViewById(R.id.spinnerEsporte);
        layoutRadioModalidade = (LinearLayout) findViewById(R.id.layoutRadioModalidade);
        radioMasculino = (RadioButton) findViewById(R.id.radioMasculino);
        radioFeminino = (RadioButton) findViewById(R.id.radioFeminino);
        editTextMin = (EditText) findViewById(R.id.editTextMin);
        editTextMax = (EditText) findViewById(R.id.editTextMax);
        buttonPesquisar = (Button) findViewById(R.id.buttonPesquisar);

        spinnerOrdenar = (Spinner) findViewById(R.id.spinnerOrdenar);
        listViewIngressos = (ListView) findViewById(R.id.listViewIngressos);

        setup();
    }

    private void setup(){

        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fazer alguma coisa
            }
        });

        anunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fazer alguma coisa
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fazer alguma coisa
            }
        });

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fazer alguma coisa
            }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fazer alguma coisa
            }
        });

        ArrayAdapter<CharSequence> adapterEsportes = ArrayAdapter.createFromResource(this,
                R.array.esportes_array, android.R.layout.simple_spinner_item);
        adapterEsportes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEsporte.setAdapter(adapterEsportes);

        ArrayAdapter<CharSequence> adapterOrdenar = ArrayAdapter.createFromResource(this,
                R.array.esportes_array, android.R.layout.simple_spinner_item);
        adapterOrdenar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrdenar.setAdapter(adapterOrdenar);
    }

}
