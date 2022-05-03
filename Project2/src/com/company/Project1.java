package com.company;

import static com.company.Calculator.isNumber;

public class Project1 {
    public float convertStringToFloat(String tempFloatValue) {
        String wholeNumber = "";
        String decimalNumber = "";
        String exponentNumber = "";
        float floatValue = 0.0f;

        if(isFloat(tempFloatValue))
        {
            int index = 0;
            boolean loop = true;
            boolean wholeNumberOnly = false;
            boolean exponent = false;

            while(loop && index < tempFloatValue.length()) {
                switch (tempFloatValue.charAt(index)) {
                    case '.':
                        loop = false;
                        break;
                    case 'e', 'E':
                        wholeNumberOnly = true;
                        exponent = true;
                        loop = false;
                        break;
                    case '_':
                        if (index == 0 ||
                                index == tempFloatValue.length() - 1 ||
                                !isNumber(tempFloatValue.charAt(index - 1)) ||
                                !isNumber(tempFloatValue.charAt(index + 1)))
                            return -1f;
                        break;
                    case 'f', 'F':
                        if (index == tempFloatValue.length() - 1) {
                            loop = false;
                            break;
                        } else
                            return -1f;
                    default:
                        if (!isNumber(tempFloatValue.charAt(index)))
                            return -1f;
                        else
                            wholeNumber += tempFloatValue.charAt(index);
                        break;
                }
                index += 1;
            }
            loop = true;

            if(wholeNumberOnly == false)
            {
                while(loop && index < tempFloatValue.length())
                {
                    switch(tempFloatValue.charAt(index))
                    {
                        case '.':
                            return -1f;
                        case 'e', 'E':
                            loop = false;
                            exponent = true;
                            break;
                        case '_':
                            if(index == tempFloatValue.length()-1 ||
                                    !isNumber(tempFloatValue.charAt(index-1)) ||
                                    !isNumber(tempFloatValue.charAt(index+1)))
                                return -1f;
                            break;
                        case 'f', 'F':
                            if(index == tempFloatValue.length()-1)
                            {
                                return stringToNumber(wholeNumber, decimalNumber);
                            }
                            else
                                return -1;
                        default:
                            if(!isNumber(tempFloatValue.charAt(index)))
                                return -1;
                            else
                                decimalNumber += tempFloatValue.charAt(index);
                            break;
                    }
                    index += 1;
                }
                floatValue = stringToNumber(wholeNumber, decimalNumber);
            }
            else
                floatValue += stringToNumber(wholeNumber, null);

            if(exponent == true)
            {
                loop = true;
                char exponentSign = '+';

                if(index < tempFloatValue.length())
                {
                    if(tempFloatValue.charAt(index) == '-')
                    {
                        exponentSign = '-';
                        index++;
                    }
                    else if (tempFloatValue.charAt(index) == '+')
                        index++;
                }

                while(loop && index < tempFloatValue.length())
                {
                    switch(tempFloatValue.charAt(index))
                    {
                        case '-', '+', '.', 'e', 'E':
                            return -1;
                        case '_':
                            if(index == tempFloatValue.length()-1 ||
                                    !isNumber(tempFloatValue.charAt(index-1)) ||
                                    !isNumber(tempFloatValue.charAt(index+1)))
                                return -1;
                        case 'f', 'F':
                            if(index == tempFloatValue.length()-1)
                                if(exponentNumber=="")
                                    return -1;
                            break;
                        default:
                            if(!isNumber(tempFloatValue.charAt(index)))
                                return -1;
                            else
                                exponentNumber += tempFloatValue.charAt(index);

                    }
                    index++;
                }

                floatValue = exponentValue(floatValue, exponentNumber, exponentSign);
            }
            return floatValue;
        }
        return -1f;
    }

    private float exponentValue(float floatValue, String exponentNumber, char exponentSign) {
        int index = exponentNumber.length()-1;
        int multiplier = 1;
        float exponentValue = stringToNumber(exponentNumber, null);

        if(exponentValue == 0.0f)
            return floatValue/10;
        else if( exponentSign == '+')
            while(exponentValue > 0)
            {
                floatValue*= 10;
                exponentValue--;
            }
        else if(exponentSign == '-')
            while(exponentValue > 0)
            {
                floatValue/=10;
                exponentValue--;
            }
        else
            return -1;

        return floatValue;
    }

    private float stringToNumber(String wholeNumber, String decimalNumber) {
        float finalNumber = 0.0f;
        int index = wholeNumber.length()-1;
        int multiplier = 1;

        while(index >= 0)
        {
            finalNumber += CharToFloat(wholeNumber.charAt(index))*multiplier;
            multiplier *= 10;
            index--;
        }

        if(decimalNumber != null)
        {
            index = 0;
            multiplier = 10;

            while(index < decimalNumber.length())
            {
                finalNumber += CharToFloat(decimalNumber.charAt(index))/multiplier;
                multiplier *= 10;
                index++;
            }
        }
        return finalNumber * 1.0f;
    }

    private float CharToFloat(char charAt) {
        switch(charAt)
        {
            case '0':
                return 0.0f;
            case '1':
                return 1.0f;
            case '2':
                return 2.0f;
            case '3':
                return 3.0f;
            case '4':
                return 4.0f;
            case '5':
                return 5.0f;
            case '6':
                return 6.0f;
            case '7':
                return 7.0f;
            case '8':
                return 8.0f;
            case '9':
                return 9.0f;
            default:
                return -1.0f;
        }
    }

    private boolean isFloat(String tempFloatValue) {
        // If it contains a . or an e or neither but has 'f' at the end, it is considered a float
        for(int i = 0; i < tempFloatValue.length();i++)
        {
            if(tempFloatValue.charAt(i) == '.' || tempFloatValue.charAt(i) == 'e' || tempFloatValue.charAt(i) == 'E')
                return true;
        }
        char lastCharacter = tempFloatValue.charAt(tempFloatValue.length()-1);
        if(lastCharacter == 'f' || lastCharacter == 'F')
            return true;

        return false;
    }
}
