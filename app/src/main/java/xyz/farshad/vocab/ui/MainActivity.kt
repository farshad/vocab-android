package xyz.farshad.vocab.ui

import android.view.Menu
import xyz.farshad.vocab.R
import xyz.farshad.vocab.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun businessLogic() {
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController: NavController = navHostFragment.navController

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavMenu)
//        bottomNavigationView.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.homeFragment || destination.id == R.id.challengeListFragment) {
//                bottomNavigationView.visibility = View.VISIBLE
//            } else {
//                bottomNavigationView.visibility = View.GONE
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}
