package co.quindio.sena.tutorialwebservice;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,IFragments{






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);












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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item ) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment = null;
        boolean fragmentSeleccionado = false;

        if (id == R.id.nav_inicio) {
            miFragment = new BienvenidaFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_registro) {
            miFragment = new RegistrarUsuarioFragment();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_registroServicio) {
            miFragment = new registrar_servicio_Fragment();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_RegistroProcesosAsistenciales){
            miFragment = new registrareventoprocedimientosasistencialesFragment();
            fragmentSeleccionado = true;


        } else if (id == R.id.nav_consulta_individual) {
            miFragment = new ConsultarUsuarioFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_consulta_gral) {
            miFragment = new ConsultarListaUsuariosFragment();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_desarrollador) {
            miFragment = new DesarrolladorFragment();
            fragmentSeleccionado = true;






        } else if (id == R.id.nav_RegistrarIncidente) {
            miFragment = new ReporteIncidenteFragment();
            fragmentSeleccionado = true;

        } else if (id == R.id.nav_RegistrarIncidentePAsistenciales) {
            miFragment = new IncidenteSeguridadPacienteFragment();
            fragmentSeleccionado = true;




        } else if (id == R.id.nav_RegistrarEvento) {
            miFragment = new RegistrarEventosE_AccidentalesFragment();
            fragmentSeleccionado = true;




        } else if (id == R.id.nav_RegistrarEventoMedicamentos) {
            miFragment = new EventoAdversoMedicamentosFragment();
            fragmentSeleccionado = true;






        } else if (id == R.id.nav_RegistrarEventoAdversoDispositivosMedicos) {
            miFragment = new EventoAdversoDispositivoFragment();
            fragmentSeleccionado = true;




        } else if (id == R.id.nav_RegistrareventoTramitesAdministrativos) {
            miFragment = new TramitesAdministrativosFragment();
            fragmentSeleccionado = true;



        } else if (id == R.id.nav_RegistrarEventoProcesosAsistenciales) {
            miFragment = new EventoProcesosAsistencialesFragment();
            fragmentSeleccionado = true;


        }

        else if (id == R.id.nav_consulta_general) {
            miFragment = new ConsultasGeneralFragment();
            fragmentSeleccionado = true;


        }


        if (fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
