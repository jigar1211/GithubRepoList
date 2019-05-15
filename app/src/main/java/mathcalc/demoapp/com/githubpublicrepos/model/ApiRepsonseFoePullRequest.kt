package mathcalc.demoapp.com.githubpublicrepos.model

data class ApiRepsonseFoePullRequest(
    val assignee: Any,
    val assignees: List<Any>,
    val author_association: String,
    val body: String,
    val closed_at: String,
    val comments_url: String,
    val commits_url: String,
    val created_at: String,
    val diff_url: String,
    val html_url: String,
    val id: Int,
    val issue_url: String,
    val labels: List<Any>,
    val locked: Boolean,
    val merge_commit_sha: String,
    val merged_at: String,
    val milestone: Any,
    val node_id: String,
    val number: Int,
    val patch_url: String,
    val requested_reviewers: List<Any>,
    val requested_teams: List<Any>,
    val review_comment_url: String,
    val review_comments_url: String,
    val state: String,
    val statuses_url: String,
    val title: String,
    val updated_at: String,
    val url: String,
    val user: User
)

data class User(
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)




