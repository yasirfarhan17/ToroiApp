package com.example.network_domain.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeResponseModel(
    @SerialName("perPage") var perPage: Int? = null,
    @SerialName("total") var total: Int? = null,
    @SerialName("data") var data: List<DataItem?>? = null,
    @SerialName("page") var page: Int? = null,
    @SerialName("totalPages") var totalPages: Int? = null,
    @SerialName("support") var support: Support? = null
)

@Serializable
data class Support(
    @SerialName("text") var text: String? = null,
    @SerialName("url") var url: String? = null
)

@Parcelize
@Serializable
data class DataItem(
    @SerialName("last_name") var lastName: String? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("avatar") var avatar: String? = null,
    @SerialName("first_name") var firstName: String? = null,
    @SerialName("email") var email: String? = null,
    @SerialName("phoneNumber") var phoneNumber: String? = null
) : Parcelable
