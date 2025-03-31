package com.example.mangaapp.presentaion.Screens.MainScreen.Setting.Account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mangaapp.R
import com.example.mangaapp.Utilities.UIAdapters.UpdateAccountDialog
import com.example.mangaapp.databinding.FragmentAccountBinding
import com.example.mangaapp.presentaion.Screens.Auth.MainActivity
import com.example.mangaapp.presentaion.Screens.MainScreen.MainScreenActivity
import com.example.mangaapp.presentaion.Screens.MainScreen.Setting.SettingsFragment
import com.example.mangaapp.presentaion.ViewModels.Auth.ResetUserViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentAccountBinding
    val resetUserViewModel=ResetUserViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user=(activity as? MainScreenActivity)!!.user
        binding.email.text=user.email
        binding.backButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,SettingsFragment())
                .commit()
        }
        binding.emailSection.setOnClickListener {
            showUpdateDialog(UpdateAccountDialog.FieldType.EMAIL)
        }
        binding.passwordSection.setOnClickListener {
            showUpdateDialog(UpdateAccountDialog.FieldType.PASSWORD)
        }

    }
    private fun showUpdateDialog(fieldType: UpdateAccountDialog.FieldType) {
        val dialog = UpdateAccountDialog(requireContext(), fieldType) { newValue ->
            when (fieldType) {
                UpdateAccountDialog.FieldType.EMAIL -> {
                resetUserViewModel.resetUserEmail(email = newValue)
                    resetUserViewModel.emailResetStatus.value?.let {
                        result ->
                        result.fold(
                            onSuccess = {
                                Toast.makeText(requireContext(),"check your email and verify it to complete the update",Toast.LENGTH_LONG).show()
                            }
                                , onFailure = {exception ->
                                Toast.makeText(requireContext(),exception.message,Toast.LENGTH_LONG).show()


                            })
                    }
                }
                UpdateAccountDialog.FieldType.PASSWORD -> {
                    resetUserViewModel.resetUserPassword(password = newValue)
                    resetUserViewModel.passwordResetStatus.value?.let {
                            result ->
                        result.fold(
                            onSuccess = {
                                Toast.makeText(requireContext(),"the password updated successfully",Toast.LENGTH_LONG).show()
                            }
                            , onFailure = {exception ->
                                Toast.makeText(requireContext(),exception.message,Toast.LENGTH_LONG).show()


                            })
                    }
                }
            }
        }
        dialog.show()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}