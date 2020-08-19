package by.grodno.vasili.domain.interactor

import by.grodno.vasili.domain.model.User
import by.grodno.vasili.domain.repository.UserRepository
import kotlinx.coroutines.async

/**
 * Use case for retrieving one [User] from repository
 */
class GetUserUseCase(
        private val repository: UserRepository
) : UseCase<GetUserUseCase.Params, User>() {

    /**
     * Parameters for request one user from repository
     */
    data class Params(val login: String)

    override suspend fun action(params: Params): User {
        val login = params.login
        val organizations = async { repository.getOrganizations(login) }
        val user = async { repository.getOne(login) }
        return user.await().copy(organizations = organizations.await())
    }
}
