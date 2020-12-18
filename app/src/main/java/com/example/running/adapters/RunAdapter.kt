package com.example.running.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.running.R
import com.example.running.db.Run
import com.example.running.other.TrackingUtility
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*

//Pada deklarasi class ini, RunAdapter bertanggung jawab memanggil konstruktor superclass-nya,RecyclerView
class RunAdapter : RecyclerView.Adapter<RunAdapter.RunViewHolder> (){

    inner class RunViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    //Ketika suatu anggota memiliki hak akses private,
    // maka anggota tersebut tidak dapat diakses dari luar scope-nya.
    // Untuk menggunakan modifier private kita perlu menambahkan keyword private seperti contoh berikut
    val diffCallBack = object  : DiffUtil.ItemCallback<Run> (){
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }
        //Pada fungsi ini kita perlu menentukan apa yang menjadi indikator pembeda
        // antara item yang satu dan yang lain. Disini saya menggunakan field repoName sebagai indikator.
        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    fun submitList(list: List<Run>) = differ.submitList(list)

    // DalamRunAdapter, Anda dapat mengganti sejumlah callback siklus proses untuk menanggapi perubahan status
    // pada RecyclerView Anda. Untuk mengganti fungsi,
    // gunakan kata kunci override, seperti yang ditunjukkan pada contoh berikut
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_run,
                        parent,
                        false
                )
        )
    }

    //Mengembalikan jumlah total item dalam kumpulan data yang dipegang oleh adaptor
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    //Dipanggil oleh RecyclerView untuk menampilkan data pada posisi yang ditentukan.
    // Metode ini harus memperbarui konten RecyclerView.ViewHolder.itemView
    // untuk mencerminkan item pada posisi tertentu.
    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(run.img).into(ivRunImage)

            val calender = Calendar.getInstance().apply {
                timeInMillis = run.timestamp
            }
            val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            tvDate.text = dateFormat.format(calender.time)

            val avgSpeed = "${run.avgSpeedInKMH}km/h"
            tvAvgSpeed.text = avgSpeed

            val distanceInKm = "${run.distanceInMeters / 1000f}km"
            tvDistance.text = distanceInKm

            tvTime.text = TrackingUtility.getFormattedStopwatchTime(run.timeInMillis)

            val caloriesBurned = "${run.caloriesBurned}kcal"
            tvCalories.text = caloriesBurned
        }
    }


}