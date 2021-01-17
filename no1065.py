def isHansu(num, hansunum=0, numlist = [], minuslist = []):
    if num < 100:
        return num
    else:
        for j in range(100, num+1):
            for i in str(j):
                numlist.append(i)
            for k in range(len(numlist)-1):
                minuslist.append(int(numlist[k+1])-int(numlist[k]))
            if len(set(minuslist))==1:
                hansunum += 1
            numlist = []
            minuslist = []
        return hansunum+99

x = int(input())
print(isHansu(x))