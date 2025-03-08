package com.example.mangaapp.Utilities

object Constants {
    val GENRES = mapOf(
        "Action" to "391b0423-d847-456f-aff0-8b0cfc03066b",
        "Adventure" to "87cc87cd-a395-47af-b27a-93258283bbc6",
        "Comedy" to "b13b2a48-c720-44a9-9c77-39c9979373fb",
        "Drama" to "b9f0af2a-f251-4307-b3b1-2a712e480166",
        "Fantasy" to "cdc58593-87dd-415e-bbc0-2ec27bf404cc",
        "Horror" to "cdad7e68-1419-41dd-bdce-27753074a640",
        "Mystery" to "ee968100-4191-4968-93d3-f82d72be7e46",
        "Romance" to "423e2eae-a7a2-4a8b-ac03-a8351462d71d",
        "Sci-Fi" to "256c8bd9-4904-4360-bf4f-508a76d67183",
        "Slice of Life" to "36fd93ea-e8b8-445e-b836-358f8936f1ce",
        "Sports" to "81c836c9-914a-4eca-981a-560dad663e73",
        "Supernatural" to "320831a8-4026-470b-94f6-9aabccc34cda",
        "Adult" to "5920b825-4181-4a17-beeb-9918b0ff7a30",
    )

    private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.com".toRegex()
    fun isValidEmail(email:String ) =EMAIL_REGEX.matches(email)

}