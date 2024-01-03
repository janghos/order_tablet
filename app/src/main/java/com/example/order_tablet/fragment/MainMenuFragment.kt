package com.example.order_tablet.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.order_tablet.adapter.RvListAdapter
import com.example.order_tablet.databinding.FragmentMainMenuBinding
import com.example.order_tablet.model.RvListContent
import com.example.order_tablet.util.LoadingDialog
import com.example.order_tablet.viewModel.MainMenuViewModel
import java.util.concurrent.Executors


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
                val items2 = mutableListOf<RvListContent>()

                for(i in it) {
                    if(it.indexOf(i) % 2 == 0) {
                        if (i.download_url != null) {
                            items.add(
                                RvListContent(
                                    text = i.id,
                                    imageUrl = i.download_url,
                                    price = i.width.toString()
                                )
                            )
                        }
                    } else {
                        if (i.download_url != null) {
                            items2.add(
                                RvListContent(
                                    text = i.id,
                                    imageUrl = i.download_url,
                                    price = i.width.toString()
                                )
                            )
                        }
                    }

                }

                initializeRecyclerView(items)
                initializeRecyclerView2(items2)

                Handler(Looper.getMainLooper()).postDelayed({
                    loadingDialog.dialog?.dismiss()
                },500)

            }
        }
    }


    private fun initializeRecyclerView(items: MutableList<RvListContent>) {
        rvListAdapter = RvListAdapter(items, requireContext())
        binding.rvList.adapter = rvListAdapter
        binding.rvList.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL,
            false
        )
    }

    private fun initializeRecyclerView2(items: MutableList<RvListContent>) {
        rvListAdapter = RvListAdapter(items, requireContext())
        binding.rvList2.adapter = rvListAdapter
        binding.rvList2.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL,
            false
        )
    }
}