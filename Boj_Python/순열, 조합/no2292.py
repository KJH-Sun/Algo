import sys
n = int(sys.stdin.readline().rstrip())
first = 1
plus = 6
room = 1
if n == 1:
    print(1)
else:
    while(True):
        first += plus
        room+=1
        if n <= first:
            print(room)
            break
        plus += 6