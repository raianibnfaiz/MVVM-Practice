package com.raian.mvvmsession.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.raian.mvvmsession.R
import com.raian.mvvmsession.model.Blog
import com.raian.mvvmsession.viewModel.BlogViewModel

class BlogRecyclerAdapter(
    private val context: Context,
    private val viewModel: BlogViewModel,
    private val arrayList: ArrayList<Blog>
) : RecyclerView.Adapter<BlogRecyclerAdapter.BlogViewHolder>() {
    

    class BlogViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val title: EditText = binding.findViewById(R.id.title)
        val deleteButton: ImageButton = binding.findViewById(R.id.delete)
        val date: TextView = binding.findViewById(R.id.current_date_textview)
        val editButton: ImageButton = binding.findViewById(R.id.edit)
        val updateButton: Button = binding.findViewById(R.id.update)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return BlogViewHolder(root)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.updateButton.visibility = View.INVISIBLE
        val blog = arrayList[position]
        holder.title.setText(blog.title)
        holder.title.isEnabled = false
        holder.date.text = blog.date
        holder.deleteButton.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(true)
                .setPositiveButton("Yes") { dialog, id ->
                    viewModel.removeBlog(blog)
                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()

            //notifyItemRemoved(arrayList.indexOf(blog))
        }

        holder.editButton.setOnClickListener {
            holder.updateButton.visibility = View.VISIBLE
            holder.deleteButton.visibility = View.INVISIBLE
            holder.editButton.visibility = View.INVISIBLE
            holder.title.isEnabled = true
        }
        holder.updateButton.setOnClickListener {
            //holder.title.text = blog.title
            val newTitle = holder.title.text.toString()
            holder.date.text = blog.date
            val blog = Blog(newTitle,blog.date)
            viewModel.updateBlog(position,blog)
            //notifyItemChanged(arrayList.indexOf(blog))
        }

    }


    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "Blog list is empty", Toast.LENGTH_SHORT).show()
        }
        return arrayList.size
    }
}