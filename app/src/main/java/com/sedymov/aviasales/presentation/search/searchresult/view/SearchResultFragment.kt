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
import com.sedymov.aviasales.core.presentation.search.searchresult.presenter.SearchResultPresenter
import com.sedymov.aviasales.core.presentation.search.searchresult.view.SearchResultView
import com.sedymov.aviasales.di.ComponentStorage
import com.sedymov.aviasales.presentation.base.fragment.BaseFragmentWithErrorMessageSupport
import com.sedymov.aviasales.presentation.core.views.CityMarkerView
import com.sedymov.aviasales.utils.platform.createDrawableFromView
import com.sedymov.aviasales.utils.platform.toLatLng
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class SearchResultFragment : BaseFragmentWithErrorMessageSupport(), SearchResultView {

    private var mGoogleMap: GoogleMap? = null
    private var planeMarker: Marker? = null

    private val cityMarkerView: CityMarkerView by lazy { CityMarkerView(activity!!) }

    @Inject
    internal lateinit var mPresenterProvider: Provider<SearchResultPresenter>

    private val mPresenter: SearchResultPresenter by moxyPresenter { mPresenterProvider.get() }

    override fun inject() = ComponentStorage.getInstance().searchComponent.inject(this)

    override fun setMarkerAtStartCity(point: Pair<Double, Double>, name: String) =
        setMarkerAt(point, name)

    override fun setMarkerAtDestinationCity(point: Pair<Double, Double>, name: String) =
        setMarkerAt(point, name)

    override fun setPlaneMarker(point: Pair<Double, Double>) {

        mGoogleMap?.let { googleMap ->

            planeMarker = googleMap.addMarker(
                MarkerOptions()
                    .position(point.toLatLng())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_plane))
                    .anchor(1.0f, 0.5f)
                    .flat(true)
            )
        }
    }

    override fun setPlaneMarkerPosition(point: Pair<Double, Double>) {

        planeMarker?.position = point.toLatLng()
    }

    override fun setPlaneMarkerRotation(rotation: Float) {

        planeMarker?.rotation = rotation
    }

    private inline fun setMarkerAt(point: Pair<Double, Double>, name: String) {

        mGoogleMap?.let { googleMap ->

            val position = point.toLatLng()
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
        builder.include(firstPoint.toLatLng())
        builder.include(secondPoint.toLatLng())
        val bounds = builder.build()

        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 0)

        mGoogleMap?.moveCamera(cu)
    }

    override fun drawLine(firstPoint: Pair<Double, Double>, secondPoint: Pair<Double, Double>) {

        fun getLinePattern(): List<PatternItem> {

            val gapLengthPx = 25f
            return listOf(Gap(gapLengthPx), Dot())
        }

        mGoogleMap?.addPolyline(

            PolylineOptions()
                .add(
                    firstPoint.toLatLng(),
                    secondPoint.toLatLng()
                )
                .geodesic(true)
                .jointType(JointType.ROUND)
                .pattern(getLinePattern())
        )
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

        fun newInstance(): SearchResultFragment {

            return SearchResultFragment()
        }
    }
}