package com.anim.tesdt

import android.R.attr
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.anim.tesdt.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform

import jp.wasabeef.glide.transformations.BlurTransformation
import android.graphics.Bitmap


import android.R.attr.bitmap
import android.animation.Animator
import android.animation.ObjectAnimator

import android.graphics.BlurMaskFilter.Blur
import android.util.Log
import android.view.View
import android.view.animation.Animation
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.coroutines.NonCancellable.start

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMotionListener()


        Glide.with(this).load(R.drawable.bg)
            .apply(bitmapTransform(BlurTransformation(25, 3)))
            .into(binding.imageView)
        var running = false
        binding.itemForClick.setOnLongClickListener {
            //Down-Up Scaling
            if (!running) {
                toStart = false
                binding.mainContainer.animate().scaleX(.9f).scaleY(.9f)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(p0: Animator?) {
                            running = true
                        }

                        override fun onAnimationEnd(p0: Animator?) {
                            binding.mainContainer.animate().scaleX(1.1f).scaleY(1.1f)
                                .setListener(object : Animator.AnimatorListener {
                                    override fun onAnimationStart(p0: Animator?) {
                                        running = true

                                    }

                                    override fun onAnimationEnd(p0: Animator?) {
                                        running = false
                                        binding.motionLayout.transitionToState(R.id.end)
                                    }

                                    override fun onAnimationCancel(p0: Animator?) {
                                    }

                                    override fun onAnimationRepeat(p0: Animator?) {
                                    }

                                })
                        }

                        override fun onAnimationCancel(p0: Animator?) {
                        }

                        override fun onAnimationRepeat(p0: Animator?) {
                        }

                    })
            }
            return@setOnLongClickListener true
        }

    }

    var toStart = false
    private fun setMotionListener() {

        var listner = object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                if (!toStart) {

                    binding.textContainer1.visibility = View.VISIBLE
                    binding.textContainer2.visibility = View.VISIBLE
                    binding.textContainer3.visibility = View.VISIBLE
                    binding.textContainer4.visibility = View.VISIBLE

                    binding.viewShow1.visibility = View.VISIBLE
                    binding.viewShow2.visibility = View.VISIBLE
                    binding.viewShow1.animate().scaleX(0f).scaleY(0f)
                    binding.viewShow1.animate().scaleX(1f).scaleY(1f).setDuration(100).start()

                    binding.viewShow2.animate().scaleX(0f).scaleY(0f)
                    binding.viewShow2.animate().scaleX(1f).scaleY(1f).setDuration(100).start()

                    binding.mainContainer.animate().scaleX(1f).scaleY(1f).startDelay = 300

                    //hide bottom buttons
                    binding.mcv1.animate().alpha(0f).setDuration(450).start()
                    binding.mcv2.animate().alpha(0f).setDuration(450).start()
                    binding.mcv3.animate().alpha(0f).setDuration(450).start()
                    binding.mcv4.animate().alpha(0f).setDuration(450).start()
                }

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(
                motionLayout: MotionLayout?,
                currentId: Int
            ) {
                if (!toStart) {


                    binding.textContainer5.visibility = View.VISIBLE
                    binding.textContainer6.visibility = View.VISIBLE


                }
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        }
        binding.releativeLayout.setOnClickListener {
            toStart = true
            binding.motionLayout.transitionToState(R.id.start)

            binding.mcv1.animate().alpha(1f).setDuration(450).start()
            binding.mcv2.animate().alpha(1f).setDuration(450).start()
            binding.mcv3.animate().alpha(1f).setDuration(450).start()
            binding.mcv4.animate().alpha(1f).setDuration(450).start()
            binding.viewShow1.visibility = View.GONE
            binding.viewShow2.visibility = View.GONE

            binding.textContainer1.visibility = View.GONE
            binding.textContainer2.visibility = View.GONE
            binding.textContainer3.visibility = View.GONE
            binding.textContainer4.visibility = View.GONE
            binding.textContainer5.visibility = View.GONE
            binding.textContainer6.visibility = View.GONE

        }
        binding.motionLayout.setTransitionListener(listner)

    }


}