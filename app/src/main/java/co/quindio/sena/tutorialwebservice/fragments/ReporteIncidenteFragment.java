
package co.quindio.sena.tutorialwebservice.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import co.quindio.sena.tutorialwebservice.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarEventosE_AccidentalesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarEventosE_AccidentalesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReporteIncidenteFragment extends Fragment implements View.OnClickListener  {



    ArrayList<String> listItems = new ArrayList<>();
    ArrayList<String> listItems2 = new  ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    //Spinner sp;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

        super.onStart();
        ReporteIncidenteFragment.BackTask bt1 = new ReporteIncidenteFragment.BackTask();
        bt1.execute();



    }

    @Override
    public void onClick(View v) {

        Thread tr = new Thread() {
            @Override
            public void run() {
                final String resultado = enviarDatoGet(campoServicio.getSelectedItem().toString(), EscribFecha.getText().toString(), campoEvento.getAdapter().toString());
                getActivity().runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosJSON( resultado );
                        if (r > 0) {
                            Intent i=new Intent( getContext(),IncidenteSeguridadPacienteFragment.class);
                            i.putExtra ("servicio",campoServicio.getSelectedItem().toString());
                            startActivity(i);

                        }else {
                            Toast.makeText( getContext() ,"Registro Incorrecto O Fallido",Toast.LENGTH_LONG).show();

                        }
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






    class BackTask extends AsyncTask<Void, Void, Void> {
        ArrayList<String> list2;

        protected void onPreExecute() {
            super.onPreExecute();
            list2 = new ArrayList<>();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://192.168.2.44/consulta_servicio/ListaServicio.php");
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // parse json data
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    // add interviewee name to arraylist
                    list2.add(jsonObject.getString("NomServicio"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            listItems2.addAll(list2);
            adapter2.notifyDataSetChanged();
        }



    }



    ///


    class BackTaskEvento extends AsyncTask<Void, Void, Void> {
        ArrayList<String> list;

        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                String ip=getString( R.string.ip );
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(ip+"/consulta_servicio/ListarReporteIncidente.php");
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // parse json data
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    // add interviewee name to arraylist
                    list.add(jsonObject.getString("Descripcion"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

        btnRegistra = (Button) v.findViewById(R.id.btnRegistra);

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.spinnerservicio, R.id.txtservicio, listItems2);
        campoServicio.setAdapter(adapter2);

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerservicio1, R.id.txtevento, listItems);
        campoEvento.setAdapter(adapter);
        btnRegistra.setOnClickListener(this);


        return v;

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
