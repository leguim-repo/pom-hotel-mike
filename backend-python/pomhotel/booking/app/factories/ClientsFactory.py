from app.domain.entities.ClientsEntity import ClientsEntity
from app.models.ClientsModel import ClientsModel


class ClientsFactory:
    def __init__(self):
        pass

    @staticmethod
    def create_entity(model: ClientsModel) -> ClientsEntity:
        entity = ClientsEntity
        entity.id_field = model.idclient
        entity.name_field = model.name
        entity.lastname_field = model.lastname
        entity.email_field = model.email
        return entity

    @staticmethod
    def create_model(entity: ClientsEntity) -> ClientsModel:
        model = ClientsModel()
        model.idclient = entity.id
        model.name = entity.name
        model.lastname = entity.lastname
        model.email = entity.email
        return model
