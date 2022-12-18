package com.example.exchangeabletoken


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
        }

        fun getReference(path: String): StorageReference {
            return FirebaseStorage.getInstance().getReference(path)
        }

        fun getReferenceFromUrl(url: String): StorageReference {
            return FirebaseStorage.getInstance().getReferenceFromUrl(url)
        }

}
