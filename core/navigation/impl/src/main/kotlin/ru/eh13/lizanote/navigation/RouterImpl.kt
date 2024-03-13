package ru.eh13.lizanote.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavController
import com.google.gson.Gson
import java.io.Serializable

class RouterImpl : Router {

    override fun navigateToFlow(flow: Flow) {
        navController?.navigate("${flow.name}")
    }

    override fun navigateToFlow(flow: Flow, args: Serializable?) {
        navController?.navigate("${flow.name}/${Gson().toJson(args)}")
    }

    override fun navigateTo(route: String) {
        navController?.navigate("$route")
    }

    override fun navigateTo(route: String, args: Serializable?) {
        navController?.navigate("$route/${Gson().toJson(args)}")
    }

    override fun back() {
        navController?.popBackStack()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var navController: NavController? = null

        fun setNavController(newNavController: NavController?) {
            navController = newNavController
        }
    }
}