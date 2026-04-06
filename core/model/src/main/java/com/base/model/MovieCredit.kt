package com.base.model

import com.google.gson.annotations.SerializedName

data class MovieCreditResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<MovieCredit>
)

data class MovieCredit(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("character") val character: String?,
    @SerializedName("profile_path") val profilePath: String?
) {
    val profileUrl: String?
        get() = profilePath?.let { "https://image.tmdb.org/t/p/w185$it" }
}
