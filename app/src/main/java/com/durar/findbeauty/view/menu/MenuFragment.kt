package com.durar.findbeauty.view.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.durar.findbeauty.R
import com.durar.findbeauty.adapters.ServiceGroupAdapter
import com.durar.findbeauty.adapters.TabPagerAdapter
import com.durar.findbeauty.databinding.FragmentMenuBinding
import com.durar.findbeauty.model.SalonData
import com.durar.findbeauty.model.SalonResponse
import com.durar.findbeauty.model.SalonServiceGroup
import com.durar.findbeauty.model.SalonWeblink
import com.durar.findbeauty.network.RetrofitClient
import com.durar.findbeauty.repository.SalonRepository
import com.durar.findbeauty.utils.ApiConstants
import com.durar.findbeauty.viewmodel.SalonViewModel
import com.durar.findbeauty.viewmodel.SalonViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SalonViewModel
    private lateinit var serviceGroups: List<SalonServiceGroup>
    private lateinit var salonWeblink: List<SalonWeblink>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using view binding
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        val repository = SalonRepository(RetrofitClient.apiService)
        val viewModelFactory = SalonViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SalonViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceGroupAdapter = ServiceGroupAdapter(emptyList()) // You can pass an initial empty list

        // Set up the RecyclerView
        binding.rvTopServices.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = serviceGroupAdapter
        }

        // Observe the data from the ViewModel
        viewModel.salonDetails.observe(viewLifecycleOwner, Observer { salonResponse ->
            // Update UI with salon details
            salonResponse?.let {
                // Handle the response
                if(salonResponse.status.equals("success")) {
                    setupViews(salonResponse)
                    serviceGroups = salonResponse.data.serviceGroups
                    salonWeblink = salonResponse.data.salonWeblink
                    serviceGroupAdapter.setData(serviceGroups)
                    binding.progressBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.VISIBLE
                }else{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.nestedScrollView.visibility = View.GONE
                }

            }
        })

        // Fetch salon details
        viewModel.fetchSalonDetails(salonId = 4, lang = "en")
    }

    private fun setupViews(salonResponse: SalonResponse) {
        binding.tvSalonName.text = salonResponse.data.salonDetails[0].salonName
        binding.tvSalonAddress.text = salonResponse.data.salonDetails[0].areaName+", "+salonResponse.data.salonDetails[0].city
        binding.tvSalonRating.text = salonResponse.data.salonDetails[0].ratingSummary.toString()
        binding.ratingBarSalon.rating = salonResponse.data.salonDetails[0].ratingSummary.toFloat()

        binding.ibRoute.setOnClickListener {
            openGoogleMaps(salonResponse.data.salonDetails[0].latitude.toDouble(), salonResponse.data.salonDetails[0].longitude.toDouble())
        }

        binding.ivShare.setOnClickListener {
            shareContent(salonResponse.data.salonDetails[0].salonName)
        }

        binding.tvEmail.setOnClickListener {
            openMailApp(salonResponse.data.salonDetails[0].email)
        }

        binding.ivFacebook.setOnClickListener{
            openLink(salonWeblink[0].weblinkFaceBook)
        }

        binding.ivTwitter.setOnClickListener{
            openLink(salonWeblink[0].weblinkTwitter)
        }

        binding.ivLinkedin.setOnClickListener{
            openLink(salonWeblink[0].weblinkLinkedIn)
        }

        binding.ivInstagram.setOnClickListener{
            openLink(salonWeblink[0].weblinkInstagram)
        }

        binding.ivYoutube.setOnClickListener{
            openLink(salonWeblink[0].weblinkYoutube)
        }

        binding.ivTiktok.setOnClickListener{
            openLink(salonWeblink[0].weblinkTiktok)
        }

        Glide.with(requireActivity())
            .load(ApiConstants.BASE_URL+salonResponse.data.salonDetails[0].salonLogo)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(binding.civProfileImage);

        binding.viewPager.adapter = TabPagerAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.viewPager, TabConfigurationStrategy()).attach()

    }

    private inner class TabConfigurationStrategy : TabLayoutMediator.TabConfigurationStrategy {
        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
            // Customize tab appearance here
            tab.customView = layoutInflater.inflate(R.layout.custom_tab, null)
            val tabText = tab.customView?.findViewById<TextView>(R.id.tvTabName)
            tabText?.text = when (position) {
                0 -> "Gallery"
                1 -> "About"
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }
    }

    private fun openGoogleMaps(latitude: Double, longitude: Double) {
        // Create a Uri from an intent string. Open map using intent to show direction from current location (latitude, longitude) to specific location (latitude, longitude)
        val mapUri = Uri.parse("https://maps.google.com/maps?daddr=$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, mapUri)
        startActivity(intent)
    }

    private fun shareContent(salonName: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, salonName)
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome salon!")

        // Create a chooser dialog to show the available sharing options
        val chooser = Intent.createChooser(shareIntent, "Share via")

        // Start the chooser activity
        startActivity(chooser)
    }

    private fun openMailApp(toAddress: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + toAddress))
        startActivity(Intent.createChooser(emailIntent, "Connect us"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openLink(url: String) {
        var navUrl= url
        if(url.isEmpty())
            navUrl = "https://example.com/"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(navUrl))
        startActivity(intent)
    }
}
