from app.services.LoggerService import LoggerService

mylogger = LoggerService('__name__').get_logger()
mylogger.info('testing Logger Service')
mylogger.debug('testing Logger Service')
