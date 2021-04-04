from typing import List

from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from app.configuration.database_configuration import db_target
from app.domain.entities.ClientsEntity import ClientsEntity
from app.domain.repositories.clients.ClientsRepositoryInterface import ClientsRepositoryInterface
from app.factories.ClientsFactory import ClientsFactory
from app.models.ClientsModel import ClientsModel


class ClientsRepository(ClientsRepositoryInterface):

    def findById(self, idclient: int) -> ClientsModel:
        return super().findById(idclient)

    def getAllClients(self) -> List[ClientsModel]:
        models = []
        client_model = ClientsModel()
        factory = ClientsFactory()
        session = self.__create_session()
        entities = session.query(ClientsEntity).all()
        session.close()
        for entity in entities:
            client_model = factory.create_model(entity)
            models.append(client_model)
        return models
        #return super().getAllClients()

    def __create_session(self):
        session_manager = sessionmaker(bind=self.engine)
        session = session_manager()
        return session

    def __init__(self):
        engine = create_engine(
            'mysql+pymysql://'+db_target,
            echo=True
        )
        self.engine = engine
        pass
