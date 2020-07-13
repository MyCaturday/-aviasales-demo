package com.sedymov.aviasales.data.net

import com.sedymov.aviasales.core.models.search.CitiesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("autocomplete")
    fun getCheckupByObjectId(@Query("term") term: String, @Query("lang") lang: String = "ru"): Single<CitiesResponse>
}