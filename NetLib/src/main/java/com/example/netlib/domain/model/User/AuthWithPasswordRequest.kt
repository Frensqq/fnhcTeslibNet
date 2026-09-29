package com.example.netlib.domain.model.User

import android.telephony.CellIdentity
import kotlinx.serialization.Serializable

@Serializable
data class AuthWithPasswordRequest(
    val identity: String,
    val password: String,
)
