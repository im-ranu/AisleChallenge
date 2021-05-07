package com.aisle.otp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aisle.MainActivity
import com.aisle.R
import com.aisle.login.LoginActivity
import com.aisle.login.PhoneNumberRequest
import com.aisle.login.PhoneNumberResponse
import com.aisle.network.ApiClient
import com.aisle.network.ApiInterface
import com.aisle.utils.Constants
import com.aisle.utils.SharedPrefsManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_otp.*
import retrofit2.Retrofit

class ConfirmOTPActivity : AppCompatActivity(),View.OnClickListener {


    var cTimer: CountDownTimer? = null
    val OTP_TIMER = 60000
    var isResendOtpFlag = false

    lateinit var apiInterface: ApiInterface
    var retrofit: Retrofit? = null
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var prefix : String
    lateinit var phoneNumber : String
    lateinit var sharedPreference:SharedPrefsManager

    val TAG = ConfirmOTPActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_otp)
        sharedPreference = SharedPrefsManager(this)
        getIntentData()
        initalizations()
        setButtonClickListener()
        startTimer()

    }

    private fun getIntentData() {
        if (intent!=null){
            prefix = intent.getStringExtra(Constants.PREFIX)!!
            phoneNumber = intent.getStringExtra(Constants.PHONE_NUMBER)!!

            tv_phone_number_otp.setText(prefix+" "+phoneNumber)
        }


    }

    private fun initalizations() {
        retrofit = ApiClient.getClient(this)
        if (retrofit!=null){
            apiInterface = retrofit!!.create(ApiInterface::class.java)
        }

        compositeDisposable = CompositeDisposable()
    }

    private fun setButtonClickListener() {
        iv_change_number.setOnClickListener(this)
        tv_otp_timer.setOnClickListener(this)
        bt_continue_otp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_change_number -> {
                finish()
            }
            R.id.tv_otp_timer ->{
                if (isResendOtpFlag) startTimer()
            }
            R.id.bt_continue_otp->{
                progressBar.visibility = View.VISIBLE
                val otp = et_otp.text.toString()
                otpNetworkCall(prefix+phoneNumber,otp)
            }
        }
    }


    private fun otpNetworkCall(number: String,otp : String) {

        val phoneNumberRequest = PhoneNumberRequest(number = number,otp = otp)
        compositeDisposable.add(apiInterface.getOtpToken(phoneNumberRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<OtpResponse>() {

                override fun onSuccess(t: OtpResponse?) {
                    Log.e(TAG,t?.token.toString())

                    sharedPreference.save(Constants.TOKEN, t!!.token)
                    progressBar.visibility = View.GONE
                    goToHomeScreen()
                }

                override fun onError(e: Throwable?) {
                    if (e != null) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@ConfirmOTPActivity,e.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }))


    }


    fun goToHomeScreen(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    private fun startTimer()
    {


        cTimer = object : CountDownTimer(OTP_TIMER.toLong(), 250)
        {
            override fun onTick(millisUntilFinished: Long)
            {
                tv_otp_timer.setText("00:" + millisUntilFinished / 1000);
            }

            override fun onFinish()
            {
                tv_otp_timer.setText(getString(R.string.resend_otp))
                isResendOtpFlag = true
            }
        }.start()
    }

    fun cancelTimer() {
        if (cTimer != null) cTimer!!.cancel()
    }


    override fun onDestroy() {
        if (compositeDisposable!=null){
            compositeDisposable.dispose()
        }
        cancelTimer()
        super.onDestroy()

    }
}

