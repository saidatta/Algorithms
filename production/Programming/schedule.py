n = int(input())
finalTime = 0
tests = []

for _ in range(n):
    test = input().split()
    time, probability = int(test[0]), float(test[1])
    tests.append((time, probability))




time_elapsed = 0
probabilityForCurrentStep = 1
tests.sort(key=lambda x: ((1 - x[1])/x[0], (1 - x[1])), reverse = True)
#tests.sort(key=lambda x: x[1] * x[0])
#3,7,9
#.1,.5,.2
for i, test in enumerate(tests):
    time, probability = test
    time_elapsed += time
    if i < n-1:
        finalTime += time_elapsed * probabilityForCurrentStep * (1 - probability)
    else:
        finalTime += time_elapsed * probabilityForCurrentStep
    probabilityForCurrentStep = (probabilityForCurrentStep) * (probability)
    
print(finalTime)
