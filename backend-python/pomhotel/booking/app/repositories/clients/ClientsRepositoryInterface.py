import abc
from typing import List

from app.models.ClientsModel import ClientsModel

# https://stackoverflow.com/questions/372042/difference-between-abstract-class-and-interface-in-python


class ClientsRepositoryInterface(object, metaclass=abc.ABCMeta):

    @abc.abstractmethod
    def findById(self, idclient: int) -> ClientsModel:
        pass

    @abc.abstractmethod
    def getAllClients(self) -> List:
        pass