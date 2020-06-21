package com.example.mygroovy

import groovy.xml.MarkupBuilder

// 构建 xml 内容
final String xml = '''
    <response version-api="2.0">
        <value>
            <books id="1" classification="android">
                <book available="20" id="1">
                    <title>疯狂Android讲义</title>
                    <author id="1">李刚</author>
                </book>
                <book available="14" id="2">
                    <title>第一行代码</title>
                    <author id="2">郭林</author>
                </book>
                <book available="13" id="3">
                    <title>Android开发艺术探索</title>
                    <author id="3">任玉刚</author>
                </book>
                <book available="5" id="4">
                    <title>Android源码设计模式</title>
                    <author id="4">何红辉</author>
                </book>
            </books>
            <books id="2" classification="web">
                <book available="10" id="1">
                    <title>Vue从入门到精通</title>
                    <author id="4">李刚</author>
                </book>
            </books>
        </value>
    </response>
    '''

// 解析 xml
def xmlSluper = new XmlSlurper()
def response = xmlSluper.parseText(xml)
// 打印 xml 内容
println response
println response.value
println response.value.books[0]
println response.value.books[0].book[0]
println response.value.books[0].book[0].title.text()
println response.value.books[0].book[0].author.text() // 访问结点内容
println response.value.books[0].book[0].@available  // 访问结点属性
println response.value.books[0].book[0].author.@id

// 查找作者为李刚到书籍
//def findBooks = []
//response.value.books.each {
//    it.book.each { // it 代表 books
//        if (it.author.text() == '李刚') {  // it 代表 book
//            findBooks.add(it)
//        }
//    }
//}
//println findBooks

/**
 * groovy 深度遍历 depthFirst
 */
//response.depthFirst().findAll{
//    println "depth : $it" // 默认会打印所有结点
//}

def bookTitle = response.depthFirst().findAll {
        //def bookTitle = response.'**'.findAll { //  depthFirst 可以使用 '**' 代替
    def book -> // 指定结点
        return book.author.text() == '李刚'
}.collect {
    return it.title.text()
}
println bookTitle

/**
 * groovy 广度遍历 children
 */

def result = response.value.books.children().findAll { // children 可以使用 '*' 代替
    println it.name() + " | " + it.@id
    return it.name() == 'book' && it.@id == '2'
}.collect { node ->
    return node.title.text()
}
println result

// --------------------------- 构建 xml 内容 ---------------------------

//def stringWriter = new StringWriter()
//def xmlBuilder = new MarkupBuilder(stringWriter)
// 开始构建xml内容
//xmlBuilder.response('version-api': 2) {
//    value() {
//        books('id': 1, 'classification': 'android') {
//            book('available': 20, 'id': 1) {
//                title('疯狂Android讲义')
//                author('id': 1, '李刚')
//            }
//            book('available': 14, 'id': 2) {
//                title('第一行代码')
//                author('id': 2, '郭林')
//            }
//        }
//    }
//}
//println stringWriter

// 根据对象来构建xml内容

class Response {

    def mVersion = 2
    def mValues = [new Value()]
}

class Value {

    def books = [new Books()]
}

class Books {

    def id = 1
    def classification = 'android'
    def book = [new Book('available': 20, 'id': 1, 'title': '疯狂Android讲义', 'author': '李刚'),
                new Book('available': 14, 'id': 2, 'title': '第一行代码', 'author': '郭林')]
}

class Book {

    def available, id
    def title, author
}

def stringWriter2 = new StringWriter()
def xmlBuilder2 = new MarkupBuilder(stringWriter2)

def response2 = new Response()
xmlBuilder2.reponse('version-api': response2.mVersion) {  // 构建结点：结点名字(){}，这里如 response(){}
    response2.mValues.each { valueNode -> // value 结点
        value() {
            valueNode.books.each { booksNode -> // books 结点
                books('id': booksNode.id, 'classification': booksNode.classification) {
                    booksNode.book.each { bookNode -> // book 结点
                        book('available': bookNode.available, 'id': bookNode.id) {
                            title(bookNode.title)
                            author(bookNode.author)
                        }
                    }
                }
            }
        }
    }
}
println stringWriter2