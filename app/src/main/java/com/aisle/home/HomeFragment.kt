package com.aisle.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aisle.R
import com.aisle.home.model.ProfileItem
import com.aisle.home.model.ProfilesItem
import com.aisle.home.model.UserResponse
import com.aisle.login.LoginActivity
import com.aisle.network.ApiClient
import com.aisle.network.ApiInterface
import com.aisle.utils.SharedPrefsManager
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Retrofit


class HomeFragment : Fragment() {


    var userList = ArrayList<String>()
    lateinit var adapter: SuggestedUserAdapter
    lateinit var sharedPrefsManager: SharedPrefsManager
    lateinit var recyclerViewSuggestedUser: RecyclerView
    val TAG = HomeFragment::class.java.simpleName
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var apiInterface: ApiInterface
    var retrofit: Retrofit? = null
    var userResponse : UserResponse? = null



    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_home,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPrefsManager = SharedPrefsManager(context as Context)

        initializationViews(view)
        setSuggestedUserAdapter()
        loadData()

    }

    private fun loadData() {
        compositeDisposable.add(
            apiInterface.getUserDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserResponse>(){
                    override fun onSuccess(t: UserResponse?) {


                        if (t!=null){
                            Log.e(TAG,"profileItem = ${t.invites.profileItem.size}")
                            val imgUrl = t.invites.profileItem[0].photos?.get(0)?.photo
                            Log.e(TAG,"imgUrl = ${imgUrl}")
                            val username = t.invites.profileItem[0].generalInformation.firstName+
                                    ", "+ t.invites.profileItem[0].generalInformation.age

                            Picasso.get().load(imgUrl)
                                .into(iv_user_image_home)

                            tx_user_full_name.setText(username)
                            tx_user_full_name.visibility = View.VISIBLE
                            tx_tap_review_notes.visibility = View.VISIBLE
                            userResponse = t
                        }
                    }

                    override fun onError(e: Throwable?) {

                    }

                })
        )
    }

    private fun setSuggestedUserAdapter() {

        userList.add("Teena")
        userList.add("Beena")
        userList.add("Bahashree")
        userList.add("Aisle")
        adapter = SuggestedUserAdapter(context!!, mUserList = userList)
        recyclerViewSuggestedUser.adapter = adapter
    }

    private fun initializationViews(view: View) {

        retrofit = ApiClient.getClient(context!!)
        if (retrofit!=null){
            apiInterface = retrofit!!.create(ApiInterface::class.java)
        }

        compositeDisposable = CompositeDisposable()

        recyclerViewSuggestedUser = view.findViewById(R.id.recyclerview_suggested_user)

        recyclerViewSuggestedUser.setHasFixedSize(true)
        recyclerViewSuggestedUser.setLayoutManager(GridLayoutManager(context, 2))


    }
}