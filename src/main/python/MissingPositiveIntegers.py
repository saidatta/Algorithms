"""
Given an array of integers, find the first missing positive integer in linear time and constant space.
You can modify the input array in-place.
Example:
>>> coding_problem_4([3, 4, -1, 1])
2
>>> coding_problem_4([1, 2, 0])
3
>>> coding_problem_4([4, 1, 2, 2, 2, 1, 0])
3
"""
def missingPositiveIntegers(arr):

    result_arr = [True] + [False] * len(arr)
    for x in filter(lambda x: 0 <= x <= len(arr), arr):
        result_arr[x] = True

    return result_arr.index(False)

def main():
    print(missingPositiveIntegers([3, 4, -1, 1]))
    print(missingPositiveIntegers([1, 2, 0]))
    print(missingPositiveIntegers([4, 1, 2, 2, 2, 1, 0]))

if __name__ == '__main__':
    main()