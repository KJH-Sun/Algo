import sys
n, m = map(int,sys.stdin.readline().rstrip().split())
numlist = list(map(int, sys.stdin.readline().rstrip().split()))
numlist.sort()
seq=[]

def layer(seq):
    if len(seq)==m:
        print(' '.join(map(str,seq)))
        return
    overlap = 0
    for index, i in enumerate(numlist, 0):
        if overlap != i:
            seq.append(i)
            overlap = i
            layer(seq)
            seq.pop()

layer(seq)