package com.example.mangaapp.presentaion.Screens.MainScreen.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mangaapp.R
import com.example.mangaapp.Utilities.Constants
import com.example.mangaapp.Utilities.UIAdapters.SearchedMangaAdapter
import com.example.mangaapp.databinding.FragmentSearchBinding
import com.example.mangaapp.presentaion.Screens.mangaPage.MangaPageFragment
import com.example.mangaapp.presentaion.Screens.MainScreen.MainScreenActivity
import com.example.mangaapp.presentaion.ViewModels.MangaAndChaptersViewModel.MangaViewModel
import com.google.android.material.chip.Chip
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mangaViewModel: MangaViewModel
    lateinit var binding: FragmentSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onDestroy() {
       val mainFragment= parentFragmentManager.findFragmentById(R.id.fragment_container) as MainFragment
        mainFragment.binding.editTextSearch.text.clear()
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        for ((genreName, genreUUID) in Constants.GENRES) {
            val chip = Chip(requireContext()).apply{
                text = genreName
                isClickable = true
                isCheckable=true
                setOnClickListener {
                    Log.d("manga genre", genreName)

                    mangaViewModel.fetchMangaByItsGenre(genreUUID)

                }
            }
            binding.chipGroupGenres.addView(chip)
        }
        mangaViewModel = (activity as? MainScreenActivity)!!.mangaViewModel
        mangaViewModel.searchedMangaList.observe(viewLifecycleOwner){searchedMangaList->
            binding.searchResultsRecyclerView.layoutManager= LinearLayoutManager(requireContext())
            binding.searchResultsRecyclerView.adapter=
                SearchedMangaAdapter(searchedMangaList) { manga ->
                    (activity as? MainScreenActivity)!!.selectedManga = manga
                    mangaViewModel.addMangaToHistList(manga)

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MangaPageFragment())
                        .addToBackStack(null)
                        .commit()
                }

        }

    }

    fun displayTheSearchedManga(searchedManga:String){
        mangaViewModel.fetchSearchedManga(mangaTitle = searchedManga)
        mangaViewModel.isLoading.observe(viewLifecycleOwner){isLoading->
            if(isLoading){
                binding.loadingSpinner.visibility= View.VISIBLE
            }
            else{
                binding.loadingSpinner.visibility= View.GONE

            }
        }
        mangaViewModel.searchedMangaList.observe(viewLifecycleOwner){searchedMangaList->
            binding.searchResultsRecyclerView.layoutManager= LinearLayoutManager(requireContext())
            binding.searchResultsRecyclerView.adapter=
                SearchedMangaAdapter(searchedMangaList) { manga ->
                    (activity as? MainScreenActivity)!!.selectedManga = manga
                    mangaViewModel.addMangaToHistList(manga)

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MangaPageFragment())
                        .addToBackStack(null)
                        .commit()
                    mangaViewModel.addMangaToHistList(manga)

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
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}