
package co.quindio.sena.tutorialwebservice.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.kevinsawicki.http.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import co.quindio.sena.tutorialwebservice.MainActivity;
import co.quindio.sena.tutorialwebservice.R;
import co.quindio.sena.tutorialwebservice.Utilities.Http;
import co.quindio.sena.tutorialwebservice.Utilities.Preferences;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarEventosE_AccidentalesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarEventosE_AccidentalesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReporteIncidenteFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener  {



    ArrayList<String> listItems = new ArrayList<>();
    ArrayList<String> listItems2 = new  ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    EditText et;
    //Spinner sp;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ProgressDialog progreso;

    private OnFragmentInteractionListener mListener;
    //Aqui se encuentrar las varible segun el objeto



    public ReporteIncidenteFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarEventosE_AccidentalesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarEventosE_AccidentalesFragment newInstance(String param1, String param2) {
        RegistrarEventosE_AccidentalesFragment fragment = new RegistrarEventosE_AccidentalesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void  onStart() {

        super.onStart();
        ReporteIncidenteFragment.BackTaskEvento bt = new ReporteIncidenteFragment.BackTaskEvento();
        bt.execute();

    }

    @Override
    public void onClick(View v) {
        Preferences.setParams(getContext(),"{'servicio':'" + campoServicio + "','evento':'" + campoEvento +"','fecha':'"+EscribFecha+"'}");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragmentG = new IncidenteSeguridadPacienteFragment();
        fragmentManager.beginTransaction().replace(
                R.id.content_main, fragmentG, "") .commit();

        //cargarWebService();
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
                            "/consulta_servicio/registrar/wJONRegistrarIncidentes.php",
                            "{'servicio':'" + campoServicio + "','evento':'" + campoEvento +"','fecha':'"+EscribFecha+"'}"
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
                            //campoServicio.setText("");
                            //campoEvento.setText("");
                            EscribFecha.setText("");
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


    public String enviarDatoGet(String s, String servicio, String evento) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;


        try {
            String ip=getString( R.string.ip );
            url = new URL( ip+"/consulta_servicio/registrar/wJONRegistrarIncidentes?servicio=" + campoServicio + "&evento=" + campoEvento +"&fecha="+EscribFecha);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            respuesta=connection.getResponseCode();

            result=new StringBuilder();

            if (respuesta==HttpURLConnection.HTTP_OK){

                InputStream in=new BufferedInputStream( connection.getInputStream() );
                BufferedReader reader=new BufferedReader(new InputStreamReader( in ));


                while ((linea= reader.readLine())!=null){

                    result.append(linea);
                }
            }

        } catch (Exception e) {}

        return result.toString();

    }

    public int obtDatosJSON(String response){
        int res=0;
        try {
            JSONArray json=new JSONArray(response);
            if (json.length()>0){

                res=1;
            }
        }catch (Exception e){}
        return res;




    }

    class BackTaskEvento extends AsyncTask<Void, Void, Void> {


        ArrayList<String> list;
        ArrayList<String> list2;
        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            list2 = new ArrayList<>();
        }

        protected Void doInBackground(Void... params) {
            final Http x = new Http(getContext());
            Thread tr2 = new Thread() {
                @Override
                public void run() {

                    try {
                        x.get(
                                "/consulta_servicio/ListaServicio.php",
                                ""
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    getActivity().runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            if (x.getCode() != 200) {
                                Toast.makeText( getContext() ,"Error Servicios",Toast.LENGTH_LONG).show();
                            }else {
                                try {
                                    JSONArray x2 = new JSONArray(x.getResult().getString("servicio"));
                                    for (int i = 0; i <x2.length(); i++) {
                                        JSONObject jsonObject = x2.getJSONObject(i);
                                        // add interviewee name to arraylist
                                        list.add(jsonObject.getString("Descripcion"));
                                    }
                                    JSONArray x3 = new JSONArray(x.getResult().getString("eventos"));
                                    for (int i = 0; i <x3.length(); i++) {
                                        JSONObject jsonObject = x3.getJSONObject(i);
                                        // add interviewee name to arraylist
                                        list2.add(jsonObject.getString("Descripcion"));
                                    }

                                    listItems.addAll(list2);
                                    adapter.notifyDataSetChanged();
                                    listItems2.addAll(list);
                                    adapter2.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } );
                }
            };
            tr2.start();
            return null;
        }

        protected void onPostExecute(Void result) {
            listItems.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    TextView textView2;


    Spinner campoServicio;
    TextView textView3;
    Spinner campoEvento;
    TextView textView1;
    EditText EscribFecha;
    EditText etPlannedDate2;
    Button btnRegistra;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registrar_eventos_e__accidentales, container, false);
        textView1 = (TextView) v.findViewById(R.id.textView1);
        textView2 = (TextView) v.findViewById(R.id.textView2);
        textView3 = (TextView) v.findViewById(R.id.textView3);
        campoServicio = (Spinner) v.findViewById(R.id.campoServicio);
        campoEvento = (Spinner) v.findViewById(R.id.campoEvento);
        EscribFecha =(EditText) v.findViewById(R.id.EscribFecha);
        etPlannedDate2 = (EditText) v.findViewById(R.id.etPlannedDate);
        Log.d("Verdadera Prueba", String.valueOf(etPlannedDate2));
        btnRegistra = (Button) v.findViewById(R.id.btnRegistra);

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.spinnerservicio, R.id.txtservicio, listItems2);
        campoServicio.setAdapter(adapter2);

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerservicio1, R.id.txtevento, listItems);
        campoEvento.setAdapter(adapter);
        btnRegistra.setOnClickListener(this);
        etPlannedDate2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("funcionaaa", "ssssssiii");
                final java.util.Calendar c = java.util.Calendar.getInstance();
                int mYear = c.get(java.util.Calendar.YEAR);
                int mMonth = c.get(java.util.Calendar.MONTH);
                int mDay = c.get(java.util.Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), ReporteIncidenteFragment.this, mYear, mMonth, mDay);
                datePickerDialog.setTitle("Seleccione la fecha");
                datePickerDialog.show();
            }
        });

        return v;

    }


    @Override
    public void onDateSet(DatePicker v, int year, int month, int dayOfMonth) {
        final String selectedDate = year + " / " + (month+1) + " / " + dayOfMonth;
        etPlannedDate2.setText(selectedDate);
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


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                EscribFecha.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

}
