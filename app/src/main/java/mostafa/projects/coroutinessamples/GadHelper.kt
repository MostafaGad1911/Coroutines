package mostafa.projects.coroutinessamples

import mostafa.projects.coroutinessamples.data.apis.ApiInterfaces
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GadHelper {

    companion object{
        var BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun GetClient():Retrofit{
            var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }

        fun GetServices():ApiInterfaces{
            var apiInterfaces = GetClient().create(ApiInterfaces::class.java)
            return apiInterfaces
        }
    }
}