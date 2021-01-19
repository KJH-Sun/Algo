
import sys
n, m = map(int,sys.stdin.readline().rstrip().split())
numlist = list(map(int, sys.stdin.readline().rstrip().split()))
numlist.sort()
seq=[]
used = [False] * n

def layer(seq):
    if len(seq)==m:
        print(' '.join(map(str,seq)))
        return
    overlap = 0
    for index, i in enumerate(numlist, 0):
        if used[index] == False and overlap != i:
            seq.append(i)
            used[index] = True
            overlap = i
            layer(seq)
            seq.pop()
            used[index] = False

layer(seq)


# 시간초과
# import sys
# n, m = map(int,sys.stdin.readline().rstrip().split())
# numlist = list(map(int, sys.stdin.readline().rstrip().split()))
# numlist = sorted(numlist)
# seq=[]
# result = []
#
# def layer(seq):
#     for index, i in enumerate(numlist,0):
#         if len(seq)==m:
#             if ' '.join(map(str,seq)) in result:
#                 return
#             result.append(' '.join(map(str,seq)))
#             print(' '.join(map(str,seq)))
#             return
#         else:
#             if i in seq:
#                 if index == numlist.index(i):
#                     continue
#             seq.append(i)
#             layer(seq)
#             seq.pop()
# layer(seq)
