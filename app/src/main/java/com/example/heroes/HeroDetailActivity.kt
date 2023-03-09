package com.example.heroes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heroes.HeroDetailActivity.Companion.EXTRA_HERO

import com.example.heroes.databinding.ActivityHeroDetailBinding

class HeroDetailActivity : AppCompatActivity() {
    companion object{
        val EXTRA_HERO = "Hero"
    }

    private lateinit var binding: ActivityHeroDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero = intent?.getParcelableExtra<Hero>(EXTRA_HERO)
        val heroName = hero?.image
        binding.textViewHeroDetailName.text = hero?.name
        binding.textViewHeroDetailDescription.text = hero?.description
        binding.textViewHeroDetailSuperpower.text = hero?.superpower
        binding.textViewHeroDetailRanking.text = hero?.ranking.toString()
        val heroDrawable = getDrawable(resources.getIdentifier(heroName, "drawable", packageName))
        binding.imageViewHeroDetailImage.setImageDrawable(heroDrawable)
        }
}