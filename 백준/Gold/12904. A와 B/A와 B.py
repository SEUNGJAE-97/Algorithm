S = list(str(input()))
T = list(str(input()))

while True:
    if T[-1] == 'A':
        T = T[0:len(T)-1]
    elif T[-1] == 'B':
        T = T[0:len(T)-1]
        T.reverse()
    if T == S:
        print(1)
        break
    elif len(T)<=len(S):
        print(0)
        break
