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
        if overlap != numlist[i]:
            seq.append(numlist[i])
            overlap = numlist[i]
            layer(seq, i)
            seq.pop()


layer(seq, 0)