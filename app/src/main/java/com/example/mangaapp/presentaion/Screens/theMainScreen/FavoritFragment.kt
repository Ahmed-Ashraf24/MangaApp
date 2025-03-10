package com.example.mangaapp.presentaion.Screens.theMainScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mangaapp.Utilities.UIAdapters.HistoryAdapter
import com.example.mangaapp.databinding.FragmentFavoritBinding
import com.example.mangaapp.presentaion.Screens.mangaPage.MangaPage
import com.example.mangaapp.presentaion.ViewModels.Auth.LogInViewModel
import com.example.mangaapp.presentaion.ViewModels.MangaAndChaptersViewModel.MangaViewModel
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentFavoritBinding
    val logInViewModel=LogInViewModel()
    val mangaViewModel= MangaViewModel()

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

        binding=FragmentFavoritBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user=(activity as? MainScreenActivity)!!.user

            lifecycleScope.launch {
                Log.d("user after loging in ",user.toString())

                user!!.favManga.forEach {
                    Log.d("favmanga after loging in ",it)
                      mangaViewModel.getMangaFromId(it)
                        Log.d("favmanga after loging in in async ",it)

                    mangaViewModel.isLoading.observe(viewLifecycleOwner){isLoading->
                        val activityViews=(activity as? MainScreenActivity)!!.binding

                        if(isLoading){
                            binding.loadingSpinner.visibility=View.VISIBLE

                            activityViews.editTextSearch.isEnabled = false
                            activityViews.editTextSearch.isFocusable = false
                            activityViews.editTextSearch.isFocusableInTouchMode = false
                            for (i in 0 until activityViews.bottomNavigationView.menu.size()) {
                                activityViews.bottomNavigationView.menu.getItem(i).isEnabled = false
                        }
                        }
                        else{
                            binding.loadingSpinner.visibility=View.GONE

                            activityViews.editTextSearch.isEnabled = true
                            activityViews.editTextSearch.isFocusable = true
                            activityViews.editTextSearch.isFocusableInTouchMode = true
                            for (i in 0 until activityViews.bottomNavigationView.menu.size()) {
                                activityViews.bottomNavigationView.menu.getItem(i).isEnabled = true
                            }
                        }

                    }
                }

                mangaViewModel.favMangaList.observe(viewLifecycleOwner){favMangaList->

                    Log.d("Fav manga list siza from fragment ",favMangaList.size.toString())

                    val adapter = HistoryAdapter(favMangaList) {manga->
                        val intent = Intent(requireContext(), MangaPage::class.java)
                            .apply {
                                putExtra("Manga Id", manga.id)
                                putExtra("Manga Name", manga.name)
                                putExtra("Manga Description", manga.description)
                                putExtra("Manga Image", manga.imageUrl)
                                putExtra("Manga genres", manga.genres)
                            }
                        startActivity(intent)
                    }
                    binding.favoriteRecyclerview.layoutManager = LinearLayoutManager(requireContext())
                    binding.favoriteRecyclerview.adapter = adapter
                    binding.favoriteRecyclerview.adapter!!.notifyDataSetChanged()
                }
            }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}