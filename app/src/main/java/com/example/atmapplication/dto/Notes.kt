package com.example.atmapplication.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    var amount: String,
    var hundrad: String,
    var twoHundrad:String,
    var fivehundrad:String,
    var twoThousand:String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)