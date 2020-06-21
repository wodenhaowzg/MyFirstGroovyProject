package com.example.mygroovy

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

// 定义对象
class JsonPerson {

    def mName
    def mAge
}

// 创建对象集合
def list = [new JsonPerson(mName: "xiaoming", mAge: 18), new JsonPerson(mName: "xiaohong", mAge: 15), new JsonPerson(mName: "xiaogang", mAge: 20),]

/**
 * 转换为json字符串
 */
def json = JsonOutput.toJson(list)
// 普通输出
println json
// 格式化输出
println JsonOutput.prettyPrint(json)

/**
 * json 字符串转换为对象
 */
def jsonSlurper = new JsonSlurper()
def objs = jsonSlurper.parseText(json) // 得到的是一个 ArrayList 集合
println objs.class
println objs