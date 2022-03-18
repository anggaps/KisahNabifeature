package com.anggaps.kisahnabi.utils

import android.content.Context
import com.anggaps.kisahnabi.data.source.remote.response.StoryListResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception


class JsonHelper(private val context :Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadStory():List<StoryListResponse>{
        val list = ArrayList<StoryListResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("Story.json").toString())
            val listArray = responseObject.getJSONArray("story")

            for (i in 0 until listArray.length()) {
                val story = listArray.getJSONObject(i)

                val id =story.getString("id")
                val titleName =story.getString("TitleName")
                val usia = story.getString("Usia")
                val tahunKelahiran = story.getString("TahunKelahiran")
                val tempatKelahiran = story.getString("TempatKelahiran")
                val desc = story.getString("desc")
                val imagePath = story.getString("imagePath")


                val storyResponse = StoryListResponse(id, titleName, usia, tahunKelahiran, tempatKelahiran, desc, imagePath)

                list.add(storyResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadDetailStory(id:String):List<StoryListResponse> {
        val detail = ArrayList<StoryListResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("Story.json").toString())
            val listArray = responseObject.getJSONArray("story")

            for (i in 0 until listArray.length()) {
                val story = listArray.getJSONObject(i)

                val id = story.getString("id")
                val titleName = story.getString("TitleName")
                val usia = story.getString("Usia")
                val tahunKelahiran = story.getString("TahunKelahiran")
                val tempatKelahiran = story.getString("TempatKelahiran")
                val desc = story.getString("desc")
                val imagePath = story.getString("imagePath")


                val storyResponse =
                    StoryListResponse(id, titleName, usia, tahunKelahiran, tempatKelahiran, desc, imagePath)

                detail.add(storyResponse)
            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
        return detail

    }





}