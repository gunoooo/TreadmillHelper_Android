package kr.hs.dgsw.treadmill_helper.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.domain.base.UseCase

open class BaseViewModelFactory constructor(
    vararg params: UseCase
) : ViewModelProvider.Factory {
    private val useCases = params

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            *useCases.map { it.javaClass }.toTypedArray()
        ).newInstance(
            *useCases.map { it }.toTypedArray()
        )
    }
}