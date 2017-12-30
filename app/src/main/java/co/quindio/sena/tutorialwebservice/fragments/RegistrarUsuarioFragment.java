package co.quindio.sena.tutorialwebservice.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import co.quindio.sena.tutorialwebservice.ConsultaActivity;
import co.quindio.sena.tutorialwebservice.Main2Activity;
import co.quindio.sena.tutorialwebservice.R;
import co.quindio.sena.tutorialwebservice.Utilities.Http;
import co.quindio.sena.tutorialwebservice.Utilities.Preferences;
import co.quindio.sena.tutorialwebservice.actualizarUsuarioFragment;
import co.quindio.sena.tutorialwebservice.entidades.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarUsuarioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarUsuarioFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    //Aqui se encuentrar los elemento q van interactuar con el usuario
    EditText campoContrasena, campoRol;
    Spinner campoCargo;
    Button botonRegistro, botonactualizar;
    ProgressDialog progreso;

    //RequestQueue request; //permite la conexion con la web service
    JsonObjectRequest jsonObjectRequest; //permite la conexion con la web service


    public RegistrarUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarUsuarioFragment newInstance(String param1, String param2) {
        RegistrarUsuarioFragment fragment = new RegistrarUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//Aqui se encuentrar la varible porposionada anteriormente y su id de campo//

        View vista = inflater.inflate(R.layout.fragment_registrar_usuario, container, false);

        campoCargo = (Spinner) vista.findViewById(R.id.campoCargos);
        campoRol=(EditText) vista.findViewById(R.id.campoRol);
        campoContrasena = (EditText) vista.findViewById(R.id.campoContra);

        botonRegistro = (Button) vista.findViewById(R.id.btnRegistrar);

        botonactualizar = (Button) vista.findViewById(R.id.btnActualizar);




        //request = Volley.newRequestQueue(getContext());

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });


        return vista;
    }






    private void cargarWebService() {

        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        Thread tr = new Thread() {
            @Override
            public void run() {
                try {

                    new Http(getContext()).post(
                            "/consulta_servicio/registrar/wsJSONRegistro.php",
                            "{'contrasena':'"+campoContrasena.getText().toString()+"','rol':'"+campoCargo.getSelectedItem().toString()+"','name':'"+campoRol.getText().toString()+"'}"
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        if (Http.getCode() == 200) {
                            Toast.makeText(getContext(),"Se Ha registrado exitosamente",Toast.LENGTH_SHORT).show();
                            progreso.hide();
                            campoContrasena.setText("");
                            campoRol.setText("");
                        }else {
                            Toast.makeText( getContext() ,Http.getError(),Toast.LENGTH_LONG).show();
                        }
                        progreso.hide();
                    }
                } );
            }
        };
        tr.start();


    }

    @Override
    public void onResponse(JSONObject response) {

        //campoCargo.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(),"No se pudo registrar "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }






    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void onclickActualizar(View view) {
        Intent i= new Intent(getContext(),actualizarUsuarioFragment.class);
        startActivity(i);

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
