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
            # used[i] = True
            layer(seq)
            used[i] = True
            seq.pop()
            # used[i] = False
            for j in range(i+1, n):
                used[j] = False

layer(seq)