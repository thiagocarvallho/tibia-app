package com.example.tibia.repository

import android.util.Log
import com.example.tibia.model.Retrofit
import com.example.tibia.model.dao.HighscoreDao
import com.example.tibia.model.entity.HighscoreEntity
import com.example.tibia.model.entity.HighscoresResponse
import com.example.tibia.model.serealizer.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.collections.emptyList

class HighscoreRepository(private val dao: HighscoreDao) {
    private val api = Retrofit.create()
    private val cacheDuration = 60 * 60 * 1000L // 1h

    suspend fun getCharacter(name: String): Response<CharacterResponse> {
        return api.getCharacter(name)
    }

    suspend fun getDefaultHighscores(): Response<HighscoresResponse> {
        return api.getHighscores("all", "experience", "all", 1)
    }

    suspend fun getHighscoresAfterRank(
        world: String,
        category: String,
        vocation: String,
        lastRank: Int,
        pageSize: Int
    ): List<HighscoreEntity> {

        // 1️⃣ Tenta buscar do banco
        val cached = dao.getHighscoresAfterRank(world, category, lastRank, pageSize)
        if (cached.isNotEmpty()) return cached

        // 2️⃣ Se não tiver no banco, busca da API
        val page = if (lastRank == 0) 1 else (lastRank / pageSize) + 1
        val vocationParam = if (vocation.lowercase() == "all") "all" else vocation

        val response = api.getHighscores(world, category, vocationParam, page)
        if (!response.isSuccessful) return cached // API falhou, retorna o que tiver no banco

        val apiItems = response.body()?.highscores?.highscore_list ?: emptyList()
        if (apiItems.isEmpty()) return cached

        // 3️⃣ Mapeia para HighscoreEntity
        val entities = apiItems.map { dto ->
            HighscoreEntity(
                name = dto.name,
                rank = dto.rank,
                level = dto.level,
                vocation = dto.vocation,
                world = world,
                category = category,
                value = dto.value,
                timestamp = System.currentTimeMillis()
            )
        }

        // 4️⃣ Salva no banco
        dao.insertAll(entities)

        // 5️⃣ Retorna os dados do banco com rank > lastRank
        return dao.getHighscoresAfterRank(world, category, lastRank, pageSize)
    }

    suspend fun getHighscoresPaged(
        world: String,
        category: String,
        vocation: String,
        lastRankLoaded: Int // rank do último item que já temos
    ): List<HighscoreEntity> {

        val pageSize = 50

        // 1. Tenta buscar do banco todos acima do lastRankLoaded
        val cached = dao.getHighscoresAfterRank(world, category, lastRankLoaded, pageSize)
        if (cached.isNotEmpty()) return cached

        Log.i("entrou","buscando API para rank > $lastRankLoaded")

        // 2. Busca da API: a API vai ignorar ranks já carregados, se você passar a page correta
        // Supondo que cada "page" corresponda a ranks sequenciais, podemos calcular:
        val nextPage = (lastRankLoaded / pageSize) + 1
        val response = api.getHighscores(world, category, vocation, nextPage)
        Log.i("entrou","API retornou ${response.body()}")

        if (response.isSuccessful) {
            val apiItems = response.body()?.highscores?.highscore_list ?: emptyList()

            val entities = apiItems.map { dto ->
                HighscoreEntity(
                    name = dto.name,
                    rank = dto.rank,
                    level = dto.level,
                    vocation = dto.vocation,
                    world = world,
                    category = category,
                    value = dto.value,
                    timestamp = System.currentTimeMillis()
                )
            }

            // 3. Salva no banco
            dao.insertAll(entities)

            // 4. Retorna do banco apenas os que ainda não tínhamos
            return dao.getHighscoresAfterRank(world, category, lastRankLoaded, pageSize)
        } else {
            return cached
        }
    }

    suspend fun getHighscores(world: String, category: String): List<HighscoreEntity> =
        withContext(Dispatchers.IO) {
            val cached = dao.getAll(world, category)
            val now = System.currentTimeMillis()

            if (cached.isNotEmpty() && now - cached.first().timestamp < cacheDuration) {
                return@withContext cached
            }

            val apiResponse = api.getHighscores(world, category, "all", 1).body()
            val highscoresData = apiResponse?.highscores

            val list = highscoresData?.highscore_list?.map {
                HighscoreEntity(
                    name = it.name,
                    rank = it.rank,
                    level = it.level,
                    vocation = it.vocation,
                    world = highscoresData.world,
                    category = highscoresData.category,
                    value = it.value,
                    timestamp = now
                )
            } ?: emptyList()

            dao.clear(world, category)
            dao.insertAll(list)
            list
        }
}
