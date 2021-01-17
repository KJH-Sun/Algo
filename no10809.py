import sys
alist = list(sys.stdin.readline().rstrip())

for i in range(ord('a'),ord('z')+1):
    try:
        print(alist.index(chr(i)))
    except ValueError:
        print(-1)

# import sys
# alpabet = "abcdefghijklmnopqrstuvwxyz"
# string = sys.stdin.readline().rstrip()
#
# for i in alpabet:
#     print(string.find(i))