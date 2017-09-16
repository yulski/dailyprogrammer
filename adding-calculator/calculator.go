package main

import "fmt"
import "errors"

func main() {
    num1 := 0
    num2 := 0
    var op string
    fmt.Print("Enter first number: ")
    fmt.Scanln(&num1)
    fmt.Print("Enter second number: ")
    fmt.Scanln(&num2)
    fmt.Print("Enter operator: ")
    fmt.Scanln(&op)

    res := 0
    var err error

    switch op {
        case "+":
            res = num1 + num2
        case "-":
            res = subtract(num1, num2)
        case "*":
            res = multiply(num1, num2)
        case "/":
            res, err = divide(num1, num2)
        case "^":
            res, err = pow(num1, num2)
    }

    if err == nil {
        fmt.Println("The solution is:", res)
    } else {
        fmt.Println("ERROR:", err)
    }
}

func subtract(num1 int, num2 int) int {
    rhs := 0
    if num2 == 0 {
        return num1
    } else if num2 > 0 {
        for i:=0; i<num2; i++ {
            rhs += -1
        }
    } else {
        for i:=0; i>num2; i+= (-1) {
            rhs += 1
        }
    }
    return num1 + rhs
}

func multiply(num1 int, num2 int) int {
    if num1 == 0 || num2 == 0 {
        return 0
    } else if num1 < 0 && num2 > 0 {
        return switchSign(performMultiply(switchSign(num1), num2))
    } else if num1 > 0 && num2 < 0 {
        return switchSign(performMultiply(num1, switchSign(num2)))
    } else if num1 < 0 && num2 < 0 {
        return performMultiply(switchSign(num1), switchSign(num2))
    } else {
        return performMultiply(num1, num2)
    }
}

func performMultiply(num1 int, num2 int) int {
    ret := 0
    for i:=0; i<num2; i++ {
        ret += num1
    }
    return ret
}

func switchSign(num int) int {
    ret := 0
    if num < 0 {
        for i:=0; i>num; i += (-1) {
            ret++
        }
    } else {
        for i:=0; i<num; i++ {
            ret += (-1)
        }
    }
    return ret
}

func divide(num1 int, num2 int) (int, error) {
    if num2 == 0 {
        return 0, errors.New("Not-defined")
    }
    if num1 < 0 && num2 > 0 {
        ret, err := performDivide(switchSign(num1), switchSign(num2))
        return switchSign(ret), err
    } else if num1 > 0 && num2 < 0 {
        ret, err := performDivide(num1, num2)
        return switchSign(ret), err
    } else if num1 < 0 && num2 < 0 {
        return performDivide(switchSign(num1), num2)
    } else {
        return performDivide(num1, switchSign(num2))
    }
}

func performDivide(num1 int, num2 int) (int, error) {
    res := 0
    for num1 > 0 {
        num1 += num2
        res++
    }
    if num1 < 0 {
        return 0, errors.New("Non-integral answer")
    }
    return res, nil
}

func pow(num1 int, num2 int) (int, error) {
    if num1 == 0 {
        return 0, nil
    } else if num2 == 0 {
        return 1, nil
    } else if num2 == 1 {
        return num1, nil
    } else if num2 > 0 {
        res := num1
        for i:=1; i<num2; i++ {
            res = multiply(res, num1)
        }
        return res, nil
    } else if num2 < 0 {
        rhs, err := pow(num1, switchSign(num2))
        if err == nil {
            return divide(1, rhs)
        } else {
            return 0, err
        }
    } else {
        return 0, errors.New("Unexpected input")
    }
}
