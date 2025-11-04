package com.example.tibia

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.appcompat.widget.SearchView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tibia.databinding.ActivityMainBinding
import com.example.tibia.model.dao.room.AppDatabase
import com.example.tibia.model.entity.CharacterDB
import com.example.tibia.repository.CharacterRepository
import com.example.tibia.ui.CharacterFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CharacterViewModel

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DB + Repository
        val db = AppDatabase.get(this)
        val repository = CharacterRepository(db.characterDao())

        // ViewModel com Factory
        val factory = CharacterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[CharacterViewModel::class.java]

        // Chama carregamento (pode ser acionado depois também)
//        viewModel.loadCharacter("Dejairzin")
//        Log.i("entrou","entrou test2 ")

        viewModel.get()

        // Layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Toolbar
        setSupportActionBar(binding.appBarMain.toolbar)
//        binding.appBarMain.fab.setOnClickListener {
//
//        }
        // FAB
        binding.appBarMain.fab.setOnClickListener { view ->
            showSearchDialog()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // DrawerLayout e NavigationView
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu) // troque para o nome do seu XML

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Digite algo..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Aqui você pode usar o texto pesquisado
                val name = query
                if (name?.isNotEmpty() == true) {
//                    viewModel.loadCharacter(name) // chama o ViewModel para buscar
                    viewModel.viewModelScope.launch(Dispatchers.Main){
                        val character = viewModel.getChar(name)
                        val navController = findNavController(R.id.nav_host_fragment_content_main)
                        val bundle = Bundle().apply {
                            putParcelable("character", character as Parcelable?)
                        }

                        if (navController.currentDestination?.id != R.id.nav_characters) {
                            navController.navigate(R.id.action_nav_home_to_nav_characters, bundle)
                        } else {
                            navController.navigate(R.id.nav_characters, bundle) // ou reusar o destino atual
                        }
                    }
                } else {
                    Snackbar.make(binding.root, "Digite um nome válido", Snackbar.LENGTH_SHORT).show()
                }
                Toast.makeText(this@MainActivity, "Pesquisou: $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Aqui dá para atualizar em tempo real (filtro, etc)
                return true
            }
        })

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun showSearchDialog() {
        val editText = android.widget.EditText(this).apply {
            hint = "Digite o nome do personagem"
        }

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Pesquisar personagem")
            .setView(editText)
            .setPositiveButton("Buscar") { _, _ ->
                val name = editText.text.toString()
                if (name.isNotEmpty()) {
                    viewModel.loadCharacter(name) // chama o ViewModel para buscar
                    val navController = findNavController(R.id.nav_host_fragment_content_main)
                    val bundle = Bundle().apply {
                        putString("characterName", name)
                    }
                    navController.navigate(R.id.action_nav_home_to_nav_characters, bundle)
                } else {
                    Snackbar.make(binding.root, "Digite um nome válido", Snackbar.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}

class CharacterViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    fun loadCharacter(name: String) {
        viewModelScope.launch {
            try {
               val teste =  repository.fetchAndSaveCharacter(name)

                Log.i("entrou","entrou test = $teste")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    suspend fun getChar(name: String): CharacterDB? {
        return repository.fetch(name)

    }
//    fun loadDeaths(name: String) {
//        viewModelScope.launch {
//            try {
//               val teste =  repository.fetchAndSaveCharacter(name)
//                Log.i("entrou","entrou test = $teste")
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
    fun get() {
        viewModelScope.launch {
               val teste = repository.get()
            Log.i("entrou","entrou test get = $teste")
        }
    }


}

class CharacterViewModelFactory(
    private val repository: CharacterRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}