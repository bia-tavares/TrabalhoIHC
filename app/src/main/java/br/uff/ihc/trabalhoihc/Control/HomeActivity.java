package br.uff.ihc.trabalhoihc.Control;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
     * Variáveis de content_anunciar
     */
    Spinner spinnerEsporteAnunciar;
    RadioButton radioMasculinoAnunciar, radioFemininoAnunciar;
    EditText valorAnunciar, editTextLocalAnunciar, editTextCodigoAnunciar;
    Button buttonAnunciar;

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
    ImageButton fotoPerfil;

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

        /**
         * Variáveis de content_anunciar
         */
        spinnerEsporteAnunciar = (Spinner) findViewById(R.id.spinnerEsporteAnunciar);
        radioMasculinoAnunciar = (RadioButton) findViewById(R.id.radioMasculinoAnunciar);
        radioFemininoAnunciar = (RadioButton) findViewById(R.id.radioFemininoAnunciar);
        valorAnunciar = (EditText) findViewById(R.id.editTextValorAnunciar);
        editTextLocalAnunciar = (EditText) findViewById(R.id.editTextLocal);
        editTextCodigoAnunciar = (EditText) findViewById(R.id.editTextCodigo);
        buttonAnunciar = (Button) findViewById(R.id.buttonCadastrar);

        /**
         * Variáveis de content_pesquisar
         */
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
        fotoPerfil = (ImageButton) findViewById(R.id.imageButtonFotoPerfil);

        spinnerOrdenar = (Spinner) findViewById(R.id.spinnerOrdenar);
        listViewIngressos = (ListView) findViewById(R.id.listViewIngressos);

        vf = (ViewFlipper)findViewById(R.id.vfHome);
        vf.setDisplayedChild(0);

        setup();
    }

    private void setup() {

        ArrayAdapter<CharSequence> adapterEsportesPesquisar = ArrayAdapter.createFromResource(this,
                R.array.esportes_array, android.R.layout.simple_spinner_item);
        adapterEsportesPesquisar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEsporte.setAdapter(adapterEsportesPesquisar);

        ArrayAdapter<CharSequence> adapterEsportesAnunciar = ArrayAdapter.createFromResource(this,
                R.array.esportes_array, android.R.layout.simple_spinner_item);
        adapterEsportesAnunciar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEsporteAnunciar.setAdapter(adapterEsportesAnunciar);

        ArrayAdapter<CharSequence> adapterOrdenar = ArrayAdapter.createFromResource(this,
                R.array.esportes_array, android.R.layout.simple_spinner_item);
        adapterOrdenar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrdenar.setAdapter(adapterOrdenar);


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

        buttonAnunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextLocalAnunciar.getText().toString().isEmpty()) {
                    if (!editTextCodigoAnunciar.getText().toString().isEmpty()) {
                        if (!valorAnunciar.getText().toString().isEmpty()) {
                            vf.setDisplayedChild(0);

                            Toast.makeText(HomeActivity.this, "Ingresso anunciado com sucesso!", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(HomeActivity.this, "O campo Preço deve ser preenchido!", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(HomeActivity.this, "O campo Código do Ingresso deve ser preenchido!", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(HomeActivity.this, "O campo Localização deve ser preenchido!", Toast.LENGTH_LONG).show();
            }
        });

        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    try {
                        Uri selectedImage = imageReturnedIntent.getData();
                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                        imageViewPerfil.setImageBitmap(yourSelectedImage);
                    }
                    catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
        }
    }

}
