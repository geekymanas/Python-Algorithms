def printName(firstName, lastName, reverse):
    if reverse:
        print lastName + ' , ' + firstName
    else:
        print firstName, lastName

firstName = str(raw_input('Enter First Name '))
lastName  = str(raw_input('Enter Last Name '))
reverse   =(raw_input('Enter True Or False '))
printName(firstName , lastName, reverse)
