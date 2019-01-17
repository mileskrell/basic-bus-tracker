package com.mileskrell.basicbustracker.net.jsonmodels
import com.google.gson.annotations.SerializedName

/**
 * A list of stops from the TransLoc API
 */
data class StopsResponse(
    @SerializedName("api_latest_version")
    val apiLatestVersion: String, // 1.2
    @SerializedName("api_version")
    val apiVersion: String, // 1.2
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("expires_in")
    val expiresIn: Int, // 300
    @SerializedName("generated_on")
    val generatedOn: String, // 2019-01-14T00:50:28+00:00
    @SerializedName("rate_limit")
    val rateLimit: Int // 1
) {
    data class Data(
        @SerializedName("agency_ids")
        val agencyIds: List<String>,
        @SerializedName("code")
        val code: String, // 3200
        @SerializedName("description")
        val description: String,
        @SerializedName("location")
        val location: Location,
        @SerializedName("location_type")
        val locationType: String, // stop
        @SerializedName("name")
        val name: String, // George Street Southbound at Paterson Street
        @SerializedName("parent_station_id")
        val parentStationId: Any, // null
        @SerializedName("routes")
        val routes: List<String>,
        @SerializedName("station_id")
        val stationId: Any, // null
        @SerializedName("stop_id")
        val stopId: Int, // 4229698
        @SerializedName("url")
        val url: String
    ) {
        data class Location(
            @SerializedName("lat")
            val lat: Double, // 40.495066
            @SerializedName("lng")
            val lng: Double // -74.443999
        )
    }
}
