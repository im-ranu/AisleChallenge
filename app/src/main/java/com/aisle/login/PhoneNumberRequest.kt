package com.aisle.login

import com.google.gson.annotations.SerializedName

data class PhoneNumberRequest(@SerializedName("number")
                              var number: String = "",
                              @SerializedName("otp")
                              var otp : String="")