
x = int(0)
max_no = int(0)

while x < 10:
  y = int(raw_input('Enter the Integer '))
  if y%2 != 0 and (y > max_no or max_no == 0):
    max_no = y 
  x = x + 1

print 'There is no Odd Number' if max_no == 0 else max_no

 
 
