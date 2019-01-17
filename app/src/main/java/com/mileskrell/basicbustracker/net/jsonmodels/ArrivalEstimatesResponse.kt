package com.mileskrell.basicbustracker.net.jsonmodels
import com.google.gson.annotations.SerializedName

/**
 * A list of arrival estimates from the TransLoc API
 */
data class ArrivalEstimatesResponse(
    @SerializedName("api_latest_version")
    val apiLatestVersion: String, // 1.2
    @SerializedName("api_version")
    val apiVersion: String, // 1.2
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("expires_in")
    val expiresIn: Int, // 5
    @SerializedName("generated_on")
    val generatedOn: String, // 2019-01-14T14:51:22+00:00
    @SerializedName("rate_limit")
    val rateLimit: Int // 1
) {
    data class Data(
        @SerializedName("agency_id")
        val agencyId: String, // 1323
        @SerializedName("arrivals")
        val arrivals: List<Arrival>,
        @SerializedName("stop_id")
        val stopId: Int // 4229592
    ) {
        data class Arrival(
            @SerializedName("arrival_at")
            val arrivalAt: String, // 2019-01-14T10:37:48-05:00
            @SerializedName("route_id")
            val routeId: Int, // 4012670
            @SerializedName("type")
            val type: String, // vehicle-based
            @SerializedName("vehicle_id")
            val vehicleId: Int // 4017111
        )
    }
}
