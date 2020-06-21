
//    int x = 10
//    long y = 20
//    float z = 3.14
//    double q = 3.1415926
//    short  w = 40
//    byte e = 100
//    boolean b = true
//    String h = "aaa"

//    println x.class
//    println y.class
//    println z.class

// def 定义的变量，类型可以随意变更
//    def x = 10
//    println x.class
//    x = "bbb"
//    println x.class


// ------- String

//def singleString = 'i am single string'
//println singleString
//
//def threeString =  '''first line
//    two line'''
//println threeString
//
//def dualString = "x=2, y=3. x + y = ${2 + 3}"
//println dualString
//println dualString.class
//
//String s = dualString;
//println s

// ------- String method

/**
 * 字符串填充 center, padLeft, padRight
 */
def str = "groovy"
str = str.center(7, 'a')
println str

def str2 = "groovy"
str2 = str2.center(8, 'a')
println str2

def str3 = "groovy"
str3 = str3.center(9, 'a')
println str3

def str4 = "groovy"
str4 = str4.center(8) // 没有第二个参数，默认填充空字符
println str4

println '------------------字符串长度比较------------------------'
def compareStr = "str1"
def compareStrTwo = "str22"
println compareStr > compareStrTwo

println '-------------------字符串中字符获取-----------------------'
def arrayStr = "array"
println arrayStr[1]
println arrayStr[0..2]
println arrayStr.class

println '------------------字符串相减------------------------'
def operationStr = 'operator hello'
def operationStr2 = 'hello'
def operationStr3 = 'test'
println operationStr.minus(operationStr2)
println operationStr - operationStr2
println operationStr - operationStr3

println '------------------字符串倒序------------------------'
def reverseStr = 'reverse hello'
println reverseStr
println reverseStr.reverse()

println '-------------------字符串首字母大写-----------------------'
def capitalizeStr = 'capitalize string'
println capitalizeStr
capitalizeStr = capitalizeStr.capitalize()
println capitalizeStr.capitalize()
println capitalizeStr.uncapitalize()

println '------------------字符串转换其他类型------------------------'
def transStr = '5'
def intStr = transStr.toInteger()
println transStr
println intStr
println intStr.class

transStr = '0'
transStr2 = '1' // 如果是1，转换为 boolean 类型为 true
transStr3 = '2'
def boolenStr = transStr.toBoolean()
def boolenStr2 = transStr2.toBoolean()
def boolenStr3 = transStr3.toBoolean()
println boolenStr
println boolenStr2
println boolenStr3

println '-------------------字符串扩充 -----------------------'
def multiplyStr = 'abcdef'
println multiplyStr.multiply(2)
