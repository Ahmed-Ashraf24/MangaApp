package com.example.mangaapp.presentaion.Screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mangaapp.Utilities.Constants
import com.example.mangaapp.Utilities.UIAdapters.SearchedMangaAdapter
import com.example.mangaapp.databinding.FragmentSearchBinding
import com.example.mangaapp.presentaion.ViewModels.MangaViewModel
import com.google.android.material.chip.Chip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
    lateinit var mangaViewModel:MangaViewModel
    lateinit var binding:FragmentSearchBinding
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
        binding=FragmentSearchBinding.inflate(layoutInflater)
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
        mangaViewModel = ViewModelProvider(this).get(MangaViewModel::class.java)
        mangaViewModel.searchedMangaList.observe(viewLifecycleOwner){searchedMangaList->
            binding.searchResultsRecyclerView.layoutManager=LinearLayoutManager(requireContext())
            binding.searchResultsRecyclerView.adapter=SearchedMangaAdapter(searchedMangaList){
                    manga->
                val intent= Intent(requireContext(),MangaPage::class.java)
                    .apply {
                        putExtra("Manga Id",manga.id)
                        putExtra("Manga Name",manga.name)
                        putExtra("Manga Description",manga.description)
                        putExtra("Manga Image",manga.imageUrl)
                        putExtra("Manga genres",manga.genres)

                    }
                mangaViewModel.addMangaToHistList(manga)

                startActivity(intent)
            }

        }

    }

    fun displayTheSearchedManga(searchedManga:String){
        mangaViewModel.fetchSearchedManga(mangaTitle = searchedManga)
        mangaViewModel.isLoading.observe(viewLifecycleOwner){isLoading->
            if(isLoading){
                binding.loadingSpinner.visibility=View.VISIBLE
            }
            else{
                binding.loadingSpinner.visibility=View.GONE

            }
        }
        mangaViewModel.searchedMangaList.observe(viewLifecycleOwner){searchedMangaList->
            binding.searchResultsRecyclerView.layoutManager=LinearLayoutManager(requireContext())
            binding.searchResultsRecyclerView.adapter=SearchedMangaAdapter(searchedMangaList){
                    manga->
                val intent= Intent(requireContext(),MangaPage::class.java)
                    .apply {
                        putExtra("Manga Id",manga.id)
                        putExtra("Manga Name",manga.name)
                        putExtra("Manga Description",manga.description)
                        putExtra("Manga Image",manga.imageUrl)
                        putExtra("Manga genres",manga.genres)

                    }
                mangaViewModel.addMangaToHistList(manga)

                startActivity(intent)
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