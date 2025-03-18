package com.example.mangaapp.presentaion.Screens.MainScreen.Home

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mangaapp.R
import com.example.mangaapp.Utilities.UIAdapters.MangaRecyclerAdapter
import com.example.mangaapp.databinding.FragmentMainBinding
import com.example.mangaapp.presentaion.Screens.mangaPage.MangaPageFragment
import com.example.mangaapp.presentaion.Screens.MainScreen.MainScreenActivity
import com.example.mangaapp.presentaion.ViewModels.Manga.MangaViewModel
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
        binding= FragmentMainBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mangaViewModel = (activity as? MainScreenActivity)!!.mangaViewModel
        binding.backButton.setOnClickListener {
            binding.editTextSearch.clearFocus()
            binding.backButton.visibility = View.GONE
            parentFragmentManager.popBackStack("SearchFragment",
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )

        }
        binding.editTextSearch.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.backButton.visibility = View.VISIBLE
                binding.editTextSearch.apply {
                    imeOptions = EditorInfo.IME_ACTION_SEARCH
                    inputType = InputType.TYPE_CLASS_TEXT
                }
                val existingSearchFragment = parentFragmentManager.findFragmentById(R.id.content_fragment_container)

                parentFragmentManager.beginTransaction()
                    .replace(R.id.content_fragment_container, SearchFragment())
                    .addToBackStack("SearchFragment")
                    .commit()

            } else {
                Log.d("focus is disabled", hasFocus.toString())


            }

        }
        binding.editTextSearch.setOnEditorActionListener { v, actionId, event ->

            val searchText = binding.editTextSearch.text.toString().trim()
            val searchFragment = parentFragmentManager.findFragmentById(R.id.content_fragment_container) as? SearchFragment
            if (searchText.isNotEmpty()) {
                searchFragment!!.displayTheSearchedManga(searchText)
            }
            val inputMethodManger = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManger.hideSoftInputFromWindow(v.windowToken, 0)


        }

        mangaViewModel.latestMangaList.observe(viewLifecycleOwner){ latestMangaList ->
            binding.latestMangaRecyclerview.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL, false
            )
            binding.latestMangaRecyclerview.adapter =
                MangaRecyclerAdapter(latestMangaList) { manga ->
                    (activity as MainScreenActivity).selectedManga = manga

                    mangaViewModel.addMangaToHistList(manga)

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MangaPageFragment())
                        .addToBackStack(null)
                        .commit()
                }

        }
        mangaViewModel.recommendedMangaList.observe(viewLifecycleOwner){ recommendedMangaList->
            binding.recommendationRecyclerview.layoutManager=
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.recommendationRecyclerview.adapter=
                MangaRecyclerAdapter(recommendedMangaList) { manga ->
                    (activity as MainScreenActivity).selectedManga = manga

                    mangaViewModel.addMangaToHistList(manga)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MangaPageFragment())
                        .addToBackStack(null)
                        .commit()
                }


        }
        mangaViewModel.popularMangaList.observe(viewLifecycleOwner){popularMangaList->
            binding.populerRecyclerview.layoutManager=
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.populerRecyclerview.adapter= MangaRecyclerAdapter(popularMangaList) { manga ->
                (activity as MainScreenActivity).selectedManga = manga
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MangaPageFragment())
                    .addToBackStack(null)
                    .commit()

            }
        }
        mangaViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
           val activityViews= (activity as? MainScreenActivity)!!.binding
            if (isLoading) {

                binding.shimmerLayout.visibility= View.VISIBLE
                binding.mainContent.visibility= View.GONE
                binding.editTextSearch.isEnabled = false
                binding.editTextSearch.isFocusable = false
                binding.editTextSearch.isFocusableInTouchMode = false
                for (i in 0 until activityViews.bottomNavigationView.menu.size()) {
                    activityViews.bottomNavigationView.menu.getItem(i).isEnabled = false
                }

                binding.shimmerLayout.startShimmer()
            }
            else{
                binding.editTextSearch.isEnabled = true
                binding.editTextSearch.isFocusable = true
                binding.editTextSearch.isFocusableInTouchMode = true
                for (i in 0 until activityViews.bottomNavigationView.menu.size()) {
                    activityViews.bottomNavigationView.menu.getItem(i).isEnabled = true
                }
                binding.searchFragmentContainer.visibility=view.visibility
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility= View.GONE
                binding.mainContent.visibility= View.VISIBLE
            }
        }

        mangaViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            println("Error: $errorMessage")
        }

        mangaViewModel.fetchMangaList()



    }

    fun clearSearchUI() {

            binding.editTextSearch.text.clear()
            binding.backButton.visibility = View.GONE
            binding.editTextSearch.clearFocus()

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