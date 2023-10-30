package net.azarquiel.chistes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CatsResult(
    @SerializedName("categorias")
    var categories: List<Category>
): Serializable

data class Category(
    var id: Int,
    @SerializedName("nombre")
    var name: String
): Serializable

data class JokesResult(
    @SerializedName("chistes")
    var jokes: List<Joke>
): Serializable

data class Joke(
    var id: Int,
    @SerializedName("idcategoria")
    var catId: Int,
    @SerializedName("contenido")
    var content: String
): Serializable