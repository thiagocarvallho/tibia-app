package com.example.tibia.model

import com.example.tibia.model.entity.HighscoresResponse
import com.example.tibia.model.remote.CharacterRequest
import com.example.tibia.model.serealizer.CharacterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitService {

    @POST("animals/classifications/sync/{last_change}")
    suspend fun getAnimalClassification(
        @Path("last_change") lastChange: Long,
        @Body data: CharacterRequest
    ) : Response<CharacterResponse>


    @GET("character/{name}")
    suspend fun getCharacter(@Path("name") name: String): Response<CharacterResponse>


    @GET("highscores/{world}/{category}/{vocation}/{page}")
    suspend fun getHighscores(
        @Path("world") world: String,
        @Path("category") category: String,
        @Path("vocation") vocation: String,
        @Path("page") page: Int
    ): Response<HighscoresResponse>
}