package com.example

class Author {
    String name
    String surname

    static hasMany = [books: Book]

    static constraints = {
        name blank: false
        surname blank: false
        books nullable: true
    }
}
