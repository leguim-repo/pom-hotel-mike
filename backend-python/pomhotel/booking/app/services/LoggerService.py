import logging


class LoggerService(logging.Logger):
    def __init__(self, logger_name: str):
        super().__init__(logger_name)
        logging.basicConfig(level=logging.NOTSET,
                            format='%(levelname)s %(asctime)s %(message)s')

        self._logger = logging.getLogger(logger_name)

    def get_logger(self) -> logging.Logger:
        return self._logger
