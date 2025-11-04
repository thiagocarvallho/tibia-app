package com.example.tibia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.tibia.model.dao.room.AppDatabase
import com.example.tibia.model.serealizer.*
import com.example.tibia.repository.HighscoreRepository
import com.example.tibia.viewmodel.HighscoreViewModel
import java.text.NumberFormat
import java.util.Locale

class Highscores : Fragment() {

    private val viewModel by lazy {
        val dao = AppDatabase.get(requireContext()).highscoreDao()
        val repo = HighscoreRepository(dao)
        HighscoreViewModel(repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    HighscoresScreen(viewModel)
                }
            }
        }
    }

    /** Funções manuais para gerar listas de campos não nulos sem reflection **/
    private fun CharacterDetail.toDisplayList(): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        name?.let { list.add("Name" to it) }
        level?.let { list.add("Level" to it.toString()) }
        vocation?.let { list.add("Vocation" to it) }
        world?.let { list.add("World" to it) }
        achievementPoints?.let { list.add("Achievement Points" to NumberFormat.getNumberInstance(Locale.US).format(it)) }
        accountStatus?.let { list.add("Account Status" to it) }
        comment?.let { list.add("Comment" to it) }
        lastLogin?.let { list.add("Last Login" to it) }
        residence?.let { list.add("Residence" to it) }
        title?.let { list.add("Title" to it) }
        sex?.let { list.add("Sex" to it) }
        marriedTo?.let { list.add("Married To" to it) }
        guild?.name?.let { list.add("Guild" to it) }
        return list
    }

    private fun AccountInformation.toDisplayList(): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        created?.let { list.add("Created" to it) }
        loyaltyTitle?.let { list.add("Loyalty Title" to it) }
        position?.let { list.add("Position" to it) }
        return list
    }

    private fun Guild.toDisplayList(): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        name?.let { list.add("Name" to it) }
        rank?.let { list.add("Rank" to it) }
        return list
    }

    private fun House.toDisplayList(): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        name?.let { list.add("Name" to it) }
        town?.let { list.add("Town" to it) }
        paid?.let { list.add("Paid" to it) }
        houseId?.let { list.add("House ID" to it.toString()) }
        return list
    }

    private fun Achievement.toDisplayList(): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        name?.let { list.add("Name" to it) }
        grade?.let { list.add("Grade" to it.toString()) }
        secret?.let { list.add("Secret" to it.toString()) }
        return list
    }

    private fun OtherCharacter.toDisplayList(): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        name?.let { list.add("Name" to it) }
        world?.let { list.add("World" to it) }
        main?.let { list.add("Main" to it.toString()) }
        deleted?.let { list.add("Deleted" to it.toString()) }
        position?.let { list.add("Position" to it) }
        status?.let { list.add("Status" to it) }
        traded?.let { list.add("Traded" to it.toString()) }
        return list
    }

    private fun Death.toDisplayList(): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        level?.let { list.add("Level" to it.toString()) }
        reason?.let { list.add("Reason" to it) }
        time?.let { list.add("Time" to it) }
        killers?.let { list.add("Killers" to it.joinToString { it.name ?: "" }) }
        assists?.let { list.add("Assists" to it.joinToString { it.name ?: "" }) }
        return list
    }

    /** Composable **/

    @Composable
    fun HighscoresScreen(viewModel: HighscoreViewModel) {
        val highscores by viewModel.highscores.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()
        val selectedCharacter by viewModel.selectedCharacter.collectAsState()
        val listState = rememberLazyListState()

        LaunchedEffect(Unit) {
            viewModel.loadNextPage("all", "experience", "all")
        }

        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo }
                .collect { layoutInfo ->
                    val totalItems = layoutInfo.totalItemsCount
                    val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    if (lastVisible >= totalItems - 5 && viewModel.hasMorePages() && !viewModel.isLoading()) {
                        viewModel.loadNextPage("all", "experience", "all")
                    }
                }
        }

        /** Dialog com estilo melhor **/
        selectedCharacter?.let { char ->
            val characterDetail = char.character?.characterDetail
            val accountInfo = char.character?.accountInformation
            val guild = char.character?.characterDetail?.guild
            val houses = char.character?.characterDetail?.houses
            val achievements = char.character?.achievements
            val otherChars = char.character?.otherCharacters
            val deaths = char.character?.deaths

            AlertDialog(
                onDismissRequest = { viewModel.clearSelectedCharacter() },
                confirmButton = {}, // removido botão "Fechar"
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = characterDetail?.name ?: "Character Info",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        IconButton(
                            onClick = { viewModel.clearSelectedCharacter() },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Fechar",
                                tint = Color.Gray
                            )
                        }
                    }
                },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        val sectionSpacing = 12.dp

                        characterDetail?.toDisplayList()?.let { list ->
                            InfoSection("Character Details", list)
                            Spacer(modifier = Modifier.height(sectionSpacing))
                        }

                        accountInfo?.toDisplayList()?.let { list ->
                            InfoSection("Account Info", list)
                            Spacer(modifier = Modifier.height(sectionSpacing))
                        }

                        guild?.toDisplayList()?.let { list ->
                            InfoSection("Guild", list)
                            Spacer(modifier = Modifier.height(sectionSpacing))
                        }

                        houses?.forEachIndexed { i, h ->
                            InfoSection("House ${i + 1}", h.toDisplayList())
                            Spacer(modifier = Modifier.height(sectionSpacing))
                        }

                        achievements?.forEachIndexed { i, a ->
                            InfoSection("Achievement ${i + 1}", a.toDisplayList())
                            Spacer(modifier = Modifier.height(sectionSpacing))
                        }

                        otherChars?.forEachIndexed { i, oc ->
                            val title = oc.name ?: "Other Character"
                            val fields = oc.toDisplayList().filter { it.first != "Name" }
                            InfoSection(title, fields)
                            Spacer(modifier = Modifier.height(sectionSpacing))
                        }

                        deaths?.forEachIndexed { i, d ->
                            InfoSection("Death ${i + 1}", d.toDisplayList())
                            Spacer(modifier = Modifier.height(sectionSpacing))
                        }
                    }
                }
            )
        }

        /** Tela principal **/
        Box(modifier = Modifier.fillMaxSize()) {
            if (highscores.isEmpty() && isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (highscores.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nenhum dado disponível")
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    items(highscores) { entry ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { viewModel.loadCharacter(entry.name) },
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5E6C8))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("#${entry.rank} - ${entry.name}", style = MaterialTheme.typography.titleMedium)
                                Text("Level ${entry.level} • ${entry.vocation}", color = Color(0xFF6B4E16))
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "Pontos: ${NumberFormat.getNumberInstance(Locale.US).format(entry.value.toLong())}",
                                    color = Color(0xFF3E2723),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    if (isLoading) {
                        item {
                            Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }

    /** Composable para seção estilizada **/
    @Composable
    private fun InfoSection(title: String, items: List<Pair<String, String>>) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color(0xFF5D4037))
                Spacer(modifier = Modifier.height(6.dp))
                items.forEach { (label, value) ->
                    Text("$label: $value", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
