
epsilon = 0.1
k = float(raw_input('Enter The Number '))
guess = k/2.0
while abs(guess*guess - k) >= epsilon:
    guess = guess-(((guess**2) - k)/(2*guess))
print 'Square Root Of ', k , 'is close to ', guess
