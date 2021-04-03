from typing import List
from domain.models.ClientsModel import ClientsModel


class ClientsRepositoryInterface:
    def findById(self, idclient: int) -> ClientsModel:
        pass

    def getAllClients(self) -> List:
        pass
