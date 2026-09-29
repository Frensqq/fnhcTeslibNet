package com.example.netlib.domain.model.User

import android.telephony.CellIdentity

data class AuthWithPasswordRequest(
    val identity: String,
    val password: String,
)
