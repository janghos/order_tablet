package com.example.order_tablet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.order_tablet.adapter.RvListAdapter
import com.example.order_tablet.databinding.ActivityMainBinding
import com.example.order_tablet.databinding.FragmentMainMenuBinding
import com.example.order_tablet.model.RvListContent
import com.example.order_tablet.util.LoadingDialog

class MainMenuFragment : Fragment() {

    companion object {
        fun newInstance() = MainMenuFragment()
    }
    private lateinit var viewModel: MainMenuViewModel
    private lateinit var binding: FragmentMainMenuBinding
    private lateinit var rvListAdapter : RvListAdapter
    private var loadingDialog = LoadingDialog.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        val view = binding.root

        // Access views using binding object, e.g., binding.textView.text = "Hello, View Binding!"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainMenuViewModel::class.java)

        if(!loadingDialog.isAdded) {
            loadingDialog.show(parentFragmentManager, tag)
        }
        viewModel.requestImageListInfoData(5,20)

        viewModel.getImageListInfoData().observe(viewLifecycleOwner) {
            it?.let {
                val items = mutableListOf<RvListContent>()

                for(i in it) {
                    if (i.download_url != null) {
                        items.add(
                            RvListContent(
                                text = i.id,
                                imageUrl = i.download_url
                            )
                        )
                    }
                }
                rvListAdapter = RvListAdapter(items, requireContext())
                binding.rvList.adapter = rvListAdapter
                binding.rvList.layoutManager = LinearLayoutManager(requireContext())

                loadingDialog.dialog?.dismiss()
            }
        }
    }
}