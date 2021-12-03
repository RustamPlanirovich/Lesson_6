package json

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.StringReader

val json = """
    {
       "owner": "John Smith",
       age: 55,
       "books":[
          {
             "id":"444",
             "language":"C",
             "edition":"First",
             "author":"Dennis Ritchie "
          },
          {
             "id":"555",
             "language":"C++",
             "edition":"second",
             "author":" Bjarne Stroustrup "
          }
       ]
    }
""".trimIndent()

fun main() {
//    val obj = JSONObject(json)
//    println(obj.getJSONArray("books").get(1))

    val gson = Gson()
    val owner: BookOwner = gson.fromJson(json, BookOwner::class.java)
    println(owner.books[1].author)
}