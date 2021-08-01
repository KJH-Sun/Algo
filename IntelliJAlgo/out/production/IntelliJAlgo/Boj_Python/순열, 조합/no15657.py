import sys
n, m = map(int,sys.stdin.readline().rstrip().split())
numlist = list(map(int, sys.stdin.readline().rstrip().split()))
numlist.sort()
seq=[]
used = [False] * n

def layer(seq):
    if len(seq)==m:
        print(' '.join(map(str,seq)))
        # print(*seq)
        return

    for index, i in enumerate(numlist,0):
        if used[index] == False:
            seq.append(i)
            layer(seq)
            used[index] = True
            seq.pop()
            for j in range(index+1, n):
                used[j] = False

layer(seq)