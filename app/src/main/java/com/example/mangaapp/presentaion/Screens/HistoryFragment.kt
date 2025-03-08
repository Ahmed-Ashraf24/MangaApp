package com.example.mangaapp.presentaion.Screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.R
import com.example.mangaapp.Utilities.UIAdapters.HistoryAdapter
import com.example.mangaapp.databinding.FragmentHistoryBinding
import com.example.mangaapp.presentaion.ViewModels.MangaViewModel
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding:FragmentHistoryBinding
    val mangaViewModel=MangaViewModel()
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
        // Inflate the layout for this fragment
        binding=FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mangaDumpData=ArrayList<Manga>()
        val user=(activity as? MainScreen)!!.user
        lifecycleScope.launch {
            Log.d("user after loging in in history ",user.toString())

            user!!.histManga.forEach {
                mangaViewModel.getMangaFromIdForHistory(it)

            }

            mangaViewModel.isLoading.observe(viewLifecycleOwner){isLoading->
                if(isLoading){
                }
                else{
                }

            }
            mangaViewModel.histMangaList.observe(viewLifecycleOwner){historyList->

                Log.d("History manga list siza from fragment ",historyList.size.toString())

                val adapter = HistoryAdapter(historyList) {manga->
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
        binding.historyRecycelrview.layoutManager=LinearLayoutManager(requireContext())
        binding.historyRecycelrview.adapter=adapter
                binding.historyRecycelrview.adapter!!.notifyDataSetChanged()

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
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}