import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val deliveryLocation = LatLng(12.9716, 77.5946) // Dummy LatLng for simulation
        googleMap.addMarker(MarkerOptions().position(deliveryLocation).title("Delivery Person"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(deliveryLocation, 15f))
    }
}


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val orderStatus = findViewById<TextView>(R.id.order_status)
        val estimatedTime = findViewById<TextView>(R.id.estimated_time)
        val contactDelivery = findViewById<Button>(R.id.contact_delivery)
        val refreshStatus = findViewById<Button>(R.id.refresh_status)

        // Dummy data for simulation
        val statuses = listOf("Order Placed", "Preparing", "Out for Delivery", "Delivered")
        var currentStatusIndex = 2 // Start with "Out for Delivery"

        // Contact Delivery Button Logic
        contactDelivery.setOnClickListener {
            Toast.makeText(this, "Calling the delivery person...", Toast.LENGTH_SHORT).show()
        }

        // Refresh Status Button Logic
        refreshStatus.setOnClickListener {
            if (currentStatusIndex < statuses.size - 1) {
                currentStatusIndex++
                orderStatus.text = "Order Status: ${statuses[currentStatusIndex]}"
                estimatedTime.text = when (currentStatusIndex) {
                    0 -> "Estimated Delivery Time: 45 mins"
                    1 -> "Estimated Delivery Time: 30 mins"
                    2 -> "Estimated Delivery Time: 15 mins"
                    else -> "Your order has been delivered!"
                }
            } else {
                Toast.makeText(this, "Order already delivered!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
