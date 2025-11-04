package com.example.tibia.repository

import android.util.Log
import com.example.tibia.model.Retrofit
import com.example.tibia.model.dao.CharacterDao
import com.example.tibia.model.entity.CharacterDB
import com.example.tibia.model.entity.OtherCharacterDB

class CharacterRepository(private val dao: CharacterDao) {

    private val api = Retrofit.create()

//    suspend fun fetchAndSaveCharacterDetails(name: String) {
//        val response = api.getCharacter(name)
//        Log.i("entrou","entrou response = ${response.body()?.character}")
//        if (response.isSuccessful) {
//            Log.i("entrou","entrou isSuccessful")
//            response.body()?.let { body ->
//                val info = body.character?.characterDetail
//                val characterDb = CharacterDB(
//                    name = info?.name,
//                    level = info?.level,
//                    vocation = info?.vocation,
//                    world = info?.world,
//                    sex = info?.sex,
//                    title = info?.title,
//                    residence = info?.residence,
//                    marriedTo = info?.marriedTo,
//                    achievementPoints = info?.achievementPoints,
//                    accountStatus = info?.accountStatus,
//                    comment = info?.comment
//                )
//                dao.insert(characterDb)
//            }
//        } else {
//            Log.i("entrou","entrou else")
//
//            throw Exception("Erro: ${response.code()} ${response.message()}")
//        }
//    }
    suspend fun getName(name: String?):String? {
        val teste = dao.getByName(name)
        return teste?.name
    }
    suspend fun fetchAndSaveCharacter(name: String) {
        val response = api.getCharacter(name)
        Log.i("entrou","entrou response = ${response.body()?.character}")
        if (response.isSuccessful) {
            Log.i("entrou","entrou isSuccessful")

            response.body()?.let { body ->
                val otherCharacs = body.character?.otherCharacters?.forEach {
                    Log.i("entrou","entrou otherCharacters forEach = ${it}")
                    val response = it?.name?.let { it1 -> api.getCharacter(it1) }
                    Log.i("entrou","entrou otherCharacters response= ${response?.body()?.character}")
                    response?.body().let { body ->
                        val character = body?.character?.characterDetail
                        val check = dao.getByName(character?.name)
                        if(check == null){
                            dao.insert(
                                CharacterDB(
                                    name = character?.name,
                                    level = character?.level,
                                    vocation = character?.vocation,
                                    world = character?.world,
                                    sex = character?.sex,
                                    title = character?.title,
                                    residence = character?.residence,
                                    marriedTo = character?.marriedTo,
                                    achievementPoints = character?.achievementPoints,
                                    accountStatus = character?.accountStatus,
                                    comment = character?.comment,
                                    deaths = body?.character?.deaths?.first()?.reason,
                                    guild = character?.guild?.name ,
                                    lastChange = character?.lastLogin
                                )
                            )
                        }
                    }

                }

            }
        } else {
            Log.i("entrou","entrou else")

            throw Exception("Erro: ${response.code()} ${response.message()}")
        }
    }


    suspend fun fetch(name: String):CharacterDB? {
        val response = api.getCharacter(name)
        Log.i("entrou","entrou response = ${response.body()?.character}")
        var retorno:CharacterDB? = null
        if (response.isSuccessful) {
            Log.i("entrou","entrou isSuccessful")

            response.body()?.let { body ->
                val otherCharacs = body.character?.otherCharacters?.forEach {
                    Log.i("entrou","entrou otherCharacters forEach = ${it}")
                    val response = it?.name?.let { it1 -> api.getCharacter(it1) }
                    Log.i("entrou","entrou otherCharacters response= ${response?.body()?.character}")
                    response?.body().let { body ->
                        val character = body?.character?.characterDetail
                        val check = dao.getByName(character?.name)
                        if(check == null){
                            retorno =  CharacterDB(
                                    name = character?.name,
                                    level = character?.level,
                                    vocation = character?.vocation,
                                    world = character?.world,
                                    sex = character?.sex,
                                    title = character?.title,
                                    residence = character?.residence,
                                    marriedTo = character?.marriedTo,
                                    achievementPoints = character?.achievementPoints,
                                    accountStatus = character?.accountStatus,
                                    comment = character?.comment,
                                    deaths = body?.character?.deaths?.first()?.reason,
                                    guild = character?.guild?.name ,
                                    lastChange = character?.lastLogin
                                )

                        }
                    }

                }

            }
        } else {
            Log.i("entrou","entrou else")

            throw Exception("Erro: ${response.code()} ${response.message()}")
        }
        return retorno
    }
    suspend fun get():List<CharacterDB>? {
        Log.i("entrou","entrou get")
        return dao.getAll()
    }
}
