from typing import List

from app.domain.repositories.clients.ClientsRepository import ClientsRepository


class ClientsService:
    def __init__(self):
        pass

    @staticmethod
    def get_all_clients() -> List:
        clients_repo = ClientsRepository()
        clients = clients_repo.getAllClients()
        clients_list = []
        for row in clients:
            clients_list.append(row)
        return clients_list

