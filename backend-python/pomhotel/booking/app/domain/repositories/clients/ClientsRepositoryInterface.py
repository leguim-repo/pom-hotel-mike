from typing import List

from app.models.ClientsModel import ClientsModel


class ClientsRepositoryInterface:
    def findById(self, idclient: int) -> ClientsModel:
        pass

    def getAllClients(self) -> List:
        pass
