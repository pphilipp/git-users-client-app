package com.example.gitusersclient.domain

import com.example.core.abstraction.domain.IRemoteToBusinessMapper
import com.example.data_network.model.responce.UserDetailsRM
import com.example.gitusersclient.domain.model.UserDetailsBM
import java.util.UUID

class UserDetailsMapperImpl : IRemoteToBusinessMapper<UserDetailsRM, UserDetailsBM> {
    override fun remoteToBusiness(model: UserDetailsRM): UserDetailsBM = UserDetailsBM(
        id = model.id ?: UUID.randomUUID().variant(),
        avatarUrl = model.avatarUrl.orEmpty(),
        bio = model.bio.orEmpty(),
        blog = model.blog.orEmpty(),
        company = model.company.orEmpty(),
        createdAt = model.createdAt.orEmpty(),
        email = model.email.orEmpty(),
        eventsUrl = model.eventsUrl.orEmpty(),
        followers = model.followers ?: 0,
        followersUrl = model.followersUrl.orEmpty(),
        following = model.following ?: 0,
        followingUrl = model.followingUrl.orEmpty(),
        gistsUrl = model.gistsUrl.orEmpty(),
        gravatarUd = model.gravatarUd.orEmpty(),
        hireable = model.hireable ?: false,
        htmlUrl = model.htmlUrl.orEmpty(),
        location = model.location.orEmpty(),
        login = model.login.orEmpty(),
        name = model.name.orEmpty(),
        nodeId = model.nodeId.orEmpty(),
        organizationsUrl = model.organizationsUrl.orEmpty(),
        publicGists = model.publicGists ?: 0,
        publicRepos = model.publicRepos ?: 0,
        receivedEventsUrl = model.receivedEventsUrl.orEmpty(),
        reposUrl = model.reposUrl.orEmpty(),
        siteAdmin = model.siteAdmin ?: false,
        starredUrl = model.starredUrl.orEmpty(),
        subscriptionsUrl = model.subscriptionsUrl.orEmpty(),
        type = model.type.orEmpty(),
        updatedAt = model.updatedAt.orEmpty(),
        url = model.url.orEmpty(),
        twitterUsername = model.twitterUsername.orEmpty()
    )
}