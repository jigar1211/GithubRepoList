package mathcalc.demoapp.com.githubpublicrepos.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mathcalc.demoapp.com.githubpublicrepos.R
import mathcalc.demoapp.com.githubpublicrepos.model.ApiRepsonseFoePullRequest

class PullRequestAdapter(val activity: Activity, private val apiResponseModel: MutableList<ApiRepsonseFoePullRequest>) :

    RecyclerView.Adapter<PullRequestAdapter.RepositoryViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RepositoryViewHolder {

        val v = LayoutInflater.from(p0.context)
            .inflate(R.layout.row_pull_request, p0, false)

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

        val tvCreated: TextView = itemView.findViewById(R.id.tvCreated)
        val tvMerge: TextView = itemView.findViewById(R.id.tvMerge)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)

        fun bind(item: ApiRepsonseFoePullRequest) {
            tvCreated.text = item.created_at
            tvMerge.text = item.merged_at
            tvTitle.text = item.title
            tvStatus.text = item.state
        }
    }
}