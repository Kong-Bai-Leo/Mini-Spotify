package com.laioffer.spotify.ui.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laioffer.spotify.datamodel.Section
import com.laioffer.spotify.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel(){
    //data: List <Section>
    //observable container: StateFlow
    private var _uiState = MutableStateFlow(HomeUiState(feed = listOf(), isLoading = true))
    var uiState: StateFlow<HomeUiState> = _uiState


    //handle event: fetch data from repository
    fun fetchHomeScreen(){
        viewModelScope.launch {
            val sections = homeRepository.getHomeSection()
            _uiState.value = HomeUiState(feed = sections, isLoading = false)
        }
    }


    }

data class HomeUiState(
    val feed: List<Section>,
    val isLoading: Boolean
)
