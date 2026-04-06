package com.base.model

import com.google.gson.annotations.SerializedName

data class MovieVideoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<MovieVideo>
)

data class MovieVideo(
    @SerializedName("id") val id: String,
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("site") val site: String,
    @SerializedName("type") val type: String
) {
    val youtubeUrl: String?
        get() = if (site.equals("YouTube", ignoreCase = true)) "https://www.youtube.com/watch?v=$key" else null

    val thumbnailUrl: String?
        get() = if (site.equals("YouTube", ignoreCase = true)) "https://img.youtube.com/vi/$key/mqdefault.jpg" else null
}
