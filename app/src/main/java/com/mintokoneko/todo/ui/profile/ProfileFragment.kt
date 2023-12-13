package com.mintokoneko.todo.ui.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mintokoneko.todo.R
import com.mintokoneko.todo.base.BaseViewModelProvider
import com.mintokoneko.todo.data.User
import com.mintokoneko.todo.databinding.FragmentProfileBinding
import com.mintokoneko.todo.repositories.UserRepository
import com.mintokoneko.todo.ui.profile.view_model.ProfileViewModel
import com.mintokoneko.todo.utils.hideKeyboard

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel
    private var photoUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        val view = requireView()

        initProfileViewModel(this, context)
        setContent(getUserDetails())
        setOnClickListeners(context, view)
    }

    private fun setContent(user: User) {
        photoUri = user.photoUri
        setUsername(user.name)
        setUserProfilePhoto(user.photoUri)
    }

    private fun setUsername(name: String) {
        binding.usernameField.editText?.setText(name)
    }

    private fun getUserDetails(): User = profileViewModel.getUserDetails()

    private fun setUserProfilePhoto(photoUri: Uri?) {
        Glide.with(this)
            .load(photoUri)
            .placeholder(R.drawable.ic_user)
            .centerCrop()
            .apply(
                RequestOptions.circleCropTransform()
            )
            .into(binding.userProfilePhoto)
    }

    private fun setOnClickListeners(context: Context, view: View) {
        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                photoUri = uri
                setUserProfilePhoto(photoUri)
            }

        binding.apply {
            saveUserDetails.setOnClickListener {
                val username = usernameField.editText?.text.toString()
                if (photoUri != null && username.isNotEmpty()) {
                    profileViewModel.setUserDetails(User(username, photoUri))
                    clearFocus(view, context, usernameField)
                } else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
            userProfilePhoto.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }

    private fun clearFocus(view: View, context: Context, viewToClearFocus: View) {
        viewToClearFocus.clearFocus()
        hideKeyboard(view, context)
    }

    private fun initProfileViewModel(fragment: Fragment, context: Context) {
        profileViewModel =
            BaseViewModelProvider.getInstance().getViewModel(fragment, UserRepository(context))
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}