package com.aisle.login

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aisle.otp.ConfirmOTPActivity
import com.aisle.R
import com.aisle.network.ApiClient
import com.aisle.network.ApiInterface
import com.aisle.utils.Constants
import com.aisle.utils.SharedPrefsManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Retrofit


class LoginActivity : AppCompatActivity(),View.OnClickListener {


    lateinit var apiInterface: ApiInterface
    var retrofit: Retrofit? = null
    lateinit var compositeDisposable: CompositeDisposable
    val TAG  = LoginActivity::class.java.simpleName
    lateinit var sharedPreference:SharedPrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        sharedPreference = SharedPrefsManager(this)
        initialization()

        setButtonClickListener()

    }

    private fun initialization() {
        retrofit = ApiClient.getClient(this)
        if (retrofit!=null){
            apiInterface = retrofit!!.create(ApiInterface::class.java)
        }

        compositeDisposable = CompositeDisposable()
    }

    private fun setButtonClickListener() {
        bt_continue.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bt_continue ->{
                progressBarLogin.visibility = View.VISIBLE
                val prefix = et_prefix.text.toString()
                val number = et_number.text.toString()

                loginNetworkCall(prefix,number)

            }
        }

    }

    private fun loginNetworkCall(prefix: String, phone: String) {

        val phoneNumber = prefix+phone

        val phoneNumberRequest = PhoneNumberRequest(number = phoneNumber)
        compositeDisposable.add(apiInterface.phoneNumberLogin(phoneNumberRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<PhoneNumberResponse>() {
                override fun onSuccess(t: PhoneNumberResponse?) {

                    Log.e(TAG,t?.status.toString())
                    if (t?.status == true){
                        progressBarLogin.visibility = View.GONE
                       goToOtpScreen(prefix,phone)
                    }

                }

                override fun onError(e: Throwable?) {
                    if (e != null) {
                        progressBarLogin.visibility = View.GONE
                        Toast.makeText(this@LoginActivity,e.message.toString(),Toast.LENGTH_SHORT).show()
                    }
                }

            }))


    }

    private fun goToOtpScreen(prefix: String, number: String) {
        val intent = Intent(this, ConfirmOTPActivity::class.java)
        intent.putExtra(Constants.PREFIX,prefix)
        intent.putExtra(Constants.PHONE_NUMBER,number)
        startActivity(intent)
    }

    override fun onDestroy() {
        if (compositeDisposable!=null){
            compositeDisposable.dispose()
        }
        super.onDestroy()
    }
}