package com.example.gitusersclient.domain

import com.example.core.abstraction.domain.IRemoteToBusinessMapper
import com.example.data_network.model.responce.UserRemoteModel
import com.example.gitusersclient.domain.model.UserBusinessModel

class UserItemMapperImpl : IRemoteToBusinessMapper<UserRemoteModel, UserBusinessModel> {
    override fun remoteToBusiness(
        model: UserRemoteModel
    ): UserBusinessModel = UserBusinessModel(
        id = model.id ?: -1,
        nodeId = model.nodeId.orEmpty(),
        type = model.type.orEmpty(),
        avatarUrl = model.avatarUrl.orEmpty(),
        eventsUrl = model.eventsUrl.orEmpty(),
        followersUrl = model.followersUrl.orEmpty(),
        followingUrl = model.followingUrl.orEmpty(),
        gistsUrl = model.gistsUrl.orEmpty(),
        gravatarUd = model.gravatarUd.orEmpty(),
        htmlUrl = model.htmlUrl.orEmpty(),
        login = model.login.orEmpty(),
        organizationsUrl = model.organizationsUrl.orEmpty(),
        receivedEventsUrl = model.receivedEventsUrl.orEmpty(),
        reposUrl = model.reposUrl.orEmpty(),
        siteAdmin = model.siteAdmin ?: false,
        starredUrl = model.starredUrl.orEmpty(),
        subscriptionsUrl = model.subscriptionsUrl.orEmpty(),
        url = model.url.orEmpty(),
    )
}