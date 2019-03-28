package com.example.service_web_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText nom;
    private String adrNumDep = "http://172.16.200.21/ws_gsb/index.php/c_webservice/lesmedecinsdudepartement/numdep/";
    private String adrNom = "http://172.16.200.21/ws_gsb/index.php/c_webservice/lesmedecins/nom/";
    private ProgressBar progressBar;
    private RadioButton rdNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        boutonClicked();
    }

    /**
     * Initialisation des liens avec les objets graphiques
     */
    private void init() {
        button = (Button) findViewById(R.id.btnValider);
        nom = (EditText) findViewById(R.id.txtNom);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setProgress(0);
        rdNom = (RadioButton) findViewById(R.id.rdNom);
    }

    /**
     * Ecoute l'événement du clic sur le bouton calcul
     */
    private void boutonClicked() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // On lance la tâche asynchrone
                AsyncJson task = new AsyncJson(MainActivity.this);
                if (!nom.getText().toString().isEmpty()) {
                    if (rdNom.isChecked()) {
                        if (containsForbiddenChars(nom.getText().toString()) == false) {
                            progressBar.setVisibility(View.VISIBLE);
                            task.execute(createAddress(1));
                        } else
                            Toast.makeText(MainActivity.this, "Rentrer que des caractères autorisés pour le nom (pas d'accents, de caractères spéciaux, de nombres)", Toast.LENGTH_SHORT).show();
                    } else {
                        if (tryParseInt(nom.getText().toString()) == true) {
                            progressBar.setVisibility(View.VISIBLE);
                            task.execute(createAddress(0));
                        } else {
                            Toast.makeText(MainActivity.this, "Rentrer une valeur numérique pour le département (pas de lettres, de caractères spéciaux)", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Remplir le champ demandé", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Créée une adresse en fonction du numéro passé en paramètre
     * @param num 0 pour recherche par département, 1 pour recherche par nom
     * @return l'adresse du service web
     */
    private String createAddress(int num) {
        if(num == 0)
            return adrNumDep + nom.getText().toString() + "/format/json";
        else if(num == 1)
            return adrNom + nom.getText().toString() + "/format/json";
        else
            return null;
    }

    /**
     * Fonction qui vérifie si la chaîne de caractères pasée en paramètre est un nombre
     * @param value
     * @return vrai ou faux
     */
    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Fonction qui vérifie si la chaîne de caractères passée en paramètre contient
     * des caractères indésirables
     * @param value
     * @return vrai ou faux
     */
    private boolean containsForbiddenChars(String value) {
        Pattern p = Pattern.compile("[^a-z]");
        Matcher m = p.matcher(value);
        return m.find();
    }
}
