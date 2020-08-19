package by.grodno.vasili.domain.interactor

import by.grodno.vasili.domain.model.User
import by.grodno.vasili.domain.repository.UserRepository

/**
 * Use case retrieving users list from repository
 */
class GetUsersUseCase(
        private val repository: UserRepository
) : UseCase<GetUsersUseCase.Params, List<User>>() {

    /**
     * Parameters for request users list from repository
     */
    data class Params(val since: Int)

    override suspend fun action(params: Params): List<User> = repository.getAll(params.since)
}
