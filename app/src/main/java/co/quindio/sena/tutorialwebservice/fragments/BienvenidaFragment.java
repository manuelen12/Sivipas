package co.quindio.sena.tutorialwebservice.fragments;

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
 * {@link BienvenidaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BienvenidaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BienvenidaFragment extends Fragment implements View.OnClickListener {


    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    //Spinner sp;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BienvenidaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BienvenidaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BienvenidaFragment newInstance(String param1, String param2) {
        BienvenidaFragment fragment = new BienvenidaFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );


        /// no estas inflando el layout donde se encuentra este Spinner
        //Observa el error en esta linea
        //Estas diciendo que el Spinner lo vas a buscar dentro del Spinner
        //sp = (Spinner) sp.findViewById(R.id.Spinner3);//
        //Ya el Spinner lo inflamos en el metodo anterior

        //Aqui, se supone que ya existe, xq ya fue inflado
        //Pero lo inflamos con otro nombre
        //veamos
        //sp.setAdapter(adapter);//sp no se estaba inflanco correctamente
    }


    @Override
    public void onClick(View v) {
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String resultado = enviarDatoGet(Spinner3.getSelectedItem().toString(), Edit_contraseña.getText().toString() );
                getActivity().runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosJSON( resultado );
                        if (r > 0) {
                            Intent i=new Intent( getContext(),IncidenteSeguridadPacienteFragment.class);
                            i.putExtra ("name",Spinner3.getSelectedItem().toString());
                            startActivity(i);

                        }else {
                            Toast.makeText( getContext() ,"Usuario O Contraseña Incorrecta",Toast.LENGTH_LONG).show();

                        }
                    }
                } );
            }
        };
        tr.start();
    }

    public String enviarDatoGet(String name, String contrasena) {
        Log.d("error", name);
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;


        try {
            String ip=getString( R.string.ip );
            url = new URL( ip+"/consulta_servicio/login/loguear.php?name=" + Spinner3 + "&contrasena=" + Edit_contraseña );
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



    public void onStart() {
        super.onStart();
        BackTask bt = new BackTask();
        bt.execute();
    }




    class BackTask extends AsyncTask<Void, Void, Void> {
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
                HttpPost httppost = new HttpPost(ip+"/consulta_servicio/ListaCargo.php");
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
                    list.add(jsonObject.getString("name"));

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

    TextView tv_cargo;//Ahora pertenece a toda la clase y no solo al metodo
    Spinner Spinner3;
    EditText Edit_contraseña;
    Button Btn_ingresar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Ahora fijate, dentro de un Fragmento, es aqui donde debes inflar los elementos de la vista
        //Estas llamando a un Spinner que no has inflado
        //En general se hace de esta forma
        View v = inflater.inflate(R.layout.fragment_bienvenida, container, false);

        // En v esta la vista que acabas de inflar, ahora debes inflar los elementos de esta vista....  VEAMOS
        //Tienes un TextView

        //FIjate que Declare un TextView, y digo que va a buscarlo en la vista por "id" (v.findViewById)
        //En la vista (layout) le asinaste un id, veamos
        //el formato es
        // tipo nombre = (tipo) v.findViewById(R.id.nombre);
        tv_cargo = (TextView) v.findViewById(R.id.tv_cargo);

        //veamos que otro elemento hay en la vista
        //Cada elemento debes inflarlo, xq el layout es solo la vista, pero la logica es java
        //y debes crear el enlace
        //Para inflar el Spinner
        Spinner3 = (Spinner) v.findViewById(R.id.Spinner3);
        Edit_contraseña = (EditText) v.findViewById(R.id.Edit_contraseña);
        Btn_ingresar = (Button) v.findViewById(R.id.Btn_ingresar);
        Btn_ingresar.setOnClickListener(this);

        //Ya se inflaron los 4 elementos de la vista

        /// FIJEMEONOS EN ALGO
        //Todos los objetos fueron declarados dentro del metodo
        //eso significa que solo se pueden usar dentro de este metodo

        //En nuestro caso queremos usarlo en toda la clase, por eso debemos declararlos fuera del metodo


        //se pueden declarar fuera, pero se inflan dentro de este metodo

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnercargo, R.id.txt, listItems);
        Spinner3.setAdapter(adapter);

        //Notaste que es normal hacerlo denetor de este mismo metodo
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


    private class MainActivity {
    }
}
