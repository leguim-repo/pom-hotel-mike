import abc


class RandomMusicURLServiceInterface(object, metaclass=abc.ABCMeta):

    @abc.abstractmethod
    def getRandomMusicURL(self) -> str:
        pass

    @abc.abstractmethod
    def getAllMusicURL(self) -> list:
        pass
