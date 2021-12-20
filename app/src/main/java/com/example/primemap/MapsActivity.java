package com.example.primemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.primemap.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    ListView menu;
    ActionMode ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        menu=(ListView)findViewById(R.id.opcion1);
        registerForContextMenu(menu);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //Declaramos un inflador
        MenuInflater m=getMenuInflater();
        //Inflamos el menú con el layout descrito
        m.inflate(R.menu.menu,menu);
        //LLamamos al constructor del madre con el menú ya inflado
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onItemClick(AdapterView<?> p, View v, int position, long id){

        // Start the CAB using the ActionMode.Callback defined above
        ac = MapsActivity.this.startActionMode((ActionMode.Callback) ac);
        v.setSelected(true);

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Marcador del Julián Marías
        LatLng iesJulianMarias = new LatLng(41.632183, -4.758648);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(iesJulianMarias).title("IES Julián Marías")
                .snippet("Teléfono: 983258932")
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(iesJulianMarias));

        //Marcador del Gregorio Fernandez
        LatLng gregorio = new LatLng(41.640145, -4.732625);
        Marker marker2 = mMap.addMarker(new MarkerOptions()
                .position(gregorio).title("IES Gregorio Fernandez")
                .snippet("Teléfono: 983258932")
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(gregorio));

        //Marcador del Galileo
        LatLng galileo = new LatLng(41.647043, -4.702394);
        Marker marker3 = mMap.addMarker(new MarkerOptions()
                .position(galileo).title("IES Galileo")
                .snippet("Teléfono: 983258932")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_delete)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(galileo));

        //Marcador del Rivera de Castilla
        LatLng rivera = new LatLng(41.664704, -4.723394);
        Marker marker4 = mMap.addMarker(new MarkerOptions()
                .position(rivera).title("IES Rivera de Castilla")
                .snippet("Teléfono: 983258932")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_dialog_map)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(rivera));

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        //Definir circulo
        CircleOptions circleOptions = new CircleOptions()
                .center(iesJulianMarias)
                .fillColor(R.color.teal_200)
                .strokeColor(R.color.design_default_color_error)
                .radius(500);
        Circle circle = mMap.addCircle(circleOptions);

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);

        /*Una polilínea (Polyline) es una serie de segmentos de líneas conectadas,
        con cada punto que se le indica, se le asigna una posicion en el mapa
        y luego se unen esos puntos mediante un segmento
         */

        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true) .color(R.color.teal_700) .width(20)
                .add(
                        new LatLng(41.632183, -4.758648),
                        new LatLng(41.640145, -4.732625),
                        new LatLng(41.647043, -4.702394),
                        new LatLng(41.664704, -4.723394)));

    }

    public void onMapClick(LatLng latLong){
        Toast.makeText(this, "Has hecho click en: " + latLong.latitude + latLong.longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}