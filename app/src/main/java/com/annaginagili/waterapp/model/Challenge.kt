package com.annaginagili.waterapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration

@Entity
data class Challenge(@PrimaryKey(autoGenerate = true) val id: Int,
                     @ColumnInfo(name = "text") val text: String,
                     @ColumnInfo(name = "progress") var progress: Int,
                     @ColumnInfo(name = "point") val point: Int,
                     @ColumnInfo(name = "duration") val duration: Int,
                     @ColumnInfo(name = "checks") var checks: String)