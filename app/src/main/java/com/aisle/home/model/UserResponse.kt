package com.aisle.home.model


import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("invites")
                        val invites: Invites,
                        @SerializedName("likes")
                        val likes: Likes)


data class Invites(@SerializedName("pending_invitations_count")
                   var pendingInvitationsCount: Int = 0,
                   @SerializedName("totalPages")
                   var totalPages: Int = 0,
                    @SerializedName("profiles")
                    var profileItem : List<ProfileItem>)


data class ProfilesItem(@SerializedName("avatar")
                        val avatar: String = "",
                        @SerializedName("first_name")
                        val firstName: String = "")


data class Likes(@SerializedName("likes_received_count")
                 val likesReceivedCount: Int = 0,
                 @SerializedName("profiles")
                 val profiles: List<ProfilesItem>?,
                 @SerializedName("can_see_profile")
                 val canSeeProfile: Boolean = false)


