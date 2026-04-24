package com.example.sokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)

        val txtname=findViewById<TextView>(R.id.txtProductName)
        val txtcost=findViewById<TextView>(R.id.txtProductCost)
        val imgProduct=findViewById<ImageView>(R.id.imageProduct)


        val name=intent.getStringExtra("product_name")
        val cost=intent.getIntExtra("product_cost",0)
        val product_photo=intent.getStringExtra("product_photo")

        txtname.text=name
        txtcost.text="$cost"


        val imageurl="https://kbenkamotho.alwaysdata.net/static/images/$product_photo"

        Glide.with(this)
            .load(imageurl)
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgProduct)

        val phone=findViewById<EditText>(R.id.phone)
        val btnPay=findViewById<Button>(R.id.pay)

        btnPay.setOnClickListener {
            val api="https://kbenkamotho.alwaysdata.net/api/mpesa_payment"

            val data= RequestParams()

            data.put("amount",cost)
            data.put("phone",phone.text.toString().trim())

            val helper= ApiHelper(applicationContext)

            helper.post(api,data)

            phone.text.clear()
        }







    }
}