package com.example.order_tablet.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.order_tablet.R
import com.example.order_tablet.databinding.DialogProgressBinding
class LoadingDialog : DialogFragment() {

    private var mBinding : DialogProgressBinding? = null
    private val binding get() = mBinding

    interface OnRefreshClickListener {
        fun onRefresh()
    }

    private lateinit var buttonClickListener : OnRefreshClickListener

    companion object {
        fun newInstance(): LoadingDialog {
            return LoadingDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.LoadingDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DialogProgressBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return mBinding?.root
    }

    fun setOnRefreshButtonClickListener(listener: OnRefreshClickListener){
        buttonClickListener = listener
    }
}