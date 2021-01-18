import sys
n, m = map(int,sys.stdin.readline().rstrip().split())
seq=[]
def layer(seq):
    for i in range(1, n+1):
        if len(seq)==m:
            print(' '.join(map(str,seq)))
            return
        else:
            seq.append(i)
            layer(seq)
            seq.pop()
layer(seq)