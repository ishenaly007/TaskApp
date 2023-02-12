package com.abit.taskapp.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abit.taskapp.App
import com.abit.taskapp.App.Companion.db
import com.abit.taskapp.R
import com.abit.taskapp.databinding.FragmentHomeBinding
import com.abit.taskapp.model.Task
import com.abit.taskapp.ui.task.adapter.TaskAdapter
import com.abit.taskapp.utils.isOnline
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var adapter: TaskAdapter
    private var _binding: FragmentHomeBinding? = null
    private lateinit var dialog: AlertDialog.Builder
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private var uid: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = TaskAdapter(this::onClick, this::onClickUpdate)
        dialog = AlertDialog.Builder(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireContext().isOnline()) {
            getTasks()
        } else {
            setData()

        }

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
        binding.rvTask.adapter = adapter
    }

    private fun getTasks() {
        uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            db.collection(uid!!).get().addOnSuccessListener {
                val data = it.toObjects(Task::class.java)
                adapter.addTasks(data)
            }.addOnFailureListener {

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickUpdate(task: Task) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment(task))
    }

    private fun onClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Delete?")
        alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, pos: Int) {
                dialog?.cancel()
            }
        })
        alertDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, pos: Int) {
                setData()
                App.db.taskDao().delete(task)
                setData()
            }
        })
        alertDialog.create().show()
    }
}
