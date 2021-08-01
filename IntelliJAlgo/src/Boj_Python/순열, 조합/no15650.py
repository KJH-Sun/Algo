# N, M = map(int, input().split())
#
# num_list = [i + 1 for i in range(N)]
# check_list = [False] * N
#
# arr = []
#
# def dfs(cnt):
#     if (cnt == M):
#         print(*arr)
#         return
#
#     for i in range(0, N):
#         if (check_list[i]):
#             continue
#
#         check_list[i] = True
#         arr.append(num_list[i])
#         dfs(cnt + 1)
#         arr.pop()
#
#         for j in range(i + 1, N):
#             check_list[j] = False
#
#
# dfs(0)



import sys
n, m = map(int,sys.stdin.readline().rstrip().split())
# numlist = list(map(int, sys.stdin.readline().rstrip().split()))
# numlist.sort()
seq=[]
used = [False] * n

def layer(seq):
    if len(seq)==m:
        print(' '.join(map(str,seq)))
        return

    for i in range(0,n):
        if used[i] == False:
            seq.append(i+1)
            used[i] = True
            layer(seq)
            seq.pop()
            for j in range(i+1, n):
                used[j] = False

layer(seq)