package com.example.greenlightexample.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greenlightexample.data.models.LocationClass
import com.example.greenlightexample.databinding.ItemLocationBinding

class LocationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var locationList: List<LocationClass> = listOf()
    var itemListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LocationHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            ::getItem,
            itemListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is LocationHolder)
            holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    fun getItem(position: Int): LocationClass {
        return locationList[position]
    }

    fun submitList(list: List<LocationClass>) {
        locationList = list
        notifyDataSetChanged()
    }

    fun setListener(listener: ItemClickListener) {
        itemListener = listener
    }

    class LocationHolder(
        private val binding: ItemLocationBinding,
        getItem: (Int) -> LocationClass,
        itemListener: ItemClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {
        init {

            binding.root.setOnClickListener {
                val position = adapterPosition
                val item = getItem(position)
                when (item) {
                    is LocationClass.ResponseArea -> itemListener?.onItemClick(item.territory)
                    is LocationClass.ResponseRegion -> itemListener?.onItemClick(item.territory)
                    is LocationClass.ResponseZone -> itemListener?.onItemClick(item.territory)
                }
            }


        }

        fun bind(locationClass: LocationClass) {
            when (locationClass) {
                is LocationClass.ResponseArea -> binding.tvLocation.text = locationClass.area
                is LocationClass.ResponseRegion -> binding.tvLocation.text =
                    locationClass.region
                is LocationClass.ResponseZone -> binding.tvLocation.text = locationClass.zone
            }
        }
    }

}