import sys
num1, num2, num3 = map(int,sys.stdin.readline().rstrip().split())
if num2 >= num3:
    print(-1)
else:
    gain = num3-num2
    print(int((num1 / gain)+1))