package com.example.gitusersclient.domain.model

import com.example.core.abstraction.domain.IBusinessModel

data class UserDetailsBM(
    val id: Int,
    val avatarUrl: String,
    val bio: String,
    val blog: String,
    val company: String,
    val createdAt: String,
    val email: String,
    val eventsUrl: String,
    val followers: Int,
    val followersUrl: String,
    val following: Int,
    val followingUrl: String,
    val gistsUrl: String,
    val gravatarUd: String,
    val hireable: Boolean,
    val htmlUrl: String,
    val location: String,
    val login: String,
    val name: String,
    val nodeId: String,
    val organizationsUrl: String,
    val publicGists: Int,
    val publicRepos: Int,
    val receivedEventsUrl: String,
    val reposUrl: String,
    val siteAdmin: Boolean,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val type: String,
    val updatedAt: String,
    val url: String,
    val twitterUsername: String
) : IBusinessModel