package com.example.mangaapp.presentaion.Screens.MainScreen.Setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mangaapp.R
import com.example.mangaapp.databinding.FragmentSettingsBinding
import com.example.mangaapp.presentaion.Screens.Auth.MainActivity
import com.example.mangaapp.presentaion.Screens.MainScreen.MainScreenActivity
import com.example.mangaapp.presentaion.Screens.MainScreen.Setting.Account.AccountFragment
import com.example.mangaapp.presentaion.Screens.MainScreen.Setting.Profile.ProfileFragment
import com.example.mangaapp.presentaion.ViewModels.Auth.LogoutViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentSettingsBinding
    val logoutViewModel= LogoutViewModel()

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

        binding= FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {

        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user=(activity as? MainScreenActivity)!!.user
        binding.settingsEmail.text=user.email
        binding.settingsUsername.text=user.name
        binding.logout.setOnClickListener {
            logoutViewModel.logout()
            val intent= Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        binding.accountSection.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,AccountFragment())
                .commit()
        }
        binding.profileSection.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,ProfileFragment())
                .commit()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}