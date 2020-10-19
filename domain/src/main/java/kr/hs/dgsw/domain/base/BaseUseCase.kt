package kr.hs.dgsw.domain.base

abstract class BaseUseCase<out T> : UseCase() {
    abstract fun buildUseCaseObservable(): T
}