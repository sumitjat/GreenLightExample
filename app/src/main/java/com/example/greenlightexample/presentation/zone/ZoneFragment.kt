package com.example.greenlightexample.presentation.zone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.greenlightexample.R
import com.example.greenlightexample.databinding.FragmentZoneBinding
import com.example.greenlightexample.presentation.ItemClickListener
import com.example.greenlightexample.presentation.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZoneFragment : Fragment() {

    private val viewModel: ZoneViewModel by viewModels()

    private var _binding: FragmentZoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZoneBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getData()
        val locationAdapter = LocationAdapter()
        binding.rvZone.apply {
            adapter = locationAdapter
        }

        viewModel.zoneData.observe(viewLifecycleOwner) {
            locationAdapter.submitList(it)
        }

        locationAdapter.setListener(object : ItemClickListener {
            override fun onItemClick(locationSelected: String) {
                val bundle = Bundle().apply {
                    putString("selectedZone", locationSelected)
                }
                findNavController().navigate(
                    R.id.action_zoneFragment_to_regionFragment,
                    bundle
                )
            }

        })

    }
}