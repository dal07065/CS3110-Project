

StringToInt = {
    "0": 0,
    "1": 1,
    "2": 2,
    "3": 3,
    "4": 4,
    "5": 5,
    "6": 6,
    "7": 7,
    "8": 8,
    "9": 9,
}

def isOperator(char)->bool:
    if char == '+' or char == '-' or char == '*' or char == '/' or char == '(' or char == ')':
        return True
    else:
        return False

def isNumber(char)->bool:
    if char == "0" or char == "1" or char == "2" or char == "3" or char == "4" or char == "5" or char == "6" or char == "7" or char == "8" or char == "9" or char == "_": 
        return True
    else:
        return False
    
def isFloat(number)->bool:

    if number[len(number)-1] == "f" or number[len(number)-1] == "F":
        return True
    else:
        return False
    
def isWithinFloatRange(number)->bool:
    return True

def stringToNumber(stringNumber):
    finalNumber = 0

    index = len(stringNumber)-1

    multiplier = 1

    while index >= 0:
        finalNumber += StringToInt[stringNumber[index]]*multiplier
        multiplier *= 10
        index -= 1
        
    return finalNumber*1.0
    

def addWholeAndDecimal(wholeNumber, decimalNumber):

    finalNumber = 0.0

    index = len(wholeNumber)-1

    multiplier = 1

    while index >= 0:
        finalNumber += StringToInt[wholeNumber[index]]*multiplier
        multiplier *= 10
        index -= 1


    if decimalNumber != "":
        index = 0
        multiplier = 10
        while index < len(decimalNumber):
            finalNumber += float(StringToInt[decimalNumber[index]])/multiplier
            multiplier *= 10
            index += 1

       

    return finalNumber*1.0
        
def exponentValue(floatValue, exponentNumber, exponentSign):

    print("floatValue: " + str(floatValue))
    print("exponentNumber: " + str(exponentNumber))
    print("exponentSign: " + str(exponentSign))

    index = len(exponentNumber)-1
    multiplier = 1
    exponentValue = 0

    while index >= 0:
        exponentValue += StringToInt[exponentNumber[index]]*multiplier
        multiplier *= 10
        index -= 1

    print("exponentValue: " + str(exponentValue))
          

    if exponentValue == 0:
        return floatValue/10

    if exponentSign == "+":
        while exponentValue > 0:
            floatValue *= 10
            exponentValue -= 1
    elif exponentSign == "-":
        while exponentValue > 0:
            print("floatValue: " + str(floatValue))
            floatValue /= 10
            exponentValue -= 1
    else:
        return -1

    return floatValue

def convertInputStrToFloat(number)->float:

    print("number: " + number)

    wholeNumber = ""
    decimalNumber = ""
    exponentNumber = ""
    floatValue = 0.0

    if isFloat(number):
        
        if isWithinFloatRange(number):

            ## Retrieve Whole Number (and take out all the underscores)

            index = 0
            loop = True
            wholeNumberOnly = False
            
            while loop is True and index < len(number):

                if number[index] == ".":
                    loop = False
                elif number[index] == "e" or number[index] == "E":
                    wholeNumberOnly = True
                    loop = False
                elif number[index] == "_":
                    if index == 0 or index == len(number)-1 or not isNumber(number[index-1]) or not isNumber(number[index+1]):
                        return -1
                elif index == len(number)-1 and (number[index] == "f" or number[index] != "F"):
                    loop = False
                    return stringToNumber(wholeNumber)
                elif not isNumber(number[index]):
                    return -1   
                else:
                    wholeNumber += number[index]
                    
                index+= 1

            ## Retrieve decimal number (and take out all underscores)

            loop = True

            if wholeNumberOnly is False:
                while loop is True and index < len(number):
                    
                    if number[index] == ".":
                        return -1
                    elif number[index] == "e" or number[index] == "E":
                        loop = False
                    elif number[index] == "_":
                        if index == len(number)-1 or not isNumber(number[index-1]) or not isNumber(number[index+1]):
                            return -1
                    elif index == len(number)-1 and (number[index] == "f" or number[index] == "F"):
                        return addWholeAndDecimal(wholeNumber, decimalNumber)
                    elif not isNumber(number[index]):
                        return -1
                    else:
                        decimalNumber += number[index]
                    index+=1

                floatValue = addWholeAndDecimal(wholeNumber, decimalNumber)
            else:
                floatValue += stringToNumber(wholeNumber)

            ## Retrieve exponent number

            loop = True
            exponentSign = "+"

            if index < len(number):    
                if number[index] == "-":
                    exponentSign = "-"
                    index+=1
                elif number[index] == "+":
                    index+=1

            while loop is True and index < len(number):

                if number[index] =="-" or number[index] == "+":
                    return -1
                if number[index] == ".":
                    return -1
                elif number[index] == "e" or number[index] == "E":
                    return -1
                elif number[index] == "_":
                    if index == len(number)-1 or not isNumber(number[index-1]) or not isNumber(number[index+1]):
                        return -1
                elif index == len(number)-1 and (number[index] == "f" or number[index] == "F"):
                    if exponentNumber == "":
                        return -1
                    else:
                        return exponentValue(floatValue, exponentNumber, exponentSign)
                    
                elif not isNumber(number[index]):
                    return -1
                else:
                    exponentNumber += number[index]
                index+=1

    return -1

## 1. Take in a character
##      - is it operator? { (, ), +, -, *, / }
##          - yes: push to operator stack
##          - no, take input until operator => float number => push to operand stack
##

def isGreaterThan(operator1, operator2):
    if operator2 == '0':
        return True
    elif operator1 == '*' || operator1 == '/':
        if operator2 == '*' || operator2 == '/':
            return False
        elif operator2 == '+' || operator2 == '-':
            return True
    elif operator1 == '+' || operator1 == '-':
        if operator2 == '+' || operator2 == '-':
            return False
        elif operator2 == '*' || operator2 == '/':
            return False
## POTENTIAL ERROR
    else
        return False
        
def computeOperation(operand1, operator, operand2):
    if operator == '*':
        return operand1 * operand2
    elif operator == '/':
        return operand1 / operand2
    elif operator == '+':
        return operand1 + operand2
    elif operator == '-':
        return operand1 - operand2
## POTENTIAL ERROR
    else
        return -999

def clean(expression):

    index = 0
    floatNumber = 0.0

    # ((5.0e-1f+2.50f)*((0.0_01f)/10.0f)
    # 5.0f * 3.0f + 2.0f
    # 5.0f *(3.0f + 2.0f)

    operatorStack = list() # (, (, 
    floatStack    = list() # 5, ., 0, e, -, 1
    operandStack  = list()
    
    operandStack.append('0')

    # Check for valid input

    while index < len(expression):
    
        print("Index: " + str(index) + "\nexpression[index]: " + str(expression[index]))
        print("Current operatorStack: ", end='')
        print(operatorStack)
        print("Current operandStack: ", end='')
        print(operandStack)

        if expression[index] == ' ':
            index+=1
            continue
            
        elif isOperator(expression[index]):
            if isGreaterThan(expression[index], operatorStack[len(operatorStack)-1]):
                operatorStack.append(expression[index])
            else:
                floatResult = computeOperation(operandStack[len(operandStack)-1],operatorStack[len(operatorStack)-1],operandStack[len(operandStack)-2])
                
                print("Result of computeOperation: " + str(floatResult))
                
                operandStack.pop()
                operandStack[len(operandStack)-1] = floatResult
                operatorStack.pop()
                operatorStack.append(expression[index])
            index+=1

        else:
            floatStack.append(expression[index])
            index+=1
            while expression[index] != 'f':
                floatStack.append(expression[index])
                index+=1
            floatStack.append(expression[index])
            index+=1
            operandStack.append(convertInputStrToFloat(''.join(floatStack)))
            floatStack.clear()
            
    print("operatorStack: ")
    print(operatorStack)
    print("floatStack: ")
    print(floatStack)
    print("operandStack: ")
    print(operandStack)
    print("operandStack[0]: ")
    print(operandStack[0])

    # Calculate

    ## Iterate through operatorStack

    ## currentOperation = ''

    ## parenthesesCount = 1
    ## parenthesesStack = list()
    ##
    ## index = 0
    ##
    ##  while len(operatorStack) > 0:
    ##      if operatorStack[index] > operatorStack[index + 1]:
    ##          result = operand[0] operatorStack[index] operand[1]
    ##          operatorStack.remove(index)
    ##          operandStack.
    ##
    ##      index+=1
    ##      if index == len(operatorStack):
    ##          index = 0
    
    return expression

def main():

        print("Enter expression: ", end='')
        expression = input()

        expression = clean(expression)

            
            



if __name__ == "__main__": 
    main()





