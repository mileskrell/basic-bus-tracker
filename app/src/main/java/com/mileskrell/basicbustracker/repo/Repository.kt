package com.mileskrell.basicbustracker.repo

import android.annotation.SuppressLint
import com.mileskrell.basicbustracker.model.Stop
import com.mileskrell.basicbustracker.net.TransLocService
import com.mileskrell.basicbustracker.net.jsonmodels.ArrivalEstimatesResponse
import com.mileskrell.basicbustracker.net.jsonmodels.StopsResponse
import java.text.SimpleDateFormat
import java.util.*

/**
 * Deals with fetching stuff from the TransLoc API.
 * Note that we don't do DB persistence in this app, because old data isn't useful.
 */
class Repository {

    suspend fun fetchInitialData(): List<Stop> {
        val client = TransLocService.getService()

        val stopsResponse = client.getStops(TransLocService.RUTGERS_AGENCY_ID) // TODO Cache this response
        val arrivalEstimatesResponse =
            client.getArrivalEstimates(TransLocService.RUTGERS_AGENCY_ID, TransLocService.TEMP_WEEKEND_1_ROUTE_ID)

        return produceListOfStops(stopsResponse.await(), arrivalEstimatesResponse.await())
    }

    private fun produceListOfStops(
        stopsResponse: StopsResponse,
        arrivalEstimatesResponse: ArrivalEstimatesResponse
    ): List<Stop> {

        return stopsResponse.data.filter { stopData ->
            // Filter out all the stops that we didn't get estimates for
            arrivalEstimatesResponse.data.any { arrivalEstimateData ->
                stopData.stopId == arrivalEstimateData.stopId
            }
        }.map { stopData ->
            // For all that remain, map each stop response to a Stop
            Stop(
                stopData.name,
                stopData.stopId,
                arrivalEstimatesResponse.data.firstOrNull { arrivalEstimateData ->
                    arrivalEstimateData.stopId == stopData.stopId
                }?.arrivals?.map { getMinutesUntil(it.arrivalAt) } ?: listOf()
            )
        }
    }

    /**
     * Returns minutes until an ISO 8601-formatted date
     */
    @SuppressLint("SimpleDateFormat")
    fun getMinutesUntil(date: String): Int {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
            .apply { timeZone = TimeZone.getTimeZone("UTC") }

        val arrivalTimeMinutes = df.parse(date).time / 1000 / 60
        val currentTimeMinutes = System.currentTimeMillis() / 1000 / 60

        return (arrivalTimeMinutes - currentTimeMinutes).toInt()
    }
}
