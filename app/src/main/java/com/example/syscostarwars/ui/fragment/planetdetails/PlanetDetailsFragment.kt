package com.example.syscostarwars.ui.fragment.planetdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.syscostarwars.R
import com.example.syscostarwars.databinding.FragmentPlanetDetailsBinding
import com.example.syscostarwars.viewmodel.PlanetsViewModel


class PlanetDetailsFragment : Fragment() {

    private val viewmodel: PlanetsViewModel by activityViewModels()
    lateinit var binding: FragmentPlanetDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planet_details, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.result = viewmodel.userSelectedPlanet.value
    }

    override fun onResume() {
        super.onResume()

    }
}