package com.example.mangaapp.presentaion.Screens.MainScreen.History

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mangaapp.Domain.Entity.Manga
import com.example.mangaapp.R
import com.example.mangaapp.Utilities.UIAdapters.HistoryAdapter
import com.example.mangaapp.databinding.FragmentHistoryBinding
import com.example.mangaapp.presentaion.Screens.mangaPage.MangaPageFragment
import com.example.mangaapp.presentaion.Screens.MainScreen.MainScreenActivity
import com.example.mangaapp.presentaion.ViewModels.Manga.MangaViewModel
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

    lateinit var binding: FragmentHistoryBinding
    private lateinit var mangaViewModel: MangaViewModel
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
        binding= FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mangaViewModel = (activity as? MainScreenActivity)!!.mangaViewModel
        val mangaDumpData=ArrayList<Manga>()
        val user=(activity as? MainScreenActivity)!!.user
        val activityViews=(activity as? MainScreenActivity)!!.binding
        lifecycleScope.launch {
            Log.d("user after loging in in history ", user.toString())

            user!!.histManga.forEach {
                mangaViewModel.getMangaFromIdForHistory(it)
                mangaViewModel.isLoading.observe(viewLifecycleOwner){isLoading->
                    if(isLoading){
                        for (i in 0 until activityViews.bottomNavigationView.menu.size()) {
                            activityViews.bottomNavigationView.menu.getItem(i).isEnabled = false
                        }
                        binding.loadingSpinner.visibility= View.VISIBLE
                    }
                    else{
                        for (i in 0 until activityViews.bottomNavigationView.menu.size()) {
                            activityViews.bottomNavigationView.menu.getItem(i).isEnabled = true
                        }
                        binding.loadingSpinner.visibility= View.GONE

                    }
                }

            }

            mangaViewModel.histMangaList.observe(viewLifecycleOwner){historyList->

                Log.d("History manga list siza from fragment ", historyList.size.toString())

                val adapter = HistoryAdapter(historyList) { manga ->
                    (activity as? MainScreenActivity)!!.selectedManga = manga
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MangaPageFragment())
                        .addToBackStack(null)
                        .commit()
                }
        binding.historyRecycelrview.layoutManager= LinearLayoutManager(requireContext())
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