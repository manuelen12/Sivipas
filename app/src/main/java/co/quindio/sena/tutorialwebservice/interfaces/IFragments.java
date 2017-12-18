package co.quindio.sena.tutorialwebservice.interfaces;


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
import layout.registrar_servicio_Fragment;
import layout.registrareventoprocedimientosasistencialesFragment;

/**
 * Created by CHENAO on 5/08/2017.
 */

public interface IFragments extends BienvenidaFragment.OnFragmentInteractionListener,DesarrolladorFragment.OnFragmentInteractionListener,
        RegistrarUsuarioFragment.OnFragmentInteractionListener,ConsultarUsuarioFragment.OnFragmentInteractionListener,
        ConsultarListaUsuariosFragment.OnFragmentInteractionListener,
        RegistrarEventosE_AccidentalesFragment.OnFragmentInteractionListener,EventoAdversoMedicamentosFragment.OnFragmentInteractionListener,
        EventoAdversoDispositivoFragment.OnFragmentInteractionListener, TramitesAdministrativosFragment.OnFragmentInteractionListener,EventoProcesosAsistencialesFragment.OnFragmentInteractionListener,
        IncidenteSeguridadPacienteFragment.OnFragmentInteractionListener, ReporteIncidenteFragment.OnFragmentInteractionListener, ConsultasGeneralFragment.OnFragmentInteractionListener, registrar_servicio_Fragment.OnFragmentInteractionListener, registrareventoprocedimientosasistencialesFragment.OnFragmentInteractionListener {



}
