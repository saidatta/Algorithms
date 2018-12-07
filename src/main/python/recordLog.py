class Log:
    def __init__(self):
        self.logs = []

    def record(self, record_id):
        self.logs.append(record_id)

    def get_last(self, index):
        if (index >= len(self.logs)):
            raise ValueError("Index too high")

        return self.logs[len(self.logs) - 1 - index]

def main():
    logger = Log()

    for i in range(1000):
        logger.record(i)

    for i in range(20):
        print(logger.get_last(i))

if __name__ == '__main__':
    main()