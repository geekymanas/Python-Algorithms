def fib(x):
    """ Assumes x an int >= 0
        Returs Fibonacci of x"""
        global numFibCalls
        numFibCalls +=1
        if  x == 0 or x == 1:
            return 1
        else:
            return fib(n-1) + fib(n-2)
def testFib(n):
    for i in range(n+1):
        global numFibCalls
        numFibCalls = 0
        print 'fib of ',i,'= ',fib(i)
        print 'fib called ', numberFibCalls,' times. '