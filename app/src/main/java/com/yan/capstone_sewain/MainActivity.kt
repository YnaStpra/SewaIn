package com.yan.capstone_sewain

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.yan.capstone_sewain.profile.UserProfil
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {

    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicles: List<Vehicle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomMeImageView = findViewById<ImageView>(R.id.bottom_me)
        val searchView = findViewById<SearchView>(R.id.searchView)
        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val slideModels = ArrayList<SlideModel>()
        recyclerView = findViewById(R.id.recycler_view)

        slideModels.add(SlideModel(R.drawable.title, ScaleTypes.FIT))
        slideModels.add(SlideModel(R.drawable.title3, ScaleTypes.FIT))

        imageSlider.setImageList(slideModels, ScaleTypes.FIT)


        bottomMeImageView.setOnClickListener {
            val intent = Intent(this, UserProfil::class.java)
            startActivity(intent)
        }

        searchView.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        // Implementasikan fungsi disableSearchViewEditText
        disableSearchViewEditText(searchView)

        // Inisialisasi data contoh
        vehicles = listOf(
            Vehicle(R.drawable.nmaxlarger, "Yamaha Nmax", "Rp. 95.000", "My Bike Rental Bali", 4.0f,"My Bike Rental Bali","Kec. Kediri, Kabupaten Tabanan"),
            Vehicle(R.drawable.yamahamio, "Yamaha Mio", "Rp. 65.000", "Agas Motorbike Rental", 4.7f,"Agas Motorbike Rental","Kec. Kediri, Kabupaten Tabanan"),
            Vehicle(R.drawable.hondabeat, "Honda Beat", "Rp. 60.000", "SMB Rent Bike Bali", 4.4f,"SMB Rent Bike Bali","Kec. Kuta Utara, Kabupaten Badung"),
            Vehicle(R.drawable.yamahagear, "Yamaha Gear", "Rp. 75.000", "Ketut motor rental and taxi", 4.0f,"Ketut motor rental and taxi","Kec. Nusa Penida, Kabupaten Klungkung"),
            Vehicle(R.drawable.yamahasoul, "Yamaha Soul", "Rp. 65.000", "Rental Motor Bike kadek", 4.7f,"Rental Motor Bike kadek","Kec. Abang, Kabupaten Karangasem"),
            Vehicle(R.drawable.hondapcx, "Honda PCX", "Rp. 90.000", "Bikago Motorbike Rental", 4.4f,"Bikago Motorbike Rental","Kec. Banjar, Kabupaten Buleleng"),
            Vehicle(R.drawable.hondabeat, "Honda Beat", "Rp. 60.000", "Bali Nice Rental Scooter", 4.0f,"Bali Nice Rental Scooter","Kec. Ubud, Kabupaten Gianyar"),
            Vehicle(R.drawable.yamahamio, "Yamaha Mio", "Rp. 65.000", "3 Monkeys Rental Bali", 4.7f,"3 Monkeys Rental Bali","Kec. Kuta Utara, Kabupaten Badung"),
            Vehicle(R.drawable.yamahafazzio, "Yamaha Fazzio", "Rp. 70.000", "cahya ubud rental motorbike", 4.4f,"cahya ubud rental motorbike","Kec. Ubud, Kabupaten Gianyar"),
        )

        vehicleAdapter = VehicleAdapter(vehicles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = vehicleAdapter
    }

    private fun disableSearchViewEditText(searchView: SearchView) {
        try {
            // Mengakses field mSearchButton
            val searchField: Field = SearchView::class.java.getDeclaredField("mSearchButton")
            searchField.isAccessible = true
            val searchButton = searchField.get(searchView) as View
            searchButton.isEnabled = false
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Menghilangkan fokus ketika SearchView mendapatkan fokus
        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                searchView.clearFocus()
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
