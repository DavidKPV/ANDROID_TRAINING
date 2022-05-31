package com.david.preferencesactivity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_CONFIG = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrarConfigUser();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.configuraciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_configuraciones:
                Intent i = new Intent(this, ConfigUser.class);
                startActivityForResult(i, RESULT_CONFIG);
                break;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_CONFIG:
                mostrarConfigUser();
                break;
        }
    }
    private void mostrarConfigUser() {
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        StringBuilder builder = new StringBuilder();
        builder.append("\n Nobre de Usuario: " +
                sharedPrefs.getString("prefUsuario", "NULL"));
        builder.append("\n Enviar Reportes:" +
                sharedPrefs.getBoolean("prefEnviarReportes", false));
        builder.append("\n Frecuencia de Sincronización: " +
                sharedPrefs.getString("prefFrecuencuaSinc", "NULL"));
        TextView settingsTextView = (TextView) findViewById(R.id.tvConfiguraciones);
        settingsTextView.setText(builder.toString());
    }
}