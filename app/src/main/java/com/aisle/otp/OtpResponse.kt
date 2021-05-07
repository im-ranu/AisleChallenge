package com.aisle.otp

import com.google.gson.annotations.SerializedName

data class OtpResponse(@SerializedName("token")
                       val token: String = "")