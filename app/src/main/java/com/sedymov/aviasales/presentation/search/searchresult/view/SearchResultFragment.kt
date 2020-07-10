package com.sedymov.aviasales.presentation.search.searchresult.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener
import com.sedymov.aviasales.presentation.search.searchresult.presenter.SearchResultMoxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class SearchResultFragment : BaseFragmentWithOnBackPressedListener(), SearchResultMoxyView {

    private var mGoogleMap: GoogleMap? = null

    @Inject
    internal lateinit var mLoggingInteractor: LoggingInteractor

    @Inject
    internal lateinit var mSearchCitiesInteractor: SearchCitiesInteractor

    @Inject
    internal lateinit var mMessagingInteractor: MessagingInteractor

    @Inject
    internal lateinit var mSearchRouter: SearchRouter

    @Inject
    internal lateinit var mRxSchedulers: RxSchedulers

    @InjectPresenter
    internal lateinit var mPresenter: SearchResultMoxyPresenter

    @ProvidePresenter
    internal fun providePresenter(): SearchResultMoxyPresenter =
        SearchResultMoxyPresenter(mLoggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mSearchRouter, mRxSchedulers, getCitiesFromArgs())

    private inline fun getCitiesFromArgs(): Pair<City, City> =
        arguments!!.getSerializable(CITIES_EXTRA) as Pair<City, City>

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    override fun setMarkerAtStartCity(lat: Double, lon: Double, name: String) = setMarkerAt(lat, lon, name)

    override fun setMarkerAtDestinationCity(lat: Double, lon: Double, name: String) = setMarkerAt(lat, lon, name)

    private inline fun setMarkerAt(lat: Double, lon: Double, name: String) {

        mGoogleMap?.let { googleMap ->

            val position = LatLng(lat, lon)
            val marker = googleMap.addMarker(MarkerOptions().position(position).title(name))
            marker.showInfoWindow()
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(position))
        }
    }

    override fun drawLine(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>) {

        fun getLinePattern(): List<PatternItem> {

            val gapLengthPx = 25f
            return listOf(Gap(gapLengthPx), Dot())
        }

        mGoogleMap?.let { googleMap ->

            googleMap.addPolyline(

                PolylineOptions()
                    .add(
                        LatLng(firstPoint.first, firstPoint.second),
                        LatLng(secondPoint.first, secondPoint.second)
                    )
                    .geodesic(true)
                    .jointType(JointType.ROUND)
                    .pattern(getLinePattern())
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_search_result, container, false)

    private val callback = OnMapReadyCallback { googleMap ->

        mGoogleMap = googleMap
        mPresenter.onMapReady()

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        // Add polylines and polygons to the map. This section shows just
        // a single polyline. Read the rest of the tutorial to learn more.
        // Add polylines and polygons to the map. This section shows just
        // a single polyline. Read the rest of the tutorial to learn more.
//        var polyline1: Polyline? = googleMap.addPolyline(
//            PolylineOptions()
//                .clickable(true)
//                .add(
//                    LatLng(-35.016, 143.321),
//                    LatLng(-34.747, 145.592),
//                    LatLng(-34.364, 147.891),
//                    LatLng(-33.501, 150.217),
//                    LatLng(-32.306, 149.248),
//                    LatLng(-32.491, 147.309)
//                )
//        )
//
//        // Position the map's camera near Alice Springs in the center of Australia,
//        // and set the zoom factor so most of Australia shows on the screen.
//
//        // Position the map's camera near Alice Springs in the center of Australia,
//        // and set the zoom factor so most of Australia shows on the screen.
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-23.684, 133.903), 4f))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onBackPressed(): Boolean {

        mPresenter.moveBack()
        return true
    }

    companion object {

        fun newInstance(cities: Pair<City, City>): SearchResultFragment {

            return SearchResultFragment().apply {

                arguments = Bundle().apply {

                    putSerializable(CITIES_EXTRA, cities)
                }
            }
        }

        private const val CITIES_EXTRA = "com.sedymov.aviasales.CITIES_EXTRA"
    }
}