package com.seiko.tv.anime.data.repository

import com.seiko.tv.anime.data.model.anime.Anime
import com.seiko.tv.anime.data.model.anime.AnimeGroup
import com.seiko.tv.anime.data.service.SakuraService
import com.seiko.tv.anime.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AnimeHomeRepository @Inject constructor(
  private val service: SakuraService,
  @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
  fun getAnimeList(url: String): Flow<List<AnimeGroup>> {
    return flow {
      val response = service.getHomeResponse(url)
      emit(
        response.groups.mapIndexed { index, item ->
          AnimeGroup(
            response.titles[index],
            item.animes.map {
              Anime(
                title = it.title,
                cover = it.cover,
                actionUrl = it.actionUrl
              )
            }
          )
        }
      )
    }.flowOn(ioDispatcher)
  }
}