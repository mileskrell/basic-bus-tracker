package com.mileskrell.basicbustracker.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mileskrell.basicbustracker.net.jsonmodels.ArrivalEstimatesResponse
import com.mileskrell.basicbustracker.net.jsonmodels.StopsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Retrofit service for accessing TransLoc API
 */
interface TransLocService {

/*
    @Headers("X-RapidAPI-Key: $TRANSLOC_KEY")
    @GET("agencies.json")
    fun getAgencies(@Query("geo_area") geoArea: String
    ): Deferred<List<AgenciesResponse>>

    @Headers("X-RapidAPI-Key: $TRANSLOC_KEY")
    @GET("routes.json")
    fun getRoutes(@Query("agencies") agency: String
    ): Deferred<RoutesResponse>
*/

    @Headers("X-RapidAPI-Key: $TRANSLOC_KEY")
    @GET("arrival-estimates.json")
    fun getArrivalEstimates(
        @Query("agencies") agency: Int,
        @Query("routes") route: Int
    ): Deferred<ArrivalEstimatesResponse>

    @Headers("X-RapidAPI-Key: $TRANSLOC_KEY")
    @GET("stops.json")
    fun getStops(
        @Query("agencies") agency: Int
    ): Deferred<StopsResponse>

    companion object TransLocRetrofitClient {
//        const val TRANSLOC_KEY = ""
        const val BASE_URL = "https://transloc-api-1-2.p.rapidapi.com/"
        const val RUTGERS_AGENCY_ID = 1323

        // In a real bus tracking app (with support for all routes), the route IDs wouldn't be constants.
        // Instead, I would use the getRoutes call to actually figure what the routes were.
        const val TEMP_WEEKEND_1_ROUTE_ID = 4012668
        const val TEMP_WEEKEND_2_ROUTE_ID = 4012670

        private var _retrofit: Retrofit? = null

        private val retrofit: Retrofit
            get() = _retrofit ?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .also { _retrofit = it }

        fun getService() = retrofit.create(TransLocService::class.java)
    }
}
