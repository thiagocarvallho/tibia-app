package com.example.tibia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.tibia.model.dao.room.AppDatabase
import com.example.tibia.model.entity.CharacterDB
import kotlinx.coroutines.launch

class CharacterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    CharacterScreen()
                }
            }
        }
    }

    @Composable
    fun CharacterScreen() {
        val context = requireContext()
        var characters by remember { mutableStateOf<List<CharacterDB>>(emptyList()) }

        val characterName = arguments?.getString("characterName")

        LaunchedEffect(characterName) {
            val dao = AppDatabase.get(context).characterDao()
            characters = if (!characterName.isNullOrEmpty()) {
                dao.getByName(characterName)?.let { listOf(it) } ?: emptyList()
            } else {
                dao.getAll() ?: emptyList()
            }
        }

        CharacterList(characters)
    }

    @Composable
    fun CharacterList(characters: List<CharacterDB>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characters) { character ->
                CharacterCard(character)
            }
        }
    }

    @Composable
    fun CharacterCard(character: CharacterDB) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5E6C8)),
            elevation = CardDefaults.cardElevation(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Nome: ${character.name}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5E2A00),
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Text(
                        text = "Level: ${character.level}",
                        fontSize = 16.sp,
                        color = Color(0xFF3D1F00),
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(
                        text = "Vocação: ${character.vocation}",
                        fontSize = 16.sp,
                        color = Color(0xFF3D1F00),
                        fontFamily = FontFamily.Serif
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Divider(
                    color = Color(0xFF8B5A2B),
                    thickness = 2.dp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                Text(
                    text = "Mundo: ${character.world}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB8860B),
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}
