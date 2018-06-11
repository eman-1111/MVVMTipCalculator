package com.wasltec.tipcalculater.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.wasltec.tipcalculater.R
import com.wasltec.tipcalculater.databinding.ActivityMainBinding
import com.wasltec.tipcalculater.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() , SaveDialogFragment.Callback{
    lateinit var binding : ActivityMainBinding


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tip_calculator, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_save ->{
                showSavedDialog()
                true
            }
           else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSavedDialog(){
        val saveFragment = SaveDialogFragment()
        saveFragment.show(supportFragmentManager,"nn")
    }
    override fun onSaveTip(name: String) {
        binding.vm?.saveCurrentTip(name)
        Snackbar.make(binding.root,"Saved $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

        setSupportActionBar(binding.toolbar)


    }

}
