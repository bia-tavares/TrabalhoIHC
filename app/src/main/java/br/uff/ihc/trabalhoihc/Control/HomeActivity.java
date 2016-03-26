package br.uff.ihc.trabalhoihc.Control;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import br.uff.ihc.trabalhoihc.Model.DataHelper;
import br.uff.ihc.trabalhoihc.R;

public class HomeActivity extends AppCompatActivity {

    private DataHelper dh;

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
    ViewFlipper vf;

    /**
     * Variáveis de content_resultpesquisar
     */
    Spinner spinnerOrdenar;
    ListView listViewIngressos;

    /**
     * Variáveis de content_perfil
     */
    TextView textViewNome;
    ImageView imageViewPerfil;
    Button buttonConfig, buttonPerguntas, buttonConvidar, buttonMeusIngressos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.dh = new DataHelper(this);

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

        /**
         * Variáveis de content_perfil
         */
        textViewNome = (TextView) findViewById(R.id.textViewNome);
        imageViewPerfil = (ImageView) findViewById(R.id.imageViewPerfil);
        buttonConfig = (Button) findViewById(R.id.buttonConfig);
        buttonPerguntas = (Button) findViewById(R.id.buttonPerguntas);
        buttonConvidar = (Button) findViewById(R.id.buttonConvidar);
        buttonMeusIngressos = (Button) findViewById(R.id.buttonMeusIngressos);

        spinnerOrdenar = (Spinner) findViewById(R.id.spinnerOrdenar);
        listViewIngressos = (ListView) findViewById(R.id.listViewIngressos);

        vf = (ViewFlipper)findViewById(R.id.vfHome);
        vf.setDisplayedChild(0);

        setup();
    }

    private void setup(){

        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(1);
            }
        });

        anunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(3);
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(0);
            }
        });

        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(4);
            }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(5);

                textViewNome.setText(dh.name(SingletonUsuarioCtrl.getIdUsuario()));
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
