package com.conacon.testcurse.data.remote.responses

data class ImageResponse(  val hits: List<ImageResult>,
                           val total: Int,
                           val totalHits: Int)
