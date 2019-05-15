package mathcalc.demoapp.com.githubpublicrepos

import android.content.Context
import android.net.ConnectivityManager

class UDF {

    companion object {
        /**
         * To check Internet Availability
         */
        fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return (cm.activeNetworkInfo != null && cm.activeNetworkInfo.isAvailable
                    && cm.activeNetworkInfo.isConnected)
        }
    }
}