sum = float(0)
number = float(0)
readingDecimal = False
mf = 10
for s in '123.56,45.08006':
  if s == '.':
     readingDecimal = True
     mf = 0.1
  elif s == ',':
     sum = sum + number
     number = float(0)
     mf = 10
  else:
      if readingDecimal:
          number = number + float(s) * mf
          mf = mf * 0.1
      else:
          number = number*mf + float(s)
sum = sum + number
print sum
