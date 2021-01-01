package com.bekci.tasteofcinema.util

import com.bekci.tasteofcinema.ParserTask
import com.bekci.tasteofcinema.`interface`.ParserInterface
import com.bekci.tasteofcinema.`interface`.TaskInterface
import com.bekci.tasteofcinema.model.ListMainInfo
import org.jsoup.Jsoup
import java.lang.Error

object WebSiteParser {

    private const val MAIN_PAGE_URL = "https://www.tasteofcinema.com/"

    fun parseLists(pageNum: Int, parserInterface: ParserInterface){
        val url = MAIN_PAGE_URL + "page/$pageNum/"

        val parserTask = ParserTask()
        parserTask.taskInterface = object : TaskInterface{
            override fun onTaskSuccess(document: String) {
                val doc = Jsoup.parse(document)
                val listContainers = doc.select("div.post-inner-content")
                val listListMainInfo = mutableListOf<ListMainInfo>()
                for (lc in listContainers){
                    val listTitle = lc.select("h2")[0].select("a").text()
                    val listMeta = lc.select("div.entry-meta")[0].select("span.posted-on")[0].select("a")[0].select("time")[0].text()
                    val listDetail = lc.select("div.entry-content")[0].select("p").text()
                    val listImgUrl = lc.select("img.wp-post-image")[0].attr("src")
                    val listInfo = ListMainInfo(listTitle, listDetail, listImgUrl, listMeta)
                    listListMainInfo.add(listInfo)
                }
                parserInterface.onListsParsed(listListMainInfo)
            }
            override fun onTaskFailed(error: Error) {
                parserInterface.onListsParsed(listOf())
            }
        }
        parserTask.execute(url)
    }

    fun parseListContent(listMainInfo: ListMainInfo){
        val url = createListURL(listMainInfo)

        val parserTask = ParserTask()
        parserTask.taskInterface = object : TaskInterface{
            override fun onTaskSuccess(document: String) {
                val doc = Jsoup.parse(document)
                val listContainers = doc.select("div.post-inner-content")
                val listListMainInfo = mutableListOf<ListMainInfo>()
                for (lc in listContainers){
                    val listTitle = lc.select("h2")[0].select("a").text()
                    val listMeta = lc.select("div.entry-meta")[0].select("span.posted-on")[0].select("a")[0].select("time")[0].text()
                    val listDetail = lc.select("div.entry-content")[0].select("p").text()
                    val listImgUrl = lc.select("img.wp-post-image")[0].attr("src")
                    val listInfo = ListMainInfo(listTitle, listDetail, listImgUrl, listMeta)
                    listListMainInfo.add(listInfo)
                }
                //parserInterface.onListsParsed(listListMainInfo)
            }
            override fun onTaskFailed(error: Error) {
                //parserInterface.onListsParsed(listOf())
            }
        }
        parserTask.execute(url)
    }

    private fun createListURL(listMainInfo : ListMainInfo) : String {
        var title= listMainInfo.title?.toLowerCase()
        title = title?.replace(" ", "-")
        var year = listMainInfo.date?.split(",")?.last().toString()
        year = year.replace(" ", "")
        return "$MAIN_PAGE_URL$year/$title/"

    }
}