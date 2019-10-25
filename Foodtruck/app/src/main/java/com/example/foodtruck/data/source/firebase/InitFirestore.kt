package com.example.foodtruck.data.source.firebase

import com.example.foodtruck.data.source.local.model.NewUser
import com.example.foodtruck.data.source.local.model.firebase_models.FoodieAccount
import com.google.firebase.firestore.*
import kotlinx.coroutines.tasks.await

class InitFirestore {

    companion object {
        val instance = InitFirestore()
    }

    private val db = FirebaseFirestore.getInstance()

    fun writeNewUser(newUser: NewUser, userId: String) {

        val userRef = db.collection("users").document(userId)
        db.runBatch { batch ->
            batch.set(userRef, newUser.getFields())
        }
    }

    suspend fun writeNewAccount(
        userId: String,
        ownerFirstName: String? = null,
        ownerLastName: String? = null,
        businessName: String? = null,
        cuisineType: String? = null,
        logo: String? = null
        ) {
        val userRef = db.collection("users")
            .document(userId)
        val userInfo = userRef
            .get().await()
        val accountType = userInfo[FieldPath.of("accountType")] as String
        when (accountType) {
            "vendor" -> {
                val vendorAccountRef =
                    db.collection("account_types")
                        .document("vendor_accounts")
                        .collection(userId)

                db.runBatch { writeBatch ->
                    vendorAccountRef.document("vendor_business_data").set(
                        hashMapOf(
                            "isOpenForBusiness" to false,
                            "lastLocation" to null
                        )
                    )
                    vendorAccountRef.document("vendor_logo").set(
                        hashMapOf(
                            "logoUri" to null
                        )
                    )
                    vendorAccountRef.document("vendor_general_info").set(
                        hashMapOf(
                            "cuisineType" to cuisineType,
                            "businessName" to businessName,
                            "ownersName" to hashMapOf(
                                "firstName" to ownerFirstName,
                                "lastName" to ownerLastName
                            )
                        )
                    )
                }
            }
            "foodie" -> {
                val foodieAccountRef =
                    db.collection("account_types")
                        .document("foodie_accounts")
                        .collection(userId)

                db.runBatch { writeBatch ->
                    foodieAccountRef.document("location_settings").set(
                        hashMapOf(
                            "mapDisplayRange" to 5
                        )
                    )
                    foodieAccountRef.document("user_info").set(
                        hashMapOf(
                           "favoriteVendors" to null
                        )
                    )

                }
            }
        }
    }
}



