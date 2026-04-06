package com.base.model

import com.google.gson.annotations.SerializedName

data class MovieReview(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("author_details") val authorDetails: AuthorDetails?
)

data class AuthorDetails(
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("avatar_path") val avatarPath: String?,
    @SerializedName("rating") val rating: Double?
) {
    val avatarUrl: String?
        get() = avatarPath?.let {
            if (it.startsWith("/https")) {
                it.substring(1)
            } else {
                "https://image.tmdb.org/t/p/w200$it"
            }
        }
}
