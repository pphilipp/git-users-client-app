package com.example.gitusersclient.domain.model

import com.example.core.abstraction.domain.IBusinessModel

data class UserBM(
    val id: Int,
    val nodeId: String,
    val type: String,
    val avatarUrl: String,
    val eventsUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val gistsUrl: String,
    val gravatarUd: String,
    val htmlUrl: String,
    val login: String,
    val organizationsUrl: String,
    val receivedEventsUrl: String,
    val reposUrl: String,
    val siteAdmin: Boolean,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val url: String
): IBusinessModel