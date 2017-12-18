package co.quindio.sena.tutorialwebservice.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import co.quindio.sena.tutorialwebservice.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarUsuarioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventoAdversoDispositivoFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    //Aqui se encuentrar los elemento q van interactuar con el usuario
    EditText nomPaciente,campDocument,nombDispositiv,CampDescrip;
    Button botonRegistro ;
    ProgressDialog progreso;

    RequestQueue request; //permite la conexion con la web service
    JsonObjectRequest jsonObjectRequest; //permite la conexion con la web service

    public EventoAdversoDispositivoFragment() {
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
    public static EventoAdversoDispositivoFragment newInstance(String param1, String param2) {
        EventoAdversoDispositivoFragment fragment = new EventoAdversoDispositivoFragment();
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

        View vista=inflater.inflate(R.layout.fragment_evento_adverso_dispositivo, container, false);

        nomPaciente= (EditText) vista.findViewById(R.id.NombPacient);
        campDocument= (EditText) vista.findViewById(R.id.CampoDocument);
        nombDispositiv= (EditText) vista.findViewById(R.id.NombDispositivo);
        CampDescrip= (EditText) vista.findViewById(R.id.campDescrip);



        botonRegistro= (Button) vista.findViewById(R.id.btnDescript);

        request= Volley.newRequestQueue(getContext());

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


        String ip=getString( R.string.ip );
        String url=ip+"/consulta_servicio/registrar/wJONRegistrarEventoDispositivos&NomPac="+nomPaciente.getText().toString()+"&Documento="+campDocument.getText().toString()+"Dispositivo="+nombDispositiv.getText().toString()+"DescSuceso="+CampDescrip.getText().toString();
        // http://192.168.2.44/consulta_servicio/registrar/wsJSONRegistro.php?contrasena=1234&name=Gerente
        url=url.replace(" ","%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Se Ha registrado exitosamente",Toast.LENGTH_SHORT).show();
        progreso.hide();


        nomPaciente.setText("");
        campDocument.setText("");
        nombDispositiv.setText("");
        CampDescrip.setText("");
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
