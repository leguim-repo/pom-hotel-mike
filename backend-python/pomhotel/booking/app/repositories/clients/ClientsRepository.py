from typing import List

from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from app.configuration.database_configuration import db_target
from app.domain.entities.ClientsEntity import ClientsEntity
from app.domain.factories.ClientsFactory import ClientsFactory
from app.models.ClientsModel import ClientsModel
from app.repositories.clients.ClientsRepositoryInterface import ClientsRepositoryInterface


class ClientsRepository(ClientsRepositoryInterface):

    def findById(self, idclient: int) -> ClientsModel:
        return super().findById(idclient)

    def getAllClients(self) -> List[ClientsModel]:
        models = []
        factory = ClientsFactory()
        session = self.__create_session()
        entities = session.query(ClientsEntity).all()
        session.close()
        for entity in entities:
            client_model = factory.create_model(entity)
            models.append(client_model)
        return models

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
