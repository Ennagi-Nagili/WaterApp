package com.annaginagili.waterapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Challenge(@PrimaryKey(autoGenerate = true) val id: Int,
                     @ColumnInfo(name = "text") val text: String)