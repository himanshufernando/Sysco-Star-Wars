package com.example.syscostarwars.ui.fragment.planets

import NETWORK_PAGE_SIZE
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.syscostarwars.services.network.api.APIInterface
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import com.example.syscostarwars.data.Result
private const val PAGE_INDEX = 1


class PlanetsPagingSource (private val service: APIInterface) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: PAGE_INDEX


        return try {

            var response =  service.getPlanets(position)
            var result = response.results
            val nextKey = if (response.next==null) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                data = result,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }catch (exception: NullPointerException) {
            LoadResult.Error(exception)
        }catch (exception : UnknownHostException) {
            LoadResult.Error(exception)
        }catch (exception : SocketTimeoutException) {
            LoadResult.Error(exception)
        }catch (exception : Exception) {
            LoadResult.Error(exception)
        }

    }
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}