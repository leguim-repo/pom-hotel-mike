class ClientsModel:

    def __init__(self):
        self._idclient = 0
        self._name = ''
        self._lastname = ''
        self._email = ''

    @property
    def idclient(self) -> int:
        return self._idclient

    @idclient.setter
    def idclient(self, value):
        self._idclient = value

    @property
    def name(self) -> str:
        return self._name

    @name.setter
    def name(self, value):
        self._name = value

    @property
    def lastname(self) -> str:
        return self._lastname

    @lastname.setter
    def lastname(self, value):
        self._lastname = value

    @property
    def email(self) -> str:
        return self._email

    @email.setter
    def email(self, value):
        self._email = value

    def __repr__(self):
        return f'ClientsModel({self.name}, {self.lastname}, {self.email})'

    def __str__(self):
        return f'ClientsModel({self.idclient}, {self.name}, {self.lastname}, {self.email})'

    def to_json(self):  # deprecated -> to_dict better way for transform to json
        return dict(id=self.idclient,
                    name=self.name,
                    lastname=self.lastname,
                    email=self.email)

    def to_dict(self):
        return {"id": self.idclient,
                "name": self.name,
                "lastname": self.lastname,
                "email": self.email}
