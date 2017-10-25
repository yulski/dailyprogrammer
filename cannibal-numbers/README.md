# [[2017-10-16] Challenge #336 [Easy] Cannibal numbers](https://www.reddit.com/r/dailyprogrammer/comments/76qk58/20171016_challenge_336_easy_cannibal_numbers/)

## Description
Imagine a given set of numbers wherein some are cannibals. We define a cannibal as a larger number can eat a smaller number and increase its value by 1. There are no restrictions on how many numbers any given number can consume. A number which has been consumed is no longer available.
Your task is to determine the number of numbers which can have a value equal to or greater than a specified value.

## Challenge Input
You'll be given two integers, i and j, on the first line. i indicates how many values you'll be given, and j indicates the number of queries.
Example:

> 7 2

> 21 9 5 8 10 1 3

> 10 15

Based on the above description, 7 is number of values that you will be given. 2 is the number of queries.
That means - 

* Query 1 - How many numbers can have the value of at least 10
* Query 2 - How many numbers can have the value of at least 15
