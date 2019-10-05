package com.alharoof.diabetracker.ui.import_export

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R

class ImportExportFragment : Fragment() {

    companion object {
        fun newInstance() = ImportExportFragment()
    }

    private lateinit var viewModel: ImportExportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.import_export_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ImportExportViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
