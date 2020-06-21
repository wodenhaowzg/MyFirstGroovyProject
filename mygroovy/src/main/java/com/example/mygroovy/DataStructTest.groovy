package com.example.mygroovy

println "--------------------------------集合定义--------------------------------"

/**
 * 用 def 定义的集合，默认使用的是 ArrayList，如想转换其他类型的集合有两种方式
 * 1、显式定义类型，不用 def
 * 2、通过 as 关键字转换类型
 */
def arrayList = [1, 2, 3, 4, 5]
println arrayList.class
println arrayList.size()

// 转换为 LinkedList
LinkedList linkedList = arrayList as LinkedList
println linkedList.class
println linkedList.size()


println "--------------------------------数组定义--------------------------------"

int[] array = [1, 2, 3]
def array2 = [1, 2, 3, 4] as int[]
println array.toString()
println array2.toString()

println "--------------------------------集合操作--------------------------------"

def addList = [5, 6, 2, 3, 1, 9]
// 添加方式1
addList << 10
println addList
// 添加方式2
addList = addList + 12
println addList
//addList = 13 + addList  // 这种不行
//println addList

println "--------------------------------集合删除--------------------------------"
def removeList = [5, 6, 2, 3, 1, 9]
println removeList
// 删除集合中指定的元素
removeList = removeList - 1
println removeList
removeList = removeList - [2, 9]
println removeList

println "--------------------------------集合排序--------------------------------"
def sortList = [5, 6, 2, 3, 1, 9]
println sortList.sort()
println sortList.sort {
    a, b ->
        a > b ? -1 : 1
}

def sortStringList = ['avvvv', 'ba', 'cca', 'cba', 'adc']
println sortStringList.sort()
// 根据字符串的长度进行排序
println sortStringList.sort {
    it.size()
    // return it.size()
}

println "--------------------------------集合查找--------------------------------"
// find、findAll、every、any
def seachList = [5, 6, 2, 3, 1, 9]
println "find method : " + seachList.find { // 查找第一个符合条件的元素，这里是如果不能被2整除，算符合条件
    return it % 2
}

println "findAll method : " + seachList.findAll { // 查找所有符合条件的元素，这里是如果不能被2整除，算符合条件
    return it % 2
}

println "every method : " + seachList.every { // 只有集合中所有元素符合条件，才返回true，否则返回false
    return it.class == Integer.class
}

// any 与 every 相反，如果有一个元素符合条件，就返回true

println seachList.count { // 返回大于6的元素个数
    return it > 6
}

// 最小值
println seachList.min()
// 最大值
println seachList.max()

println "--------------------------------Map集合--------------------------------"

// 定义，默认key的类型为单引号的字符串
def colors = [red: 'ff0000', green: '00ff00', blue: '0000ff']
println colors.class
println colors

// 添加
colors.yellow = 'ffff00'
println colors // 相当于 colors.toMapString()
// 支持复杂类型添加
colors.complex = ['1': 1, 2: 2] // 2 相当于 '2'
println colors
// 移除
colors.remove('red')
println colors

// 遍历1
colors.each {
    println "the key is : ${it.key} | value is : ${it.value}"
}
// 遍历2
colors.eachWithIndex {
    def color, int index ->
        println "the key is : ${color.key} | value is : ${color.value} | index is : $index"
}

