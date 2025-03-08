package com.example.mangaapp.presentaion.Screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mangaapp.R
import com.example.mangaapp.Utilities.Constants
import com.example.mangaapp.Utilities.UIAdapters.MangaRecyclerAdapter
import com.example.mangaapp.databinding.FragmentMainBinding
import com.example.mangaapp.presentaion.ViewModels.MangaViewModel
import com.google.android.material.chip.Chip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentMainBinding
    val mangaViewModel = MangaViewModel()


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
        binding=FragmentMainBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        mangaViewModel.latestMangaList.observe(viewLifecycleOwner){ latestMangaList ->
            binding.latestMangaRecyclerview.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            binding.latestMangaRecyclerview.adapter = MangaRecyclerAdapter(latestMangaList) { manga ->
                val intent = Intent(requireContext(), MangaPage::class.java)
                    .apply {
                        putExtra("Manga Id", manga.id)
                        putExtra("Manga Name", manga.name)
                        putExtra("Manga Description", manga.description)
                        putExtra("Manga Image", manga.imageUrl)
                        putExtra("Manga genres", manga.genres)

                    }
                mangaViewModel.addMangaToHistList(manga)
                startActivity(intent)
            }

        }
        mangaViewModel.recommendedMangaList.observe(viewLifecycleOwner){ recommendedMangaList->
            binding.recommendationRecyclerview.layoutManager=
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            binding.recommendationRecyclerview.adapter=
                MangaRecyclerAdapter(recommendedMangaList){ manga->
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
        mangaViewModel.popularMangaList.observe(viewLifecycleOwner){popularMangaList->
            binding.populerRecyclerview.layoutManager=
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            binding.populerRecyclerview.adapter= MangaRecyclerAdapter(popularMangaList){ manga->
                val intent= Intent(requireContext(),MangaPage::class.java)
                    .apply {
                        putExtra("Manga Id",manga.id)
                        putExtra("Manga Name",manga.name)
                        putExtra("Manga Description",manga.description)
                        putExtra("Manga Image",manga.imageUrl)
                        putExtra("Manga genres",manga.genres)
                        mangaViewModel.addMangaToHistList(manga)

                    }
                startActivity(intent)
            }
        }
        mangaViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.shimmerLayout.visibility=View.VISIBLE
                binding.mainContent.visibility=View.GONE
                binding.shimmerLayout.startShimmer()
            }
            else{
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility=View.GONE
                binding.mainContent.visibility=View.VISIBLE
            }
        }

        mangaViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            println("Error: $errorMessage")
        }

        mangaViewModel.fetchMangaList()



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}