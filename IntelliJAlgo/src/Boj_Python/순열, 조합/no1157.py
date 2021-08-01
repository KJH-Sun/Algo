import sys

alpabet = "abcdefghijklmnopqrstuvwxyz"
string = sys.stdin.readline().rstrip()
lstring = string.lower()
result = []
for i in alpabet:
    result.append(lstring.count(i))

if result.count(max(result))>1:
    print("?")
else:
    print(chr(result.index(max(result))+97).upper())

# s,a=input().lower(),[]
# for i in range(97,123):
#  a.append(s.count(chr(i)))
# print('?'if a.count(max(a))>1 else chr(a.index(max(a))+97).upper())