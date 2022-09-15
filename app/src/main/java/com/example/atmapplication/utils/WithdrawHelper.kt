package com.example.atmapplication.utils

import kotlin.math.floor

class WithdrawHelper {
        fun withdraw(amount: Int): IntArray {
            var moneyValue = amount
            val noteValues = intArrayOf(2000,500, 200, 100)
            if (moneyValue > 15000) {

            } else {
                var i = 0
                while (i < noteValues.size && moneyValue != 0) {
                    if (moneyValue >= noteValues[i]) println("No of " + noteValues[i] + "'s" + " :" + moneyValue / noteValues[i])
                    moneyValue %= noteValues[i]
                    i++
                }
            }
            return noteValues
        }
    var neew = Withdraw()

    }

fun Withdraw() {
        var moneyWithdrawn = 2040
        val amountsFromLargestToSmallest = intArrayOf(100, 50, 20, 10)
        val smallestBillAmount = 10
        val amountsOfEachBill = IntArray(amountsFromLargestToSmallest.size)
        if (moneyWithdrawn > 15000) {
            println("ATM Cash Limit exceeds.")
        } else if (moneyWithdrawn <= 0 || moneyWithdrawn % 100 != 0) {
            println("Please enter a valid number.")
        } else {
            for (i in amountsFromLargestToSmallest.indices) {
                val billAmount = amountsFromLargestToSmallest[i]
                val amount = floor((moneyWithdrawn / billAmount).toDouble()).toInt()
                if (amount > 0) {
                    moneyWithdrawn -= amount * billAmount
                }
                amountsOfEachBill[i] = amount
            }
        }
    }
