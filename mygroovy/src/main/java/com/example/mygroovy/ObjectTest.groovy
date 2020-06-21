package com.example.mygroovy

/**
 * groovy 中类，变量和方法默认都是 public
 * groovy 中类继承于 GroovyObject，而不是 Object
 * groovy 中所有属性，默认会实现get和set方法
 */
class ObjectPerson {

    String mName
    int mAge

    /**
     * 返回值为 def，相当于 java 中的 Object 类型。
     * @param age
     */
    def increaseAge(int age) {
        return mAge + age
    }
}

/**
 * 对象构造上，groovy支持传递自定义个数的属性构造
 */
ObjectPerson person = new ObjectPerson(mName: "xiaoming", mAge: 15)
ObjectPerson person2 = new ObjectPerson(mName: "xiaohong")

// 这里 person.mName 并不是操作变量 mName，而是相当于 person.getName
println "get person name : ${person.mName}"

/**
 * groovy 中的接口，不允许出现非 public 修饰的方法
 */
interface ObjectPersonInter {

    void eat()
}

/**
 * groovy 中的 trait class，相当于 java 中的抽象类
 */
trait ObjectPersonAbstrat {

    abstract void eat()

    void play() {

    }
}

/**
 * 在调用对象中的方法时，与 java 不同的是，如果对象中没有包含该方法，在编译器并不会报错，而是走
 * methodMissing 和 invokeMethod，如果两个方法都未实现，报运行时错误。
 */
class MethodObject {

    /**
     * 如果实现了这个方法，调用不存在的方法时，触发它
     */
    def methodMissing(String s, Object o) {
        return "Method is missing, name : $s | args : $o"
    }

    /**
     * 如果实现了这个方法，并且未实现 methodMissing，调用不存在的方法时，触发它
     */
    @Override
    Object invokeMethod(String s, Object o) {
        return "invoke method : $s | args : $o"
    }
}

MethodObject methodObject = new MethodObject()
println methodObject.test()

/**
 * groovy 支持动态注入属性或方法，通过任意对象中的 metaClass (此对象来源于 GroovyObject) 实现。
 * 注意：默认通过 metaClass 注入的属性和方法，仅在此脚本文件中应用，不能跨脚本使用。
 * 跨脚本使用，即只注入一次，可以在其他文件中也能使用，在执行注入操作之前调用如下方法即可。
 * ExpandoMetaClass.enableGlobally()
 */
// 注入属性
methodObject.metaClass.name = 'xiaoliang' // 如果没有这一行，调用 name 属性会直接报错
println methodObject.name

// 注入方法
methodObject.metaClass.callName = {
    return name
}
println methodObject.callName()

// 注入静态方法，这里使用的是 MethodObject 类名，而不是 methodObject 对象。
MethodObject.metaClass.static.createMethodObject = {
    new MethodObject()
}

def methodObject2 = MethodObject.createMethodObject();
println methodObject2


