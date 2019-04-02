package com.example.firebase_project_5

class noteClass{






// zedt var  id   3la hssab kkey id lidart fi manactivity


    var id: String? = null
    var title: String? = null
    var note: String? = null
    var timestamp: String? = null


    constructor() {


    }

    constructor(id: String, title: String, note: String, timestamp: String) {

        this.id = id
        this.title = title
        this.note = note
        this.timestamp = timestamp


    }

}