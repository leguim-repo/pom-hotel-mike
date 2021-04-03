from typing import List
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from domain.entities.ClientsEntity import ClientsEntity
from domain.models.ClientsModel import ClientsModel
from .ClientsRepositoryInterface import ClientsRepositoryInterface


class ClientsRepository(ClientsRepositoryInterface):

    def findById(self, idclient: int) -> ClientsModel:
        return super().findById(idclient)

    def getAllClients(self) -> List:
        session = self.__create_session()
        rs = session.query(ClientsEntity).all()
        session.close()
        # conversion entity a modelo pendiente
        return rs
        #return super().getAllClients()

    def __create_session(self):
        session_manager = sessionmaker(bind=self.engine)
        session = session_manager()
        return session

    def __init__(self):
        engine = create_engine(
            'mysql+pymysql://root:secret1234@192.168.0.110/pom_hotel?charset=utf8mb4',
            echo=True
        )
        self.engine = engine
        pass
