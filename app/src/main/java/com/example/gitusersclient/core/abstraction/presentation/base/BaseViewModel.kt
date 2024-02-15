package com.example.gitusersclient.core.abstraction.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : ViewEvent, S : ViewState> : ViewModel() {

    private val _viewState: MutableStateFlow<S> by lazy { MutableStateFlow(getInitialViewState()) }
    val viewState: StateFlow<S> by lazy { _viewState }

    private val _events = MutableSharedFlow<E>()

    init {
        subscribeEvents()
    }

    protected fun executeInViewModelScope(block: suspend () -> Unit) {
        viewModelScope.launch() {
            block.invoke()
        }
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        executeInViewModelScope {
            _events.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * Handle each event
     */
    abstract fun handleEvent(event: E)

    /**
     * Initial state of state flow for the ViewState
     */
    abstract fun getInitialViewState(): S

    /**
     * update the value of the mutable ViewState
     */
    protected fun setState(newState: S.() -> S) {
        _viewState.update { it.newState() }
    }

}