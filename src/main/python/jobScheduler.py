'''
This problem was asked by Apple.
Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
'''
from threading import Timer

class Job:
    def __init__(self, name, seconds, f, *args):
        def callbackFunction():
            f(*args)
            print("Executed Job '{}'".format(name))

        self.name = name
        self.timer = Timer(seconds, callbackFunction)


    def trigger(self):
        self.timer.start()

class JobScheduler:
    def trigger(self, j):
        j.trigger()
        print("started waiting for Job '{}'".format(j.name))

if __name__ == '__main__':
    scheduler = JobScheduler()
    scheduler.trigger(Job("j1", 1, print, "j1 is meow"))
    scheduler.trigger(Job("j2", 2, print, "j2 is woof"))