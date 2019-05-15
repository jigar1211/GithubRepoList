package mathcalc.demoapp.com.githubpublicrepos.model

data class ApiResponseModel(
    var owner: Owner?, var full_name: String, var pulls_url: String,
    var name:String)


data class Owner(var avatar_url: String,var login:String)