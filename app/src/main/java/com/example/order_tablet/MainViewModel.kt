package com.example.order_tablet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // 네비게이션 메뉴 아이템 선택 상태를 나타내는 LiveData
    private val _selectedNavItem = MutableLiveData<Int>()
    val selectedNavItem: LiveData<Int>
        get() = _selectedNavItem

    init {
        // 초기 선택 상태 설정 (예: 첫 번째 아이템)
        _selectedNavItem.value = R.id.nav_item1
    }

    // 네비게이션 메뉴 아이템을 선택할 때 호출되는 함수
    fun onNavigationItemSelected(itemId: Int) {
        _selectedNavItem.value = itemId
        // 선택한 아이템에 따른 추가적인 동작을 수행할 수 있음
    }
}