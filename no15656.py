import sys
n, m = map(int,sys.stdin.readline().rstrip().split())
numlist = list(map(int,sys.stdin.readline().rstrip().split()))
numlist = sorted(numlist)
seq=[]
def layer(seq):
    for i in numlist:
        if len(seq)==m:
            print(' '.join(map(str,seq)))
            return
        else:
            seq.append(i)
            layer(seq)
            seq.pop()
layer(seq)