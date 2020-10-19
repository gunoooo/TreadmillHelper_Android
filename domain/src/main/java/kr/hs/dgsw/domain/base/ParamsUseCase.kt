package kr.hs.dgsw.domain.base

abstract class ParamsUseCase<in Params, out T> : UseCase() {
    abstract fun buildUseCaseObservable(params: Params): T
}