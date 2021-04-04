from sqlalchemy import Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()


class ClientsEntity(Base):
    __tablename__ = 'clients'

    id = Column(Integer, primary_key=True, nullable=False)
    name = Column(String, nullable=True)
    lastname = Column(String, nullable=True)
    email = Column(String, nullable=True)

    def __init__(self, id, name, lastname, email):
        self.id = id
        self.name = name
        self.lastname = lastname
        self.email = email

    @property
    def id_field(self) -> int:
        return self.id

    @id_field.setter
    def id_field(self, value):
        self.id = value

    @property
    def name_field(self) -> str:
        return self.name

    @name_field.setter
    def name_field(self, value):
        self.name = value

    @property
    def lastname_field(self) -> str:
        return self.lastname

    @lastname_field.setter
    def lastname_field(self, value):
        self.lastname = value

    @property
    def email_field(self) -> str:
        return self.email

    @email_field.setter
    def email_field(self, value):
        self.email = value

    def __repr__(self):
        return f'ClientsEntity({self.name}, {self.lastname}, {self.email})'

    def __str__(self):
        return f'ClientsEntity({self.id}, {self.name}, {self.lastname}, {self.email})'

    def to_json(self):
        return dict(id=self.id,
                    name=self.name,
                    lastname=self.lastname,
                    email=self.email)
