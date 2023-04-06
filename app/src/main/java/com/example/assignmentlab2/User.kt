package com.example.assignmentlab2

data class User(val firstName: String, val lastName: String, val userName: String, val password: String) : java.io.Serializable {
    val fullName: String
    get() {
        return "$firstName $lastName"
    }
}
