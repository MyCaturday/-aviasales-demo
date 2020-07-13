package com.sedymov.aviasales.presentation.search.searchresult.view

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.SphericalUtil
import com.sedymov.aviasales.R
import com.sedymov.aviasales.core.executors.RxSchedulers
import com.sedymov.aviasales.core.interactors.common.LoggingInteractor
import com.sedymov.aviasales.core.interactors.common.MessagingInteractor
import com.sedymov.aviasales.core.interactors.search.cities.SearchCitiesInteractor
import com.sedymov.aviasales.core.models.search.City
import com.sedymov.aviasales.core.presentation.base.TimeInterpolator
import com.sedymov.aviasales.core.presentation.search.navigation.SearchRouter
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithOnBackPressedListener
import com.sedymov.aviasales.presentation.core.views.CityMarkerView
import com.sedymov.aviasales.presentation.search.searchresult.presenter.SearchResultMoxyPresenter
import com.sedymov.aviasales.utils.platform.createDrawableFromView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class SearchResultFragment : BaseFragmentWithOnBackPressedListener(), SearchResultMoxyView {

    private var mGoogleMap: GoogleMap? = null
    private var planeMarker: Marker? = null

    private val cityMarkerView: CityMarkerView by lazy { CityMarkerView(activity!!) }

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

    @Inject
    internal lateinit var mTimeInterpolator: TimeInterpolator

    @Inject
    internal lateinit var mSphericalUtil: com.sedymov.aviasales.core.presentation.base.SphericalUtil

    @InjectPresenter
    internal lateinit var mPresenter: SearchResultMoxyPresenter

    @ProvidePresenter
    internal fun providePresenter(): SearchResultMoxyPresenter =
        SearchResultMoxyPresenter(mLoggingInteractor, mSearchCitiesInteractor, mMessagingInteractor, mSearchRouter, mRxSchedulers, mTimeInterpolator, mSphericalUtil, getCitiesFromArgs())

    private inline fun getCitiesFromArgs(): Pair<City, City> =
        arguments!!.getSerializable(CITIES_EXTRA) as Pair<City, City>

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    override fun setMarkerAtStartCity(lat: Double, lon: Double, name: String) =
        setMarkerAt(lat, lon, name)

    override fun setMarkerAtDestinationCity(lat: Double, lon: Double, name: String) =
        setMarkerAt(lat, lon, name)

    override fun setPlaneMarker(lat: Double, lon: Double) {

        mGoogleMap?.let { googleMap ->

            planeMarker = googleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(lat, lon))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_plane))
                    .anchor(1.0f, 0.5f)
                    .flat(true)
            )
        }
    }

    override fun setPlaneMarkerPosition(lat: Double, lon: Double) {

        planeMarker?.position = LatLng(lat, lon)
    }

    override fun setPlaneMarkerRotation(rotation: Float) {

        planeMarker?.rotation = rotation
    }

    private inline fun setMarkerAt(lat: Double, lon: Double, name: String) {

        mGoogleMap?.let { googleMap ->

            val position = LatLng(lat, lon)
            cityMarkerView.setCityName(name)
            googleMap.addMarker(
                MarkerOptions()
                    .position(position)
                    .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(activity!!, cityMarkerView.rootView)))
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(position))
        }
    }

    override fun setCameraAt(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>) {

        val builder = LatLngBounds.Builder()
        builder.include(LatLng(firstPoint.first, firstPoint.second))
        builder.include(LatLng(secondPoint.first, secondPoint.second))
        val bounds = builder.build()

        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 0)

        mGoogleMap?.moveCamera(cu)
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