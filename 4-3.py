def f(x):
    def g():
        x = 'abc'
        print 'x in g = ',x
    def h():
        z = x
        print 'zin h = ',z
#   x = x + 1
    print 'x in f 1 = ', x
    h()
    g()
    print 'x in f 2 = ', x
    return g
x = 3
z = f(x)
print 'x outside = ',x
print 'z = ',z
z()