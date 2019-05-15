package mathcalc.demoapp.com.githubpublicrepos.adapter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import mathcalc.demoapp.com.githubpublicrepos.model.ApiResponseModel
import mathcalc.demoapp.com.githubpublicrepos.view.PullRequestActivity


class RepositoryAdapter(val activity: Activity, private val apiResponseModel: MutableList<ApiResponseModel>) :

    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepositoryViewHolder {

        val v = LayoutInflater.from(p0.context)
            .inflate(mathcalc.demoapp.com.githubpublicrepos.R.layout.row_repository, p0, false)

        return RepositoryViewHolder(v)
    }

    override fun getItemCount(): Int {
        return apiResponseModel.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {

        val item = apiResponseModel[position]
        holder.bind(item)
    }

    interface OnItemClickListener {
        fun onItemClick(itemView: View, position: Int, id: Long)
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivProfile: CircleImageView = itemView.findViewById(mathcalc.demoapp.com.githubpublicrepos.R.id.ivProfile)
        val tvRepoName: TextView = itemView.findViewById(mathcalc.demoapp.com.githubpublicrepos.R.id.tvRepoName)


        fun bind(item: ApiResponseModel) {

            Picasso.with(activity).load(item.owner!!.avatar_url).resize(80, 80).into(ivProfile)
            tvRepoName.text = item.full_name
            itemView.setOnClickListener { v ->

                val i = Intent(activity, PullRequestActivity::class.java)
                i.putExtra("REPOSITORY_NAME", item.name)
                i.putExtra("USER_NAME", item.owner!!.login)
                activity.startActivity(i)

            }

        }
    }
}