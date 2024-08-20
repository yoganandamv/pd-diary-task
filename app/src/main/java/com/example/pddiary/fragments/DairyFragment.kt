package com.example.pddiary.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pddiary.adapter.DairyAdapter
import com.example.pddiary.databinding.DairyFragmentBinding
import com.example.pddiary.models.DairyButtonModel
import com.example.pddiary.models.DairyModel
import com.example.pddiary.models.HeaderModel

class DairyFragment : Fragment() {

    private lateinit var dairyBinding: DairyFragmentBinding
    private val binding: DairyFragmentBinding get() = dairyBinding

    private lateinit var viewModel: DairyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dairyBinding = DairyFragmentBinding.inflate(inflater, container, false)
        return dairyBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DairyViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val list = viewModel.getDairyList()
        Log.v("fragmentResumed", list.size.toString() )
        binding.dairyRecyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = DairyAdapter(list)
        binding.dairyRecyclerview.adapter = adapter
    }

    override fun onPause() {
        val adapter = binding.dairyRecyclerview.adapter as DairyAdapter
        val listToSave = adapter.getCurrentList()
         Log.v("fragmentPaused", listToSave.toString() )
        context?.filesDir?.absoluteFile?.let { viewModel.saveDairyList(it) }
        super.onPause()
    }

}