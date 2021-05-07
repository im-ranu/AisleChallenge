package com.aisle.network

import com.aisle.home.model.UserResponse
import com.aisle.otp.OtpResponse
import com.aisle.login.PhoneNumberRequest
import com.aisle.login.PhoneNumberResponse

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


interface ApiInterface {



    @POST("users/phone_number_login")
    fun phoneNumberLogin(@Body phoneNumberRequest: PhoneNumberRequest) : Single<PhoneNumberResponse>

    @POST("users/verify_otp")
    fun getOtpToken(@Body phoneNumberRequest: PhoneNumberRequest) : Single<OtpResponse>


    @GET("users/test_profile_list")
    fun getUserDetails() : Single<UserResponse>


/*
    companion object {

        var BASE_URL = "http://api.parceldeck.in/api/v1/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }*/
}