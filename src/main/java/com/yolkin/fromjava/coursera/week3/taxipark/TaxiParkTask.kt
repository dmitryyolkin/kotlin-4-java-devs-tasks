package com.yolkin.fromjava.coursera.week3.taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        allDrivers
                .filter {driver ->  trips.none{ trip -> trip.driver == driver } }
                .toSet()


/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        allPassengers
                .filter {passenger -> trips.count{passenger in it.passengers} >= minTrips}
                .toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        allPassengers
                .filter {passenger -> trips.count{ it.driver == driver && passenger in it.passengers} > 1}
                .toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        trips
                .flatMap {trip -> trip.passengers.associateWith { trip }.entries }
                .groupBy({ it.key }, { it.value })
                .filter { it.value.count{ it.discount != null && it.discount > 0 } > it.value.size / 2 }
                .map { it.key }
                .toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return trips
            .groupBy {
                val bucket = it.duration / 10
                IntRange(bucket * 10, bucket * 10 + 9 )
            }
            .maxBy { it.value.size }
            ?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) return false;

    val driver2TripsCost: Map<Driver, Double> = trips
            .groupBy { it.driver }.entries
            .associate { it.key to it.value.sumByDouble { it.cost } }

    val driversByCostList = allDrivers
            .associateBy(
                    { it },
                    { driver2TripsCost.get(it) ?: 0.0 }
            )
            .values
            .sortedByDescending { it }

    val top20DriversMaxIndex = (driversByCostList.size * .2).toInt()
    val top20DriversSum = driversByCostList
            .take(top20DriversMaxIndex)
            .sumByDouble { it }

    return top20DriversSum >= trips.sumByDouble { it.cost } * 0.8
}