package xml

import org.simpleframework.xml.core.Persister
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.math.absoluteValue

val xml = """
    <catalog>
       <book id="bk101">
          <author>Gambardella, Matthew</author>
          <title>XML Developer's Guide</title>
          <genre>Computer</genre>
          <price>44.95</price>
          <publish_date>2000-10-01</publish_date>
          <description>An in-depth look at creating applications with XML.</description>
       </book>
       <book id="bk102">
          <author>Ralls, Kim</author>
          <title>Midnight Rain</title>
          <genre>Fantasy</genre>
          <price>5.95</price>
          <publish_date>2000-12-16</publish_date>
          <description>A former architect battles corporate zombies.</description>
       </book>
    </catalog>
""".trimIndent()

fun main() {
    val serializer = Persister()
    val catalog = serializer.read(Catalog::class.java, xml)
    println(catalog.books?.get(1)?.description)
//    val bilderFactory = DocumentBuilderFactory.newInstance()
//    val documentBuilder = bilderFactory.newDocumentBuilder()
//    val doc = documentBuilder.parse(InputSource(StringReader(xml)))
//    val book = doc.getElementsByTagName("book")
//    for (i in 0 until book.length){
//        val books = book.item(i)
//        println(books.attributes.getNamedItem("id"))
//        println(books.childNodes.item(1).textContent)
//    }
}