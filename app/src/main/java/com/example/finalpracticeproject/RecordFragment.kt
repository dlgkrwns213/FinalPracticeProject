package com.example.finalpracticeproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalpracticeproject.databinding.FragmentMainBinding
import com.example.finalpracticeproject.databinding.FragmentRecordBinding

/**
 * A simple [Fragment] subclass.
 * Use the [RecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecordFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDeposit.setOnClickListener {
            val money = binding.moneyInput.text.toString().toIntOrNull() ?: 0
            val memo = binding.memoInput.text.toString() ?: ""
            viewModel.setMoney(money)
            viewModel.setMemo(memo)
            viewModel.record(1)
            findNavController().navigate(R.id.action_recordFragment_to_mainFragment)
        }

        binding.btnWithdraw.setOnClickListener {
            val money = binding.moneyInput.text.toString().toIntOrNull() ?: 0
            val memo = binding.memoInput.text.toString() ?: ""
            viewModel.setMoney(money)
            viewModel.setMemo(memo)
            viewModel.record(-1)
            findNavController().navigate(R.id.action_recordFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}