package co.quindio.sena.tutorialwebservice;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import co.quindio.sena.tutorialwebservice.fragments.BienvenidaFragment;
import co.quindio.sena.tutorialwebservice.fragments.ConsultarListaUsuariosFragment;
import co.quindio.sena.tutorialwebservice.fragments.ConsultarUsuarioFragment;
import co.quindio.sena.tutorialwebservice.fragments.ConsultasGeneralFragment;
import co.quindio.sena.tutorialwebservice.fragments.DesarrolladorFragment;
import co.quindio.sena.tutorialwebservice.fragments.EventoAdversoDispositivoFragment;
import co.quindio.sena.tutorialwebservice.fragments.EventoAdversoMedicamentosFragment;
import co.quindio.sena.tutorialwebservice.fragments.EventoProcesosAsistencialesFragment;
import co.quindio.sena.tutorialwebservice.fragments.IncidenteSeguridadPacienteFragment;
import co.quindio.sena.tutorialwebservice.fragments.RegistrarEventosE_AccidentalesFragment;
import co.quindio.sena.tutorialwebservice.fragments.RegistrarUsuarioFragment;
import co.quindio.sena.tutorialwebservice.fragments.ReporteIncidenteFragment;
import co.quindio.sena.tutorialwebservice.fragments.TramitesAdministrativosFragment;
import co.quindio.sena.tutorialwebservice.interfaces.IFragments;
import layout.registrar_servicio_Fragment;
import layout.registrareventoprocedimientosasistencialesFragment;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,IFragments {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Fragment miFragment=new BienvenidaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
