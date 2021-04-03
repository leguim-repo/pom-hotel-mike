from ..entities.ClientsEntity import ClientsEntity
from ..models.ClientsModel import ClientsModel


class ClientsFactory:
    def __init__(self):
        pass

    def create_entity(self, model: ClientsModel) -> ClientsEntity:
        pass

    def create_model(self, entity: ClientsEntity) -> ClientsModel:
        pass
