class ClientsModel:
    def __init__(self, idclient, name, lastname, email):
        self._idclient = idclient
        self._name = name
        self._lastname = lastname
        self._email = email

    @property
    def idclient(self) -> str:
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
        return f'Call({self._name}, {self._lastname}, {self._email})'

    def __str__(self):
        return f'Call({self._idclient}, {self._name}, {self._lastname}, {self._email})'

    def to_json(self):
        return dict(id=self._idclient,
                    name=self._name,
                    lastname=self._lastname,
                    email=self._email)



