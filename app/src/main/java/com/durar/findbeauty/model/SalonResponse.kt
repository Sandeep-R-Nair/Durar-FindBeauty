package com.durar.findbeauty.model

import com.google.gson.annotations.SerializedName

data class SalonResponse(
    val status: String,
    val data: SalonData,
    val message: String
)

data class SalonData(
    @SerializedName("ObjSalonDetails")
    val salonDetails: List<SalonDetails>,
    @SerializedName("ObjService")
    val services: List<Service>,
    @SerializedName("ObjEmployee")
    val employees: List<Employee>,
    @SerializedName("ObjSalonServiceGroup")
    val serviceGroups: List<SalonServiceGroup>,
    @SerializedName("ObjSalonPackageServices")
    val salonPackageServices: List<SalonPackageService>,
    @SerializedName("ObjSalonWeblink")
    val salonWeblink: List<SalonWeblink>,
    @SerializedName("ObjSalonProfileImage")
    val salonProfileImage: List<Any>, // Adjust the type based on actual data
    @SerializedName("ObjSalonRatingDetails")
    val salonRatingDetails: List<SalonRatingDetail>,
    @SerializedName("ObjSalonRatingSummary")
    val salonRatingSummary: List<SalonRatingSummary>,
    @SerializedName("ObjSalonWorkingHours")
    val salonWorkingHours: List<SalonWorkingHour>,
    @SerializedName("ObjSalonDeal")
    val salonDeals: List<SalonDeal>
)

data class SalonDetails(
    @SerializedName("Salon_Id")
    val salonId: Int,
    @SerializedName("Salon_Name")
    val salonName: String,
    @SerializedName("Salon_Logo")
    val salonLogo: String,
    @SerializedName("Salon_Banner")
    val salonBanner: String,
    @SerializedName("Salon_Description")
    val salonDescription: String,
    @SerializedName("Salon_IsbookingEnabled")
    val isBookingEnabled: Boolean,
    @SerializedName("Salon_IsHomeServiceEnabled")
    val isHomeServiceEnabled: Boolean,
    @SerializedName("Salon_HomeService")
    val isHomeService: Boolean,
    @SerializedName("Salon_IsComplete")
    val isComplete: Boolean,
    @SerializedName("Salon_RatingSummary")
    val ratingSummary: Double,
    @SerializedName("Salon_RatingCount")
    val ratingCount: String,
    @SerializedName("Area_Name")
    val areaName: String,
    @SerializedName("City")
    val city: String,
    @SerializedName("Mobile")
    val mobile: String,
    @SerializedName("Email")
    val email: String,
    @SerializedName("Website")
    val website: String,
    @SerializedName("Latitude")
    val latitude: String,
    @SerializedName("Longitude")
    val longitude: String,
    @SerializedName("MO_OfficeNumber")
    val officeNumber: String,
    @SerializedName("MO_Building")
    val building: String,
    @SerializedName("MO_Street")
    val street: String,
    @SerializedName("MO_Neighbour")
    val neighbour: String,
    val homeservicecount: Int
)

data class Service(
    @SerializedName("Service_Id")
    val serviceId: Int,
    @SerializedName("Service_Name")
    val serviceName: String
)

data class Employee(
    @SerializedName("Emp_Id")
    val empId: Int,
    @SerializedName("Emp_Name")
    val empName: String,
    @SerializedName("Emp_EmployeeProfileImg")
    val empEmployeeProfileImg: String,
    @SerializedName("Emp_Designation")
    val empDesignation: String,
    @SerializedName("Emp_RatingSummary")
    val empRatingSummary: Double,
    @SerializedName("Emp_RatingCount")
    val empRatingCount: Int,
    @SerializedName("Emp_Gender")
    val empGender: String
)

data class SalonServiceGroup(
    @SerializedName("ServiceGroup_Id")
    val serviceGroupId: Int,
    @SerializedName("ServiceGroup_Name")
    val serviceGroupName: String,
    @SerializedName("ServiceGroup_Icon")
    val serviceGroupIcon: String,
    @SerializedName("Service_Name")
    val serviceNames: String
)

data class SalonPackageService(
    @SerializedName("Package_Id")
    val packageId: Int,
    @SerializedName("Package_Name")
    val packageName: String,
    @SerializedName("Service_Name")
    val serviceNames: String,
    @SerializedName("Package_Price")
    val packagePrice: Double,
    @SerializedName("Package_FinalPrice")
    val packageFinalPrice: Double
)

data class SalonWeblink(

    @SerializedName("Weblink_Id")
    val weblinkId: Int,
    @SerializedName("Salon_Id")
    val salonId: Int,
    @SerializedName("Weblink_FaceBook")
    val weblinkFaceBook: String,
    @SerializedName("Weblink_LinkedIn")
    val weblinkLinkedIn: String,
    @SerializedName("Weblink_Twitter")
    val weblinkTwitter: String,
    @SerializedName("Weblink_Gplus")
    val weblinkGplus: String,
    @SerializedName("Weblink_Instagram")
    val weblinkInstagram: String,
    @SerializedName("Weblink_Youtube")
    val weblinkYoutube: String,
    @SerializedName("Weblink_YoutubeChannel")
    val weblinkYoutubeChannel: String,
    @SerializedName("Weblink_Tiktok")
    val weblinkTiktok: String
)

data class SalonRatingDetail(

    @SerializedName("SalonRating_Id")
    val salonRatingId: Int,
    @SerializedName("Salon_Id")
    val salonId: Int,
    @SerializedName("User_Id")
    val userId: Int,
    @SerializedName("User_Name")
    val userName: String,
    @SerializedName("SalonRating_Value")
    val salonRatingValue: Double,
    @SerializedName("SalonRating_Comment")
    val salonRatingComment: String,
    @SerializedName("SalonRating_Date")
    val salonRatingDate: String,
    @SerializedName("SalonRating_IsVerified")
    val salonRatingIsVerified: Int
)

data class SalonRatingSummary(
    @SerializedName("Rating_Star")
    val ratingStar: Int,
    @SerializedName("TotalRating_Star")
    val totalRatingStar: Int,
    @SerializedName("Percentage")
    val percentage: Int,
    @SerializedName("TotRating_Value")
    val totRatingValue: Int,
    @SerializedName("Rating_Id")
    val ratingId: Int
)

data class SalonWorkingHour(
    @SerializedName("SalonWorking_Id")
    val salonWorkingId: Int,
    @SerializedName("Salon_Id")
    val salonId: Int,
    @SerializedName("SalonWorking_Day")
    val salonWorkingDay: Int,
    @SerializedName("SalonWorking_TimeFrom")
    val salonWorkingTimeFrom: String,
    @SerializedName("SalonWorking_TimeTo")
    val salonWorkingTimeTo: String,
    @SerializedName("IsOffday")
    val isOffday: Boolean
)

data class SalonDeal(
    @SerializedName("Deal_Id")
    val dealId: Int,
    @SerializedName("Salon_Id")
    val salonId: Int,
    @SerializedName("Service_Id")
    val serviceId: Int,
    @SerializedName("Deal_Title")
    val dealTitle: String,
    @SerializedName("Deal_Title_Ar")
    val dealTitleAr: String,
    @SerializedName("Deal_Condition")
    val dealCondition: String,
    @SerializedName("Service_Price")
    val servicePrice: Double,
    @SerializedName("Service_Discount")
    val serviceDiscount: Double,
    @SerializedName("Service_FinalPrice")
    val serviceFinalPrice: Double,
    @SerializedName("Deal_Banner")
    val dealBanner: String,
    @SerializedName("Deal_AvailPostingDate")
    val dealAvailPostingDate: String,
    @SerializedName("Deal_AvailFromDate")
    val dealAvailFromDate: String,
    @SerializedName("Deal_Todate")
    val dealTodate: String,
    @SerializedName("Deal_TimeFrom")
    val dealTimeFrom: String,
    @SerializedName("Deal_TimeTo")
    val dealTimeTo: String,
    @SerializedName("Deal_Status")
    val dealStatus: Boolean,
    @SerializedName("Deal_OnlineFromDate")
    val dealOnlineFromDate: String,
    @SerializedName("Deal_OnlineToDate")
    val dealOnlineToDate: String
)

