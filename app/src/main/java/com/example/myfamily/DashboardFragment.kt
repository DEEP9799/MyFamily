package com.example.myfamily

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfamily.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

     lateinit var binding:FragmentDashboardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        binding.btn.setOnClickListener{

        }
        return binding.root

    }

    companion object {

        @JvmStatic
        fun newInstance() = DashboardFragment()
    }
}