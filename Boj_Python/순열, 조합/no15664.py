import sys
n, m = map(int,sys.stdin.readline().rstrip().split())
numlist = list(map(int, sys.stdin.readline().rstrip().split()))
numlist.sort()

seq = []
used = [False] * n

def layer(seq, index):
    if len(seq) == m:
        print(' '.join(map(str, seq)))
        # print(*seq)
        return
    overlap = 0
    for i in range(index, n):
        if not used[index] and overlap != numlist[i]:
            seq.append(numlist[i])
            overlap = numlist[i]
            used[index] = True
            layer(seq, i+1)
            seq.pop()
            used[index] = False



layer(seq, 0)


# import sys
# n, m = map(int,sys.stdin.readline().rstrip().split())
# numlist = list(map(int, sys.stdin.readline().rstrip().split()))
# numlist.sort()
# seq=[]
# used = [False] * n
#
# def layer(seq):
#     if len(seq)==m:
#         print(' '.join(map(str,seq)))
#         # print(*seq)
#         return
#     overlap = 0
#     for index, i in enumerate(numlist, 0):
#         if used[index] == False and overlap != i:
#             seq.append(i)
#             overlap = i
#             used[index] = True
#             layer(seq)
#             seq.pop()
#             for j in range(index+1, n):
#                 used[j] = False
#
#
# layer(seq)