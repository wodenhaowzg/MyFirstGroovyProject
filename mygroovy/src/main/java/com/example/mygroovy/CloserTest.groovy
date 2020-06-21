package com.example.mygroovy

/**
 * 闭包最简单的定义
 */
def closer = { println 'i am closer!' }
/**
 * 闭包两种调用方式
 */
closer.call()
closer()

// ------------------------ 闭包参数

/**
 * 闭包携带空参数定义
 */
def closerArgs = { -> println 'i am closer width args!' }
closerArgs()

/**
 * 闭包携带参数定义
 */
def closerArgs2 = { String name -> println "i am closer width args! name : ${name}" }
closerArgs2('hello')
//closerArgs2('hello', 5)  // 个数不匹配报错

def closerArgs3 = { String name, int age -> println "i am closer width args! name : ${name} | age : ${age}" }
def age = 18
closerArgs3('xiaoming', age)

/**
 * 闭包携带的隐式参数，如果想去掉隐式参数，添加箭头即可
 */
def closerHideArgs = { println "i am closer width default args! def : ${it}" }
closerHideArgs("default")

/**
 * 去掉闭包携带的隐式参数，添加箭头即可，下面代码运行会报错
 */
//def closerHideArgs2 = { -> println "i am closer width default args! def : ${it}" }
//closerHideArgs2("default")

// ------------------------ 闭包返回值

/**
 * 闭包一定会有返回值，默认是最后一行，如果没定义 return ，会返回 null
 */
def retCloser = {
    println "i am return closer!"
}

def retCloser2 = {
    println "i am return closer!"
    return "value"
}
def returnValue = retCloser()
def returnValue2 = retCloser2()
println returnValue
println returnValue2

// ------------------------ 闭包与基本类型结合

def basicTypeResult = basicTypeOperationAdd(5)
println basicTypeResult

/**
 * 与基本类型 Number 结合，实现阶乘，在 java 中就需要写个 for 循环，groovy 使用封装好的 upto 即可。
 */
private static int basicTypeOperationAdd(int number) { // 5
    int result = 1
    result.upto(number, {
        num -> result += num  // num 值为 result, 2, 3, ... number ，即 1, 2, 3, ... 5
    })
    return result;
}

def basicTypeResult2 = basicTypeOperationDown(5)
println basicTypeResult2

/**
 * 与 upto 相反，倒序遍历
 */
private static int basicTypeOperationDown(int number) { // 5
    int result = 1
    number.downto(1) {
        num -> result += num // num 的值为 5, 4, 3, 2, 1
    }
    return result;
}

def basicTypeResult3 = basicTypeOperationTimes(5)
println basicTypeResult3

/**
 * 与 upto 和 downto 相比有两点区别
 * 1、起始点始终是从0开始
 * 2、循环判断条件是小于号，而不是小于等于号，比如这里传5，但最后计算时是不包含5的
 */
private static int basicTypeOperationTimes(int number) { // 5
    int result = 1
    number.times {
        num -> result += num // num 的值为 0, 1, 2, 3, 4
    }
    return result;
}

println "--------------------------------字符串遍历--------------------------------"
def closerStr = 'i am closer str'
String retValue = closerStr.each {
        //    println "$it"
    String temp -> println temp
}
println retValue

println "--------------------------------字符串查找--------------------------------"
def closureFindStr = 'i am closer str'
/**
 * find 闭包遍历字符串，查找到第一个符合条件的字符，并返回
 * 要注意：find 闭包返回值必须是 boolean 类型
 */
String findTargetStr = closureFindStr.find {
    String temp -> temp == 'm' // 这里 == 号比较的不是地址值，而是字符串内容
}
println findTargetStr

/**
 * findAll 闭包遍历字符串，查找到所有符合条件的字符，并返回一个集合
 * 要注意：findAll 闭包返回值必须是 boolean 类型
 */
def list = closureFindStr.findAll {
    String temp -> temp == 's'
}
println list.toListString()

/**
 * any 闭包遍历字符串，查找符合条件到字符，如果存在返回true，否则false
 * 要注意：any 闭包返回值必须是 boolean 类型
 */
def anyResult = closureFindStr.any {
    String temp -> temp == 's'
}
println anyResult

/**
 * every 闭包遍历字符串，必须每个字符都符合条件，否则false
 * 要注意：every 闭包返回值必须是 boolean 类型
 */
def everyResult = closureFindStr.every {
    String temp -> temp == 's'
}
println everyResult

/**
 * collect 闭包遍历字符串，然后返回处理后都字符添加到新到字符串。
 */
def newStr = closureFindStr.collect {
    String temp -> temp.toUpperCase()
}
println newStr.toListString()


println "--------------------------------闭包进阶--------------------------------"

/**
 * 闭包三个重要关键字 this , owner, delegate
 * this : 代表闭包定义处的类
 * owner : 代表闭包定义处的类或者对象
 * delegate : 代表一个任意对象，默认与owner一致
 */
def scriptClosure = {
    println "script closure : $this"
    println "script closure : $owner"
    println "script closure : $delegate"
}
scriptClosure.call()

println "--------------------------------闭包关键字--------------------------------"
/**
 * 区分闭包三个重要关键字 this , owner, delegate 的三句话
 * 1、在类或方法中定义的闭包，this , owner, delegate 三者默认是一致的。
 * 2、在闭包A中定义闭包B，那么闭包B的 this 和 owner 不再一样，this 还是指定闭包定义处的类或对象，而 owner 则指向闭包A
 * 3、当人为修改类 delegate 后，delegate 和 this、owner 不再一样，但是 this 和 owner 是不能认为修改的。
 */

class Person {

    def personClosure = {
        println "person closure : $this"
        println "person closure : $owner"
        println "person closure : $delegate"
    }

    void callMethod() {
        def callMethodClosure = {
            println "call method closure : $this"
            println "call method closure : $owner"
            println "call method closure : $delegate"
        }
        personClosure.call()
        callMethodClosure.call()
    }

    static void callMethodStatic() {
        def callMethodClosure = {
            println "call method static closure : $this"
            println "call method static closure : $owner"
            println "call method static closure : $delegate"
        }

        callMethodClosure.call()
    }
}

Person person = new Person()
person.callMethod()
Person.callMethodStatic()

def callMethodClosure2 = {
    def innerClosure2 = {
        println "call method inner closure : $this"
        println "call method inner closure, owner : " + owner  // 这里不能使用 $owner 否则会无限递归调用
        println "call method inner closure, delegate : " + delegate
    }
    innerClosure2.delegate = person
    innerClosure2.call()
}
callMethodClosure2.call()

println "--------------------------------闭包委托策略--------------------------------"

class Student {
    def mName

    def mNameClosure = {
        println "My name is $mName"
    }

    def callName() {
        return mNameClosure.call()
    }
}

Student student = new Student(mName: "xiaoming")
student.callName()

class Teacher {
    def mName
}
/**
 * 常用的四种委托策略
 *      public static final int OWNER_FIRST = 0;  owner 优先
 *      public static final int DELEGATE_FIRST = 1; delegate 优先
 *      public static final int OWNER_ONLY = 2; 仅使用 owner
 *      public static final int DELEGATE_ONLY = 3; 仅使用 delegate
 */
Teacher teacher = new Teacher(mName: "zhangguang")
student.mNameClosure.delegate = teacher  // 手动修改 delegate 对象
student.mNameClosure.resolveStrategy = Closure.DELEGATE_FIRST // 指定委托策略
student.callName()

