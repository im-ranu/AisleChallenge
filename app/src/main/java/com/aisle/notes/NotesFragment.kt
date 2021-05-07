package com.aisle.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aisle.R
import com.aisle.home.HomeFragment

class NotesFragment:Fragment() {

    companion object {

        fun newInstance(): NotesFragment {
            return NotesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes,container,false)
        return view
    }
}