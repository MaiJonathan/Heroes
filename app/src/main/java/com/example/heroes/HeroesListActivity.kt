package com.example.heroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heroes.databinding.ActivityHeroesListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HeroesListActivity : AppCompatActivity() {
    companion object {
        val TAG = "HeroesListActivity"
    }
    private lateinit var binding: ActivityHeroesListBinding
    lateinit var adapter: HeroAdapter
    private lateinit var heroes : List<Hero>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputStream = resources.openRawResource(R.raw.heroes)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        Log.d(TAG, "onCreate: $jsonString")

        val gson = Gson()

        val type = object : TypeToken<List<Hero>>() { }.type
        heroes = gson.fromJson<List<Hero>>(jsonString, type)

        heroes = heroes.sorted()
//        heroes = heroes.sortedBy{
//            //lets you choose the instant variable that you want to use to sort the list with
//            it.superpower.length
//            //sorts by the length of the superpower field shortest to longests
//        }

        Log.d(TAG, "onCreate: $heroes")

        adapter = HeroAdapter(heroes)

        binding.reyclerViewHeroesList.adapter = adapter

        binding.reyclerViewHeroesList.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.hero_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.item_heroListMenu_sortByRank -> {
                heroes = heroes.sorted()
                System.out.println("pepe")
                adapter = HeroAdapter(heroes)
                binding.reyclerViewHeroesList.adapter = adapter
                binding.reyclerViewHeroesList.layoutManager = LinearLayoutManager(this)
                true
            }
            R.id.item_heroListMenu_sortByName -> {
                heroes = heroes.sortedBy {
                    it.name

                }
                adapter = HeroAdapter(heroes)
                binding.reyclerViewHeroesList.adapter = adapter
                binding.reyclerViewHeroesList.layoutManager = LinearLayoutManager(this)
                true
            }
            R.id.item_heroListMenu_sortByDescription -> {
                heroes = heroes.sortedBy {
                    it.description

                }
                adapter = HeroAdapter(heroes)
                binding.reyclerViewHeroesList.adapter = adapter
                binding.reyclerViewHeroesList.layoutManager = LinearLayoutManager(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}