package com.example.greenlightexample.presentation.area

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.greenlightexample.R
import com.example.greenlightexample.databinding.FragmentZoneBinding
import com.example.greenlightexample.presentation.ItemClickListener
import com.example.greenlightexample.presentation.LocationAdapter
import com.example.greenlightexample.presentation.region.RegionFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AreaFragment : Fragment() {

    private val viewModel: AreaViewModel by viewModels()

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

        val bundle = arguments ?: return

        val args = AreaFragmentArgs.fromBundle(bundle)
        val characterData = args.selectedTerritory

        viewModel.setFilter(characterData)

        binding.svSearchArea.isVisible = true

        val locationAdapter = LocationAdapter()
        binding.rvZone.apply {
            adapter = locationAdapter
        }

        viewModel.areaData.observe(viewLifecycleOwner) {
            locationAdapter.submitList(it)
        }

        binding.svSearchArea.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchValue(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchValue(newText)
                return false
            }
        }
        )

    }

}