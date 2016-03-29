package br.uff.ihc.trabalhoihc.Control;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.content.pm.Signature;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import br.uff.ihc.trabalhoihc.Model.DataHelper;
import br.uff.ihc.trabalhoihc.R;

public class LoginActivity extends FragmentActivity {


    private Button logar, criar, cadastrar, iniciarLogin;
    private LoginButton loginButton;
    private EditText login, novaSenha, repetirSenha, nome, usuarioLogin, senhaLogin;
    private CallbackManager callbackManager;
    private DataHelper dh;
    private TextView errorText, errorTextLogin;
    ViewFlipper vf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.dh = new DataHelper(this);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        logar = (Button) findViewById(R.id.logar);
        criar = (Button) findViewById(R.id.criarConta);
        iniciarLogin = (Button) findViewById(R.id.iniciarLogin);
        vf = (ViewFlipper)findViewById(R.id.vfLogin);
        login = (EditText) findViewById(R.id.login);
        novaSenha = (EditText) findViewById(R.id.novaSenha);
        repetirSenha = (EditText)findViewById(R.id.repeteSenha);
        usuarioLogin = (EditText)findViewById(R.id.usuarioLogin);
        senhaLogin = (EditText)findViewById(R.id.senhaLogin);
        cadastrar = (Button) findViewById(R.id.cadastrar);
        errorText = (TextView) findViewById(R.id.errorText);
        errorTextLogin = (TextView) findViewById(R.id.errorTextLogin);
        nome = (EditText) findViewById(R.id.nome);

        setuplogin();
    }

    public void setuplogin(){

        vf.setDisplayedChild(0);
        loginButton.setReadPermissions("public_profile");


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getFacebookData(loginResult);

                Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                LoginActivity.this.startActivity(it);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(2);
            }
        });

        iniciarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!usuarioLogin.getText().toString().isEmpty()) && (!senhaLogin.getText().toString().isEmpty())) {
                    int id = dh.login(usuarioLogin.getText().toString(), senhaLogin.getText().toString());
                    if (id != -1) {

                        SingletonUsuarioCtrl.setIdUsuario(id);

                        Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                        LoginActivity.this.startActivity(it);
                    } else errorTextLogin.setText("Login/Senha incorreto!");
                } else errorTextLogin.setText("O campo Login/Senha deve ser preenchido");
            }
        });

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vf.setDisplayedChild(1);

            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorText.setText("");
                if (!nome.getText().toString().isEmpty()) {
                    if (login.getText().length() > 7) {
                        if (novaSenha.getText().length() > 7) {
                            if (novaSenha.getText().toString().compareTo(repetirSenha.getText().toString()) == 0) {

                                int idUsuario = dh.cadastrar(login.getText().toString(), novaSenha.getText().toString(), nome.getText().toString());

                                if (idUsuario != -1) {

                                    SingletonUsuarioCtrl.setIdUsuario(idUsuario);

                                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                                    LoginActivity.this.startActivity(it);
                                }
                            } else errorText.setText("As senhas devem ser iguais");
                        } else errorText.setText("A senha deve ter no mínimo 8 caracteres");
                    }
                    if (login.getText().toString().isEmpty())
                        errorText.setText("O campo login está vazio");
                    if ((login.getText().length() < 7) && (login.getText().length() > 0))
                        errorText.setText("O campo login deve ter no mínimo 8 caracteres");
                } else errorText.setText("O campo nome está vazio");
            }
        });

    }

    private void getFacebookData(LoginResult loginResult){

    }

    public static void showHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    "br.uff.ihc.trabalhoihc", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("TIKET","KeyHash:"+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {

        if((vf.getDisplayedChild()==1)||(vf.getDisplayedChild()==2))
            vf.setDisplayedChild(0);

    }
}
