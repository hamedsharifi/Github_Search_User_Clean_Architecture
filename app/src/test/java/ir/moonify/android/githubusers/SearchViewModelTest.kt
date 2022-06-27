package ir.moonify.android.githubusers

import ir.moonify.android.githubusers.data.UsersRepository
import ir.moonify.android.githubusers.framework.UseCases
import ir.moonify.android.githubusers.framework.network.Services
import ir.moonify.android.githubusers.usecases.GetUserDetailUseCase
import ir.moonify.android.githubusers.usecases.SearchUserUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi

class SearchViewModelTest {


    private val mockWebServer = MockWebServer()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Services::class.java)
    private val usersRepository = UsersRepository(TestRemoteUserDataSource(api))
    val useCases: UseCases = UseCases(SearchUserUseCase(usersRepository), GetUserDetailUseCase(usersRepository))

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch movies correctly given 200 response`() {
        println(Paths.get("").toAbsolutePath().toString())
        mockWebServer.enqueue(
            MockResponse().setBody(
                File(
                    Paths.get("").toAbsolutePath()
                        .toString() + "/src/test/java/ir/moonify/android/githubusers/user_list.json"
                ).readText(Charsets.UTF_8)
            )
        )
        runBlocking {
            val result = useCases.searchUserUseCase("hamedsharifi")
            Assert.assertNotNull(result)
        }
    }
}