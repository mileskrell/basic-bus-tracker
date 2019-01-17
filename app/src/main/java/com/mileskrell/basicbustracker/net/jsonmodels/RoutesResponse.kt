package com.mileskrell.basicbustracker.net.jsonmodels
import com.google.gson.annotations.SerializedName

/**
 * A list of routes from the TransLoc API
 */
data class RoutesResponse(
    @SerializedName("api_latest_version")
    val apiLatestVersion: String, // 1.2
    @SerializedName("api_version")
    val apiVersion: String, // 1.2
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("expires_in")
    val expiresIn: Int, // 300
    @SerializedName("generated_on")
    val generatedOn: String, // 2019-01-14T00:08:40+00:00
    @SerializedName("rate_limit")
    val rateLimit: Int // 1
) {
    data class Data(
        @SerializedName("1323")
        val x1323: List<X1323>
    ) {
        data class X1323(
            @SerializedName("agency_id")
            val agencyId: Int, // 1323
            @SerializedName("color")
            val color: String, // 666699
            @SerializedName("description")
            val description: String,
            @SerializedName("is_active")
            val isActive: Boolean, // false
            @SerializedName("is_hidden")
            val isHidden: Boolean, // false
            @SerializedName("long_name")
            val longName: String, // Weekend 2
            @SerializedName("route_id")
            val routeId: String, // 4012670
            @SerializedName("segments")
            val segments: List<Any>,
            @SerializedName("short_name")
            val shortName: String, // TEMP
            @SerializedName("stops")
            val stops: List<String>,
            @SerializedName("text_color")
            val textColor: String, // FFFFFF
            @SerializedName("type")
            val type: String, // bus
            @SerializedName("url")
            val url: String
        )
    }
}
