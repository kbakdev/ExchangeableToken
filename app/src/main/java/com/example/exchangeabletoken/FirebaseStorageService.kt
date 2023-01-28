package com.example.exchangeabletoken


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseStorageService {
        companion object {
            private var instance: FirebaseStorageService? = null

            fun getInstance(): FirebaseStorageService {
                if (instance == null) {
                    instance = FirebaseStorageService()
                }
                return instance!!
            }

            fun getWalletName(): String {
                val user = FirebaseAuth.getInstance().currentUser
                val storage = FirebaseStorage.getInstance()
                val storageRef = storage.reference
                val walletRef = storageRef.child("wallets/${user?.uid}/name")
                var walletName: String = ""
                walletRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
                    walletName = String(bytes)
                }.addOnFailureListener {
                    // Handle any errors
                }
                return walletName
            }

            fun getWalletAddress(): String {
                val user = FirebaseAuth.getInstance().currentUser
                val storage = FirebaseStorage.getInstance()
                val storageRef = storage.reference
                val walletRef = storageRef.child("wallets/${user?.uid}/address")
                var walletAddress: String = ""
                walletRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
                    walletAddress = String(bytes)
                }.addOnFailureListener {
                    // Handle any errors
                }
                return walletAddress
            }

            fun getWalletBalance(): String {
                val user = FirebaseAuth.getInstance().currentUser
                val storage = FirebaseStorage.getInstance()
                val storageRef = storage.reference
                val walletRef = storageRef.child("wallets/${user?.uid}/balance")
                var walletBalance: String = ""
                walletRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
                    walletBalance = String(bytes)
                }.addOnFailureListener {
                    // Handle any errors
                }
                return walletBalance
            }
        }

        fun getReference(path: String): StorageReference {
            return FirebaseStorage.getInstance().getReference(path)
        }

        fun getReferenceFromUrl(url: String): StorageReference {
            return FirebaseStorage.getInstance().getReferenceFromUrl(url)
        }

}
