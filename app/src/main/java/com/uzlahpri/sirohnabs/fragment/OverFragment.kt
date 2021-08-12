package com.uzlahpri.sirohnabs.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.uzlahpri.sirohnabs.R
import com.uzlahpri.sirohnabs.databinding.FragmentOverBinding

class OverFragment : Fragment() {
    private lateinit var overBinding: FragmentOverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appBarTitle()
        overBinding = FragmentOverBinding.inflate(inflater, container, false)
        return overBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        overBinding.btnCobaLagi.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_overFragment_to_welcomeFragment))
    }

    private fun appBarTitle() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.app_name)
    }
}