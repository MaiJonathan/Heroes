package com.example.heroes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//only need this annotation and the describeContents
//and write functions are implemented for us
@Parcelize
data class Hero(
    val name: String,
    val description: String,
    val superpower: String,
    val ranking: Int,
    val image: String
) : Parcelable, Comparable<Hero> {
    override fun compareTo(other: Hero): Int {
        //decide what the natural ordering for a Hero should be
        //negative value if current object comes before the other
        //0 if the two are the same
        //positive value if current object comes after the other

        //ranking
        return this.ranking - other.ranking

        //alphabetical
        //return this.name.compareTo(other.name)
    }
}
