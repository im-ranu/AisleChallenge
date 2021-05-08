package com.aisle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aisle.home.HomeFragment
import com.aisle.profile.MatchesFragment
import com.aisle.profile.NotesFragment
import com.aisle.profile.ProfileFragment
import com.aisle.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {


    val TAG = MainActivity::class.java.simpleName
     var savedInstance : Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        this.savedInstance = savedInstanceState

        initialization(savedInstance)
    }

    private fun initialization(savedInstanceState: Bundle?) {

        discoverLL.setOnClickListener(this)
        notesLL.setOnClickListener(this)
        matchesLL.setOnClickListener(this)
        profileLL.setOnClickListener(this)

        if (savedInstanceState == null) {
            // 2
            supportFragmentManager
                // 3
                .beginTransaction()
                // 4
                .add(R.id.container, HomeFragment.newInstance(), Constants.HOME_FRAGMENT)
                // 5
                .commit()
        }
    }

    override fun onClick(v: View?) {

        val fragment = supportFragmentManager.findFragmentByTag(Constants.HOME_FRAGMENT) as HomeFragment

        when(v?.id){

            R.id.discoverLL->{
                    // 2

                Log.e(TAG,fragment.toString())

                if (!fragment.isVisible && fragment!=null) {
                    Log.e(TAG, "true" + fragment.toString())
                    supportFragmentManager
                            // 3
                            .beginTransaction()
                            // 4
                            .replace(R.id.container, HomeFragment.newInstance(), Constants.HOME_FRAGMENT)
                            .addToBackStack(Constants.HOME_FRAGMENT)
                            // 5
                            .commit()
                }

            }
            R.id.notesLL->{

                // 2
                supportFragmentManager
                    // 3
                    .beginTransaction()
                    // 4
                    .replace(R.id.container, NotesFragment.newInstance(), Constants.NOTES_FRAGMENT)
                    // 5
                        .addToBackStack(Constants.NOTES_FRAGMENT)
                    .commit()

            }
            R.id.matchesLL->{

                // 2
                supportFragmentManager
                    // 3
                    .beginTransaction()
                    // 4
                    .replace(R.id.container, MatchesFragment.newInstance(), Constants.MATCHES_FRAGMENT)
                    // 5
                        .addToBackStack(Constants.MATCHES_FRAGMENT)
                    .commit()

            }
            R.id.profileLL->{

                    // 2
                    supportFragmentManager
                        // 3
                        .beginTransaction()
                        // 4
                        .replace(R.id.container, ProfileFragment.newInstance(), Constants.PROFILE_FRAGMENT)
                        // 5
                            .addToBackStack(Constants.PROFILE_FRAGMENT)
                        .commit()

            }
        }

    }
}