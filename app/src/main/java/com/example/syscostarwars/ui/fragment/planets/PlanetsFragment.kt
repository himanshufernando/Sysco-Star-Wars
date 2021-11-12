package com.example.syscostarwars.ui.fragment.planets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.LoadState
import com.example.syscostarwars.R
import com.example.syscostarwars.ui.adapter.PagingLoadStateAdapter
import com.example.syscostarwars.viewmodel.PlanetsViewModel
import kotlinx.android.synthetic.main.fragment_planets.*
import kotlinx.coroutines.Job
import com.example.syscostarwars.data.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

class PlanetsFragment : Fragment(),View.OnClickListener {

    private val viewmodel: PlanetsViewModel by activityViewModels()


    private var adapter = PlanetsAdapter()
    private lateinit var postJob: Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_planets, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initPlanetsRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        getAllPlanetFromServer()
    }

    override fun onStop() {
        super.onStop()
        if (::postJob.isInitialized) {
            postJob.cancel()
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.txt_planets_retry -> adapter?.retry()
        }
    }



    private fun initViews(){
        txt_planets_retry.setOnClickListener(this)
    }


    private fun getAllPlanetFromServer() {
        if (::postJob.isInitialized) {
            postJob.cancel()
        }
        postJob = lifecycleScope.launch {
            viewmodel.getAllPlanetFromServer().collectLatest {
                adapter?.submitData(it)
            }

        }
    }


    private fun initPlanetsRecyclerView() {
        recyclerView_planets.adapter = adapter
        recyclerView_planets.adapter = adapter?.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { adapter?.retry() },
            footer = PagingLoadStateAdapter { adapter?.retry() }
        )
        adapter?.addLoadStateListener { loadState ->

            if (cl_loading != null) {
                cl_loading.isVisible = loadState.source.refresh is LoadState.Loading
            }

            if (txt_planets_retry != null) {
                txt_planets_retry.isVisible = loadState.source.refresh is LoadState.Error
            }

            val error = when {
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            error?.let {
                when (it.error) {
                    is HttpException -> Toast.makeText(requireContext(), resources.getString(R.string.network_failed), Toast.LENGTH_LONG).show()
                    is SocketTimeoutException -> Toast.makeText(requireContext(),resources.getString(R.string.timeout), Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(requireContext(),resources.getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                }
            }

        }

        adapter?.setOnItemClickListener(object : PlanetsAdapter.ClickListener {
            override fun onClick(result: Result, aView: View, position: Int) {
                if(result != null){
                    viewmodel.userSelectedPlanet.value = result
                    navigateToPlanetDetails()
                }

            }
        })
    }

    private fun navigateToPlanetDetails() {
        NavHostFragment.findNavController(this).navigate(R.id.fragment_planets_to_planetdetails)
    }




}