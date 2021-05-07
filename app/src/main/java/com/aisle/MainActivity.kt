package com.aisle

import android.os.Bundle
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
    lateinit var savedInstanceState : Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            if (savedInstanceState != null) {
                this.savedInstanceState = savedInstanceState
            }



        initialization(savedInstanceState)
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

        when(v?.id){
            R.id.discoverLL->{
                    // 2
                    supportFragmentManager
                        // 3
                        .beginTransaction()
                        // 4
                        .replace(R.id.container, HomeFragment.newInstance(), Constants.HOME_FRAGMENT)
                        // 5
                        .commit()

            }
            R.id.notesLL->{

                // 2
                supportFragmentManager
                    // 3
                    .beginTransaction()
                    // 4
                    .replace(R.id.container, NotesFragment.newInstance(), Constants.PROFILE_FRAGMENT)
                    // 5
                    .commit()

            }
            R.id.matchesLL->{

                // 2
                supportFragmentManager
                    // 3
                    .beginTransaction()
                    // 4
                    .replace(R.id.container, MatchesFragment.newInstance(), Constants.PROFILE_FRAGMENT)
                    // 5
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
                        .commit()

            }
        }

    }
}