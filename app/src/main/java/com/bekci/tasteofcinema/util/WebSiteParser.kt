package com.bekci.tasteofcinema.util

import com.bekci.tasteofcinema.ParserTask
import com.bekci.tasteofcinema.contracts.DialogContract
import com.bekci.tasteofcinema.contracts.ParserInterface
import com.bekci.tasteofcinema.contracts.TaskInterface
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.ListContent
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
                    val listURL = lc.select("h2")[0].select("a").attr("href")
                    val listInfo = ListMainInfo(listTitle, listDetail, listImgUrl, listMeta, listURL)
                    listListMainInfo.add(listInfo)
                }
                parserInterface.onListsParsed(listListMainInfo)
            }
            override fun onTaskFailed(error: String) {
                parserInterface.onRequestFailed()
            }
        }
        try {
            parserTask.execute(url)
        }
        catch (err: Exception){
            err.printStackTrace()
        }
    }

    fun parseListContent(listMainInfo: ListMainInfo, parserInterface: ParserInterface){
        val url = listMainInfo.url
        val listContent = ListContent()
        listContent.date = listMainInfo.date
        listContent.title = listMainInfo.title
        listContent.imgURL = listMainInfo.imgURL
        listContent.listURL = url
        val parserTask = ParserTask()
        var listElementStart = false
        var newFilm = false
        var currentFilm = Film()
        parserTask.taskInterface = object : TaskInterface{
            override fun onTaskSuccess(document: String) {
                val doc = Jsoup.parse(document)
                val siteContent = doc.select("div.entry-content")[0]
                val pList = siteContent.select("p")
                for (pEl in pList){
                    val imgChildren = pEl.select("img")
                    val spanChildren = pEl.select("span")
                    // List film has not started yet. Fetching list main content
                    if(!listElementStart){
                        // There is an img element in <p>. Obtain image url
                        when {
                            imgChildren.size > 0 -> {
                                listContent.imgURL = imgChildren[0].attr("src")
                            }
                            // There is an span element in <p>. Obtain text
                            spanChildren.size > 0 -> {
                                listContent.detail += spanChildren[0].text()
                            }
                            // End of the main content
                            else -> {
                                listElementStart = true
                                newFilm = true
                            }
                        }
                    }
                    // List films started. Fetch films
                    else{
                        // First element is the title of the film
                        if (newFilm && spanChildren.size > 0){
                            currentFilm = Film()
                            currentFilm.title = spanChildren[0].text()
                            newFilm = false
                        }
                        // Detail of the film
                        else if (spanChildren.size > 0){
                            currentFilm.detail += spanChildren[0].text()
                        }
                        // Img url of the film
                        else if(imgChildren.size > 0){
                            currentFilm.imgURL = imgChildren[0].attr("src")
                        }
                        // End of current film element
                        else{
                            listContent.films.add(currentFilm)
                            newFilm = true
                        }
                    }
                }
                // Finally add the last film
                listContent.films.add(currentFilm)
                // Evaluate number of pages
                val pageLinks = if(doc.select("div.page-links").isNotEmpty())
                    doc.select("div.page-links")[0].select("a").size
                else
                    doc.select("a.post-page-numbers").size
                // 1 for the current page and others will be the links of the pages
                listContent.numPages = pageLinks + 1
                parserInterface.onListContentParsed(listContent)
            }
            override fun onTaskFailed(error: String) {
                parserInterface.onRequestFailed()
            }
        }
        parserTask.execute(url)
    }

    fun parseListPage(listMainInfo: ListMainInfo, pageNum: Int, parserInterface: ParserInterface) {
        val url = createListURL(listMainInfo, pageNum)
        val films = mutableListOf<Film>()
        val parserTask = ParserTask()
        var newFilm = true
        var currentFilm = Film()
        parserTask.taskInterface = object : TaskInterface{
            override fun onTaskSuccess(document: String) {
                val doc = Jsoup.parse(document)
                val siteContent = doc.select("div.entry-content")[0]
                val pList = siteContent.select("p")
                for (pEl in pList){
                    val imgChildren = pEl.select("img")
                    val spanChildren = pEl.select("span")
                    // First element is the title of the film
                    if (newFilm && spanChildren.size > 0){
                        currentFilm = Film()
                        currentFilm.title = spanChildren[0].text()
                        newFilm = false
                    }
                    // Detail of the film
                    else if (spanChildren.size > 0){
                        currentFilm.detail += spanChildren[0].text()
                    }
                    // Img url of the film
                    else if(imgChildren.size > 0){
                        currentFilm.imgURL = imgChildren[0].attr("src")
                    }
                    // End of current film element
                    else{
                        films.add(currentFilm)
                        newFilm = true
                    }
                }
                // Finally add the last film
                films.add(currentFilm)
                parserInterface.onListPageParsed(films)
            }
            override fun onTaskFailed(error: String) {
                parserInterface.onRequestFailed()
            }
        }
        parserTask.execute(url)
    }
    private fun createListURL(listMainInfo : ListMainInfo, pageNum: Int = 1) : String {
        var title= listMainInfo.title?.toLowerCase()
        title = title?.replace(" ", "-")
        var year = listMainInfo.date?.split(",")?.last().toString()
        year = year.replace(" ", "")
        return "$MAIN_PAGE_URL$year/$title/$pageNum/"
    }
}