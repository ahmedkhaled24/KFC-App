package com.example.aaaaa

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class database : RealmObject() {
    @PrimaryKey
    var id : Int?= null

    var postId : Int?= null
    var name : String?= null
    var email : String?= null
    var body : String?= null

}