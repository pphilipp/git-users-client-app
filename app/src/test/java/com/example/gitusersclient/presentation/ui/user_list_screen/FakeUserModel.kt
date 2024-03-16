package com.example.gitusersclient.presentation.ui.user_list_screen

import com.example.gitusersclient.domain.model.UserBM
import java.util.UUID

val mockUser = UserBM(
    id = UUID.randomUUID().variant(),
    nodeId = "nodeId",
    type = "type",
    avatarUrl = "avatarUrl",
    eventsUrl = "eventsUrl",
    followersUrl = "followersUrl",
    followingUrl = "followingUrl",
    gistsUrl = "gistsUrl",
    gravatarUd = "gravatarUd",
    htmlUrl = "htmlUrl",
    login = "login",
    organizationsUrl = "organizationsUrl",
    receivedEventsUrl = "receivedEventsUrl",
    reposUrl = "reposUrl",
    siteAdmin = true,
    starredUrl = "starredUrl",
    subscriptionsUrl = "subscriptionsUrl",
    url = "url",
)