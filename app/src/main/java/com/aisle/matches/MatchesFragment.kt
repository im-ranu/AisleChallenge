package com.aisle.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aisle.R
import com.aisle.home.HomeFragment

class MatchesFragment:Fragment() {

    companion object {

        fun newInstance(): MatchesFragment {
            return MatchesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_matches,container,false)
        return view
    }
}