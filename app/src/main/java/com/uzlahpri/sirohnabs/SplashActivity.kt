package com.uzlahpri.sirohnabs

import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.uzlahpri.sirohnabs.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        val animSiroh = AnimationUtils.loadAnimation(this, R.anim.splash_siroh)
        val animNubs = AnimationUtils.loadAnimation(this, R.anim.splash_nabs)
        binding.tvSirohSplash.animation = animSiroh
        binding.tvNabsSplash.animation = animNubs

        object : CountDownTimer(3600, 200) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                startActivity(MainActivity.getLaunchService(this@SplashActivity))
            }

        }.start()

        super.onResume()
    }
}