package com.mileskrell.basicbustracker.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mileskrell.basicbustracker.R
import com.mileskrell.basicbustracker.model.Stop
import kotlinx.android.synthetic.main.viewholder_for_stop.view.*

class EstimatesAdapter : RecyclerView.Adapter<EstimatesAdapter.StopViewHolder>() {

    var stops: List<Stop> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopViewHolder {
        return StopViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_for_stop, parent, false)
        )
    }

    override fun getItemCount() = stops.size

    override fun onBindViewHolder(holder: StopViewHolder, position: Int) {
        holder.setupFor(position)
    }

    fun loadStops(stops: List<Stop>) {
        this.stops = stops
        notifyDataSetChanged()
    }

    inner class StopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setupFor(position: Int) {
            itemView.text_view_stop_name.text = stops[position].name
            itemView.text_view_estimates.text = "Arriving in "
            for ((i, estimate) in stops[position].estimates.withIndex()) {
                itemView.text_view_estimates.append(estimate.toString())
                if (i < stops[position].estimates.size - 1) {
                    itemView.text_view_estimates.append(", ")
                }
            }
            itemView.text_view_estimates.append(" minutes")
            val color = when (stops[position].estimates[0]) {
                in 0..5 -> 0xFFDB4437.toInt() // red
                in 6..10 -> 0xFFF4B400.toInt() // yellow
                else -> 0xFF4285F4.toInt() // blue
            }
            itemView.text_view_estimates.setTextColor(color)
        }
    }
}
