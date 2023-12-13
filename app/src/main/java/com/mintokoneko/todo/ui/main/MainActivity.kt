package com.mintokoneko.todo.ui.main

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mintokoneko.todo.R
import com.mintokoneko.todo.base.BaseViewModelProvider
import com.mintokoneko.todo.data.User
import com.mintokoneko.todo.databinding.ActivityMainBinding
import com.mintokoneko.todo.repositories.UserRepository
import com.mintokoneko.todo.ui.main.view_model.MainViewModel
import com.mintokoneko.todo.ui.profile.ProfileFragment
import com.mintokoneko.todo.ui.tasks.TasksFragment
import com.mintokoneko.todo.utils.dpToPx

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var mainViewModel: MainViewModel
    private lateinit var profileMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMainViewModel(this, this)
        initObservers(this)
        setupNavigation()
    }

    private fun initObservers(owner: LifecycleOwner) {
        mainViewModel.userDetails.observe(owner) { user ->
            changeProfileMenuItem(user)
        }
    }

    private fun initMainViewModel(owner: ViewModelStoreOwner, context: Context) {
        mainViewModel =
            BaseViewModelProvider.getInstance().getViewModel(owner, UserRepository(context))
    }

    private fun setupNavigation() {
        bottomNavigation = binding.bottomNavigation
        profileMenuItem = bottomNavigation.menu.findItem(R.id.profile_menu_item)

        val fragmentContainerId = binding.mainFragmentContainer.id
        val fragmentManager = supportFragmentManager

        setupBottomNavigation(fragmentManager, fragmentContainerId)
        setupBottomNavigationBehavior()
    }

    private fun setupBottomNavigationBehavior() {
        bottomNavigation.itemIconTintList = null
    }

    private fun setupBottomNavigation(fragmentManager: FragmentManager, fragmentContainerId: Int) {
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.todo_menu_item -> {
                    fragmentManager
                        .beginTransaction()
                        .replace(fragmentContainerId, TasksFragment.newInstance())
                        .commit()
                    true
                }

                R.id.profile_menu_item -> {
                    fragmentManager
                        .beginTransaction()
                        .replace(fragmentContainerId, ProfileFragment.newInstance())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }

    private fun changeProfileMenuItem(user: User) {
        changeProfileMenuItemIcon(user.photoUri)
        changeProfileMenuItemTitle(user.name)
    }

    private fun changeProfileMenuItemTitle(name: String) {
        profileMenuItem.title = name
    }

    private fun changeProfileMenuItemIcon(photoUri: Uri?) {
        Glide.with(this)
            .asBitmap()
            .load(photoUri)
            .placeholder(R.drawable.ic_user)
            .centerCrop()
            .apply(
                RequestOptions.circleCropTransform()
            )
            .into(object :
                CustomTarget<Bitmap>(dpToPx(24), dpToPx(24)) {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    profileMenuItem.icon = BitmapDrawable(
                        resources, resource
                    )
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }
}
