package mathcalc.demoapp.com.githubpublicrepos.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mathcalc.demoapp.com.githubpublicrepos.R
import mathcalc.demoapp.com.githubpublicrepos.model.ApiResponseModel

class RepositoryAdapter(val activity: Activity, private val apiResponseModel: MutableList<ApiResponseModel>) :

    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepositoryViewHolder {

        val v = LayoutInflater.from(p0.context)
            .inflate(R.layout.row_repository, p0, false)

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

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        val ivProfile: CircleImageView = itemView.findViewById(R.id.ivProfile)
        val tvRepoName: TextView = itemView.findViewById(R.id.tvRepoName)
        val tvPullRequest: TextView = itemView.findViewById(R.id.tvPullRequest)

        fun bind(item: ApiResponseModel) {

//            Picasso.with(activity).load(item.owner.avatar_url).resize(80, 80).into(ivProfile)
            tvRepoName.text = item.full_name
            tvPullRequest.text = item.pulls_url
        }
    }
}