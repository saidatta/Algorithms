class OrdersAudit(object):
    def __init__(self, N):
        self.circular_buffer = [None] * N
        self.index_ptr = 0

    def record(self, order_id):
        self.circular_buffer[self.index_ptr] = order_id
        self.index_ptr += 1
        if self.index_ptr == len(self.circular_buffer):
            self.index_ptr = 0


    def get_last(self, index):
        start_index = self.index_ptr - index
        if start_index < 0:  # wrap around
            return self.circular_buffer[start_index:] + self.circular_buffer[:self.index_ptr]
        else:  # no wrapping required
            return self.circular_buffer[start_index:self.index_ptr]


def main():
    logger = OrdersAudit(10)

    for i in range(9):
        logger.record(i)

    # for i in range(10):
    print(logger.get_last(10))

if __name__ == '__main__':
    main()