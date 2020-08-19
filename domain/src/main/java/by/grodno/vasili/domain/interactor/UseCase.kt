package by.grodno.vasili.domain.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

/**
 * Base use case for application needs. Execute some action with [Params] and return the result with [Type].
 *
 */
abstract class UseCase<Params, Type> : CoroutineScope by CoroutineScope(Dispatchers.IO) {

    /**
     * Action for retrieving data.
     */
    protected abstract suspend fun action(params: Params): Type

    /**
     * Run action.
     */
    suspend fun execute(params: Params): Type = action(params)

    /**
     * Cancel use case execution.
     */
    fun dispose() {
        cancel()
    }
}
