package com.aisle.home.model


import com.google.gson.annotations.SerializedName

data class DrinkingV(@SerializedName("name_alias")
                     val nameAlias: String = "",
                     @SerializedName("name")
                     val name: String = "",
                     @SerializedName("id")
                     val id: Int = 0)


data class ProfileItem(@SerializedName("latest_poll")
                       var latestPoll: String = "",
                       @SerializedName("preferences")
                       var preferences: List<PreferencesItem>?,
                       @SerializedName("lng")
                       var lng: String = "",
                       @SerializedName("last_seen")
                       var lastSeen: String = "",
                       @SerializedName("poll_info")
                       var pollInfo: String = "",
                       @SerializedName("work")
                       var work: Work,
                       @SerializedName("last_seen_window")
                       var lastSeenWindow: String = "",
                       @SerializedName("has_active_subscription")
                       var hasActiveSubscription: Boolean = false,
                       @SerializedName("verification_status")
                       var verificationStatus: String = "",
                       @SerializedName("photos")
                       var photos: List<PhotosItem>?,
                       @SerializedName("show_concierge_badge")
                       var showConciergeBadge: Boolean = false,
                       @SerializedName("approved_time")
                       var approvedTime: Double = 0.0,
                       @SerializedName("general_information")
                       var generalInformation: GeneralInformation,
                       @SerializedName("profile_data_list")
                       var profileDataList: List<ProfileDataListItem>?,
                       @SerializedName("instagram_images")
                       var instagramImages: String = "",
                       @SerializedName("online_code")
                       var onlineCode: Int = 0,
                       @SerializedName("is_facebook_data_fetched")
                       var isFacebookDataFetched: Boolean = false,
                       @SerializedName("icebreakers")
                       var icebreakers: String = "",
                       @SerializedName("meetup")
                       var meetup: String = "",
                       @SerializedName("lat")
                       var lat: String = "",
                       @SerializedName("story")
                       var story: String = "")


data class HighestQualificationV(@SerializedName("name")
                                 val name: String = "",
                                 @SerializedName("id")
                                 val id: Int = 0,
                                 @SerializedName("preference_only")
                                 val preferenceOnly: Boolean = false)


data class ExperienceV(@SerializedName("name_alias")
                       val nameAlias: String = "",
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("id")
                       val id: Int = 0)


data class PhotosItem(@SerializedName("photo_id")
                      val photoId: Int = 0,
                      @SerializedName("photo")
                      val photo: String = "",
                      @SerializedName("selected")
                      val selected: Boolean = false,
                      @SerializedName("status")
                      val status: String = "")


data class FieldOfStudyV(@SerializedName("name")
                         val name: String = "",
                         @SerializedName("id")
                         val id: Int = 0)


data class Faith(@SerializedName("name")
                 val name: String = "",
                 @SerializedName("id")
                 val id: Int = 0)


data class PreferenceQuestion(@SerializedName("second_choice")
                              val secondChoice: String = "",
                              @SerializedName("first_choice")
                              val firstChoice: String = "")


data class SmokingV(@SerializedName("name_alias")
                    val nameAlias: String = "",
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("id")
                    val id: Int = 0)


data class MaritalStatusV(@SerializedName("name")
                          val name: String = "",
                          @SerializedName("id")
                          val id: Int = 0,
                          @SerializedName("preference_only")
                          val preferenceOnly: Boolean = false)


data class PreferencesItem(@SerializedName("answer")
                           val answer: String = "",
                           @SerializedName("second_choice")
                           val secondChoice: String = "",
                           @SerializedName("answer_id")
                           val answerId: Int = 0,
                           @SerializedName("first_choice")
                           val firstChoice: String = "")


data class MotherTongue(@SerializedName("name")
                        val name: String = "",
                        @SerializedName("id")
                        val id: Int = 0)


data class GeneralInformation(@SerializedName("ref_id")
                              val refId: String = "",
                              @SerializedName("politics")
                              val politics: String = "",
                              @SerializedName("drinking")
                              val drinking: String = "",
                              @SerializedName("gender")
                              val gender: String = "",
                              @SerializedName("date_of_birth")
                              val dateOfBirth: String = "",
                              @SerializedName("smoking_v1")
                              val smokingV: SmokingV,
                              @SerializedName("kid")
                              val kid: String = "",
                              @SerializedName("settle")
                              val settle: String = "",
                              @SerializedName("faith")
                              val faith: Faith,
                              @SerializedName("cast")
                              val cast: String = "",
                              @SerializedName("drinking_v1")
                              val drinkingV: DrinkingV,
                              @SerializedName("marital_status")
                              val maritalStatus: String = "",
                              @SerializedName("sun_sign")
                              val sunSign: String = "",
                              @SerializedName("marital_status_v1")
                              val maritalStatusV: MaritalStatusV,
                              @SerializedName("sun_sign_v1")
                              val sunSignV: SunSignV,
                              @SerializedName("smoking")
                              val smoking: String = "",
                              @SerializedName("date_of_birth_v1")
                              val dateOfBirthV: String = "",
                              @SerializedName("location")
                              val location: Location,
                              @SerializedName("diet")
                              val diet: String = "",
                              @SerializedName("first_name")
                              val firstName: String = "",
                              @SerializedName("pet")
                              val pet: String = "",
                              @SerializedName("age")
                              val age: Int = 0,
                              @SerializedName("mother_tongue")
                              val motherTongue: MotherTongue,
                              @SerializedName("height")
                              val height: Int = 0)


data class ProfileDataListItem(@SerializedName("preferences")
                               val preferences: List<PreferencesItem>?,
                               @SerializedName("question")
                               val question: String = "",
                               @SerializedName("invitation_type")
                               val invitationType: String = "")


data class Work(@SerializedName("experience_v1")
                val experienceV: ExperienceV,
                @SerializedName("industry_v1")
                val industryV: IndustryV,
                @SerializedName("highest_qualification_v1")
                val highestQualificationV: HighestQualificationV,
                @SerializedName("industry")
                val industry: String = "",
                @SerializedName("monthly_income_v1")
                val monthlyIncomeV: String = "",
                @SerializedName("experience")
                val experience: String = "",
                @SerializedName("highest_qualification")
                val highestQualification: String = "",
                @SerializedName("field_of_study")
                val fieldOfStudy: String = "",
                @SerializedName("field_of_study_v1")
                val fieldOfStudyV: FieldOfStudyV)


data class IndustryV(@SerializedName("name")
                     val name: String = "",
                     @SerializedName("id")
                     val id: Int = 0,
                     @SerializedName("preference_only")
                     val preferenceOnly: Boolean = false)


data class SunSignV(@SerializedName("name")
                    val name: String = "",
                    @SerializedName("id")
                    val id: Int = 0)


data class Location(@SerializedName("summary")
                    val summary: String = "",
                    @SerializedName("full")
                    val full: String = "")


