package com.mileskrell.basicbustracker.model

/**
 * Represents a stop, including the estimated arrival times (Weekend 1) for that stop
 */
data class Stop(val name: String, val stopId: Int, val estimates: List<Int>)
