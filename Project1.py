

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

    index = len(wholeNumber)-1

    multiplier = 1

    while index >= 0:
        finalNumber += StringToInt[wholeNumber[index]]*multiplier
        multiplier *= 10
        index -= 1
        
    return finalNumber*1.0
    

def addWholeAndDecimal(wholeNumber, decimalNumber):

    finalNumber = 0

    index = len(wholeNumber)-1

    multiplier = 1

    while index >= 0:
        finalNumber += StringToInt[wholeNumber[index]]*multiplier
        multiplier *= 10
        index -= 1

    if decimalNumber != "":
        index = 0
        multiplier = 0.1
        while index < len(decimalNumber):
            finalNumber += StringToInt[decimalNumber[index]]*multiplier
            multiplier /= 10
            index += 1

    return finalNumber*1.0
        
def exponentValue(floatValue, exponentNumber):

    initialFloatValue = floatValue

    index = len(exponentNumber)-1
    multiplier = 1
    exponentValue = 0

    while index >= 0:
        exponentValue += StringToInt[exponentNumber[index]]*multiplier
        multiplier *= 10
        index -= 1


    if exponentValue == 0:
        return floatValue*0.1

    while exponentValue > 0:
        floatValue *= 10
        exponentValue -= 1


    return floatValue*0.1

def main(number)->float:

    wholeNumber = ""
    decimalNumber = ""
    exponentNumber = ""

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

            ## Retrieve exponent number

            loop = True

            while loop is True and index < len(number):
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
                        return exponentValue(floatValue, exponentNumber)
                    
                elif not isNumber(number[index]):
                    return -1
                else:
                    exponentNumber += number[index]
                index+=1

    return -1

if __name__ == "__main__": 
    while True:
        print("Enter number: ", end='')
        number = input()
        if number != "-1":
            result = main(number)
            if result == -1:
                print("Invalid Result")
            else:
                print(result)
            print()
        else:
            break






