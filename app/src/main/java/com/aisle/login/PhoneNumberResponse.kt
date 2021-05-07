package com.aisle.login

import com.google.gson.annotations.SerializedName

data class PhoneNumberResponse(@SerializedName("status")
                               var status: Boolean = false)