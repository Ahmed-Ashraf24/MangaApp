package com.example.mangaapp.presentaion.Screens.mangaPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mangaapp.Utilities.UIAdapters.ChapterAdapter
import com.example.mangaapp.databinding.FragmentMangaPageBinding
import com.example.mangaapp.presentaion.Screens.ChapterPage.PanelActivity
import com.example.mangaapp.presentaion.Screens.MainScreen.MainScreenActivity
import com.example.mangaapp.presentaion.ViewModels.Chapter.ChapterViewModel
import com.example.mangaapp.presentaion.ViewModels.Manga.MangaViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MangaPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MangaPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentMangaPageBinding
    val chapterViewModel= ChapterViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as? MainScreenActivity)!!.binding.bottomNavigationView.visibility=View.VISIBLE
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMangaPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activityInstance=(activity as? MainScreenActivity)
        val mangaViewModel=activityInstance!!.mangaViewModel
        activityInstance!!.binding.bottomNavigationView.visibility=View.GONE
        val selectedManga=activityInstance!!.selectedManga
        if(activityInstance.mangaViewModel.favMangaList.value!!.contains(selectedManga)){
            binding.buttonFavorite.isEnabled=false
        }
        binding.buttonFavorite.setOnClickListener {

            mangaViewModel.addMangaToFavList(
            selectedManga!!
            )
            binding.buttonFavorite.isEnabled=false
        }
        Glide.with(requireContext()).load(selectedManga!!.imageUrl)
            .into(binding.mangaCover)

        binding.pageTitle.text=selectedManga.name
        binding.mangaGenres.text=selectedManga.genres
        binding.mangaDescription.text=selectedManga.description
        binding.chapterList.layoutManager= LinearLayoutManager(requireContext())
        chapterViewModel.fetchMangaChapters(selectedManga.id!!)
        chapterViewModel.isLoading.observe(viewLifecycleOwner){ isLoading->
            if (isLoading) {
                binding.blurOverlay.visibility= View.VISIBLE
                binding.progressSpinner.visibility= View.VISIBLE

            }
            else{

                binding.blurOverlay.visibility= View.GONE
                binding.progressSpinner.visibility= View.GONE
            }
        }
        binding.continueButton.setOnClickListener {
            chapterViewModel.fetchMoreMangaChapters(selectedManga.id)
        }

        chapterViewModel.chapterList.observe(viewLifecycleOwner){ chapterList->
            Log.d("Chapter data",chapterList.toString())
            binding.chapterList.layoutManager= GridLayoutManager(requireContext(), 4)
            binding.chapterList.adapter= ChapterAdapter(chapterList){ chapter ->
                val intent= Intent(requireContext(), PanelActivity::class.java)
                    .apply {
                        putExtra("Chapter Name",chapter.chapterName)
                        putExtra("Chapter Id" ,chapter.chapterId)
                    }
                startActivity(intent)

            }
            binding.chapterList.adapter!!.notifyDataSetChanged()


        }
        chapterViewModel.isChaptersCompleted.observe(viewLifecycleOwner){ isChaptersCompleted->
            if(isChaptersCompleted){
                binding.continueButton.isEnabled=false
                binding.continueButton.visibility=View.GONE
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
         * @return A new instance of fragment MangaPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MangaPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}