package com.example.mygroovy

import com.example.mygroovy.bean.Course

/**
 * 创建 File 对象
 */
def checkPath = new File("./")
// 获取根路径
println checkPath.absolutePath

/**
 * 获取当前路径下文件列表
 */
//checkPath.listFiles().each { tempFile ->
//    println "file : ${tempFile.name}"
//}

// 找到build.gradle文件
def file = new File("./mygroovy/build.gradle")
// 传统遍历内容
//file.eachLine {
//    println "line $it"
//}

/**
 * groovy 获取文件内容
 */
//def fileContent = file.getText()
//println fileContent

/**
 * groovy 获取文件每一行内容，得到行集合
 */
//def lineContents = file.readLines()
//println lineContents

/**
 * groovy 获取文件部分内容
 * groovy 封装了 with 、new 开头的方法，with 开头的输入/输出流是不需要 close 的，但
 * new 开头创建但输入/输出流是需要手动关闭流的，调用 close 。
 * 常用 with 开头的方法有：
 * withReader、withWriter、withObjectInputStream、withObjectOutputStream
 * 常用 new 开头的方法有：
 * newReader、newWriter、newObjectInputStream、newObjectOutputStream
 */
//def buf = file.withReader { reader ->
//    def buf = new char[100]
//    reader.read(buf)
//    return buf
//}
//println buf

// ------------------------------ 文件拷贝练习 ----------------------------------------

//try {
//    def lines = file.readLines()
//    def desFile = new File("./mygroovy/build2.gradle")
//    if (!desFile.exists()) {
//        desFile.createNewFile()
//    }
//
//    desFile.new
//    desFile.withWriter { writer ->
//        lines.each { line ->
//            writer.writeLine(line)
//        }
//    }
//} catch (Exception e) {
//    e.printStackTrace()
//}

// ------------------------------ 对象存储/读取练习 ----------------------------------------

// 1、创建对象
def course = new Course(mCourseName: 'groovy', mCourseSize: 1)
String path = './mygroovy/course.bin'
//def save = saveObject(path, course)
//println save
Course readObj = readObject(path)
println readObj.mCourseName

boolean saveObject(String path, def obj) {
    try {
        def desFile = new File(path)
        if (desFile.exists())
            desFile.delete()

        desFile.createNewFile()
        desFile.withObjectOutputStream { objStream ->
            objStream.writeObject(obj)
        }
        return true
    } catch (Exception e) {
        e.printStackTrace()
    }
    return false
}

def readObject(String path) {
    def obj = null
    try {
        def srcFile = new File(path)
        srcFile.withObjectInputStream { objStream ->
            obj = objStream.readObject();
        }
    } catch (Exception e) {
        e.printStackTrace()
    }
    return obj
}