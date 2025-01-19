package tmdb.arch.movieapp.domain.remote

import okhttp3.Interceptor
import okhttp3.Response
import tmdb.arch.movieapp.BuildConfig

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        return chain.proceed(original.newBuilder().url(url).build())
    }
}

