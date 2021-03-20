package cinema


import java.math.RoundingMode
import java.text.DecimalFormat


fun main() {

    // write your code here
    println("Enter the number of rows:")
    val row = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seats = readLine()!!.toInt()
    var cinemaArray: Array<Array<String>> = Array(row, { Array(seats, { "" }) })
    createMe(cinemaArray)
    if (row == 0) throw Exception("Division by zero, please fix the second argument!")



    do {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        var menu = readLine()!!.toInt()

        when(menu){
            1 -> printMe(seats, cinemaArray)
            2 -> do {
                var exept = try {
                    tiketsBuy(row, seats, cinemaArray)
                    true
                } catch (e: Exception) {
                    println(e.message)
                    false
                } catch (e: Exception) {
                    println(e.message)
                    false
                }

            } while (!exept)
            3 -> statistics(row, seats, cinemaArray)
        }
    }while (menu != 0)
}

private fun tiketsBuy(row: Int, seats: Int, cinemaArray: Array<Array<String>>) {
    println()
    println("Enter a row number:")
    val a = readLine()!!.toInt()
    println("Enter a seat number in that row:")
    val b = readLine()!!.toInt()

    if (a > row || b > seats) throw Exception("Wrong input!")
    if (cinemaArray[a - 1][b - 1] == "B") throw Exception("That ticket has already been purchased!")

    print("Ticket price: ")
    var price = 0
    price = priceOne(row,seats, a)
    println("\$$price")

    println()
    cinemaArray[a - 1][b - 1] = "B"
    printMe(seats, cinemaArray)
}

private fun printMe(seats: Int, cinemaArray: Array<Array<String>>) {
    println()
    println("Cinema:")
    print(" ")
    for (n in 1..seats) {
        print(" ${n}")
    }
    println()
    for (i in 0..cinemaArray.lastIndex) {
        print("${i + 1} ")
        for (j in 0..cinemaArray[i].lastIndex) {
            print("${cinemaArray[i][j]} ")
        }
        println()
    }
}
private fun createMe(cinemaArray: Array<Array<String>>) {

    for (i in 0..cinemaArray.lastIndex) {
        for (j in 0..cinemaArray[i].lastIndex) {
            cinemaArray[i][j] = "S"
        }
    }
}

private fun statistics(row: Int, seats: Int, cinemaArray: Array<Array<String>>) {
    var inc = 0
    var oll = 0
    var sum = 0
    var sumOb = 0
    var proc1 = ""
    var proc = 0.0
    for (i in 0..cinemaArray.lastIndex) {
        for (j in 0..cinemaArray[i].lastIndex) {
            oll++
            sumOb += priceOne(row, seats, i+1)
            if (cinemaArray[i][j] == "B"){
                inc++
                sum += priceOne(row, seats, i+1)
            }
        }
    }
    if (inc == 0){
        proc1 = "0.00"
    }
    else{
        proc = inc.toDouble() / oll.toDouble() *100
        proc1 = proc.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toString()
    }
    println("Number of purchased tickets: $inc")
    println("Percentage: $proc1%")
    println("Current income: \$$sum")
    println("Total income: \$$sumOb")
}

fun  priceOne (row: Int, seats: Int, i: Int):Int{
    var price = 0
    if (row * seats > 60 && i * 2 >= row) {
        price = 8
    } else {
        price = 10
    }
    return price
}







